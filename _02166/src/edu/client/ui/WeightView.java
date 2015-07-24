package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IASEServiceAsync;

public class WeightView extends Composite {


	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	private HorizontalPanel hPanel2 = new HorizontalPanel();
	private Label weightLabel = new Label("Netto"); 

	public interface Callback{
		public IASEServiceAsync getASEService();
	}

	public WeightView(final Callback c) {

		//-------------------------------------------
		//initialiserer panel, tilføjer paneler og labels
		//-------------------------------------------

		initWidget(this.vPanel);
		weightLabel.addStyleName("weightLabel");
		vPanel.add(weightLabel);
		vPanel.add(hPanel2);
		vPanel.add(hPanel1);
		

		//-------------------------------------------
		//Weight-button, get
		//-------------------------------------------

		Button getWeightButton = new Button("Get weight", new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				try {
					c.getASEService().getSWeight(new AsyncCallback<Double>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("An error occured: " + caught.getMessage());
						}

						@Override
						public void onSuccess(Double result) {
							weightLabel.setText("Netto: " + result + " kg");
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		hPanel1.add(getWeightButton);
		getWeightButton.setPixelSize( 105, 30);
		hPanel1.setBorderWidth(3);
		

		//-------------------------------------------		
		//Weight-button, tara
		//-------------------------------------------

		Button taraWeightButton = new Button("Tara", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event){
				try {
					c.getASEService().tara(new AsyncCallback<Void>(){
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("An error occured: " + caught.getMessage());
						}
						@Override
						public void onSuccess(Void result) {
							c.getASEService().getSWeight(new AsyncCallback<Double>(){

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("An error occured: " + caught.getMessage());
								}

								@Override
								public void onSuccess(Double result) {
									weightLabel.setText("Netto: " + result + " kg");
								}
							});

						}
					}
							); 
				}	catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		hPanel1.add(taraWeightButton);
		taraWeightButton.setPixelSize(105, 30);
		hPanel1.setBorderWidth(3);

	}
}
