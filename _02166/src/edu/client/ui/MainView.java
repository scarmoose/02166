package edu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IASEService;
import edu.client.service.IASEServiceAsync;
import edu.client.service.IBatchService;
import edu.client.service.IBatchServiceAsync;

public class MainView extends Composite implements WeightView.Callback,
		UnitWeightView.Callback, BatchWeightView.Callback, Batch2.Callback {

	private VerticalPanel content = new VerticalPanel();
	private MenuView menu;
	private final IASEServiceAsync ASEservice = GWT.create(IASEService.class);
	private final IBatchServiceAsync BatchService = GWT.create(IBatchService.class);
	private AbsolutePanel aPanel = new AbsolutePanel();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label errorLabel1 = new Label();
	private boolean recursiveRunning = true;
	
	public MainView() throws Exception {
		initWidget(aPanel);
		vPanel.add(hPanel);
		
		ASEservice.reconnect(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				hPanel.clear();
				errorLabel1.setText("A connection to the weight could not be established.");
				hPanel.add(errorLabel1);
				System.out.println("No connection established :(");
			}

			@Override
			public void onSuccess(Void result) {
				hPanel.clear();
				System.out.println("Connection established!");
			}
		});

		aPanel.setSize(Integer.toString(Window.getClientWidth()) + "px",
				Integer.toString(Window.getClientHeight()) + "px");
		aPanel.clear();
		aPanel.add(vPanel);
		menu = new MenuView(this);
		vPanel.add(menu);
		aPanel.setWidgetPosition(vPanel, Window.getClientWidth()/2-185, 5);

	}

	/**
	 * Methods for opening the different views
	 * @throws Exception
	 */
	public void openStykWeight() throws Exception {
		setRecursiveRunning(false);
		content.clear();
		UnitWeightView coin = new UnitWeightView(this);
		content.add(coin);
		aPanel.add(content);
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-130, Window.getClientHeight()/6);
	}

	
	public void openWeightView() throws Exception {
		setRecursiveRunning(false);
		content.clear();
		WeightView weight = new WeightView(this);
		content.add(weight);
		aPanel.add(content);
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-110, Window.getClientHeight()/6);

	}


	public void openBatchWeightView() throws Exception {
		content.clear();
//		BatchWeightView b = new BatchWeightView(this);
		Batch2 b = new Batch2(this);
		content.add(b);
		aPanel.add(content);
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-280, Window.getClientHeight()/16+50);
	}


	public IASEServiceAsync getASEService() {
		return ASEservice;
	}

	public IBatchServiceAsync getBatchService() {
		return BatchService;
	}

	public boolean isRecursiveRunning() {
		return recursiveRunning;
	}

	public void setRecursiveRunning(boolean recursiveRunning) {
		this.recursiveRunning = recursiveRunning;
	}
}
