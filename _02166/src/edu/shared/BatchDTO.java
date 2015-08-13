package edu.shared;

import java.io.Serializable;

public class BatchDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1097429204239740446L;
	
	int batch_id;
	int raavare_id;
	String raavare_navn;
	double batchweight;
	double tolerance;
	
	public BatchDTO() {
		
	}
	
	public BatchDTO(int batch_id, int raavare_id, String raavare_navn,
			double batchweight, double tolerance) {
		this.batch_id = batch_id;
		this.raavare_id = raavare_id;
		this.raavare_navn = raavare_navn;
		this.batchweight = batchweight;
		this.tolerance = tolerance;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public int getRaavare_id() {
		return raavare_id;
	}

	public void setRaavare_id(int raavare_id) {
		this.raavare_id = raavare_id;
	}

	public String getRaavare_navn() {
		return raavare_navn;
	}

	public void setRaavare_navn(String raavare_navn) {
		this.raavare_navn = raavare_navn;
	}

	public double getBatchweight() {
		return batchweight;
	}

	public void setBatchweight(double batchweight) {
		this.batchweight = batchweight;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
	
	public String toString() {
		return batch_id+", "+raavare_id+", "+raavare_navn+", "
				+batchweight+", "+tolerance+". ";
	}
	
	

}
