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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;


import dao.StationDAO;
import source.Station;
import api.BillBoard;
import api.BillBoardLoader;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;


public class StationInfoGui extends JPanel {
	private JTextField txtTijd;
	private JButton btnZoeken;
	private JComboBox<String> cmbbStation;
	private StationDAO stationDAO = new StationDAO();
	private ArrayList<BillBoard> billBoardLijst;
	private DefaultListModel<BillBoard> dlm;
	private JTable table;
	private JLabel lblTijdstip;
	private JLabel lblRichting;
	private JLabel lblPerron;
	private JLabel lblTrein;
	private JLabel lblAfgeschaft;
	public StationInfoGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel("Station");
		lblStation.setForeground(Color.WHITE);
		
		cmbbStation = new JComboBox();
		ArrayList<Station> lijst = stationDAO.getAll();
		for (Station station : lijst) {
			cmbbStation.addItem(station.getNaam());
		}
		
		JLabel lblTijd = new JLabel("Tijd");
		lblTijd.setForeground(Color.WHITE);
		
		txtTijd = new JTextField();
		txtTijd.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.addActionListener(new MenuItemHandler());
		JLabel lblStationInfo = DefaultComponentFactory.getInstance().createTitle("Station info");
		
		table = new JTable();
		
		lblTijdstip = new JLabel("Tijdstip");
		
		lblRichting = new JLabel("Richting");
		
		lblPerron = new JLabel("Perron");
		
		lblTrein = new JLabel("Trein");
		
		lblAfgeschaft = new JLabel("Afgeschaft");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(lblStationInfo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStation)
												.addComponent(lblTijd))
											.addGap(30)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(110)
															.addComponent(lblRichting)))
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(65)
															.addComponent(btnZoeken))
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(109)
															.addComponent(lblPerron))))))
										.addComponent(lblTijdstip))
									.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
									.addComponent(lblTrein)
									.addGap(100)
									.addComponent(lblAfgeschaft)
									.addGap(47)))))
					.addGap(36))
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
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTijd)
						.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTijdstip)
						.addComponent(lblTrein)
						.addComponent(lblAfgeschaft)
						.addComponent(lblRichting)
						.addComponent(lblPerron))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addContainerGap())
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
				Date date = new Date();				String datum = dateFormat.format(date);
				if (cmbbStation.getSelectedItem() != null && txtTijd.getText().isEmpty())
				{
				station = cmbbStation.getSelectedItem().toString();

					try 
					{
						billBoardLijst = new ArrayList<BillBoard>();
						billBoardLijst = BillBoardLoader.getDepartures(station,datum);//.toString();
						Object [][] rowData = new Object [billBoardLijst.size()][5];
						for(int i = 0; i < billBoardLijst.size(); i++)
						{
							rowData[i][0] = billBoardLijst.get(i).getTime()+" vertraging: + "+ billBoardLijst.get(i).getDelay();
							rowData[i][1] = billBoardLijst.get(i).getDirection();
							rowData[i][2] = billBoardLijst.get(i).getPlatform();
							rowData[i][3] = billBoardLijst.get(i).getTrain();
							rowData[i][4] = billBoardLijst.get(i).isCanceled();
						}
							
						String columnNames[] = {"Tijd","Richting", "Spoor", "Trein", "canceled" };
						TableModel model = new DefaultTableModel(rowData, columnNames);
						table.setModel(model);
					    table.setShowVerticalLines(false);
					    table.getColumnModel().getColumn(0).setPreferredWidth(80);
					    table.getColumnModel().getColumn(1).setPreferredWidth(50);
					    table.getColumnModel().getColumn(2).setPreferredWidth(5);
					    table.getColumnModel().getColumn(3).setPreferredWidth(40);
					    table.getColumnModel().getColumn(4).setPreferredWidth(10);
						
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Geen vertrekken gevonden.");
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
									//uur +vertraging, richting, perron, trainnr, canceled

									tijd = uur+min;
									billBoardLijst = new ArrayList<BillBoard>();
									billBoardLijst = BillBoardLoader.getDepartures(station,datum, tijd);//.toString();
									Object [][] rowData = new Object [billBoardLijst.size()][5];
									for(int i = 0; i < billBoardLijst.size(); i++)
									{
										rowData[i][0] = billBoardLijst.get(i).getTime()+" vertraging: + "+ billBoardLijst.get(i).getDelay();
										rowData[i][1] = billBoardLijst.get(i).getDirection();
										rowData[i][2] = billBoardLijst.get(i).getPlatform();
										rowData[i][3] = billBoardLijst.get(i).getTrain();
										rowData[i][4] = billBoardLijst.get(i).isCanceled();
									}
									
									String columnNames[] = {"Tijd","Richting", "Spoor", "Trein", "canceled" };
									TableModel model = new DefaultTableModel(rowData, columnNames);
									table.setModel(model);
									table.setShowVerticalLines(false);
									table.getColumnModel().getColumn(0).setPreferredWidth(80);
									table.getColumnModel().getColumn(1).setPreferredWidth(50);
									table.getColumnModel().getColumn(2).setPreferredWidth(5);
									table.getColumnModel().getColumn(3).setPreferredWidth(40);
									table.getColumnModel().getColumn(4).setPreferredWidth(10);
								}
								catch (Exception e1)
								{
									JOptionPane.showMessageDialog(new JFrame(), "Geen vertrekken gevonden.");
								}	
							}
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Niet numerieke invoer");
						}
					}
				}
			}	
		}
	}
}

