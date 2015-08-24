/**
 * 
 */
package test;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Test {
	
	
	public static void main(String[] args) throws Exception {
//		BatchDAO dao = new BatchDAO();
//		List<BatchDTO> list = dao.getBatchList();
//		for(BatchDTO batch : list) {
//			System.out.println(batch);
//		}
		
		double lowerWeightBound = 4.25129;
		
		System.out.println("før: "+lowerWeightBound);
		
		BigDecimal bd = new BigDecimal(lowerWeightBound).setScale(2, RoundingMode.HALF_EVEN);
		lowerWeightBound = bd.doubleValue();
		
		System.out.println("nu: "+lowerWeightBound);
	}
}
