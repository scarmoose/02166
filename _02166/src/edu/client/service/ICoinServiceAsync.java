package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.CoinDTO;

public interface ICoinServiceAsync {
	
	void getCoinInfo(double value, AsyncCallback<CoinDTO> callback);
	void getCoinList(AsyncCallback<List<CoinDTO>> callback);

}
