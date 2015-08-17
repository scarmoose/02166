package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuView extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();


	public MenuView(final MainView main){
		initWidget(this.vPanel);
		this.vPanel.setBorderWidth(2);

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
		
		vPanel.add(weightBtn);
		vPanel.add(unitWBtn);
		vPanel.add(deltaWBtn);
	
		vPanel.setCellHeight(weightBtn, "60px");
		vPanel.setCellHeight(unitWBtn, "60px");
		vPanel.setCellHeight(deltaWBtn, "60px");
		
		vPanel.setCellWidth(weightBtn, "120px");
		vPanel.setCellWidth(unitWBtn, "120px");
		vPanel.setCellWidth(deltaWBtn, "120px");

		
	}
}



