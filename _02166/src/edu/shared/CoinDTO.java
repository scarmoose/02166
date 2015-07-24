package edu.shared;

import java.io.Serializable;

public class CoinDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846969203314216759L;

	private double value;
	private double weightPerUnit;
	private double tolerance;

	public CoinDTO() {

	}

	public CoinDTO(double value, double weightPerUnit, double tolerance) {
		this.value = value;
		this.weightPerUnit = weightPerUnit;
		this.tolerance = tolerance;
	}
	public double getValue() {return value;}
	public void setValue(double value) {this.value = value;}
	public double getWeightPerUnit() {return weightPerUnit;}
	public void setWeightPerUnit(double weightPerUnit) {this.weightPerUnit = weightPerUnit;	}
	public double getTolerance() {return tolerance;}
	public void setTolerance(double tolerance) {this.tolerance = tolerance;}
	public String toString() { return value + " " + weightPerUnit + " " + tolerance; }


}
