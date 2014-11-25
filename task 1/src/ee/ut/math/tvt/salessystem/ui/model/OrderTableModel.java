package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.Order;

public class OrderTableModel extends SalesSystemTableModel<Order>{

	public OrderTableModel() {
		super(new String[]{"Date of order", "Time of order", "Total order price"});
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected Object getColumnValue(Order order, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return order.getDate();
		case 1:
			return order.getTime();
		case 2:
			return order.getTotal_price();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	
	public void addOrderAndRefresh(Order order){
		rows.add(order);
		fireTableDataChanged();
	}

}
