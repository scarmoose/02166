package edu.shared;

import java.io.Serializable;

public class LogDTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 539673977986529416L;

	private int log_id;
	private int opr_id;
	private int batch_id;
	private double afvigelse;

	public LogDTO() {

	}

	public LogDTO(int log_id, int opr_id, int batch_id, double afvigelse) {
		this.log_id = log_id;
		this.opr_id = opr_id;
		this.batch_id = batch_id;
		this.afvigelse = afvigelse;
	}

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getOpr_id() {
		return opr_id;
	}

	public void setOpr_id(int opr_id) {
		this.opr_id = opr_id;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public double getAfvigelse() {
		return afvigelse;
	}

	public void setAfvigelse(double afvigelse) {
		this.afvigelse = afvigelse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}






}
