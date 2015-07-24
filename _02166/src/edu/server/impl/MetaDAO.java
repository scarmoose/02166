package edu.server.impl;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.service.IMetaService;
import edu.server.Connector;
import edu.server.interfaces.IMetaDAO;
import edu.shared.DALException;

public class MetaDAO extends RemoteServiceServlet implements IMetaDAO, IMetaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5711854845648826350L;

	public MetaDAO(){
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getTables() throws DALException {
		List<String> list = new ArrayList<String>();
		try{
			DatabaseMetaData md = Connector.getConnection().getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				list.add(rs.getString(3));
			}
		}catch(SQLException e) {throw new DALException(e.getMessage()); }
		return list;
	}

}
