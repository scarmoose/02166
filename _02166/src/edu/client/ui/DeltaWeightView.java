package edu.client.ui;

import java.util.List;

import edu.client.service.IASEServiceAsync;
import edu.client.service.IBatchServiceAsync;
import edu.shared.BatchDTO;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class DeltaWeightView extends Composite{
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel vPanel2 = new VerticalPanel();
	private HorizontalPanel hPanel1 = new HorizontalPanel();

	private FlexTable ft = new FlexTable();
	private FlexTable ft2 = new FlexTable();
	private Label viewInfo = new Label("Delta-weight");
	private Label prdName = new Label("Product Name");
	private Label batchID = new Label("BatchID");
	private Label wData = new Label("Batch weight");
	private Label SIData = new Label("SI - WeightData");

	private TextBox productName = new TextBox(); 
	private TextBox batchIDBox = new TextBox();
	private TextBox batchData = new TextBox();
	private TextBox SIDataBox = new TextBox();

	private List<BatchDTO> batchList;
	private DeltaBar dbar = new DeltaBar();


	public interface Callback{
		public IASEServiceAsync getASEService();
		public IBatchServiceAsync getBatchService();
	}

	public DeltaWeightView(final Callback c) throws Exception {
		initWidget(vPanel);

		vPanel.setHeight("328px");
		viewInfo.addStyleName("deltaWeight");
		vPanel.add(viewInfo);
		viewInfo.setHeight("92px");

		vPanel.add(dbar);
		vPanel.add(vPanel2);
		dbar.setHeight("71px");
		vPanel2.add(hPanel1);
		vPanel2.setBorderWidth(2);
		hPanel1.setHeight("52px");
		vPanel2.add(ft2);
		ft2.setWidth("550");

		hPanel1.add(ft);
		ft.setWidth("555px");
		ft.setWidget(1, 0, prdName);
		ft.setWidget(1, 1, batchID);		
		ft.setWidget(1, 2, wData);
		ft.setWidget(1, 3, SIData);
		ft.setWidget(2, 0, productName);
		productName.setWidth("");
		ft.setWidget(2, 1, batchIDBox);
		ft.setWidget(2, 2, batchData);
		ft.setWidget(2, 3, SIDataBox);
		SIDataBox.setWidth("128px");

		ft2.setStyleName("H2");
		batchCellView(c);
	}


	private void batchCellView(final Callback c) {
		c.getBatchService().getBatchList(new AsyncCallback<List<BatchDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to access database: "+caught.getMessage());
			}
			@Override
			public void onSuccess(List<BatchDTO> result) {
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
					/*
					 * Det er vist et problem at getSIData kaldes IGEN onSelectionChange uden at stoppe det gamle rekursive kald.
					 * Se getSIData, der rekursivt kalder sig selv 
					 */
					public void onSelectionChange(SelectionChangeEvent event) {
						BatchDTO selected = selectionModel.getSelectedObject();
						/*
						 * Here we want to display the data in the table operatoer
						 * then we want to make it able to then show both coins and operatoer
						 * 
						 */

						productName.setText(selected.getRaavare_navn());
						batchIDBox.setText(""+selected.getBatch_id());
						batchData.setText("" + selected.getBatchweight());

						getSIData(c, selected.getBatchweight(), selected.getTolerance());
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

	private void getSIData(final Callback c, final double bW, final double tol) {
		c.getASEService().getSIWeight(new AsyncCallback<Double>(){
			@Override
			public void onFailure(Throwable caught) {
				if(caught.getMessage().equals("Weight Overload")) {
					SIDataBox.setText("N/A");
					getSIData(c,bW,tol);
				}else{
					Window.alert("Error accesing weight" + caught.getMessage());
				}
			}

			@Override
			public void onSuccess(Double result) {
				SIDataBox.setText(Double.toString(result));
				dbar.deltaBarData(result, bW, tol);
				getSIData(c,bW,tol);
			}
		});		
	}


}
