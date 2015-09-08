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
import edu.client.service.IMetaService;
import edu.client.service.IMetaServiceAsync;

public class MainView extends Composite implements WeightView.Callback,
		UnitWeightView.Callback, BatchWeightView.Callback {

	private VerticalPanel content = new VerticalPanel();
	private MenuView menu;
	private final IASEServiceAsync ASEservice = GWT.create(IASEService.class);
	private final IMetaServiceAsync Metaservice = GWT.create(IMetaService.class);
	private final IBatchServiceAsync BatchService = GWT.create(IBatchService.class);
	private AbsolutePanel aPanel = new AbsolutePanel();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label errorLabel1 = new Label();
	
	public MainView() throws Exception {
		initWidget(aPanel);
		vPanel.add(hPanel);
		ASEservice.connect(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				hPanel.clear();
				errorLabel1.setText("Der kunne ikke skabes forbindelse til v�gten.");
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
		content.clear();
		UnitWeightView coin = new UnitWeightView(this);
		content.add(coin);
		aPanel.add(content);
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-130, Window.getClientHeight()/6);
	}

	
	public void openWeightView() throws Exception {
		content.clear();
		WeightView weight = new WeightView(this);
		content.add(weight);
		aPanel.add(content);
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-110, Window.getClientHeight()/6);

	}


	public void openBatchWeightView() throws Exception {
		content.clear();
		BatchWeightView bWView = new BatchWeightView(this);
		content.add(bWView);
		aPanel.add(content);
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-280, Window.getClientHeight()/16);
	}


	public IASEServiceAsync getASEService() {
		return ASEservice;
	}

	public IBatchServiceAsync getBatchService() {
		return BatchService;
	}

}
