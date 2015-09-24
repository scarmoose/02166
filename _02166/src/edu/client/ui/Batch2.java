package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IASEServiceAsync;
import edu.client.service.IBatchServiceAsync;

public class Batch2 extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private DeltaBar dbar;
	private Callback c;
	private Label error;
	private Button start;
	private Button stop;
	private boolean running = true;

	public interface Callback {
		public IASEServiceAsync getASEService();
		public IBatchServiceAsync getBatchService();
		public void openBatchWeightView() throws Exception;
		public void setRecursiveRunning(boolean running);
		public boolean isRecursiveRunning();
	}

	public Batch2(final Callback c) {
		initWidget(vPanel);
		dbar = new DeltaBar();
		error = new Label("Hej jeg er ikke en fejl endnu");
		start = new Button("Start");
		stop = new Button("Stop");
		vPanel.add(dbar);
		vPanel.add(start);
		vPanel.add(stop);
		vPanel.add(error);
		this.c = c;
		dbar.boundarySetup(3, (1.0/3));

		start.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				startIndicator();
			}

		});
		
		stop.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				stop();
			}
			
		});
		


	}
	
	public void stop() {
		running = false;
	}

	public void startIndicator() {
		running = true;
		c.getASEService().getSIWeight(new AsyncCallback<Double>() {

			@Override
			public void onFailure(Throwable caught) {
				error.setText("Der skete en fejl!");
				if (running) {
					startIndicator();
				}
			}

			@Override
			public void onSuccess(Double result) {
				dbar.setIndicator(result);
				if (running) {
					startIndicator();
				}
			}

		});
	}
}

