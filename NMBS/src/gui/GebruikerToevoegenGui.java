package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;
import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;
import source.Validation;

public class GebruikerToevoegenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -83102949843216643L;
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

		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle("Medewerker toevoegen");
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
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rbtnUser)
							.addGap(18)
							.addComponent(rbtnAdmin)
							.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
							.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(98))
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
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblVoornaamError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGap(353))
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblUsernameError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
														.addComponent(lblStraatError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
														.addComponent(lblAchternaamError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
														.addComponent(lblGemeenteError, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
													.addGap(111)))
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(lblEmailError, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtHuisnr, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(txtBus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBusError, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblHuisnrError, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE))
									.addGap(100))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPostcodeError)
									.addPreferredGap(ComponentPlacement.RELATED, 462, Short.MAX_VALUE)))
							.addGap(52))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMedewerkerToevoegen)
							.addPreferredGap(ComponentPlacement.RELATED, 549, Short.MAX_VALUE)))
					.addGap(88))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblMedewerkerToevoegen)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoornaam)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVoornaamError)))
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
						.addComponent(lblHuisnummer)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHuisnrError)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBus)
						.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBusError, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUsername)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblUsernameError, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblEmail))
						.addComponent(lblEmailError, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(rbtnUser)
						.addComponent(rbtnAdmin)
						.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(label_1)
					.addGap(91))
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
							try {
								login = new Login(txtUsername.getText().trim(), DualHash.hashString(txtPassword.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
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