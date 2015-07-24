package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.CondimentsDTO;
import edu.shared.DALException;

@RemoteServiceRelativePath("condimentsservice")
public interface ICondimentsService extends RemoteService {
	CondimentsDTO getCondimentsInfo(String name) throws DALException;
	List<CondimentsDTO> getCondimentsList() throws DALException;
}
