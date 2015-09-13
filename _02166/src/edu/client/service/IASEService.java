package edu.client.service;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DALException;

@RemoteServiceRelativePath("aseservice")
public interface IASEService extends RemoteService{
	
	void setBrutto(double brutto);
	void connect() throws IOException;
	double getSWeight() throws IOException;
	double getSIWeight() throws IOException, DALException;
	void tara()throws Exception;
	void disconnect(); 
	void reconnect() throws IOException, InterruptedException;
	

}
