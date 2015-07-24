package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.CoinDTO;
import edu.shared.DALException;

@RemoteServiceRelativePath("coinservice")
public interface ICoinService extends RemoteService {
	CoinDTO getCoinInfo(double value) throws DALException;
	List<CoinDTO> getCoinList() throws DALException;
}
