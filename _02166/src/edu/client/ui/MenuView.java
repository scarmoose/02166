package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MenuView extends Composite {
	private HorizontalPanel hPanel = new HorizontalPanel();


	public MenuView(final MainView main){
		initWidget(this.hPanel);
		this.hPanel.setBorderWidth(2);

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

		Button deltaWBtn = new Button("DELTA", new ClickHandler(){
			@Override
			public void onClick(ClickEvent even){
				try{
					main.openDeltaWeightView();
				} catch (Exception e) {
					e.printStackTrace();
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
	
		hPanel.setCellHeight(weightBtn, "60px");
		hPanel.setCellHeight(unitWBtn, "60px");
		hPanel.setCellHeight(deltaWBtn, "60px");
	
		hPanel.setCellWidth(weightBtn, "120px");
		hPanel.setCellWidth(unitWBtn, "120px");
		hPanel.setCellWidth(deltaWBtn, "120px");

		
	}
}



