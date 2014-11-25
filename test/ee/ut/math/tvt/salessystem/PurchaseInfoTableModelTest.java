package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class PurchaseInfoTableModelTest {

	private StockItem item1;
	private StockItem item2;
	private SoldItem sold1;
	private SoldItem sold2;
	private SoldItem sold3;
	
	@Before
	public void setUp() {
		item1 = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		item2 = new StockItem(new Long(2), "Melon", "", 60.0, 3);
		sold1 = new SoldItem(item1, 2);
		sold2 = new SoldItem(item2, 3);
		sold3 = new SoldItem(item1, 5);
	}
	
	@Test
	public void testAddMultipleItems() {
		PurchaseInfoTableModel model = new PurchaseInfoTableModel();
		model.addItem(sold1);
		model.addItem(sold2);
		if (model.getRowCount() != 2) {
			fail("There wasn't 2 items in table although exactly 2 items were added.");
		}
	}
	
	@Test
	public void testSameItemIsAddedMultipleTimes() {
		PurchaseInfoTableModel model = new PurchaseInfoTableModel();
		model.addItem(sold1);
		model.addItem(sold3);
		if (model.getRowCount() > 1) {
			fail("New item was added although same item already was in order.");
		}
		if ((int)model.getValueAt(0, 3) != 7) {
			fail("Item quatity was not increased correctly.");
		}
	}
	
	@Test
	public void testAddItemWithZeroQuantity() {
		PurchaseInfoTableModel model = new PurchaseInfoTableModel();
		model.addItem(new SoldItem(item1, 0));
		if (model.getRowCount() > 0) {
			fail("Item with zero quantity was added.");
		}
	}
	
	@Test
	public void testColumnValues() {
		PurchaseInfoTableModel model = new PurchaseInfoTableModel();
		model.addItem(sold1);
		model.addItem(sold2);
		if (!"Melon".equals(model.getValueAt(1, 1))) {
			fail("Item name was not correct.");
		}
		if (!"Melon".equals(model.getValueAt(1, 1))) {
			fail("Item name was not correct.");
		}
		if ((double)model.getValueAt(1, 2) != 60.0) {
			fail("Item price was not correct.");
		}
		if ((int)model.getValueAt(1, 3) != 3) {
			fail("Item quatity was not correct.");
		}
		if ((double)model.getValueAt(1, 4) != 3*60.0) {
			fail("Sum was not correct.");
		}
	}
}
