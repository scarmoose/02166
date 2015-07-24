package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.FruitDTO;

public interface IFruitServiceAsync {
	
	void getFruitInfo(String name, AsyncCallback<FruitDTO> callback);
	void getFruitList(AsyncCallback<List<FruitDTO>> callback);

}
