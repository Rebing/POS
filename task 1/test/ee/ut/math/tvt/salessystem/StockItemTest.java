package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	private StockItem item1;
	
	@Before
	public void setUp() {
		item1 = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
	}
	
	@Test
	public void testClone() {
		StockItem item2 = (StockItem) item1.clone();
		
		assertEquals(item1.getName(), item2.getName());
		assertEquals(item1.getId(), item2.getId());
	}
	
	@Test
	public void testGetColumn() {
		long id1 = (long) item1.getColumn(0);
		long id2 = item1.getId();
		
		assertEquals(id1, id2);
	}
}
