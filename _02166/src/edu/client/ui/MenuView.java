package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuView extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();


	public MenuView(final MainView main){
		initWidget(this.vPanel);
		this.vPanel.setBorderWidth(1);

		
		//-----------------------------------------------------
		//list-button, opens ListView when pressed
		//-----------------------------------------------------

		Button listBtn = new Button("LIST", new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				try {
					main.openListView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});



		//-----------------------------------------------------
		//weight-button, opens WeightView when pressed
		//-----------------------------------------------------

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

		//-----------------------------------------------------
		//unit-weight-button, opens StykWeight when pressed
		//-----------------------------------------------------

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


		//-----------------------------------------------------
		//delta-weight-button, opens DeltaWeightView when pressed
		//-----------------------------------------------------

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
		
		//-----------------------------------------------------
		//adding buttons to vertical panel, setting pixel size
		//-----------------------------------------------------

		weightBtn.setPixelSize( 100, 30);
		unitWBtn.setPixelSize( 100, 30);
		deltaWBtn.setPixelSize( 100, 30);
		listBtn.setPixelSize(100, 30);

		
		vPanel.add(weightBtn);
		vPanel.add(unitWBtn);
		vPanel.add(deltaWBtn);
		vPanel.add(listBtn);

		
	}
	
//	vPanel.add(logoutBtn);
//	logoutBtn.setPixelSize(100, 30);
	
//	//-----------------------------------------------------
//	//logout-button, reopens LoginView when pressed
//	//-----------------------------------------------------
//
//	Button logoutBtn = new Button("LOGOUT", new ClickHandler() {
//		@Override
//		public void onClick(ClickEvent event) {
//			main.openLoginView();
//		}
//	});
	
}



