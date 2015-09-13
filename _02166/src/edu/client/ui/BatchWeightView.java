package edu.client.ui;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.client.service.IASEServiceAsync;
import edu.client.service.IBatchServiceAsync;
import edu.shared.BatchDTO;

public class BatchWeightView extends Composite implements Runnable {
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel vPanel2 = new VerticalPanel();
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	private HorizontalPanel hPanel2 = new HorizontalPanel();

	private FlexTable ft = new FlexTable();
	private FlexTable ft2 = new FlexTable();
	private Label prdName = new Label("Product Name");
	private Label batchID = new Label("BatchID");
	private Label wData = new Label("Batch weight");
	private Label SIData = new Label("SI - WeightData");
	private Label errorLabel1 = new Label();

	private TextBox productName = new TextBox(); 
	private TextBox batchIDBox = new TextBox();
	private TextBox batchData = new TextBox();
	private TextBox SIDataBox = new TextBox();

	private List<BatchDTO> batchList;
	private DeltaBar dbar = new DeltaBar();
	public interface Callback{
		public IASEServiceAsync getASEService();
		public IBatchServiceAsync getBatchService();
		public void openBatchWeightView() throws Exception;
		public void setRecursiveRunning(boolean running);
		public boolean isRecursiveRunning();
	}

	public BatchWeightView(final Callback c) throws Exception {
		initWidget(vPanel);
		
		vPanel.setWidth("100%");
	    vPanel.setHeight("100%");
	    vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    
	    vPanel.add(dbar);
	    dbar.addStyleName("dbar");
	    dbar.setHeight("60px");
	   
	    vPanel.add(vPanel2);
		vPanel2.add(hPanel1);
		vPanel2.add(hPanel2);
//		vPanel2.setBorderWidth(2);
		hPanel1.setHeight("52px");
		
		vPanel.add(ft2);
		ft2.setWidth("550");
		vPanel.setStyleName("DVP2");

		hPanel1.add(ft);
		ft.setWidth("474px");
		ft.setWidget(1, 0, prdName);
		ft.setWidget(1, 1, batchID);		
		ft.setWidget(1, 2, wData);
		ft.setWidget(1, 3, SIData);
		productName.setEnabled(false);
		ft.setWidget(2, 0, productName);
		productName.setWidth("");
		batchIDBox.setEnabled(false);
		ft.setWidget(2, 1, batchIDBox);
		batchData.setEnabled(false);
		ft.setWidget(2, 2, batchData);
		SIDataBox.setEnabled(false);
		ft.setWidget(2, 3, SIDataBox);
		SIDataBox.setWidth("128px");

		ft2.setStyleName("H2");
		batchCellView(c);
	}


	private void batchCellView(final Callback c) {
		c.getBatchService().getBatchList(new AsyncCallback<List<BatchDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				hPanel2.clear();
				errorLabel1.setText("Failed to access batchlist");
				hPanel2.add(errorLabel1);
			}
			@Override
			public void onSuccess(List<BatchDTO> result) {
				hPanel2.clear();
				batchList = result;
				CellTable<BatchDTO> batchTable = new CellTable<BatchDTO>();
				batchTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

				// Add a text column to show the name.
				TextColumn<BatchDTO> IDColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Integer.toString(object.getBatch_id());
					}
				};
				batchTable.addColumn(IDColumn, "Batch ID");

				// Add a text column to show the name.
				TextColumn<BatchDTO> raaNavnColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return object.getRaavare_navn();
					}
				};
				batchTable.addColumn(raaNavnColumn, "Raavare");

				// Add a text column to show the name.
				TextColumn<BatchDTO> raavareIDColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Integer.toString(object.getRaavare_id());
					}
				};
				batchTable.addColumn(raavareIDColumn, "Raavare ID");

				// Add a text column to show the name.
				TextColumn<BatchDTO> baWghtColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Double.toString(object.getBatchweight());
					}
				};
				batchTable.addColumn(baWghtColumn, "Batch Weight");
				batchTable.setStyleName("H2");
				// Add a text column to show the name.
				TextColumn<BatchDTO> toleranceColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Double.toString(object.getTolerance());
					}
				};
				batchTable.addColumn(toleranceColumn, "Tolerance");
				final SingleSelectionModel<BatchDTO> selectionModel = new SingleSelectionModel<BatchDTO>();
				batchTable.setSelectionModel(selectionModel);
				
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					
					public void onSelectionChange(SelectionChangeEvent event) {
						c.setRecursiveRunning(false);
						BatchDTO selected = selectionModel.getSelectedObject();
						productName.setText(selected.getRaavare_navn());
						batchIDBox.setText(""+selected.getBatch_id());
						batchData.setText("" + selected.getBatchweight());
						dbar.boundarySetup(selected.getBatchweight(), selected.getTolerance());
						c.setRecursiveRunning(true);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						refreshIndicator(c);
					}
				});

				batchTable.setRowCount(batchList.size(), true);
				// Push the data into the widget.
				batchTable.setRowData(0, batchList);
				batchTable.redraw();
				ft2.setWidget(0,0, batchTable);
			}
		});
	}

	private void refreshIndicator(final Callback c) {
		c.getASEService().getSIWeight(new AsyncCallback<Double>() {

			@Override
			public void onFailure(Throwable caught) {
				hPanel2.clear();
				if(caught.getMessage().equals("Weight Overload")) {
					SIDataBox.setText("N/A");
					if(c.isRecursiveRunning()){
						refreshIndicator(c);	
					}
				}else{
					errorLabel1.setText("Error accesing weight");
					hPanel2.add(errorLabel1);	
					if(c.isRecursiveRunning()){
						refreshIndicator(c);	
					}
				}
			}

			@Override
			public void onSuccess(Double result) {
				hPanel2.clear();
				SIDataBox.setText(Double.toString(result));
				dbar.setIndicator(result);
				if(c.isRecursiveRunning()){
					refreshIndicator(c);	
				}
			}	
		});
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
