package edu.server.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.service.IASEService;
import edu.shared.DALException;

public class ASE extends RemoteServiceServlet implements edu.server.interfaces.IASE, IASEService {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3486195199860414450L;

	private Socket sock;
	private BufferedReader in;
	private DataOutputStream out;
	private double brutto;
	private double tara;
	private double netto;
	private static String host = "62.79.16.17";
	private static int port = 8000;

	/**
	 * connects to the specified host. 
	 * if a connection is already made, nothing is done.
	 */

	@Override
	public void connect() throws UnknownHostException, IOException {

		if(sock!=null) return;

		sock = new Socket(host, port);
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		out = new DataOutputStream(sock.getOutputStream());

	}

	/**
	 * Waits for the weight to stabilize, then returns the load in kg.
	 */

	@Override
	public double getSWeight() throws IOException {
		out.writeBytes("S\r\n");
		String response = in.readLine();
		brutto = Double.parseDouble(response.substring(3,response.length()-2).trim());
		return returnNetto();
	}

	/**
	 * Returns the current unstabilized load in kg.
	 */

	@Override
	public double getSIWeight() throws IOException, DALException {
		out.writeBytes("SI\r\n");
		String response = in.readLine();
		if(response.startsWith("ES")) {
			return -1;
		}else if(response.startsWith("S +")){
			throw new DALException("Weight Overload");
		}
		double weight = Double.parseDouble(response.substring(3,response.length()-2).trim());
		return weight;

	}

	/**
	 * Tares the weight stores in ASE. Does NOT tare the actual weight.
	 */

	@Override
	public void tara() throws Exception {
		tara = brutto;	
	}

	/**
	 * calculates the current netto load.
	 * @return netto weight
	 */

	public double returnNetto() {
		return netto = (brutto - tara);
	}

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}

	public double getBrutto() {
		return brutto;
	}

	public void setBrutto(double brutto) {
		this.brutto = brutto;
	}

	public double getNetto() {
		return netto;
	}

	public void setNetto(double netto) {
		this.netto = netto;
	}

}
