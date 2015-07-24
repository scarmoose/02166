package edu.server.interfaces;

import java.util.List;

import edu.shared.DALException;
import edu.shared.FruitDTO;

public interface IFruitDAO {
	FruitDTO getFruitInfo(String name) throws DALException;
	List<FruitDTO> getFruitList() throws DALException;
}
