package edu.client.ui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
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

	public MainView() throws Exception {
		initWidget(aPanel);
		ASEservice.connect(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der kunne ikke skabes forbindelse til vægten.");
				System.out.println("No connection established :(");
			}

			@Override
			public void onSuccess(Void result) {
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

	//----------------------------------------
	// Methods for opening the different views
	//----------------------------------------
	
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
		aPanel.setWidgetPosition(content, Window.getClientWidth()/2-265, (Window.getClientHeight()/16)+40);

	}


	public IASEServiceAsync getASEService() {
		return ASEservice;
	}

	public IBatchServiceAsync getBatchService() {
		return BatchService;
	}

}
