package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.Client;

/**
 * Client model.
 */
public class ClientTableModel extends SalesSystemTableModel<Client> {
	private static final long serialVersionUID = 1L;

	private SalesSystemModel model;
	
	private List<Client> tableRows;
	
	public ClientTableModel(SalesSystemModel salesSystemModel) {
		super(new String[] { "Id", "First name", "Discount"});
		this.model = salesSystemModel;
		tableRows = new ArrayList<Client>();
	}

	@Override
	protected Object getColumnValue(Client client, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return client.getId();
		case 1:
			return client.getFirstName();
		case 2:
			return client.getDiscountPercentage();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final Client client : getTableRows()) {
			buffer.append(client.getId() + "\t");
			buffer.append(client.getFirstName() + "\t");
			buffer.append(client.getDiscountPercentage() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	public void refresh() {
		populateWithData(model.getDomainController().getAllClients());
		fireTableDataChanged();
		
	}

	@Override
	public List<Client> getTableRows() {
		// TODO Auto-generated method stub
		return tableRows;
	}
}
