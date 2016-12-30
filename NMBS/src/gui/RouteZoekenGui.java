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
import javax.swing.JViewport;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.media.sound.ModelAbstractChannelMixer;

import source.Station;
import source.Validation;
import javax.swing.JComboBox;
import dao.StationDAO;
//import gui.VerlorenVoorwerpenToevoegenGui.MenuItemHandler;
import api.*;
import java.awt.List;
import java.awt.ScrollPane;

import javax.swing.JList;
import source.VerlorenVoorwerp;
import javax.swing.ListModel;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.Point;

public class RouteZoekenGui extends JPanel {
	private JTextField txtUur;
	private JButton btnZoeken;
	private JLabel lblDatumerror, lblUurerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private StationDAO stationDAO = new StationDAO();
	private JComboBox<String> stationLijstVan;
	private JComboBox<String> stationLijstNaar;
	private ArrayList<Trip> tripLijst;
	private DefaultListModel<Trip> dlm;
	private JRadioButton rdbtnVertrek;
	private JRadioButton rdbtnAankomst;
	private ButtonGroup buttonGroup;
	private JTable table = new JTable();
	private JDateChooser dateChooser = new JDateChooser();
	private DefaultTableModel tableModel = new DefaultTableModel();
	/**
	 * @wbp.nonvisual location=-341,219
	 */
	
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
		rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setForeground(Color.WHITE);
		rdbtnAankomst = new JRadioButton("Aankomst");
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
		
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("dd-MM-yyyy");
		
		JLabel lblDatum_1 = new JLabel("Datum");
		
		JLabel lblRoute = new JLabel("Route");
		
		JLabel lblUur_1 = new JLabel("Uur");
		
		JLabel lblReistijd = new JLabel("Reistijd");
		
		JLabel lblPerron = new JLabel("Perron");
		
