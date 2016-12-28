package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import source.Station;

public class StationInfoGui extends JPanel {
	private JTextField txtDatum;
	private JTextField txtTijd;
	public StationInfoGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblStation = new JLabel("Station");
		lblStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStation.setForeground(Color.WHITE);
		
		JComboBox cmbbStation = new JComboBox();
		cmbbStation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		 
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtDatum.setColumns(10);
		
		JLabel lblTijd = new JLabel("Tijd");
		lblTijd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTijd.setForeground(Color.WHITE);
		
		txtTijd = new JTextField();
		txtTijd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtTijd.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnVertrek.setForeground(Color.WHITE);
		JRadioButton rdbtnAankomst = new JRadioButton("Aankomst");
		rdbtnAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnAankomst.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnVertrek);
		buttonGroup.add(rdbtnAankomst);
		
		JButton btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblStationInfo = DefaultComponentFactory.getInstance().createTitle("Station info");
		lblStationInfo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
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
										.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtTijd)
											.addComponent(txtDatum))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(lblStationInfo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(124)
							.addComponent(btnZoeken)))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblStationInfo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStation)
						.addComponent(cmbbStation, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(8, Short.MAX_VALUE))
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