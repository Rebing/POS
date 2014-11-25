package ee.ut.math.tvt.salessystem;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.OrderTableModel;

public class OrderTableModelTest {

	private StockItem item1;
	private StockItem item2;
	private SoldItem sold1;
	private SoldItem sold2;
	private SoldItem sold3;
	private Order order1;
	private Order order2;
	
	@Before
	public void setUp() {
		item1 = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		item2 = new StockItem(new Long(2), "Melon", "", 60.0, 3);
		sold1 = new SoldItem(item1, 2);
		sold2 = new SoldItem(item2, 3);
		sold3 = new SoldItem(item1, 5);
		order1 = new Order(Arrays.asList(sold1, sold2, sold3), (long)3);
		order2 = new Order(Arrays.asList(new SoldItem(item1, 20), new SoldItem(item2, 10)), (long)4);
	}
	
	@Test
	public void testAddOrder() {
		OrderTableModel model = new OrderTableModel();
		model.addOrderAndRefresh(order1);
		model.addOrderAndRefresh(order2);
		if (model.getRowCount() != 2) {
			fail("There wasn't 2 items in table although exactly 2 items were added.");
		}
	}
	
	@Test
	public void testTotalPriceColumn() {
		OrderTableModel model = new OrderTableModel();
		model.addOrderAndRefresh(order1);
		model.addOrderAndRefresh(order2);
		if ((double)model.getValueAt(1, 2) != 10*60.0 + 20*10.0) {
			fail("Total price was not correct.");
		}
	}
}
