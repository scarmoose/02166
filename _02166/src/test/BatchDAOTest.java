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
	public void testGetBatch2() throws Exception {
		BatchDTO dto = dao.getBatch(2); 
		assertEquals(dto.getBatch_id(), 2);
		assertEquals(dto.getRaavare_id(), 1);
		assertTrue(dto.getRaavare_navn().equals("tomat"));
		assertEquals((int) dto.getBatchweight(), 5);
		assertEquals(dto.getTolerance(), 0.05, 0.01);
		
	}
	
	@Test
	public void testGetBatch4() throws Exception {
		BatchDTO dto = dao.getBatch(4);
		assertEquals(dto.getBatch_id(), 4);
		assertEquals(dto.getRaavare_id(), 3);
		assertTrue(dto.getRaavare_navn().equals("kokain"));
		assertEquals((int) dto.getBatchweight(), 1);
		assertEquals(dto.getTolerance(), 0.005, 0.001);
		
	}
}
