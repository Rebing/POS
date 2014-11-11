package ee.ut.math.tvt.salessystem.domain.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

@Entity
@Table(name = "ORDER")
public class Order implements DisplayableItem{
	
	private static Long newId = (long) 1;
	
	@Id
	@Column(name="id")
    private Long id;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "time")
    private String time;
    
    @Column(name = "price")
    private double total_price;
    
    @Transient
	private List<SoldItem> soldItems;
	
	public Order(){
		soldItems = new ArrayList<SoldItem>();
	}
	
	public Order(List<SoldItem> soldItems, Long id){
		this.id = id;
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
		
		Session session = HibernateUtil.currentSession();
	    session.beginTransaction();
	    session.save(order);
	    
	    for(SoldItem item : soldItems) {
	    	session.save(item);
	    }
	    session.getTransaction().commit();
	}
	
	private static Order createOrder(List<SoldItem> soldItems){
		return new Order(soldItems, newId);
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
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public void addSoldItem(SoldItem item) {
		soldItems.add(item);
	}

	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItems = soldItems;
	}

	public static Long getCurrentOrderId() {
		return newId;
	}
	
	public static Long getNewOrderId() {
		newId++;
		return newId;
	}
}