		JLabel lblTrein = new JLabel("Trein");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVan)
								.addComponent(lblNaar)
								.addComponent(lblDatum_1))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(stationLijstNaar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
										.addComponent(stationLijstVan, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRoute))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblUur)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(54)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(rdbtnVertrek)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(18)
															.addComponent(rdbtnAankomst)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblUurerror)
																.addComponent(lblDatumerror)))
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(21)
															.addComponent(lblUur_1)
															.addGap(125)
															.addComponent(lblReistijd)
															.addGap(48)
															.addComponent(lblPerron)
															.addGap(46)
															.addComponent(lblTrein))))))
										.addComponent(lblDatum))
									.addContainerGap(134, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRouteZoeken)
										.addComponent(btnZoeken))
									.addContainerGap(389, Short.MAX_VALUE))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblRouteZoeken)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblVan)
							.addComponent(stationLijstVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDatum))
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaar)
						.addComponent(stationLijstNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUur))
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
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoute)
						.addComponent(lblDatum_1)
						.addComponent(lblUur_1)
						.addComponent(lblReistijd)
						.addComponent(lblPerron)
						.addComponent(lblTrein))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
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
				Date date = dateChooser.getDate();
				String datum = dateFormat.format(date);
				boolean sel;

				    if(rdbtnAankomst.isSelected() == true)
				    {
				       sel = true;
				    }
				    else if(rdbtnVertrek.isSelected()== true)
				    {
				       sel = false;
				    }
				    else
				    {
				        sel = false;
				    }
				
				if (stationLijstVan.getSelectedItem() != null && stationLijstNaar.getSelectedItem() != null && stationLijstVan.getSelectedItem() != stationLijstNaar.getSelectedItem()&& txtUur.getText().isEmpty())
				{
				stationVan = stationLijstVan.getSelectedItem().toString();
				stationNaar = stationLijstNaar.getSelectedItem().toString();
				System.out.println("Van: " + stationVan);
				System.out.println("Naar: " + stationNaar);
				
					try 
					{
						tripLijst = new ArrayList<Trip>();
						tripLijst =TripLoader.getTrips(stationVan,stationNaar, datum);
						Object [][] rowData = new Object [(tripLijst.size()*2)][7];
						int row = 0;
						for(int i = 0; i < tripLijst.size(); i++)
						{
							rowData[row][0] = tripLijst.get(i).vertrek.getDatum();
							rowData[row][1] = "vertrek";
							rowData[row][2] = tripLijst.get(i).vertrek.getStation();
							rowData[row][3] = tripLijst.get(i).vertrek.getTijd() +" vertraging: + "+ tripLijst.get(i).vertrek.getVertraging();
							rowData[row][4] = tripLijst.get(i).getDuration() +"minuten";
							rowData[row][5] =tripLijst.get(i).vertrek.getPerron();
							rowData[row][6] =tripLijst.get(i).vertrek.getTrein();
							row++;
							rowData[row][0] = " ";
							rowData[row][1] = "aankomst";
							rowData[row][2] = tripLijst.get(i).aankomst.getStation();
							rowData[row][3] = tripLijst.get(i).aankomst.getTijd() +" vertraging: + "+ tripLijst.get(i).aankomst.getVertraging();
							rowData[row][4] = " ";
							rowData[row][5] =tripLijst.get(i).aankomst.getPerron();
							rowData[row][6] =tripLijst.get(i).aankomst.getTrein();
							row++;
						}
					    String columnNames[] = {" ", " ", "Route", "Tijd", "Reistijd", "Spoor", "Trein" };
					    
					    TableModel model = new DefaultTableModel(rowData, columnNames);
					    table.setModel(model);
					    table.getColumnModel().getColumn(0).setPreferredWidth(30);
					    table.getColumnModel().getColumn(1).setPreferredWidth(30);
					    table.getColumnModel().getColumn(2).setPreferredWidth(40);
					    table.getColumnModel().getColumn(3).setPreferredWidth(80);
					    table.getColumnModel().getColumn(4).setPreferredWidth(10);
					    table.getColumnModel().getColumn(5).setPreferredWidth(10);
					    table.getColumnModel().getColumn(6).setPreferredWidth(40);
					    table.setShowVerticalLines(false);
					    table.setRowSelectionAllowed(isEnabled());		       
						
					} 
					catch (Exception e1) {
						JOptionPane.showMessageDialog(new JFrame(), "Geen routes gevonden.");
						
					}
				
				}
				else if (stationLijstVan.getSelectedItem() != null && stationLijstNaar.getSelectedItem() != null && stationLijstVan.getSelectedItem() != stationLijstNaar.getSelectedItem()&& !txtUur.getText().isEmpty())
				{
					
					stationVan = stationLijstVan.getSelectedItem().toString();
					stationNaar = stationLijstNaar.getSelectedItem().toString();
					System.out.println("Van: " + stationVan);
					System.out.println("Naar: " + stationNaar);
					//arrive = true
					//depart = false
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
										tripLijst = new ArrayList<Trip>();
										tripLijst =TripLoader.getTrips(stationVan, stationNaar, datum, tijd, sel);
										Object [][] rowData = new Object [(tripLijst.size()*2)][7];
										int row = 0;
										for(int i = 0; i < tripLijst.size(); i++)
										{
											
											rowData[row][0] = tripLijst.get(i).vertrek.getDatum();
											rowData[row][1] = "vertrek";
											rowData[row][2] = tripLijst.get(i).vertrek.getStation();
											rowData[row][3] = tripLijst.get(i).vertrek.getTijd() +" vertraging: + "+ tripLijst.get(i).vertrek.getVertraging();
											rowData[row][4] = tripLijst.get(i).getDuration() +"minuten";
											rowData[row][5] =tripLijst.get(i).vertrek.getPerron();
											rowData[row][6] =tripLijst.get(i).vertrek.getTrein();
											row++;
											rowData[row][0] = " ";
											rowData[row][1] = "aankomst";
											rowData[row][2] = tripLijst.get(i).aankomst.getStation();
											rowData[row][3] = tripLijst.get(i).aankomst.getTijd() +" vertraging: + "+ tripLijst.get(i).aankomst.getVertraging();
											rowData[row][4] = " ";
											rowData[row][5] =tripLijst.get(i).aankomst.getPerron();
											rowData[row][6] =tripLijst.get(i).aankomst.getTrein();
											row++;
										}
									    String columnNames[] = {" ", " ", "Route", "Tijd", "Reistijd", "Spoor", "Trein" };
									    
									    TableModel model = new DefaultTableModel(rowData, columnNames);
									    table.setModel(model);
									    table.getColumnModel().getColumn(0).setPreferredWidth(30);
									    table.getColumnModel().getColumn(1).setPreferredWidth(30);
									    table.getColumnModel().getColumn(2).setPreferredWidth(40);
									    table.getColumnModel().getColumn(3).setPreferredWidth(80);
									    table.getColumnModel().getColumn(4).setPreferredWidth(10);
									    table.getColumnModel().getColumn(5).setPreferredWidth(10);
									    table.getColumnModel().getColumn(6).setPreferredWidth(40);
									    table.setShowVerticalLines(false);
									    table.setRowSelectionAllowed(isEnabled());
										
											
											
									}
									catch (Exception e1)
									{
										JOptionPane.showMessageDialog(new JFrame(), "Geen routes gevonden.");
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
		
