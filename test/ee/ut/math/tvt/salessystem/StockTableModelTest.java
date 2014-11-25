package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.OrderTableModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	private StockItem item1;
	private StockItem item2;
	private StockItem item3;
	
	@Before
	public void setUp() {
		item1 = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		item2 = new StockItem(new Long(2), "Melon", "", 60.0, 3);
		item3 = new StockItem(new Long(3), "Banaan", "", 25.0, 18);
	}
	
	@Test
	public void testValidateNameUniqueness() {
		StockTableModel model = new StockTableModel();
		model.addItem(item1, false);
		boolean hasProblem = false;
		try {
			model.addItem(new StockItem(new Long(5), "Arbuus", "", 10.0, 5), false);
		}
		catch (Exception e) {
			hasProblem = true;
		}
		if (!hasProblem && model.getRowCount() > 1) {
			fail("It is possible to add multiple items with same name.");
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