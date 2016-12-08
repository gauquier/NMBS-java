package gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import source.Station;

public class StationInfoGui extends JPanel {
	private JTextField txtDatum;
	private JTextField txtTijd;
	public StationInfoGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel("Station");
		lblStation.setForeground(Color.WHITE);
		
		JComboBox cmbbStation = new JComboBox();
		 
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		
		JLabel lblTijd = new JLabel("Tijd");
		lblTijd.setForeground(Color.WHITE);
		
		txtTijd = new JTextField();
		txtTijd.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setForeground(Color.WHITE);
		JRadioButton rdbtnAankomst = new JRadioButton("Aankomst");
		rdbtnAankomst.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnVertrek);
		buttonGroup.add(rdbtnAankomst);
		
		JButton btnZoeken = new JButton("Zoeken");
		
		JLabel lblStationInfo = DefaultComponentFactory.getInstance().createTitle("Station info");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnVertrek)
									.addGap(18)
									.addComponent(rdbtnAankomst))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStation)
										.addComponent(lblDatum)
										.addComponent(lblTijd))
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(109)
							.addComponent(btnZoeken))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(lblStationInfo)))
					.addContainerGap(155, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatum)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTijd)
						.addComponent(txtTijd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnVertrek)
						.addComponent(rdbtnAankomst))
					.addGap(18)
					.addComponent(btnZoeken)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		setLayout(groupLayout); 
		StationDAO stationDAO=new StationDAO(); 
		for(Station station:stationDAO.getStationsLazyLoading()){
			cmbbStation.addItem(station.getNaam());
		}
		
	}
	public void close()
	{
		this.setVisible(false);
	}
}