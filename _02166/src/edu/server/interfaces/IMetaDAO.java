package edu.server.interfaces;

import java.sql.SQLException;
import java.util.List;

import edu.shared.DALException;

public interface IMetaDAO {
	List<String> getTables() throws DALException, SQLException;
	
}
