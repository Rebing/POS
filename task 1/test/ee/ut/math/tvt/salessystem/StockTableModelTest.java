package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.OrderTableModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import java.util.NoSuchElementException;

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
		boolean hasProblem = false;
		try {
			model.addItem(item1, false);
			model.addItem(new StockItem(new Long(5), "Arbuus", "", 10.0, 5), false);
		}
		catch (Exception e) {
			hasProblem = true;
		}
		if (!hasProblem && model.getRowCount() > 1) {
			fail("It is possible to add multiple items with same name.");
		}
	}
	
	public void testHasEnoughInStock() {
		// It is not possible to implement this method with logic we have at the moment.
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() throws VerificationFailedException {
			StockTableModel model = new StockTableModel();
			try {
				model.addItem(item2, false);
				Assert.assertEquals(model.getItemById(2), item2);
			} catch (VerificationFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	

	@Test(expected=NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		StockTableModel model = new StockTableModel ();
		model.getItemById(8);
	}

}
