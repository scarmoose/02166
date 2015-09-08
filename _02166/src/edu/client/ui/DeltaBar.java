package edu.client.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class DeltaBar extends Composite {
	
	private double weightIndicator = 201.0;
	private double lowerWeightBound = 191.0;
	private double upperWeightBound = 211.0;
	private double a_w;
	private double b_w;
	//h�jden angivet i pixels
	private static final double HEIGHT = 37;
	private static final double TOTALPIXELS = 536;
	private double pxPrUnit;

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
		layoutPanel.setSize(TOTALPIXELS+"px", HEIGHT+"px");

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
	 * sets the indicator position
	 * @param w_input input weight
	 */
	
	public void setIndicator(double w_input) {
		//hvis vægten er under barens nedre grænse, står viseren blot på grænsen.
		if(w_input < a_w) weightIndicator = 0.0;
		
		//det samme hvis den er over den være grænse
		else if(w_input > b_w) weightIndicator = TOTALPIXELS;
		
		//ellers vises den aktuelle belastning
		else weightIndicator = (w_input - a_w) * pxPrUnit;
		
		layoutPanel.setWidgetLeftWidth(layoutPanel_1, weightIndicator, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_1, 0.0, Unit.PX, HEIGHT, Unit.PX);
	}
	
	/**
	 * boundary setup for the delta bar. 
	 * calculates pixels pr kg 
	 * @param w_batch
	 * @param tolerance
	 */
	
	public void boundarySetup(double w_batch, double tolerance) {
		
		double lower_w;
		double upper_w;
		
		a_w = w_batch-(3*w_batch*tolerance);
		b_w = w_batch+(3*w_batch*tolerance);
		
		pxPrUnit = TOTALPIXELS/(b_w-a_w);
		
		lower_w = w_batch-(w_batch*tolerance);
		upper_w = w_batch+(w_batch*tolerance);
		
		lowerWeightBound = (lower_w - a_w)*pxPrUnit;
		upperWeightBound = (upper_w - a_w)*pxPrUnit;
		
		BigDecimal bd;
		//gr�nsev�rdierne rundes af til 2 decimaler
		bd = new BigDecimal(a_w).setScale(2, RoundingMode.HALF_UP);
		a_w = bd.doubleValue();
		bd = new BigDecimal(b_w).setScale(2, RoundingMode.HALF_UP);
		b_w = bd.doubleValue();
		
		l1.setText(Double.toString(a_w));
		l2.setText(Double.toString(b_w));
		
		layoutPanel.setWidgetLeftWidth(layoutPanel_2, lowerWeightBound, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_2, 0.0, Unit.PX, HEIGHT, Unit.PX);
		layoutPanel.setWidgetLeftWidth(layoutPanel_3, upperWeightBound, Unit.PX, 2.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(layoutPanel_3, 0.0, Unit.PX, HEIGHT, Unit.PX);
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
