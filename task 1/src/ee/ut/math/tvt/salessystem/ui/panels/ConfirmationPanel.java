package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

/**
 * ConfirmationPanel.
 */
public class ConfirmationPanel extends JPanel {
	
    private JTextField amountField;
    private JLabel changeField;
    
    List<SoldItem> goods;
    double sum;

    private JButton okButton;
    private JButton cancelButton;

    // Warehouse model
    private SalesSystemModel model;

    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public ConfirmationPanel(List<SoldItem> goods, final JFrame frame, final PurchaseTab purchaseTab) {
        this.goods = goods;
        sum = calculateSum();

        setLayout(new GridLayout(4, 2));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); 
        
        drawSumComponent();
        drawAmountComponent();
        drawChangeComponent();
        drawButtons(frame, purchaseTab);
    }

    private void drawButtons(final JFrame frame, final PurchaseTab purchaseTab) {
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       frame.dispose();
		       purchaseTab.purchaseConfirmed();
		    }
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		       frame.dispose();
		    }
		});
		
		add(cancelButton);
		add(okButton);
	}

	private void drawSumComponent() {
        add(new JLabel("Sum:"));
        add(new JLabel(Double.toString(sum)));
    }
    
    private double calculateSum() {
        double sum = 0;
        for (SoldItem good : goods) {
        	sum += good.getSum();
        }
        return sum;
    }
    
    private void drawAmountComponent() {
        amountField = new JTextField();
        amountField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                fillChangeField();
            }
        });
        amountField.addKeyListener(
            new KeyListener() { 
            	@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){

                        fillChangeField();
                       } 
				}
					
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
            }
        );
        add(new JLabel("Payment amount:"));
        add(amountField);
    }
    
    public void fillChangeField() {
    	try {
    		double amount = Double.parseDouble(amountField.getText());
    		if (amount < sum) {
    			changeField.setText("-");
    		}
    		else {
    			changeField.setText(Double.toString(amount - sum));
    		}
    	}
    	catch (NumberFormatException e) {
    		changeField.setText("-");
    	}
    }
    
    private void drawChangeComponent() {
        changeField = new JLabel("-");
        add(new JLabel("Change:"));
        add(changeField);
    }
}

