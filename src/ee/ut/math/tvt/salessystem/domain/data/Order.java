package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class Order implements DisplayableItem{
	
	String date;
	String time;
	double total_price;
	List<SoldItem> soldItems;
	
	public Order(){
		
	}
	
	public Order(List<SoldItem> soldItems){
		this.soldItems = soldItems;
		total_price = Order.calculateTotalPrice(soldItems);
		date = currentDate();
		time = currentTime();
	}
	
	private static String currentDate(){
		Date date = new Date();
		DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dFormat. format(date);
	}
	
	private static String currentTime(){
		Date date = new Date();
		DateFormat dFormat = new SimpleDateFormat("HH:mm:ss");
		return dFormat. format(date);
	}
	

	public static void saveOrder(SalesSystemModel model){
		
		List<SoldItem> soldItems = model.getCurrentPurchaseTableModel().getTableRows();		
		
		Order order = Order.createOrder(soldItems);
		
		model.getOrderTableModel().addOrderAndRefresh(order);
	}
	
	private static Order createOrder(List<SoldItem> soldItems){
		return new Order(soldItems);
	}
	
	private static double calculateTotalPrice(List<SoldItem> soldItems){
		double sum = 0;
		for(SoldItem item : soldItems){
			sum += item.getSum();
		}
		return sum;
	}
	
	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public List<SoldItem> getSoldItems() {
		return soldItems;
	}

	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItems = soldItems;
	}

	
}
