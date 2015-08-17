package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	private HorizontalPanel hPanel3 = new HorizontalPanel();
	private HorizontalPanel hPanel4 = new HorizontalPanel();
	private Label weightLabel = new Label("Netto");
	private Label errorLabel1 = new Label();
	private Label errorLabel2 = new Label();
	private Label lblOne = new Label("Init Units");
	private Label lblTwo = new Label("Total Units");
	private TextBox initBox = new TextBox();
	private TextBox unitText = new TextBox();
	private double unitW;

	public interface Callback{
		public IASEServiceAsync getASEService();
	}

	public UnitWeightView(final Callback c) {

		/**
		 * Initializing and adding panels, adding labels 
		 */

		initWidget(this.vPanel);
		weightLabel.addStyleName("weightLabel");
		vPanel.add(weightLabel);
		vPanel.add(hPanel1);vPanel.add(hPanel2);vPanel.add(hPanel3);vPanel.add(hPanel4);
		unitText.setEnabled(false);
		
		
		hPanel1.add(lblOne);
		lblOne.setPixelSize(50, 40);
		hPanel1.add(initBox);
		initBox.setPixelSize(105, 30);
		hPanel1.setBorderWidth(3);

		/**
		 * Creation of "Get units" and "Kalibrer"-button
		 */
		
		final Button UnitButton = new Button("Get units", new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				try {
					c.getASEService().getSWeight(new AsyncCallback<Double>(){

						@Override
						public void onFailure(Throwable caught) {
							hPanel3.clear();hPanel4.clear();
							errorLabel1.setText("Der er fejl på vægten.");
							errorLabel2.setText("Tjek eventuelt om maksbelastningen er overskredet");
							hPanel3.add(errorLabel1);
							hPanel4.add(errorLabel2);
						}

						@Override
						public void onSuccess(Double result) {
						double units = Math.round(result/unitW);
						if(units < 0){
							hPanel3.clear();hPanel4.clear();
							errorLabel1.setText("Der er mindre på vægten end ved kalibreringen");
							hPanel3.add(errorLabel1);
							
						}
						else{
							weightLabel.setText("Netto: " + result + " kg");
							unitText.setText(""+units);
						}	
							
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Button kalibButton = new Button("Kalibrer", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event){
				try {
					c.getASEService().getSWeight(new AsyncCallback<Double>(){

						@Override
						public void onFailure(Throwable caught) {
							hPanel3.clear();hPanel4.clear();
							errorLabel1.setText("Der er fejl på vægten.");
							errorLabel2.setText("Tjek eventuelt om maksbelastningen er overskredet");
							hPanel3.add(errorLabel1);
							hPanel4.add(errorLabel2);
							
						}

						@Override
						public void onSuccess(Double result) {
							double antal;
							try {
								antal = Integer.parseInt(initBox.getText());
								if(antal<1 || result == 0){
									hPanel3.clear();hPanel4.clear();
									errorLabel1.setText("Ved kalibrering skal der være mere end 1 af produktet");
									hPanel3.add(errorLabel1);
									
								}
								else{
									unitW = result/antal;
									weightLabel.setText("Netto: " + result + " kg");
									UnitButton.setEnabled(true);
									hPanel3.clear();hPanel4.clear();
								}
							} catch (NumberFormatException e) {
								hPanel3.clear();hPanel4.clear();
								errorLabel1.setText("Antallet skal være i heltal");
								hPanel3.add(errorLabel1);
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		
		hPanel1.setStyleName("UHP1");
		hPanel2.setStyleName("UHP2");
		hPanel3.setStyleName("UHP3");
		hPanel4.setStyleName("UHP4");

		hPanel1.add(kalibButton);
		kalibButton.setPixelSize(105, 40);
		hPanel1.setBorderWidth(3);
		hPanel2.add(lblTwo);
		lblTwo.setPixelSize(50, 40);
		hPanel2.add(unitText);
		unitText.setPixelSize(105, 30);
		hPanel2.add(UnitButton);
		UnitButton.setEnabled(false);
		UnitButton.setPixelSize(105, 40);
		hPanel2.setBorderWidth(3);
	}
}
