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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;


import dao.StationDAO;
import source.AutoComboBox;
import source.Station;
import api.BillBoard;
import api.BillBoardLoader;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;


public class StationInfoGui extends JPanel {
	private JTextField txtTijd;
	private JButton btnZoeken;
	private AutoComboBox cmbbStation;
	private StationDAO stationDAO = new StationDAO();
	private ArrayList<BillBoard> billBoardLijst;
	private DefaultListModel<BillBoard> dlm;
	private JTable table;
	private JLabel lblTijdstip;
	private JLabel lblRichting;
	private JLabel lblPerron;
	private JLabel lblTrein;
	private JLabel lblAfgeschaft;
	private int start, stop,max = 0;
	private JButton btnVorige;
	private JButton btnVolgende;
	private JLabel lblPagina;
	public StationInfoGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel("Station");
		lblStation.setForeground(Color.WHITE);
		lblStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		cmbbStation = new AutoComboBox();
		ArrayList<Station> lijst = StationDAO.getAll();
		ArrayList<String> stringLijst = new ArrayList<String>();
		for (Station station : lijst) {
			stringLijst.add(station.getNaam());
		}
		cmbbStation.setKeyWord(stringLijst);
		
		JLabel lblTijd = new JLabel("Tijd");
		lblTijd.setForeground(Color.WHITE);
		lblTijd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		txtTijd = new JTextField();
		txtTijd.setColumns(10);
		
		//ButtonGroup buttonGroup = new ButtonGroup();
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.addActionListener(new MenuItemHandler());
		btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JLabel lblStationInfo = DefaultComponentFactory.getInstance().createTitle("Station info");
		lblStationInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		table = new JTable();
		
		lblTijdstip = new JLabel("Tijdstip");
		lblTijdstip.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblRichting = new JLabel("Richting");
		lblRichting.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblPerron = new JLabel("Perron");
		lblPerron.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblTrein = new JLabel("Trein");
		lblTrein.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblAfgeschaft = new JLabel("Toestand");
		lblAfgeschaft.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnVorige = new JButton("Vorige");
		btnVorige.setVisible(false);
		btnVorige.addActionListener(new MenuItemHandler());
		btnVorige.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnVolgende = new JButton("Volgende");
		btnVolgende.setVisible(false);
		btnVolgende.addActionListener(new MenuItemHandler());
		btnVolgende.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblPagina = new JLabel("pagina");
		lblPagina.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPagina.setVisible(false);
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
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStation)
										.addComponent(lblTijd))
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtTijd)
										.addComponent(cmbbStation, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
									.addGap(68)
									.addComponent(btnZoeken))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblTijdstip)
											.addGap(124)
											.addComponent(lblRichting)
											.addGap(73)
											.addComponent(lblPerron)
											.addGap(53)
											.addComponent(lblTrein)
											.addGap(91)
											.addComponent(lblAfgeschaft))
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 691, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnVorige)
											.addGap(28)
											.addComponent(btnVolgende)
											.addGap(26)
											.addComponent(lblPagina)))))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(194))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblStationInfo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStation)
						.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTijd)
						.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZoeken))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTijdstip)
						.addComponent(lblRichting)
						.addComponent(lblPerron)
						.addComponent(lblTrein)
						.addComponent(lblAfgeschaft))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVorige)
						.addComponent(btnVolgende)
						.addComponent(lblPagina))
					.addContainerGap(260, Short.MAX_VALUE))
		);
		setLayout(groupLayout); 
	}
	public void close()
	{
		this.setVisible(false);
	}
	public void addData(ArrayList<BillBoard> billBoardLijst, int start, int stop)
	{
		lblPagina.setVisible(true);
		lblPagina.setText("Doorkomst: " + (start+1)+ " - " + (stop) + " / " + (max)); 
		System.out.println(start);
		System.out.println(stop);
		System.out.println(max);
		int lengte = stop-start;
		int stap = 0;
		Object [][] rowData = new Object [lengte][5];
		for(int i = start; i < stop; i++)
		{
			
			rowData[stap][0] = billBoardLijst.get(i).getTime()+" vertraging: + "+ billBoardLijst.get(i).getDelay();
			rowData[stap][1] = billBoardLijst.get(i).getDirection();
			rowData[stap][2] = billBoardLijst.get(i).getPlatform();
			rowData[stap][3] = billBoardLijst.get(i).getTrain();
			rowData[stap][4] = billBoardLijst.get(i).isCanceled();
			stap++;
		}
		System.out.println(start);
		System.out.println(stop);
		System.out.println(max);
		String columnNames[] = {"Tijd","Richting", "Spoor", "Trein", "canceled" };
		TableModel model = new DefaultTableModel(rowData, columnNames);
		table.setModel(model);
	    table.setShowVerticalLines(false);
	    table.getColumnModel().getColumn(0).setPreferredWidth(80);
	    table.getColumnModel().getColumn(1).setPreferredWidth(50);
	    table.getColumnModel().getColumn(2).setPreferredWidth(5);
	    table.getColumnModel().getColumn(3).setPreferredWidth(40);
	    table.getColumnModel().getColumn(4).setPreferredWidth(10);
	    System.out.println(start);
		System.out.println(stop);
		System.out.println(max);
	}
	private class MenuItemHandler implements ActionListener
	{
		String station;

		@Override
		public void actionPerformed(ActionEvent e)
		{			
			if (e.getSource() == btnZoeken)
			{
				btnVorige.setVisible(false);
				btnVolgende.setVisible(false);
				DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
				Date date = new Date();				String datum = dateFormat.format(date);
				if (cmbbStation.getSelectedItem() != null && txtTijd.getText().isEmpty())
				{
				
				station = cmbbStation.getSelectedItem().toString();

					try 
					{
						billBoardLijst = new ArrayList<BillBoard>();
						billBoardLijst = BillBoardLoader.getDepartures(station);
					
						max = billBoardLijst.size();
						if(max<=10)
						{
							stop = billBoardLijst.size();
							start = 0;
						}
						else if(max>10)
						{
							btnVolgende.setVisible(true);
							stop = 10;
							start = 0;

						}
						addData(billBoardLijst,start,stop);
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
									max = billBoardLijst.size();
									if(max<=10)
									{
										stop = billBoardLijst.size();
										start = 0;
									}
									else if(max>10)
									{
										btnVolgende.setVisible(true);
										stop = 10;
										start = 0;

									}
									addData(billBoardLijst,start,stop);
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
			if (e.getSource() == btnVolgende)
			{
				if(start+10 < max)
				{
					start = start +10;
					if(stop+10 >= max)
					{
						stop = max;
						btnVolgende.setVisible(false);

					}
					else if(stop+10 < max)
					{
						stop = start +10;
					}
				}
				else if(start + 10 > max)
				{
					btnVolgende.setVisible(false);
				}
				
					btnVorige.setVisible(true);
				
				addData(billBoardLijst,start,stop);
			}
			if (e.getSource() == btnVorige)
			{
				
				if(start-10 <= 0)
				{
					stop = 10;
					start = 0;
					btnVorige.setVisible(false);
					btnVolgende.setVisible(true);
				}
				else if(start-10 > 0)
				{
					start = start-10;
					stop = start +10;
					btnVorige.setVisible(true);
					btnVolgende.setVisible(true);
				}
				addData(billBoardLijst,start,stop);
			}
		}
	}
}

