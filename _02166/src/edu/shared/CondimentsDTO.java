package edu.shared;

import java.io.Serializable;

public class CondimentsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2846969203314216759L;

	private double id;
	private String name;
	private double weightPerUnit;
	private double tolerance;

	public CondimentsDTO() {

	}

	public CondimentsDTO(double id, String name, double weightPerUnit, double tolerance) {
		this.id = id;
		this.name = name;
		this.weightPerUnit = weightPerUnit;
		this.tolerance = tolerance;
	}
	public double getId() {return id;}
	public void setId(double id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public double getWeightPerUnit() {return weightPerUnit;}
	public void setWeightPerUnit(double weightPerUnit) {this.weightPerUnit = weightPerUnit;	}
	public double getTolerance() {return tolerance;}
	public void setTolerance(double tolerance) {this.tolerance = tolerance;}
	public String toString() { return name + " " + weightPerUnit + " " + tolerance; }


}
