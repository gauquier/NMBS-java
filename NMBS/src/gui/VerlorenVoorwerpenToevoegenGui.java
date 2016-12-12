package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import source.Station;
import source.Validation;
import source.VerlorenVoorwerp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;

public class VerlorenVoorwerpenToevoegenGui extends JPanel{
	
	private JTextField txtDatum;
	private JButton btnToevoegen;
	JTextArea txtrBeschrijving;
	VerlorenVoorwerp verlorenVoorwerp;
	Station station;
	private JTextField txtStation;
	private JLabel lblBeschrijvingerror;
	private JLabel lblDatumerror;
	private JLabel lblStationerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private StationDAO stationDAO = new StationDAO();
	private VerlorenVoorwerpDAO verlorenVoorwerpDAO = new VerlorenVoorwerpDAO();
	
	public VerlorenVoorwerpenToevoegenGui() {
		setBackground(new Color(0, 191, 255));
		
		JLabel lblBeschrijving = new JLabel("Beschrijving");
		lblBeschrijving.setForeground(Color.WHITE);
		
		txtrBeschrijving = new JTextArea();
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		
		btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.addActionListener(new MenuItemHandler());
		
		JLabel lblStation = new JLabel("Station");	
		lblStation.setForeground(Color.WHITE);
		
		txtStation = new JTextField();
		txtStation.setColumns(10);
		
		JLabel label = DefaultComponentFactory.getInstance().createTitle("Verloren voorwerp toevoegen");
		
		lblBeschrijvingerror = new JLabel("");
		lblBeschrijvingerror.setForeground(Color.RED);
		
		lblDatumerror = new JLabel("");
		lblDatumerror.setForeground(Color.RED);
		
		lblStationerror = new JLabel("");
		lblStationerror.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBeschrijving)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblStation)
									.addComponent(lblDatum)))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblStationerror))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDatumerror))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblBeschrijvingerror))
								.addComponent(btnToevoegen))))
					.addContainerGap(211, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(label)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBeschrijving)
						.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBeschrijvingerror))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatum)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatumerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtStation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStationerror))
						.addComponent(lblStation))
					.addGap(18)
					.addComponent(btnToevoegen)
					.addGap(68))
		);
		setLayout(groupLayout);
	}
	
	public void close()
	{
		this.setVisible(false);
	}
	
	private class MenuItemHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			if (e.getSource() == btnToevoegen){
				
				lblDatumerror.setText("");
				lblStationerror.setText("");
				txtDatum.setBorder(border);
				txtStation.setBorder(border);
				
				if(!txtrBeschrijving.getText().isEmpty() && !txtDatum.getText().isEmpty() && !txtStation.getText().isEmpty()){
					if (!Validation.checkDate(txtDatum.getText(), "dd/MM/yyyy")){
						lblDatumerror.setText("Gelieve een juiste datum in te vullen!");
						txtDatum.setBorder(bordererror);

					}
					if (!Validation.checkLastName(txtStation.getText())){
						lblStationerror.setText("Gelieve een juist Station in te vullen!");
						txtStation.setBorder(bordererror);

					}
					else{
						boolean gevonden = false;
						String startDateString = txtDatum.getText();
						java.util.Date myDate = new java.util.Date(startDateString);
						java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
						int stationId = StationDAO.getStationId(txtStation.getText().trim());
						station = new Station(stationId);
						verlorenVoorwerp = new VerlorenVoorwerp(stationId, txtrBeschrijving.getText().trim(), sqlDate ,gevonden);
						VerlorenVoorwerpDAO.insertVerlorenVoorwerp(verlorenVoorwerp);
					}

				if(!txtrBeschrijving.getText().isEmpty() && !txtDatum.getText().isEmpty()){
					boolean gevonden = false;
					String startDateString = txtDatum.getText();
					java.util.Date myDate = new java.util.Date(startDateString);
					java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
					int stationId = stationDAO.getStationId(txtStation.getText().trim());
					station = new Station(stationId);
					verlorenVoorwerp = new VerlorenVoorwerp(stationId, txtrBeschrijving.getText().trim(), sqlDate ,gevonden);
					verlorenVoorwerpDAO.insertVerlorenVoorwerp(verlorenVoorwerp);
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Please fill in all required fields!");
				}
			}
			*/
		}		    	
    }
}
