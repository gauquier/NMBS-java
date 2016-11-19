package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import source.Login;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class WachtwoordVeranderenGui extends JPanel {
	private JPasswordField pwdHerhaaldWachtwoord;
	private JPasswordField pwdNieuwwachtwoord;
	
	public WachtwoordVeranderenGui() {
		setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		
		JLabel lblWachtwoordWijzigen = DefaultComponentFactory.getInstance().createTitle("Wachtwoord wijzigen");
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		
		JLabel lblWachtwoord = new JLabel("Nieuw wachtwoord");
		lblWachtwoord.setForeground(Color.WHITE);
		
		JLabel lblHerhaalWachtwoord = new JLabel("Herhaal wachtwoord");
		lblHerhaalWachtwoord.setForeground(Color.WHITE);
		
		pwdHerhaaldWachtwoord = new JPasswordField();
		
		pwdNieuwwachtwoord = new JPasswordField();
		
		JLabel lblGebruikersnaam = new JLabel("");
		
		lblGebruikersnaam.setForeground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(lblWachtwoordWijzigen))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHerhaalWachtwoord)
								.addComponent(lblWachtwoord)
								.addComponent(lblUsername))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblGebruikersnaam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pwdNieuwwachtwoord)
								.addComponent(pwdHerhaaldWachtwoord, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblWachtwoordWijzigen)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(lblGebruikersnaam))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWachtwoord)
						.addComponent(pwdNieuwwachtwoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHerhaalWachtwoord)
						.addComponent(pwdHerhaaldWachtwoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(142, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
