package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	private StockItem stItem;
	private SoldItem item1;
	
	@Before
	public void setUp() {
		stItem = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
	}
	
	@Test
	public void testGetSum() {
		item1 = new SoldItem(stItem, 3);
		
		assertEquals(item1.getSum(), 30.0, 0.0001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		item1 = new SoldItem(stItem, 0);
		
		assertEquals(item1.getSum(), 0.0, 0.0001);
	}
}
