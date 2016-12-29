package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dao.TicketDao;
import source.Login;
import source.Station;
import source.Ticketstatistiek;

public class HomeGui extends JPanel {

	private static final long serialVersionUID = 2564170957766548127L;

	private static ResourceBundle bundle;
	private JTable table;

	public HomeGui(boolean isOffline) {
		bundle = ResourceBundle.getBundle("localization.HomeGui");
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.setForeground(Color.WHITE);

		JLabel lblHuidigeGebruiker;
		JLabel lblStation;

		if (!isOffline) {
			lblHuidigeGebruiker = new JLabel(bundle.getString("curUser") + Login.getCurrentUser());

			lblStation = new JLabel(bundle.getString("station") + Station.getCurrentStation());
		} else {
			lblHuidigeGebruiker = new JLabel(bundle.getString("curUser") + bundle.getString("offline"));

			lblStation = new JLabel(bundle.getString("station") + bundle.getString("unknown"));
		}

		JLabel lblTicketverkoop;

		if (!isOffline) {
			lblTicketverkoop = new JLabel(bundle.getString("ticketSales"));

			ArrayList<Ticketstatistiek> ticketstats = TicketDao.getTicketstatistieken();

			// Om mogelijke out-of-boundsexception tegen te gaan
			if (ticketstats.size() < 5) {
				String legeString = "";
				int legeInt = 0;
				Ticketstatistiek legeStat = new Ticketstatistiek(legeString, legeInt);
				for (int i = ticketstats.size(); i < 5; i++) {
					ticketstats.add(legeStat);
				}
			}

			this.table = new JTable();
			this.table.setModel(
					new DefaultTableModel(
							new Object[][] {
									{ ticketstats.get(0).getVerkoopdatum(), ticketstats.get(0).getVerkochteTickets() },
									{ ticketstats.get(1).getVerkoopdatum(), ticketstats.get(1).getVerkochteTickets() },
									{ ticketstats.get(2).getVerkoopdatum(), ticketstats.get(2).getVerkochteTickets() },
									{ ticketstats.get(3).getVerkoopdatum(), ticketstats.get(3).getVerkochteTickets() },
									{ ticketstats.get(4).getVerkoopdatum(),
											ticketstats.get(4).getVerkochteTickets() }, },
							new String[] { "Dag", "Verkochte tickets" }) {

						private static final long serialVersionUID = 7778959677031066336L;
						boolean[] columnEditables = new boolean[] { false, false };

						@Override
						public boolean isCellEditable(int row, int column) {
							return this.columnEditables[column];
						}
					});
			this.table.getColumnModel().getColumn(0).setResizable(false);
			this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
			this.table.getColumnModel().getColumn(1).setResizable(false);
			this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
		} else {
			lblTicketverkoop = new JLabel("");
			this.table = new JTable();
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(this.table, GroupLayout.PREFERRED_SIZE, 254,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHuidigeGebruiker).addComponent(lblStation)
										.addComponent(lblTicketverkoop))
								.addContainerGap(186, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblHuidigeGebruiker)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblStation).addGap(30)
						.addComponent(lblTicketverkoop)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.table,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(125, Short.MAX_VALUE)));
		this.setLayout(groupLayout);
	}
}