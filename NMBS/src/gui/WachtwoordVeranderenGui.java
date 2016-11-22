package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WachtwoordVeranderenGui extends JPanel {
	private JPasswordField pwdHerhaaldWachtwoord;
	private JPasswordField pwdNieuwwachtwoord;
	private JButton btnWijzigen;
	
	public WachtwoordVeranderenGui() {
		setBackground(new Color(0, 191, 255));
		
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
		lblGebruikersnaam.setText(Login.getCurrentUser());
		
		lblGebruikersnaam.setForeground(Color.WHITE);
		
		btnWijzigen = new JButton("Wijzigen");
		btnWijzigen.addActionListener(new MenuItemHandler());
		
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
								.addComponent(pwdHerhaaldWachtwoord, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(144)
							.addComponent(btnWijzigen)))
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
					.addGap(30)
					.addComponent(btnWijzigen)
					.addContainerGap(63, Short.MAX_VALUE))
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
			if (e.getSource() == btnWijzigen){
				String nieuwWachtwoord = new String(pwdNieuwwachtwoord.getPassword());
				String herhaalwachtwwoord = new String(pwdHerhaaldWachtwoord.getPassword());
				
				if (nieuwWachtwoord != null && herhaalwachtwwoord != null){
					if (nieuwWachtwoord.equals(herhaalwachtwwoord)){
						LoginDao.updateWachtwoord(nieuwWachtwoord);
						JOptionPane.showMessageDialog(new JFrame(), "Uw wachtwoord is aangepast!");
						close();
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(), "De wachtwoorden komen niet overeen!");
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Gelieve alle velden in te vullen!");
				}
			}
		}
	}
}
