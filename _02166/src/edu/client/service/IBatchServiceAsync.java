package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.shared.BatchDTO;

public interface IBatchServiceAsync {
	void getBatch(int batchid, AsyncCallback<BatchDTO> callback);
	void getBatchList(AsyncCallback<List<BatchDTO>> callback);
}
