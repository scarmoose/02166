package edu.server.interfaces;

import java.util.List;

import edu.shared.CondimentsDTO;
import edu.shared.DALException;

public interface ICondimentsDAO {
	CondimentsDTO getCondimentsInfo(String name) throws DALException;
	List<CondimentsDTO> getCondimentsList() throws DALException;
}
