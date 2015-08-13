package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.server.impl.BatchDAO;
import edu.shared.BatchDTO;

public class BatchDAOTest {
	
	BatchDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = new BatchDAO(); 
	}

	@Test
	public void testGetBatch() throws Exception {
		BatchDTO dto = dao.getBatch(1); 
		assertEquals(dto.getBatch_id(), 1);
		assertEquals(dto.getRaavare_id(), 1);
		assertTrue(dto.getRaavare_navn().equals("tomat"));
		assertEquals((int) dto.getBatchweight(), 1);
		assertEquals(dto.getTolerance(), 0.05, 0.01);
		
	}
}
