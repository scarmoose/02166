package edu.server.interfaces;

import java.util.List;

import edu.shared.BatchDTO;

public interface IBatchDAO {
	BatchDTO getBatch(int batchid) throws Exception;
	List<BatchDTO> getBatchList() throws Exception;
}
