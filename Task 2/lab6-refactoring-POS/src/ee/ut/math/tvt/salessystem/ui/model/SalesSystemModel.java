package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;


/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
		
	
    // Warehouse model
    private StockTableModel warehouseTableModel;

    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    // Puchase history model
    private PurchaseHistoryTableModel purchaseHistoryTableModel;

    private ClientTableModel clientTableModel;

    private Client selectedClient;
    
    private SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
    	
    	

    	this.domainController = domainController;
        warehouseTableModel = new StockTableModel(this);
        currentPurchaseTableModel = new PurchaseInfoTableModel(this);
        currentPurchaseTableModel.setCurrentSale(new Sale());
        purchaseHistoryTableModel = new PurchaseHistoryTableModel(this);
        clientTableModel = new ClientTableModel(this);

        // Load data from the database
/*
        List<StockItem> stockItems = domainController.getAllStockItems();
        warehouseTableModel.populateWithData(stockItems);

        List<Client> clients = domainController.getAllClients();
        clientTableModel.populateWithData(clients);
*/
/*        List<Sale> sales = domainController.getAllSales();
        purchaseHistoryTableModel.populateWithData(sales);*/
        
        warehouseTableModel.refresh();
        clientTableModel.refresh();
        purchaseHistoryTableModel.refresh();

    }
    

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }

    public PurchaseHistoryTableModel getPurchaseHistoryTableModel() {
        return purchaseHistoryTableModel;
    }

    public ClientTableModel getClientTableModel() {
        return clientTableModel;
    }

    public void setPurchaseHistoryTableModel(
            PurchaseHistoryTableModel purchaseHistoryTableModel) {
        this.purchaseHistoryTableModel = purchaseHistoryTableModel;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client client) {
        this.selectedClient = client;
    }
    
    public SalesDomainController getDomainController(){
    	return domainController;
    }

	public void setCurrentPurchaseTableModel(
			PurchaseInfoTableModel currentPurchaseTableModel) {
		this.currentPurchaseTableModel = currentPurchaseTableModel;
	}

    
    
}
