package edu.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IASEServiceAsync {
	
	void connect(AsyncCallback<Void> callback);
	void getSWeight(AsyncCallback<Double> callback);
	void getSIWeight(AsyncCallback<Double> callback);
	void tara(AsyncCallback<Void> callback);
	void setBrutto(double brutto, AsyncCallback<Void> callback);
	void disconnect(AsyncCallback<Void> callback);
	void reconnect(AsyncCallback<Void> callback); 

}
