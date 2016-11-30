package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.PersoonDao;
import source.Persoon;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class GebruikerVerwijderenGui extends JPanel {
	private JTextField txtVoornaam;
	private JTextField txtAchternaam;
	private JButton btnZoek;
	private String achternaam;
	List<Persoon> personen;
	public GebruikerVerwijderenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setForeground(Color.WHITE);
		
		JLabel lblVoornaam = new JLabel("Voornaam");
		lblVoornaam.setForeground(Color.WHITE);
		
		txtVoornaam = new JTextField();
		txtVoornaam.setColumns(10);
		
		JLabel lblAchternaam = new JLabel("Achternaam");
		lblAchternaam.setForeground(Color.WHITE);
		
		txtAchternaam = new JTextField();
		txtAchternaam.setColumns(10);
		
		btnZoek = new JButton("Zoek");
		btnZoek.addActionListener(new MenuItemHandler());
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setMaximumRowCount(15);
		
		JButton btnVerwijder = new JButton("Verwijder");
		
		JLabel lblGebruikerVerwijderen = DefaultComponentFactory.getInstance().createTitle("Gebruiker verwijderen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblAchternaam)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblVoornaam)
											.addGap(18)
											.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addComponent(btnZoek))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(147)
							.addComponent(btnVerwijder))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(lblGebruikerVerwijderen)))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblGebruikerVerwijderen)
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVoornaam)
								.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAchternaam)
								.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addComponent(btnZoek)))
					.addGap(26)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVerwijder)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnZoek) {	
				
			}
			
		}
		
	}
}