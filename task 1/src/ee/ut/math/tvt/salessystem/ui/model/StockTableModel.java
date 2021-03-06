package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	public StockTableModel() {
		super(new String[] {"Id", "Name", "Price", "Quantity"});
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	/**
	 * Add new stock item to table. If there already is a stock item with
	 * same id, then existing item's quantity will be increased.
	 * Will return true if new item is created. Otherwise will return false.
	 * @param stockItem
	 * @throws VerificationFailedException 
	 */
	public boolean addItem(final StockItem stockItem, boolean saveToDatabase) throws VerificationFailedException {
		try {
			StockItem item = getItemById(stockItem.getId());
			item.setQuantity(item.getQuantity() + stockItem.getQuantity());
			log.debug("Found existing item " + stockItem.getName()
					+ " increased quantity by " + stockItem.getQuantity());
			if (saveToDatabase) {
				Session session = HibernateUtil.currentSession();
				session.beginTransaction();
				session.save(item);
				session.getTransaction().commit();
			}
			fireTableDataChanged();
			return false;
		}
		catch (NoSuchElementException e) {
			try {
				getItemByName(stockItem.getName());
				throw new VerificationFailedException("Item with same name exists already");
			}
			catch (NoSuchElementException ex) {
				rows.add(stockItem);
				log.debug("Added " + stockItem.getName()
						+ " quantity of " + stockItem.getQuantity());
				if (saveToDatabase) {
					Session session = HibernateUtil.currentSession();
					session.beginTransaction();
					session.save(stockItem);
					session.getTransaction().commit();
				}
				fireTableDataChanged();
				return true;
			}
		}
	}
	
	/**
	 * Remove a quantity of a certain item from stock. If the removable quantity
	 * is greater than what is currently in stock, "false" will be returned.
	 */
	public boolean removeItem(final StockItem stockItem, int quantity) {
		try {
			StockItem item = getItemById(stockItem.getId());
			int change = item.getQuantity() - quantity;
			if(change >= 0) {
				item.setQuantity(change);
				fireTableDataChanged();
				log.debug("Decreased quantity of " + stockItem.getName()
						+ " to " + change);
				return true;
			} else {
				return false;
			}
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final StockItem stockItem : rows) {
			buffer.append(stockItem.getId() + "\t");
			buffer.append(stockItem.getName() + "\t");
			buffer.append(stockItem.getPrice() + "\t");
			buffer.append(stockItem.getQuantity() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

}
