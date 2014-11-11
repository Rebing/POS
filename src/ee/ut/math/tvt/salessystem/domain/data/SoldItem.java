package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */
@Entity
@Table(name = "SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "stockitem_id")
    private Long stockItemId;
	
	@Column(name = "sale_id")
    private Long orderId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "itemprice")
    private double price;
    
    public SoldItem(StockItem stockItem, int quantity) {
        this.stockItemId = stockItem.getId();
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.quantity = quantity;
        this.orderId = Order.getCurrentOrderId();
    }
    
    public SoldItem() {
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return price * ((double) quantity);
    }

    public Long getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(Long stockItemId) {
        this.stockItemId = stockItemId;
    }
    
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
}
