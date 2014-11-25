package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;


public class StockTab {

  private JButton addItem;
  private JButton CancelButton;
  private JButton OkButton;
  private JTextField idField;
  private JTextField nameField;
  private JTextField descriptionField;
  private JTextField priceField;
  private JTextField quantityField;
  private SalesSystemModel model;
  private PurchaseTab purchaseTab;
  
  public StockTab(SalesSystemModel model, PurchaseTab purchaseTab) {
    this.model = model;
    this.purchaseTab = purchaseTab;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;

    addItem =  createNewAddButton();
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }
  
//Creates the button "Add new"
 private JButton createNewAddButton() {
   JButton b = new JButton("Add new item");
   b.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
       AddButtonClicked();
     }
   });

   return b;
 }
 
 protected void AddButtonClicked() {
	  final JFrame frame = new JFrame("Stock");
	  
     //Frame in the middle of the screen
	 frame.setSize(500,200);
	 frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
     Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
                                   middle.y - (frame.getHeight() / 2));
	    
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(6, 2));

	idField = new JTextField();
	nameField = new JTextField();
	descriptionField = new JTextField();
	priceField = new JTextField();
	quantityField = new JTextField();
		
	// ID
	panel.add(new JLabel("ID: "));
	panel.add(idField);
			
	// Name
	panel.add(new JLabel("Name: "));
	panel.add(nameField);
	
	// Description
	panel.add(new JLabel("Description: "));
	panel.add(descriptionField);
	
	// Price
	panel.add(new JLabel("Price: "));
	panel.add(priceField);

	// Quantity
	panel.add(new JLabel("Quantity: "));
	panel.add(quantityField);
		
	//OK button
	OkButton = new JButton("OK");
	OkButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e)
	    {
	       addingConfirmed(frame);
	    }
	});
	panel.add(OkButton);
	
	//Cancel button
	CancelButton = new JButton("Cancel");
	CancelButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e)
	    {
	       frame.dispose();
	    }
	});
	panel.add(CancelButton);
	
	frame.add(panel);
	frame.setLocation(newLocation);
	frame.setAlwaysOnTop(true);
	frame.setVisible(true);

 }

 public void addingConfirmed(JFrame frame) {
	 
	 try {
		 long id = Long.parseLong(idField.getText());
		 String name = nameField.getText();
		 String description = descriptionField.getText();
		 double price = Double.parseDouble(priceField.getText());
		 int quanity = Integer.parseInt(quantityField.getText());
		 StockItem item = new StockItem(id, name, description, price, quanity);
		 if (model.getWarehouseTableModel().addItem(item, true)) {
			 purchaseTab.addItemToNameField(item);
		 }
		 frame.dispose();
	 }
	 catch (NumberFormatException e) {
		 JOptionPane.showMessageDialog(frame, "Id, quantity and price must be numbers.",
     			"Invalid entry", JOptionPane.WARNING_MESSAGE);
	 }
 }
 

  // table of the warehouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

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

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }

}
