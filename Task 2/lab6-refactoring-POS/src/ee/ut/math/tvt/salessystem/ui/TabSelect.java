package ee.ut.math.tvt.salessystem.ui;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class TabSelect implements ChangeListener{

	SalesSystemModel model;
	SalesSystemUI ui;
	public TabSelect(SalesSystemUI ui){
		this.ui = ui;
		model = ui.getSalesSystemModel();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tabbed = (JTabbedPane) e.getSource();
		String title = tabbed.getTitleAt(tabbed.getSelectedIndex());
		if(title.equals(ui.getStockTitle())){
			model.getWarehouseTableModel().refresh();
		}
		else if(title.equals(ui.getHistoryTitle())){
			model.getPurchaseHistoryTableModel().refresh();
		}
		else if(title.equals(ui.getClientTitle())){
			model.getClientTableModel().refresh();
		}
	}

}
