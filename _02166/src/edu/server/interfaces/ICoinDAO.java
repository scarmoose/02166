package edu.server.interfaces;

import java.util.List;

import edu.shared.CoinDTO;
import edu.shared.DALException;

public interface ICoinDAO {
	CoinDTO getCoinInfo(double value) throws DALException;
	List<CoinDTO> getCoinList() throws DALException;
}
