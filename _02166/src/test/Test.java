/**
 * 
 */
package test;

import java.util.List;

import edu.server.impl.BatchDAO;
import edu.shared.BatchDTO;


public class Test {
	
	
	public static void main(String[] args) throws Exception {
		BatchDAO dao = new BatchDAO();
		List<BatchDTO> list = dao.getBatchList();
		for(BatchDTO batch : list) {
			System.out.println(batch);
		}
	}
}
