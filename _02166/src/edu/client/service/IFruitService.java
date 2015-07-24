package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.DALException;
import edu.shared.FruitDTO;

@RemoteServiceRelativePath("fruitservice")
public interface IFruitService extends RemoteService {
	FruitDTO getFruitInfo(String name) throws DALException;
	List<FruitDTO> getFruitList() throws DALException;
}
