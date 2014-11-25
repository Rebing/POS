package ee.ut.math.tvt.salessystem;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class OrderTest {
	private StockItem stItem;
	private StockItem sItem1;
	private StockItem sItem2;
	private SoldItem item1;
	private SoldItem item3;
	private SoldItem item4;
	
	@Before
	public void setUP() {
	}
	
	@Test
	public void testAddSoldItem() {
		stItem = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		item1 = new SoldItem(stItem, 3);
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		soldItems.add(item1);
		Order item2 = new Order(soldItems, null);
		Assert.assertTrue(item2.getSoldItems().contains(item1));
	}
	
	@Test
	public void testGetSumWithNoItems() {
		Order item = new Order();
		Assert.assertEquals(item.getTotal_price(), 0.0, 0.0001);
	}
	
	@Test
	public void testGetSumWithOneItem() {
		stItem = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		item1 = new SoldItem(stItem, 3);
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		soldItems.add(item1);
		Order item2 = new Order(soldItems, null);
		Assert.assertEquals(item2.getTotal_price(), item1.getSum(), 0.0001 );
	}
	
	@Test
	public void testGetSumWithMultipleItems() {
		sItem1 = new StockItem(new Long(1), "Arbuus", "", 10.0, 5);
		sItem2 = new StockItem(new Long(2), "Melon", "", 60.0, 3);
		item3 = new SoldItem(sItem1, 1);
		item4 = new SoldItem(sItem2, 1);
		List<SoldItem> soldItems = new ArrayList<SoldItem>();
		soldItems.add(item3);
		soldItems.add(item4);
		Order item1 = new Order(soldItems, null);
		Assert.assertEquals(item1.getTotal_price(), item3.getSum()+item4.getSum(), 0.0001);
		
	}

}
