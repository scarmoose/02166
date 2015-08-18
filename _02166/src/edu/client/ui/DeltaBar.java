package edu.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class DeltaBar extends Composite {

	private double weightIndicator = 201.0;
	private double lowerWeightBound = 191.0;
	private double upperWeightBound = 211.0;
	private static final double TOTALPIXELS = 536;

	private LayoutPanel layoutPanel = new LayoutPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	Label l1 = new Label("0.0");
	Label l2 = new Label("6.0");

	private	LayoutPanel layoutPanel_1 = new LayoutPanel();
	private	LayoutPanel layoutPanel_2 = new LayoutPanel();
	private	LayoutPanel layoutPanel_3 = new LayoutPanel();



	public DeltaBar(){
		initWidget(hPanel);
		hPanel.add(l1);
		hPanel.add(layoutPanel);
		hPanel.add(l2);
		layoutPanel.setStyleName("layoutPanel0");
		layoutPanel.setSize("536px", "37px");

		layoutPanel_1.setStyleName("layoutPanel2");
		layoutPanel.add(layoutPanel_1);
		layoutPanel_2.setStyleName("layoutPanel1");
		layoutPanel.add(layoutPanel_2);


		layoutPanel_3.setStyleName("layoutPanel1");
		layoutPanel.add(layoutPanel_3);


	}
	public double getWeightIndicator() {
		return weightIndicator;
	}
	public void setWeightIndicator(double weigthIndicator) {
		this.weightIndicator = weigthIndicator;
	}
	public double getLowerWeightBound() {
		return lowerWeightBound;
	}
	public void setLowerWeightBound(double lowerWiegthBound) {
		this.lowerWeightBound = lowerWiegthBound;
	}
	public double getUpperWeightBound() {
		return upperWeightBound;
	}
	public void setUpperWeightBound(double upperWiegthBound) {
		this.upperWeightBound = upperWiegthBound;
	}
	
	
	/**
	 * 
	 * @param w_input SI-input
	 * @param w_batch batchvægt
	 * @param tolerance	batchtolerance
	 */

	public void deltaBarData1(double w_batch, double tolerance){

		double lower_w;
		double upper_w;
		
		//gør baren 3 gange så stor som tolerancen
		double a_w = w_batch-(3*w_batch*tolerance);
		double b_w = w_batch+(3*w_batch*tolerance);
		
		double pxPrUnit = TOTALPIXELS/(b_w-a_w);
		
		lower_w = w_batch-(w_batch*tolerance);
		upper_w = w_batch+(w_batch*tolerance);
		
		lowerWeightBound = (lower_w - a_w)*pxPrUnit;
		upperWeightBound = (upper_w - a_w)*pxPrUnit;
		
		//hvis vægten er under barens nedre grænse, står viseren blot på grænsen.
//		if(w_input < a_w) weightIndicator = 0.0;
		//det samme hvis den er over den være grænse
//		else if(w_input > b_w) weightIndicator = TOTALPIXELS;
		//ellers viser den nuværende vægt
//		else weightIndicator = (w_input - a_w) * pxPrUnit;
		
		l1.setText(Double.toString(a_w));
		l2.setText(Double.toString(b_w));
		layoutPanel.setWidgetLeftWidth(layoutPanel_1, weightIndicator, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_1, 0.0, Unit.PX, 37.0, Unit.PX);
		layoutPanel.setWidgetLeftWidth(layoutPanel_2, lowerWeightBound, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_2, 0.0, Unit.PX, 37.0, Unit.PX);
		layoutPanel.setWidgetLeftWidth(layoutPanel_3, upperWeightBound, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_3, 0.0, Unit.PX, 37.0, Unit.PX);

	}

	
	public void deltaBarData(double w_input, double w_batch, double tolerance){

		double lower_w;
		double upper_w;
		
		//gør baren 3 gange så stor som tolerancen
		double a_w = w_batch-(3*w_batch*tolerance);
		double b_w = w_batch+(3*w_batch*tolerance);
		
		double pxPrUnit = TOTALPIXELS/(b_w-a_w);
		
		lower_w = w_batch-(w_batch*tolerance);
		upper_w = w_batch+(w_batch*tolerance);
		
		lowerWeightBound = (lower_w - a_w)*pxPrUnit;
		upperWeightBound = (upper_w - a_w)*pxPrUnit;
		
		//hvis vægten er under barens nedre grænse, står viseren blot på grænsen.
		if(w_input < a_w) weightIndicator = 0.0;
		//det samme hvis den er over den være grænse
		else if(w_input > b_w) weightIndicator = TOTALPIXELS;
		//ellers viser den nuværende vægt
		else weightIndicator = (w_input - a_w) * pxPrUnit;
		
		l1.setText(Double.toString(a_w));
		l2.setText(Double.toString(b_w));
		layoutPanel.setWidgetLeftWidth(layoutPanel_1, weightIndicator, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_1, 0.0, Unit.PX, 37.0, Unit.PX);
		layoutPanel.setWidgetLeftWidth(layoutPanel_2, lowerWeightBound, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_2, 0.0, Unit.PX, 37.0, Unit.PX);
		layoutPanel.setWidgetLeftWidth(layoutPanel_3, upperWeightBound, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_3, 0.0, Unit.PX, 37.0, Unit.PX);

	}
	
	public void setIndicator(double w_input, double leftborder, double pxprkg) {
		weightIndicator = (w_input - leftborder) * pxprkg;
	}
}
