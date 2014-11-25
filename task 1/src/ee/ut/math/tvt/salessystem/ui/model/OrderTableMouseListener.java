package ee.ut.math.tvt.salessystem.ui.model;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.data.Order;

public class OrderTableMouseListener implements MouseListener {

	JTable table;
	OrderTableModel orderTableModel;
	
	public OrderTableMouseListener(JTable table, OrderTableModel orderTableModel){
		this.table = table;
		this.orderTableModel = orderTableModel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
			int row = table.rowAtPoint(e.getPoint());
			
			Order order = orderTableModel.rows.get(row);
		
			popUpWindow(order);
		
	}

	  protected void popUpWindow(Order order) {
		  
		  // saaks lyhamaks ja ilusamaks teha. 
		  // samuti aken on huge :D:D
		  final JFrame frame = new JFrame("Order details");
		    
	        JPanel panel = new JPanel();
 
	        PurchaseInfoTableModel purchaseInfo = new PurchaseInfoTableModel();
	        purchaseInfo.rows.addAll(order.getSoldItems());
	    
	        JTable table = new JTable(purchaseInfo);

	        JTableHeader header = table.getTableHeader();
	        header.setReorderingAllowed(false);

	        JScrollPane scrollPane = new JScrollPane(table);

	        GridBagConstraints gc = new GridBagConstraints();
	        GridBagLayout gb = new GridBagLayout();
	        gc.fill = GridBagConstraints.BOTH;
	        gc.weightx = 1.0;
	        gc.weighty = 1.0;

	        panel.setLayout(gb);
	        panel.add(scrollPane, gc);

	        panel.setBorder(BorderFactory.createTitledBorder("Sold items"));

		  frame.getContentPane().add(panel);
	      frame.pack();
	      
	      //Sets the frame in the middle of the screen
	      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
	      Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
	                                    middle.y - (frame.getHeight() / 2));
	      frame.setLocation(newLocation);
	      
	      frame.setAlwaysOnTop(true);
	      frame.setVisible(true);
	  }
	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
