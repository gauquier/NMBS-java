package gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import api.Trip;
import api.TripLoader;
import dao.StationDAO;
import source.Station;
import api.BillBoard;
import api.BillBoardLoader;
import com.toedter.calendar.JDateChooser;
import javax.swing.ListModel;
import java.awt.SystemColor;
import javax.swing.JOptionPane;


public class StationInfoGui extends JPanel {
	private JTextField txtTijd;
	private JButton btnZoeken;
	private JComboBox<String> cmbbStation;
	private JList<String> list;
	private StationDAO stationDAO = new StationDAO();
	private ArrayList<BillBoard> billBoardLijst;
	private JDateChooser dateChooser;
	private DefaultListModel<BillBoard> dlm;
	public StationInfoGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel("Station");
		lblStation.setForeground(Color.WHITE);
		
		cmbbStation = new JComboBox();
		ArrayList<Station> lijst = stationDAO.getAll();
		for (Station station : lijst) {
			cmbbStation.addItem(station.getNaam());
		}
		 
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		JLabel lblTijd = new JLabel("Tijd");
		lblTijd.setForeground(Color.WHITE);
		
		txtTijd = new JTextField();
		txtTijd.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.addActionListener(new MenuItemHandler());
		
		JLabel lblStationInfo = DefaultComponentFactory.getInstance().createTitle("Station info");
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setCalendar(Calendar.getInstance());
		dateChooser.setDateFormatString("dd-MM-yyyy");
		
		list = new JList<String>();
		list.setBackground(SystemColor.menu);	
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStation)
						.addComponent(lblDatum)
						.addComponent(lblTijd))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 205, Short.MAX_VALUE)))
					.addGap(65)
					.addComponent(btnZoeken)
					.addGap(304))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblStationInfo)
					.addContainerGap(629, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblStationInfo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStation)
						.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZoeken))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDatum)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTijd))
					.addGap(18)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout); 
	}
	public void close()
	{
		this.setVisible(false);
	}
	
	private class MenuItemHandler implements ActionListener
	{
		String station;

		@Override
		public void actionPerformed(ActionEvent e)
		{			
			if (e.getSource() == btnZoeken)
			{
				DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
				Date date = new Date();
				String datum = dateFormat.format(date);
				if (cmbbStation.getSelectedItem() != null && txtTijd.getText().isEmpty())
				{
				station = cmbbStation.getSelectedItem().toString();

					try 
					{
						billBoardLijst = new ArrayList<BillBoard>();
						billBoardLijst = BillBoardLoader.getDepartures(station, datum);
						DefaultListModel<String> dlm = new DefaultListModel<String>();
						for(BillBoard  b: billBoardLijst)
						{
							dlm.addElement(b.toString());
							System.out.println(b.toString());
						}
						list.setModel(dlm);
					    list.setSelectedIndex(0);
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Fout bij het ophalen van de data, probeer het later opnieuw.");
					}
				
				}
				//enkel datum
				if (cmbbStation.getSelectedItem() != null && !txtTijd.getText().isEmpty())
				{
					station = cmbbStation.getSelectedItem().toString();
					String tijd= txtTijd.getText().toString();
					StringBuilder time = new StringBuilder();
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
									txtTijd.setText("uu:mm");
								}
								else
								{
									try 
									{
										tijd = uur+min;
										billBoardLijst = new ArrayList<BillBoard>();
										billBoardLijst = BillBoardLoader.getDepartures(station,datum, tijd);//.toString();
										DefaultListModel<String> dlm = new DefaultListModel<String>();
										for(BillBoard  b: billBoardLijst)
										{
											dlm.addElement(b.toString());
											System.out.println(b.toString());
										}
										list.setModel(dlm);
									    list.setSelectedIndex(0);
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
						txtTijd.setText("uu:mm");
					}
				}
			}	
		}
	}
}