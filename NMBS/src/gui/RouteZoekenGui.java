package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import source.Station;
import source.Validation;
import javax.swing.JComboBox;
import dao.StationDAO;
//import gui.VerlorenVoorwerpenToevoegenGui.MenuItemHandler;
import api.*;
import java.awt.List;
import javax.swing.JList;
import source.VerlorenVoorwerp;
import javax.swing.ListModel;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;

public class RouteZoekenGui extends JPanel {
	private JTextField txtUur;
	private JButton btnZoeken;
	private JLabel lblDatumerror, lblUurerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private StationDAO stationDAO = new StationDAO();
	private JComboBox<String> stationLijstVan;
	private JComboBox<String> stationLijstNaar;
	private JList<String> list;
	private ArrayList<Trip> tripLijst;
	private DefaultListModel<Trip> dlm;
	private JRadioButton rdbtnVertrek;
	private JRadioButton rdbtnAankomst;
	private ButtonGroup buttonGroup;
	public RouteZoekenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		
		
		JLabel lblVan = new JLabel("Van");
		lblVan.setForeground(Color.WHITE);
		
		JLabel lblNaar = new JLabel("Naar");
		lblNaar.setForeground(Color.WHITE);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		
		JLabel lblUur = new JLabel("Uur");
		lblUur.setForeground(Color.WHITE);
		
		txtUur = new JTextField();
		txtUur.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setForeground(Color.WHITE);
		JRadioButton rdbtnAankomst = new JRadioButton("Aankomst");
		rdbtnAankomst.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnVertrek);
		buttonGroup.add(rdbtnAankomst);
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.addActionListener(new MenuItemHandler());
		
		JLabel lblRouteZoeken = DefaultComponentFactory.getInstance().createTitle("Route zoeken");
		
		
		lblDatumerror = new JLabel("");
		lblDatumerror.setForeground(Color.RED);
		
		lblUurerror = new JLabel("");
		lblUurerror.setForeground(Color.RED);
		
		stationLijstVan = new JComboBox();
		ArrayList<Station> lijst = stationDAO.getAll();
		for (Station station : lijst) {
			stationLijstVan.addItem(station.getNaam());
		}
		
		stationLijstNaar = new JComboBox();
		for (Station station : lijst) {
			stationLijstNaar.addItem(station.getNaam());
		}
		
		list = new JList<String>();
		list.setBackground(SystemColor.menu);
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVan)
						.addComponent(lblNaar))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRouteZoeken)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(stationLijstNaar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(stationLijstVan, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addGap(0)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUur)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtnVertrek)
											.addGap(18)
											.addComponent(rdbtnAankomst)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUurerror)
												.addComponent(lblDatumerror)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(26)
											.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblDatum)))
						.addComponent(btnZoeken))
					.addGap(438))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblRouteZoeken)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVan)
						.addComponent(stationLijstVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatum))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaar)
						.addComponent(stationLijstNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUur)
						.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDatumerror)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblUurerror))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnVertrek)
							.addComponent(rdbtnAankomst)
							.addComponent(btnZoeken)))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addGap(41))
		);
		setLayout(groupLayout);
	}
	public void close()
	{
		this.setVisible(false);
	}
	
	private class MenuItemHandler implements ActionListener
	{
		String stationVan;
		String stationNaar;
		@Override
		public void actionPerformed(ActionEvent e)
		{			
			if (e.getSource() == btnZoeken)
			{
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
				Date date = new Date();
				String datum = dateFormat.format(date);
				if (stationLijstVan.getSelectedItem() != null && stationLijstNaar.getSelectedItem() != null && stationLijstVan.getSelectedItem() != stationLijstNaar.getSelectedItem()&& txtUur.getText().isEmpty())
				{
				stationVan = stationLijstVan.getSelectedItem().toString();
				stationNaar = stationLijstNaar.getSelectedItem().toString();
				System.out.println("Van: " + stationVan);
				System.out.println("Naar: " + stationNaar);

					try {
						tripLijst = new ArrayList<Trip>();
						tripLijst =TripLoader.getTrips(stationVan,stationNaar);
						DefaultListModel<String> dlm = new DefaultListModel<String>();
						for(Trip t: tripLijst)
						{
							dlm.addElement(t.toString());
							System.out.println(t.toString());
						}
						list.setModel(dlm);
					    list.setSelectedIndex(0);
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				//enkel datum
				if (stationLijstVan.getSelectedItem() != null && stationLijstNaar.getSelectedItem() != null && stationLijstVan.getSelectedItem() != stationLijstNaar.getSelectedItem()&& !txtUur.getText().isEmpty())
				{
					stationVan = stationLijstVan.getSelectedItem().toString();
					stationNaar = stationLijstNaar.getSelectedItem().toString();
					System.out.println("Van: " + stationVan);
					System.out.println("Naar: " + stationNaar);
					StringBuilder time = new StringBuilder();
					String tijd = txtUur.getText();
					time.append(tijd);
					if(time.length()==5)
					{
						String min = time.substring(3,5);
						String uur = time.substring(0,2);
						try
						{
						int mintel = Integer.parseInt(min);
							try
							{
								int uurtel = Integer.parseInt(uur);
								if(mintel < 0 || mintel >59 || uurtel <0 || uurtel > 23)
								{
									JOptionPane.showMessageDialog(new JFrame(), "Foutieve invoer voor uur, probeer een ander tijdstip");
									txtUur.setText("uu:mm");
								}
								else
								{
									try 
									{
										tijd = uur+min;
										stationVan = stationLijstVan.getSelectedItem().toString();
										stationNaar = stationLijstNaar.getSelectedItem().toString();
										System.out.println(buttonGroup.getSelection().getActionCommand());
										boolean sel = false;
										
										System.out.println("Van: " + stationVan);
										System.out.println("Naar: " + stationNaar);

											try {
												tripLijst = new ArrayList<Trip>();
												tripLijst =TripLoader.getTrips(stationVan,stationNaar, datum, tijd, sel);
												DefaultListModel<String> dlm = new DefaultListModel<String>();
												for(Trip t: tripLijst)
												{
													dlm.addElement(t.toString());
													System.out.println(t.toString());
												}
												list.setModel(dlm);
											    list.setSelectedIndex(0);
											
											} catch (Exception e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
									}
									catch (Exception e1)
									{
										JOptionPane.showMessageDialog(new JFrame(), "Fout bij het ophalen van de data, probeer het later opnieuw.");
									}
								}
							}
							
							catch(Exception e1)
							{
								JOptionPane.showMessageDialog(new JFrame(), "Niet numerieke invoer");

							}
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Niet numerieke invoer");

						}
					}
						
						
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "Foutieve invoer voor uur, probeer een ander formaat");
						txtUur.setText("uu:mm");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "Foutieve invoer, probeer ander waardes");
				}
			}
		}		    		
	}
}
		
