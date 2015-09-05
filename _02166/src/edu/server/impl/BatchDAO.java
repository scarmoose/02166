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
}
