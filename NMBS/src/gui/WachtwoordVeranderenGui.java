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
import dao.MedewerkerDAO;
import source.Login;

public class WachtwoordVeranderenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6274650572795135242L;
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();

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
		lblWachtwoordWijzigen.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		this.btnWijzigen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnWijzigen.addActionListener(new MenuItemHandler());

		JLabel lblHuidigWachtwoord = new JLabel(bundle.getString("lblHuidigWachtwoord"));
		lblHuidigWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuidigWachtwoord.setForeground(Color.WHITE);

		this.pwdHuidigWachtwoord = new JPasswordField();
		this.pwdHuidigWachtwoord.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblHerhaalWachtwoord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblWachtwoord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblHuidigWachtwoord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblUsername))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGebruikersnaam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(pwdHuidigWachtwoord, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addComponent(pwdNieuwwachtwoord)
									.addComponent(pwdHerhaaldWachtwoord))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(150)
							.addComponent(btnWijzigen))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblWachtwoordWijzigen)))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWachtwoordWijzigen)
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername)
						.addComponent(lblGebruikersnaam, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
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
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
								JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wachtwoordAangepast"));
								if(huidigeRol==1){
									AdminGui.setHuidigeKeuze(new HomeGui(false));
								} else {
									MedewerkerGui.setHuidigeKeuze(new HomeGui(false));
								}
							} else {
								JOptionPane.showMessageDialog(new JFrame(), bundle.getString("unmatchingPasswords"));
								WachtwoordVeranderenGui.this.pwdNieuwwachtwoord.setText("");
								WachtwoordVeranderenGui.this.pwdHerhaaldWachtwoord.setText("");
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("wrongCurrentPassword"));
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
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}
