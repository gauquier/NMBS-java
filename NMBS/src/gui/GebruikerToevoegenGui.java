package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.PersoonDao;
import source.Adres;
import source.Login;
import source.Medewerker;
import source.Persoon;
import source.Rol;
import source.Validation;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class GebruikerToevoegenGui extends JPanel {
	private static ResourceBundle bundle;
	
	private JTextField txtVoornaam;
	private JTextField txtAchternaam;
	private JTextField txtStraat;
	private JTextField txtHuisnr;
	private JTextField txtBus;
	private JTextField txtGemeente;
	private JTextField txtPostcode;
	private JButton btnToevoegen;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private Adres adres;
	private Login login;
	private LoginDao loginDao = new LoginDao();
	private Persoon persoon;
	private Rol rol;
	private List<Persoon> mijnpersonen;
	private ButtonGroup radiobuttons;
	private JRadioButton rbtnAdmin;
	private JRadioButton rbtnUser;

	private JPasswordField txtPassword;
	private JLabel lblAchternaamError;
	private JLabel lblStraatError;
	private JLabel lblHuisnrError;
	private JLabel lblBusError;
	private JLabel lblGemeenteError;
	private JLabel lblPostcodeError;
	private JLabel lblEmailError;
	private JLabel lblVoornaamError;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private JLabel lblUsernameError;

	public GebruikerToevoegenGui() {
		bundle = ResourceBundle.getBundle("localization.GebruikerToevoegenGui");
		
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVoornaam = new JLabel(bundle.getString("lblVoornaam"));
		lblVoornaam.setForeground(Color.WHITE);

		JLabel lblAchternaam = new JLabel(bundle.getString("lblAchternaam"));
		lblAchternaam.setForeground(Color.WHITE);

		JLabel lblStraat = new JLabel(bundle.getString("lblStraat"));
		lblStraat.setForeground(Color.WHITE);

		JLabel lblHuisnummer = new JLabel(bundle.getString("lblHuisnummer"));
		lblHuisnummer.setForeground(Color.WHITE);

		JLabel lblGemeente = new JLabel(bundle.getString("lblGemeente"));
		lblGemeente.setForeground(Color.WHITE);

		JLabel lblPostcode = new JLabel(bundle.getString("lblPostcode"));
		lblPostcode.setForeground(Color.WHITE);

		JLabel lblBus = new JLabel(bundle.getString("lblBus"));
		lblBus.setForeground(Color.WHITE);

		txtVoornaam = new JTextField();
		txtVoornaam.setColumns(10);

		txtAchternaam = new JTextField();
		txtAchternaam.setColumns(10);

		txtStraat = new JTextField();
		txtStraat.setColumns(10);

		txtHuisnr = new JTextField();
		txtHuisnr.setColumns(10);

		txtBus = new JTextField();
		txtBus.setColumns(10);

		txtGemeente = new JTextField();
		txtGemeente.setColumns(10);

		txtPostcode = new JTextField();
		txtPostcode.setColumns(10);

		btnToevoegen = new JButton(bundle.getString("btnToevoegen"));
		btnToevoegen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnToevoegen.setBackground(Color.ORANGE);
		btnToevoegen.addActionListener(new MenuItemHandler());

		lblUsername = new JLabel(bundle.getString("lblUsername"));
		lblUsername.setForeground(Color.WHITE);

		lblPassword = new JLabel(bundle.getString("lblPassword"));
		lblPassword.setForeground(Color.WHITE);

		lblEmail = new JLabel(bundle.getString("lblEmail"));
		lblEmail.setForeground(Color.WHITE);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblMedewerkerToevoegen"));
		lblMedewerkerToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 14));

		radiobuttons = new ButtonGroup();
		rbtnAdmin = new JRadioButton(bundle.getString("rbtnAdmin"));
		rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnAdmin.setForeground(Color.WHITE);

		rbtnUser = new JRadioButton(bundle.getString("rbtnUser"));
		rbtnUser.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnUser.setForeground(Color.WHITE);

		radiobuttons.add(rbtnAdmin);
		radiobuttons.add(rbtnUser);
		rbtnUser.setSelected(true);

		JLabel label = new JLabel(bundle.getString("label"));
		label.setForeground(Color.WHITE);

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_1.setForeground(Color.WHITE);

		txtPassword = new JPasswordField();

		lblVoornaamError = new JLabel("");
		lblVoornaamError.setForeground(Color.RED);

		lblAchternaamError = new JLabel("");
		lblAchternaamError.setForeground(Color.RED);

		lblStraatError = new JLabel("");
		lblStraatError.setForeground(Color.RED);

		lblHuisnrError = new JLabel("");
		lblHuisnrError.setForeground(Color.RED);

		lblBusError = new JLabel("");
		lblBusError.setForeground(Color.RED);

		lblGemeenteError = new JLabel("");
		lblGemeenteError.setForeground(Color.RED);

		lblPostcodeError = new JLabel("");
		lblPostcodeError.setForeground(Color.RED);

		lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);

		lblUsernameError = new JLabel("");
		lblUsernameError.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(30).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE).addGap(
										49)
								.addComponent(rbtnUser).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(rbtnAdmin)
								.addPreferredGap(ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
								.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addGap(28))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblVoornaam)
										.addComponent(lblAchternaam).addComponent(lblBus).addComponent(lblPostcode)
										.addComponent(lblHuisnummer).addComponent(lblPassword).addComponent(lblGemeente)
										.addComponent(lblUsername).addComponent(lblStraat)
										.addComponent(lblEmail))
								.addGap(50)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
										.createSequentialGroup()
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
														.createSequentialGroup().addGroup(groupLayout
																.createParallelGroup(Alignment.TRAILING)
																.addComponent(txtEmail, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(txtGemeente, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(txtUsername, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(txtStraat, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(txtAchternaam, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(txtVoornaam, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
																.addComponent(label_1, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
														.addGap(18)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblUsernameError).addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblGemeenteError)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblStraatError)
																				.addGroup(groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(groupLayout
																								.createSequentialGroup()
																								.addComponent(
																										lblVoornaamError,
																										GroupLayout.DEFAULT_SIZE,
																										31,
																										Short.MAX_VALUE)
																								.addGap(44))
																						.addComponent(
																								lblAchternaamError))))))
												.addGroup(Alignment.TRAILING,
														groupLayout.createSequentialGroup()
																.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE,
																		200, Short.MAX_VALUE)
																.addGap(49).addComponent(lblEmailError).addGap(44)))
										.addGap(272))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, 51,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(lblBusError))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, 51,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(lblHuisnrError)))
												.addGap(435))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 52,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lblPostcodeError)
												.addContainerGap(495, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblMedewerkerToevoegen)
								.addContainerGap(563, Short.MAX_VALUE)))));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
						.createSequentialGroup().addGap(
								19)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
								.createSequentialGroup().addComponent(lblMedewerkerToevoegen).addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVoornaam)
										.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVoornaamError))
								.addGap(15)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAchternaam)
												.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAchternaamError))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStraat).addComponent(lblStraatError))
								.addGap(15)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblHuisnummer)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblHuisnrError)))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblBus)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtBus, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBusError)))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGemeente).addComponent(lblGemeenteError))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPostcode)
										.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPostcodeError))
								.addGap(15)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblUsername)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblUsernameError)))
								.addGap(15).addComponent(lblPassword).addGap(24)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup().addComponent(lblEmail).addGap(29))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(17))))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmailError))
										.addGap(60)))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label)
								.addComponent(rbtnUser).addComponent(rbtnAdmin))
						.addGap(11).addComponent(label_1).addGap(32))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap(457, Short.MAX_VALUE)
								.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addGap(44)));

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == btnToevoegen) {
				lblVoornaamError.setText("");
				txtVoornaam.setBorder(border);
				lblAchternaamError.setText("");
				txtAchternaam.setBorder(border);
				lblStraatError.setText("");
				txtStraat.setBorder(border);
				lblHuisnrError.setText("");
				txtHuisnr.setBorder(border);
				lblGemeenteError.setText("");
				txtGemeente.setBorder(border);
				lblPostcodeError.setText("");
				txtPostcode.setBorder(border);
				lblEmailError.setText("");
				txtEmail.setBorder(border);
				lblBusError.setText("");
				txtBus.setBorder(border);

				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty()
						&& !txtPassword.getText().isEmpty() && !txtUsername.getText().isEmpty()
						&& !txtStraat.getText().isEmpty() && !txtGemeente.getText().isEmpty()
						&& !txtPostcode.getText().isEmpty() && !txtHuisnr.getText().isEmpty()) {

					String username = txtUsername.getText().trim();
					if (loginDao.checkUsername(username) > 0) {
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userAlreadyExists"));
						txtPassword.setText("");
						return;
					} else {
						if (!Validation.checkFirstName(txtVoornaam.getText())) {
							lblVoornaamError.setText(bundle.getString("lblVoornaamError"));
							txtVoornaam.setBorder(bordererror);
						}
						if (!Validation.checkLastName(txtAchternaam.getText())) {
							lblAchternaamError.setText(bundle.getString("lblAchternaamError"));
							txtAchternaam.setBorder(bordererror);
						}
						if (!Validation.checkAlphabetical(txtStraat.getText())) {
							lblStraatError.setText(bundle.getString("lblStraatError"));
							txtStraat.setBorder(bordererror);
						}
						if (!Validation.checkHouseNumber(txtHuisnr.getText())) {
							lblHuisnrError.setText(bundle.getString("lblHuisnrError"));
							txtHuisnr.setBorder(bordererror);
						}
						if (!Validation.checkAlphabetical(txtGemeente.getText())) {
							lblGemeenteError.setText(bundle.getString("lblGemeenteError"));
							txtGemeente.setBorder(bordererror);
						}
						if (!Validation.checkPostalCode(txtPostcode.getText())) {
							lblPostcodeError.setText(bundle.getString("lblPostcodeError"));
							txtPostcode.setBorder(bordererror);
						}
						if (!txtEmail.getText().isEmpty() && !Validation.checkEmail(txtEmail.getText())) {
							lblEmailError.setText(bundle.getString("lblEmailError"));
							txtEmail.setBorder(bordererror);
						}
						if (!txtBus.getText().isEmpty() && !Validation.checkBoxNumber(txtBus.getText())) {
							lblBusError.setText(bundle.getString("lblBusError"));
							txtBus.setBorder(bordererror);
						}
						if (!Validation.checkUsername(txtUsername.getText())) {
							lblUsernameError.setText(bundle.getString("lblUsernameError"));
							txtUsername.setBorder(bordererror);
						} else {
							login = new Login(txtUsername.getText().trim(), txtPassword.getText().trim());
							login.toString();

							if (!txtBus.getText().isEmpty()) {
								adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()),
										txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()),
										txtBus.getText());
							} else {
								adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()),
										txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()), "");
							}
							adres.toString();

							persoon = new Persoon(txtVoornaam.getText().trim(), txtAchternaam.getText().trim(),
									txtEmail.getText().trim(), adres);
							persoon.toString();

							int rolid;

							if (rbtnAdmin.isSelected()) {
								rolid = 1;
							} else {
								rolid = 2;
							}

							rol = new Rol(rolid);
							rol.setRolId(rolid);
							MedewerkerDAO.addMedewerker(login, persoon, rol, adres);
							close();
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userAdded"));
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));

				}
			}
		}
	}
}