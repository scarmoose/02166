package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.BatchDTO;

@RemoteServiceRelativePath("batchservice")
public interface IBatchService extends RemoteService {
	BatchDTO getBatch(int batchid) throws Exception;
	List<BatchDTO> getBatchList() throws Exception;
}
