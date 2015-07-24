package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DALException;

@RemoteServiceRelativePath("metaservice")
public interface IMetaService extends RemoteService {
	
	List<String> getTables() throws DALException;

}
