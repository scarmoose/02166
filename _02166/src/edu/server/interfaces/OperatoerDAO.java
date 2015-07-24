package edu.server.interfaces;

import java.util.List;

import edu.shared.DALException;
import edu.shared.OperatoerDTO;

public interface OperatoerDAO {
	
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
