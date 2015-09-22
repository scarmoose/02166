package edu.client.ui;

import java.util.List;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
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

public class BatchWeight2 extends Composite {

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

	public BatchWeight2(final Callback c) throws Exception {
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
		//			vPanel2.setBorderWidth(2);
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
	}

}
