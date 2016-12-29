package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;
import dao.LoginDao;
import source.Login;

public class WachtwoordVeranderenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6274650572795135242L;

	private static ResourceBundle bundle;

	private JPasswordField pwdHerhaaldWachtwoord;
	private JPasswordField pwdNieuwwachtwoord;
	private JButton btnWijzigen;
	private JPasswordField pwdHuidigWachtwoord;

	public WachtwoordVeranderenGui() {
		bundle = ResourceBundle.getBundle("localization.WachtwoordVeranderenGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblWachtwoordWijzigen = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblWachtwoordWijzigen"));
		lblWachtwoordWijzigen.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblWachtwoordWijzigen.setForeground(new Color(0, 0, 0));

		JLabel lblUsername = new JLabel(bundle.getString("lblUsername"));
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUsername.setForeground(Color.WHITE);

		JLabel lblWachtwoord = new JLabel(bundle.getString("lblWachtwoord"));
		lblWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWachtwoord.setForeground(Color.WHITE);

		JLabel lblHerhaalWachtwoord = new JLabel(bundle.getString("lblHerhaalWachtwoord"));
		lblHerhaalWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHerhaalWachtwoord.setForeground(Color.WHITE);

		this.pwdHerhaaldWachtwoord = new JPasswordField();
		this.pwdHerhaaldWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.pwdNieuwwachtwoord = new JPasswordField();
		this.pwdNieuwwachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblGebruikersnaam = new JLabel("");
		lblGebruikersnaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGebruikersnaam.setText(Login.getCurrentUser());

		lblGebruikersnaam.setForeground(Color.WHITE);

		this.btnWijzigen = new JButton(bundle.getString("btnWijzigen"));
		this.btnWijzigen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.btnWijzigen.addActionListener(new MenuItemHandler());

		JLabel lblHuidigWachtwoord = new JLabel(bundle.getString("lblHuidigWachtwoord"));
		lblHuidigWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuidigWachtwoord.setForeground(Color.WHITE);

		this.pwdHuidigWachtwoord = new JPasswordField();
		this.pwdHuidigWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
								.addGap(29)
								.addComponent(lblWachtwoordWijzigen))
						.addGroup(groupLayout.createSequentialGroup().addGap(69)
								.addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
												groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblHerhaalWachtwoord, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblWachtwoord, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblHuidigWachtwoord, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(lblUsername))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(69)
												.addComponent(lblGebruikersnaam))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(this.pwdHuidigWachtwoord,
																GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
														.addComponent(this.pwdNieuwwachtwoord)
														.addComponent(this.pwdHerhaaldWachtwoord)))))
						.addGroup(groupLayout.createSequentialGroup().addGap(150).addComponent(this.btnWijzigen)))
						.addContainerGap(172, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(24).addComponent(lblWachtwoordWijzigen).addGap(34)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblUsername)
						.addComponent(lblGebruikersnaam))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblHuidigWachtwoord)
						.addComponent(this.pwdHuidigWachtwoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblWachtwoord).addComponent(
						this.pwdNieuwwachtwoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblHerhaalWachtwoord)
						.addComponent(this.pwdHerhaaldWachtwoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(30).addComponent(this.btnWijzigen).addContainerGap(95, Short.MAX_VALUE)));
		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == WachtwoordVeranderenGui.this.btnWijzigen) {
				String huidigWachtwoord = new String(WachtwoordVeranderenGui.this.pwdHuidigWachtwoord.getPassword());
				String nieuwWachtwoord = new String(WachtwoordVeranderenGui.this.pwdNieuwwachtwoord.getPassword());
				String herhaalwachtwwoord = new String(
						WachtwoordVeranderenGui.this.pwdHerhaaldWachtwoord.getPassword());

				String databasePassword = LoginDao.getWachtwoord(Login.getCurrentUser());

				if (!nieuwWachtwoord.isEmpty() && !herhaalwachtwwoord.isEmpty() && !huidigWachtwoord.isEmpty()) {
					try {
						if (databasePassword.equals(DualHash.hashString(huidigWachtwoord))) {

							if (nieuwWachtwoord.equals(herhaalwachtwwoord)) {
								try {
									LoginDao.updateWachtwoord(DualHash.hashString(nieuwWachtwoord));
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(new JFrame(), "Uw wachtwoord is aangepast!");
								WachtwoordVeranderenGui.this.close();
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "De wachtwoorden komen niet overeen!");
								WachtwoordVeranderenGui.this.pwdNieuwwachtwoord.setText("");
								WachtwoordVeranderenGui.this.pwdHerhaaldWachtwoord.setText("");
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Het huidige wachtwoord is fout.");
							WachtwoordVeranderenGui.this.pwdHuidigWachtwoord.setText("");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Gelieve alle velden in te vullen!");
				}
			}
		}
	}
}
