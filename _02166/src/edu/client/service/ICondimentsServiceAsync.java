package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.CondimentsDTO;

public interface ICondimentsServiceAsync {
	
	void getCondimentsInfo(String name, AsyncCallback<CondimentsDTO> callback);
	void getCondimentsList(AsyncCallback<List<CondimentsDTO>> callback);

}
