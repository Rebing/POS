package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	private StockTableModel model;
	private StockItem item1;
	private StockItem item2;
	private StockItem item3;
	
	@Before
	public void setUp() {
		item1 = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		item2 = new StockItem(new Long(2), "Melon", "", 60.0, 3);
		item3 = new StockItem(new Long(3), "Arbuus", "", 25.0, 18);
		
		model.addItem(item1);
		model.addItem(item2);
		model.addItem(item3);
		model.fireTableDataChanged();
	}
	
	@Test
	public void testValidateNameUniqueness() {
		try {
			StockItem s = model.getItemByName("Arbuus");
			assertEquals(s.getName(), item2.getName());
		} catch (NullPointerException e) {
			fail("fail");
		}
	}
	
	// TODO
	public void testHasEnoughInStock() {
		
	}
	
	// TODO
	public void testGetItemByIdWhenItemExists() {
		
	}
	
	// TODO
	public void testGetItemByIdWhenThrowsException() {
		
	}
}
