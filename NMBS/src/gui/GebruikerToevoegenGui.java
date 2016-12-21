package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.List;

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
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVoornaam = new JLabel("Voornaam*:");
		lblVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVoornaam.setForeground(Color.WHITE);

		JLabel lblAchternaam = new JLabel("Achternaam*:");
		lblAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAchternaam.setForeground(Color.WHITE);

		JLabel lblStraat = new JLabel("Straat*:");
		lblStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStraat.setForeground(Color.WHITE);

		JLabel lblHuisnummer = new JLabel("Huisnummer*:");
		lblHuisnummer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuisnummer.setForeground(Color.WHITE);

		JLabel lblGemeente = new JLabel("Gemeente*:");
		lblGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGemeente.setForeground(Color.WHITE);

		JLabel lblPostcode = new JLabel("Postcode*:");
		lblPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPostcode.setForeground(Color.WHITE);

		JLabel lblBus = new JLabel("Bus:");
		lblBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBus.setForeground(Color.WHITE);

		txtVoornaam = new JTextField();
		txtVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtVoornaam.setColumns(10);

		txtAchternaam = new JTextField();
		txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtAchternaam.setColumns(10);

		txtStraat = new JTextField();
		txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtStraat.setColumns(10);

		txtHuisnr = new JTextField();
		txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtHuisnr.setColumns(10);

		txtBus = new JTextField();
		txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtBus.setColumns(10);

		txtGemeente = new JTextField();
		txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtGemeente.setColumns(10);

		txtPostcode = new JTextField();
		txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPostcode.setColumns(10);

		btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.setFont(new Font("Dialog", Font.BOLD, 20));
		btnToevoegen.setBackground(Color.ORANGE);
		btnToevoegen.addActionListener(new MenuItemHandler());

		lblUsername = new JLabel("Username*:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUsername.setForeground(Color.WHITE);

		lblPassword = new JLabel("Password*:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPassword.setForeground(Color.WHITE);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmail.setForeground(Color.WHITE);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtUsername.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtEmail.setColumns(10);

		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle("Gebruiker toevoegen");
		lblMedewerkerToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		radiobuttons = new ButtonGroup();
		rbtnAdmin = new JRadioButton("Administrator");
		rbtnAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnAdmin.setForeground(Color.WHITE);

		rbtnUser = new JRadioButton("Medewerker");
		rbtnUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rbtnUser.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnUser.setForeground(Color.WHITE);

		radiobuttons.add(rbtnAdmin);
		radiobuttons.add(rbtnUser);
		rbtnUser.setSelected(true);

		JLabel label = new JLabel("Type*:");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);

		JLabel label_1 = new JLabel("* Verplichte velden");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblVoornaamError = new JLabel("");
		lblVoornaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVoornaamError.setForeground(Color.RED);

		lblAchternaamError = new JLabel("");
		lblAchternaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAchternaamError.setForeground(Color.RED);

		lblStraatError = new JLabel("");
		lblStraatError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStraatError.setForeground(Color.RED);

		lblHuisnrError = new JLabel("");
		lblHuisnrError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuisnrError.setForeground(Color.RED);

		lblBusError = new JLabel("");
		lblBusError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBusError.setForeground(Color.RED);

		lblGemeenteError = new JLabel("");
		lblGemeenteError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGemeenteError.setForeground(Color.RED);

		lblPostcodeError = new JLabel("");
		lblPostcodeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPostcodeError.setForeground(Color.RED);

		lblEmailError = new JLabel("");
		lblEmailError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmailError.setForeground(Color.RED);

		lblUsernameError = new JLabel("");
		lblUsernameError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUsernameError.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMedewerkerToevoegen)
							.addContainerGap(522, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVoornaam)
								.addComponent(lblAchternaam)
								.addComponent(lblBus)
								.addComponent(lblPostcode)
								.addComponent(lblHuisnummer)
								.addComponent(lblPassword)
								.addComponent(lblGemeente)
								.addComponent(lblUsername)
								.addComponent(lblStraat)
								.addComponent(lblEmail))
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblBusError)
											.addComponent(lblHuisnrError))
										.addGap(435))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(txtVoornaam, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtAchternaam, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblEmailError)
												.addGap(44))
											.addComponent(txtStraat, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtGemeente, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
													.addComponent(txtPassword, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(lblUsernameError)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblGemeenteError)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
															.addComponent(lblStraatError)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(lblVoornaamError, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
																	.addGap(44))
																.addComponent(lblAchternaamError))))))
											.addComponent(txtEmail, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
										.addGap(272))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtPostcode, 0, 0, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(70)
												.addComponent(lblPostcodeError)))
										.addContainerGap(448, Short.MAX_VALUE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rbtnUser)
									.addGap(18)
									.addComponent(rbtnAdmin)))
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(553)
					.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMedewerkerToevoegen)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVoornaam)
								.addComponent(lblVoornaamError)
								.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAchternaam)
								.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAchternaamError))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStraat)
								.addComponent(lblStraatError))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblHuisnummer)
									.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblHuisnrError))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblBus)
									.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblBusError))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGemeente)
								.addComponent(lblGemeenteError))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPostcode)
								.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPostcodeError))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsernameError)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmailError)
							.addGap(8)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblEmail))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(rbtnUser)
						.addComponent(rbtnAdmin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addGap(18)
					.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(8, Short.MAX_VALUE))
		);

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
						JOptionPane.showMessageDialog(new JFrame(), "Deze gebruiker bestaat al.");
						txtPassword.setText("");
						return;
					} else {
						if (!Validation.checkFirstName(txtVoornaam.getText())) {
							lblVoornaamError.setText("Gelieve een juist voornaam in te vullen!");
							txtVoornaam.setBorder(bordererror);
						}
						if (!Validation.checkLastName(txtAchternaam.getText())) {
							lblAchternaamError.setText("Gelieve een juist achternaam in te vullen!");
							txtAchternaam.setBorder(bordererror);
						}
						if (!Validation.checkAlphabetical(txtStraat.getText())) {
							lblStraatError.setText("Gelieve een juist straat in te vullen!");
							txtStraat.setBorder(bordererror);
						}
						if (!Validation.checkHouseNumber(txtHuisnr.getText())) {
							lblHuisnrError.setText("Gelieve een juist huisnummer in te vullen!");
							txtHuisnr.setBorder(bordererror);
						}
						if (!Validation.checkAlphabetical(txtGemeente.getText())) {
							lblGemeenteError.setText("Gelieve een juiste gemeente in te vullen!");
							txtGemeente.setBorder(bordererror);
						}
						if (!Validation.checkPostalCode(txtPostcode.getText())) {
							lblPostcodeError.setText("Gelieve een juiste postocde in te vullen!");
							txtPostcode.setBorder(bordererror);
						}
						if (!txtEmail.getText().isEmpty() && !Validation.checkEmail(txtEmail.getText())) {
							lblEmailError.setText("Gelieve een juist emailadres in te vullen!");
							txtEmail.setBorder(bordererror);
						}
						if (!txtBus.getText().isEmpty() && !Validation.checkBoxNumber(txtBus.getText())) {
							lblBusError.setText("Gelieve een juiste bus in te vullen!");
							txtBus.setBorder(bordererror);
						}
						if (!Validation.checkUsername(txtUsername.getText())) {
							lblUsernameError.setText("Een username mag niet enkel nummers bevatten!");
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
							JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is toegevoegd!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}
		}
	}
}