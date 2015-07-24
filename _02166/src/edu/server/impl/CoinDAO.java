package edu.server.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.service.ICoinService;
import edu.server.Connector;
import edu.shared.CoinDTO;
import edu.shared.DALException;

public class CoinDAO extends RemoteServiceServlet implements edu.server.interfaces.ICoinDAO, ICoinService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6897819696675555317L;

	public CoinDAO(){
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

	public CoinDTO getCoinInfo(double value) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM coins WHERE value = " + value);
		try {
			if (!rs.first()) throw new DALException("Mønt med værdi " + value + " findes ikke");
			return new CoinDTO(rs.getDouble("value"), rs.getDouble("weight"), rs.getDouble("tolerance"));
		}
		catch (SQLException e) {throw new DALException(e.getMessage()); }	
	}
	
	public List<CoinDTO> getCoinList() throws DALException {
		List<CoinDTO> list = new ArrayList<CoinDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM coins");
		try {
			while (rs.next()) 
			{
				list.add(new CoinDTO(rs.getDouble("value"), rs.getDouble("weight"), rs.getDouble("tolerance")));
			}
		} catch(SQLException e) {
			throw new DALException("Kunne ikke få CoinList");
		}
		return list;
	}

	
}
