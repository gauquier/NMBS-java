package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;

import source.Login;

import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import dao.DBA;
import dao.TicketDao;

import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;

public class HomeGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2564170957766548127L;

	private JPanel jPanel, jPanel2;

	ImageIcon photo;
	WritableRaster raster;
	DataBufferByte data;
	File image;
	private JTable table;
	
	public HomeGui(){
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setForeground(Color.WHITE);
		
		JLabel lblHuidigeGebruiker = new JLabel("Huidige gebruiker: " + Login.getCurrentUser());
		
		JLabel lblStation = new JLabel("Station: ");
		
		JLabel lblTicketverkoop = new JLabel("Ticketverkoop");
		JLabel lblStation = new JLabel("Station: ");
		
		String datumpje = TicketDao.getVerkoopdatumAsString();//tijdelijke code
		JLabel lblTicketverkoop = new JLabel("Ticketverkoop");
		
		/* SQL-statement die nodig is:
		 * SELECT verkoopDatum, COUNT(verkoopDatum)
		 * FROM Ticket
		 * GROUP BY verkoopDatum; 
		 */
		//String datumpje = TicketDao.getVerkoopdatumAsString();//tijdelijke code
		String datumpje = "28/11/2016";
		/* SQL-statement die nodig is:
		 * SELECT verkoopDatum, COUNT(verkoopDatum)
		 * FROM Ticket
		 * GROUP BY verkoopDatum; 
		 */
		
		//tabel zal opgevuld worden wanneer bovenstaande in orde is
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{datumpje, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Dag", "Verkochte tickets"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHuidigeGebruiker)
						.addComponent(lblStation)
						.addComponent(lblTicketverkoop))
					.addContainerGap(186, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHuidigeGebruiker)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStation)
					.addGap(30)
					.addComponent(lblTicketverkoop)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
