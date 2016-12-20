package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;
import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WachtwoordVeranderenGui extends JPanel {
	private static ResourceBundle bundle;
	
	private JPasswordField pwdHerhaaldWachtwoord;
	private JPasswordField pwdNieuwwachtwoord;
	private JButton btnWijzigen;
	private JPasswordField pwdHuidigWachtwoord;
	
	public WachtwoordVeranderenGui() {
		bundle = ResourceBundle.getBundle("localization.WachtwoordVeranderenGui");
		
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblWachtwoordWijzigen = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblWachtwoordWijzigen"));
		
		JLabel lblUsername = new JLabel(bundle.getString("lblUsername"));
		lblUsername.setForeground(Color.WHITE);
		
		JLabel lblWachtwoord = new JLabel(bundle.getString("lblWachtwoord"));
		lblWachtwoord.setForeground(Color.WHITE);
		
		JLabel lblHerhaalWachtwoord = new JLabel(bundle.getString("lblHerhaalWachtwoord"));
		lblHerhaalWachtwoord.setForeground(Color.WHITE);
		
		pwdHerhaaldWachtwoord = new JPasswordField();
		
		pwdNieuwwachtwoord = new JPasswordField();
		
		JLabel lblGebruikersnaam = new JLabel("");
		lblGebruikersnaam.setText(Login.getCurrentUser());
		
		lblGebruikersnaam.setForeground(Color.WHITE);
		
		btnWijzigen = new JButton(bundle.getString("btnWijzigen"));
		btnWijzigen.addActionListener(new MenuItemHandler());
		
		JLabel lblHuidigWachtwoord = new JLabel(bundle.getString("lblHuidigWachtwoord"));
		lblHuidigWachtwoord.setForeground(Color.WHITE);
		
		pwdHuidigWachtwoord = new JPasswordField();
		
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
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsername)
									.addGap(69)
									.addComponent(lblGebruikersnaam))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(75)
									.addComponent(btnWijzigen))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHerhaalWachtwoord)
										.addComponent(lblWachtwoord)
										.addComponent(lblHuidigWachtwoord, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(pwdHuidigWachtwoord, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(pwdNieuwwachtwoord)
											.addComponent(pwdHerhaaldWachtwoord, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))))))
					.addContainerGap(132, Short.MAX_VALUE))
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
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHuidigWachtwoord)
						.addComponent(pwdHuidigWachtwoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(56, Short.MAX_VALUE))
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
				String huidigWachtwoord = new String(pwdHuidigWachtwoord.getPassword());
				String nieuwWachtwoord = new String(pwdNieuwwachtwoord.getPassword());
				String herhaalwachtwwoord = new String(pwdHerhaaldWachtwoord.getPassword());
				
				String databasePassword = LoginDao.getWachtwoord(Login.getCurrentUser());
				
				
				
				
				if (!nieuwWachtwoord.isEmpty() && !herhaalwachtwwoord.isEmpty() && !huidigWachtwoord.isEmpty()){
					try {
						if (databasePassword.equals(DualHash.hashString(huidigWachtwoord))){
						
						if (nieuwWachtwoord.equals(herhaalwachtwwoord)){
							try {
								LoginDao.updateWachtwoord(DualHash.hashString(nieuwWachtwoord));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(new JFrame(), "Uw wachtwoord is aangepast!");
							close();
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "De wachtwoorden komen niet overeen!");
							pwdNieuwwachtwoord.setText("");
							pwdHerhaaldWachtwoord.setText("");
						}
						} 
						else {
							JOptionPane.showMessageDialog(new JFrame(), "Het huidige wachtwoord is fout.");
							pwdHuidigWachtwoord.setText("");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Gelieve alle velden in te vullen!");
				}
			}
		}
	}
}
