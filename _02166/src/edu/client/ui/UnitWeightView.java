package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IASEServiceAsync;

public class UnitWeightView extends Composite {


	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	private HorizontalPanel hPanel2 = new HorizontalPanel();
	private Label weightLabel = new Label("Netto");
	private Label lblOne = new Label("Antal");
	private TextBox antalBox = new TextBox();
	private double unitW;

	public interface Callback{
		public IASEServiceAsync getASEService();
	}

	public UnitWeightView(final Callback c) {

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

/*		Button getWeightButton = new Button("Get weight", new ClickHandler(){
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
		hPanel1.setBorderWidth(3);*/
		hPanel1.add(lblOne);
		lblOne.setPixelSize(50, 40);
		hPanel1.add(antalBox);
		antalBox.setPixelSize(105, 30);
		hPanel1.setBorderWidth(3);

		//-------------------------------------------		
		//Weight-button, Kalibrer
		//-------------------------------------------

		Button kalibButton = new Button("Kalibrer", new ClickHandler() {
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
							double antal = Double.parseDouble(antalBox.getText());
							unitW = result/antal;
							weightLabel.setText("Netto: " + result + " kg");
							hPanel1.clear();
							Button UnitButton = new Button("Get units", new ClickHandler(){
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
												double units = Math.round(result/unitW);
												weightLabel.setText("Netto: " + result + " kg  -  "+"Units: " + units);
											}
										});
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							hPanel1.add(UnitButton);
							UnitButton.setPixelSize(105, 40);
							hPanel1.setBorderWidth(3);
							
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		hPanel1.add(kalibButton);
		kalibButton.setPixelSize(105, 40);
		hPanel1.setBorderWidth(3);

	}
}
