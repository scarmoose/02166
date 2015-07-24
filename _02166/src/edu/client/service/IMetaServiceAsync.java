package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IMetaServiceAsync {
	void getTables(AsyncCallback<List<String>> callback);
}
