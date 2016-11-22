package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import dao.MedewerkerDAO;
import dao.PersoonDao;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;
import source.Validation;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JLabel lblVoornaamerror, lblAchternaamerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	
	public GebruikerVerwijderenGui() {
		setBackground(new Color(0, 191, 255));
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
		
		lblVoornaamerror = new JLabel("");
		lblVoornaamerror.setForeground(Color.RED);
		
		lblAchternaamerror = new JLabel("");
		lblAchternaamerror.setForeground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblVoornaam)
									.addGap(18)
									.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblVoornaamerror))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAchternaam)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnZoek, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblAchternaamerror))))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(lblGebruikerVerwijderen))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(149)
							.addComponent(btnVerwijder)))
					.addContainerGap(95, Short.MAX_VALUE))
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
								.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVoornaamerror))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAchternaam)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAchternaamerror))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(120)
							.addComponent(btnZoek)))
					.addGap(3)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVerwijder)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnZoek) {	
				lblVoornaamerror.setText("");
				lblAchternaamerror.setText("");
				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty()){
					if (!Validation.checkFirstName(txtVoornaam.getText())){
						lblVoornaamerror.setText("Gelieve een juiste voornaam in te vullen!");
						txtVoornaam.setBorder(bordererror);

					}
					if (!Validation.checkLastName(txtAchternaam.getText())){
						lblAchternaamerror.setText("Gelieve een juiste Achternaam in te vullen!");
						txtAchternaam.setBorder(bordererror);

					}
					else{
						
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Gelieve alle velden in te vullen!");
				}
			}
			
		}
		
	}
}
