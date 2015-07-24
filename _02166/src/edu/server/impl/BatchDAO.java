package edu.server.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.client.service.IBatchService;
import edu.server.Connector;
import edu.shared.BatchDTO;
import edu.shared.DALException;

public class BatchDAO extends RemoteServiceServlet implements edu.server.interfaces.IBatchDAO, IBatchService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147045543911648126L;

	public BatchDAO() {
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
	public BatchDTO getBatch(int batchid) throws Exception {
		ResultSet rs = Connector.doQuery("SELECT * FROM batch WHERE batch_id = " + batchid);
		try {
			if (!rs.first()) throw new DALException("Batch med id " + batchid + " findes ikke");
			return new BatchDTO(rs.getInt("batch_id"), rs.getInt("raavare_id"), rs.getString("raavare"),
					rs.getDouble("batchweight"), rs.getDouble("tolerance"));
		}
		catch (SQLException e) {throw new DALException(e.getMessage()); }
	}

	@Override
	public List<BatchDTO> getBatchList() throws Exception {
		List<BatchDTO> list = new ArrayList<BatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM batch");
		try {
			while (rs.next()) 
			{
				list.add(new BatchDTO(rs.getInt("batch_id"), rs.getInt("raavare_id"), rs.getString("raavare"),
						rs.getDouble("batchweight"), rs.getDouble("tolerance")));
			}
		} catch(SQLException e) {
			throw new DALException("Kunne ikke få BatchList: " + e.getMessage());
		}
		return list;
	}
	//Doesn't atm so we cant test or use it.
	
}
