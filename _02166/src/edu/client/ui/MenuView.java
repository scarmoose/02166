package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

import edu.client.service.IASEServiceAsync;

public class MenuView extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();

	public MenuView(final MainView main){
		initWidget(this.hPanel);
		this.hPanel.setBorderWidth(2);
		hPanel.setWidth("100%");
	    hPanel.setHeight("100%");
	    hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	    
	    
		/**
		 * Button-functionality:
		 * weightBtn - opening WeightView
		 * unitBtn - opening UnitWeightView
		 * deltaBtn - opening DeltaWeightView
		 */
		
		Button weightBtn = new Button("WEIGHT", new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				try {
					main.openWeightView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Button unitWBtn = new Button("UNIT", new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {

				try {
					main.openStykWeight();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Button deltaWBtn = new Button("BATCH", new ClickHandler(){
			@Override
			public void onClick(ClickEvent even){
				try{
					main.openBatchWeightView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Button disconnectBtn = new Button("RECONNECT", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {
					main.getASEService().reconnect(new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Failure");
							
						}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Success");
							
						}
						
					});
				} catch(Exception e) {
					Window.alert("LOL");
				}
			}
		});
		
		/**
		 * Adding buttons to the menu-panel 
		 * Setting pixel-sizes for each button 
		 */
		
		hPanel.add(weightBtn);
		hPanel.add(unitWBtn);
		hPanel.add(deltaWBtn);
		hPanel.add(disconnectBtn);
	
		hPanel.setCellHeight(weightBtn, "60px");
		hPanel.setCellHeight(unitWBtn, "60px");
		hPanel.setCellHeight(deltaWBtn, "60px");
		hPanel.setCellHeight(disconnectBtn, "60px");
	
		hPanel.setCellWidth(weightBtn, "120px");
		hPanel.setCellWidth(unitWBtn, "120px");
		hPanel.setCellWidth(deltaWBtn, "120px");
		hPanel.setCellWidth(disconnectBtn, "120px");

		
	}
}



