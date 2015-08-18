package edu.server.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.opencsv.CSVParser;

import edu.client.service.IBatchService;
import edu.shared.BatchDTO;

public class BatchDAO extends RemoteServiceServlet implements edu.server.interfaces.IBatchDAO, IBatchService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147045543911648126L;

	public BatchDAO() {
//		try {
//			new Connector();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

//	public BatchDTO getBatch(int batchid) throws Exception {
//		ResultSet rs = Connector.doQuery("SELECT * FROM batch WHERE batch_id = " + batchid);
//		try {
//			if (!rs.first()) throw new DALException("Batch med id " + batchid + " findes ikke");
//			return new BatchDTO(rs.getInt("batch_id"), rs.getInt("raavare_id"), rs.getString("raavare"),
//					rs.getDouble("batchweight"), rs.getDouble("tolerance"));
//		}
//		catch (SQLException e) {throw new DALException(e.getMessage()); }
//	}
	
	@Override
	public BatchDTO getBatch(int batchid) throws Exception {
		CSVParser csv = new CSVParser();
		BufferedReader in = new BufferedReader(new FileReader("batches.csv"));
		String[] values = new String[5];
		values[0] = "-1";
		while(Integer.parseInt(values[0]) != batchid){
			String line;
			if((line = in.readLine()) == null) break;
			values = csv.parseLine(line);
		}
		in.close();
		return new BatchDTO(Integer.parseInt(values[0]), Integer.parseInt(values[1]), 
				values[2], Double.parseDouble(values[3]), Double.parseDouble(values[4]));
	}

	@Override
	public List<BatchDTO> getBatchList() throws Exception {
		List<BatchDTO> list = new ArrayList<BatchDTO>(); 
		CSVParser csv = new CSVParser();
		BufferedReader in = new BufferedReader(new FileReader("batches.csv"));
		String line;
		while((line = in.readLine()) != null) {
			String[] values = csv.parseLine(line); 
			list.add(new BatchDTO(Integer.parseInt(values[0]), Integer.parseInt(values[1]), 
				values[2], Double.parseDouble(values[3]), Double.parseDouble(values[4])));
		}
		in.close();
		return list;
	}
	
//	public List<BatchDTO> getBatchList() throws Exception {
//		List<BatchDTO> list = new ArrayList<BatchDTO>();
//		ResultSet rs = Connector.doQuery("SELECT * FROM batch");
//		try {
//			while (rs.next()) 
//			{
//				list.add(new BatchDTO(rs.getInt("batch_id"), rs.getInt("raavare_id"), rs.getString("raavare"),
//						rs.getDouble("batchweight"), rs.getDouble("tolerance")));
//			}
//		} catch(SQLException e) {
//			throw new DALException("Kunne ikke få BatchList: " + e.getMessage());
//		}
//		return list;
//	}
	//Doesn't atm so we cant test or use it.
	
}
