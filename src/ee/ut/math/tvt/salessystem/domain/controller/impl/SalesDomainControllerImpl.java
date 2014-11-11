package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		return HibernateUtil.currentSession().createQuery("from StockItem").list();
	}
	
	public List<Order> loadHistoryState() {
		Order.getCurrentOrderId();
		List<Order> orders = HibernateUtil.currentSession().createQuery("from Order").list();
		for (Order order : orders) {
			while (order.getId().longValue() >= Order.getCurrentOrderId().longValue()) {
				Order.getNewOrderId();
			}
		}

		List<SoldItem> items = HibernateUtil.currentSession().createQuery("from SoldItem").list();
		for (SoldItem item : items) {
			for (Order order : orders) {
				if (order.getId() == item.getOrderId()) {
					order.addSoldItem(item);
				}
			}
		}
		return orders;
	}
	
	public void endSession() {
		HibernateUtil.closeSession();
	}
}
