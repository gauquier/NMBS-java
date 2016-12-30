package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

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
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();
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
	private boolean checkvoornaam=true, checkachternaam=true, checkstraat=true, checkhuisnr=true, checkbus=true, checkgemeente=true, checkpostcode=true, checkemail=true;

	public GebruikerToevoegenGui() {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

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

		this.txtVoornaam = new JTextField();
		this.txtVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtVoornaam.setColumns(10);

		this.txtAchternaam = new JTextField();
		this.txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtAchternaam.setColumns(10);

		this.txtStraat = new JTextField();
		this.txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtStraat.setColumns(10);

		this.txtHuisnr = new JTextField();
		this.txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtHuisnr.setColumns(10);

		this.txtBus = new JTextField();
		this.txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtBus.setColumns(10);

		this.txtGemeente = new JTextField();
		this.txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtGemeente.setColumns(10);

		this.txtPostcode = new JTextField();
		this.txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtPostcode.setColumns(10);

		this.btnToevoegen = new JButton("Toevoegen");
		this.btnToevoegen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnToevoegen.setBackground(Color.ORANGE);
		this.btnToevoegen.addActionListener(new MenuItemHandler());

		this.lblUsername = new JLabel("Username*:");
		this.lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblUsername.setForeground(Color.WHITE);

		this.lblPassword = new JLabel("Password*:");
		this.lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblPassword.setForeground(Color.WHITE);

		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblEmail.setForeground(Color.WHITE);

		this.txtUsername = new JTextField();
		this.txtUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtUsername.setColumns(10);

		this.txtEmail = new JTextField();
		this.txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtEmail.setColumns(10);

		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle("Gebruiker toevoegen");
		lblMedewerkerToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.radiobuttons = new ButtonGroup();
		this.rbtnAdmin = new JRadioButton("Administrator");
		this.rbtnAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.rbtnAdmin.setForeground(Color.WHITE);

		this.rbtnUser = new JRadioButton("Medewerker");
		this.rbtnUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.rbtnUser.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.rbtnUser.setForeground(Color.WHITE);

		this.radiobuttons.add(this.rbtnAdmin);
		this.radiobuttons.add(this.rbtnUser);
		this.rbtnUser.setSelected(true);

		JLabel label = new JLabel("Type*:");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);

		JLabel label_1 = new JLabel("* Verplichte velden");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);

		this.txtPassword = new JPasswordField();
		this.txtPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblVoornaamError = new JLabel("");
		this.lblVoornaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblVoornaamError.setForeground(Color.RED);

		this.lblAchternaamError = new JLabel("");
		this.lblAchternaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblAchternaamError.setForeground(Color.RED);

		this.lblStraatError = new JLabel("");
		this.lblStraatError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblStraatError.setForeground(Color.RED);

		this.lblHuisnrError = new JLabel("");
		this.lblHuisnrError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblHuisnrError.setForeground(Color.RED);

		this.lblBusError = new JLabel("");
		this.lblBusError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblBusError.setForeground(Color.RED);

		this.lblGemeenteError = new JLabel("");
		this.lblGemeenteError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblGemeenteError.setForeground(Color.RED);

		this.lblPostcodeError = new JLabel("");
		this.lblPostcodeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblPostcodeError.setForeground(Color.RED);

		this.lblEmailError = new JLabel("");
		this.lblEmailError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblEmailError.setForeground(Color.RED);

		this.lblUsernameError = new JLabel("");
		this.lblUsernameError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblUsernameError.setForeground(Color.RED);
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
							.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
							.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
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
														.addComponent(lblUsernameError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														.addComponent(lblStraatError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														.addComponent(lblAchternaamError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														.addComponent(lblGemeenteError, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
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
							.addPreferredGap(ComponentPlacement.RELATED, 528, Short.MAX_VALUE)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblMedewerkerToevoegen)
					.addPreferredGap(ComponentPlacement.RELATED)
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
			// refresh();

			if (e.getSource() == GebruikerToevoegenGui.this.btnToevoegen) {
				GebruikerToevoegenGui.this.lblVoornaamError.setText("");
				GebruikerToevoegenGui.this.txtVoornaam.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblAchternaamError.setText("");
				GebruikerToevoegenGui.this.txtAchternaam.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblStraatError.setText("");
				GebruikerToevoegenGui.this.txtStraat.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblHuisnrError.setText("");
				GebruikerToevoegenGui.this.txtHuisnr.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblGemeenteError.setText("");
				GebruikerToevoegenGui.this.txtGemeente.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblPostcodeError.setText("");
				GebruikerToevoegenGui.this.txtPostcode.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblEmailError.setText("");
				GebruikerToevoegenGui.this.txtEmail.setBorder(GebruikerToevoegenGui.this.border);
				GebruikerToevoegenGui.this.lblBusError.setText("");
				GebruikerToevoegenGui.this.txtBus.setBorder(GebruikerToevoegenGui.this.border);
				checkbus = true;

				if (!GebruikerToevoegenGui.this.txtVoornaam.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtAchternaam.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtPassword.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtUsername.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtStraat.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtGemeente.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtPostcode.getText().isEmpty()
						&& !GebruikerToevoegenGui.this.txtHuisnr.getText().isEmpty()) {

					String username = GebruikerToevoegenGui.this.txtUsername.getText().trim();
					if (GebruikerToevoegenGui.this.loginDao.checkUsername(username) > 0) {
						JOptionPane.showMessageDialog(new JFrame(), "Deze gebruiker bestaat al.");
						GebruikerToevoegenGui.this.txtPassword.setText("");
						return;
					} else {
						if (!Validation.checkFirstName(GebruikerToevoegenGui.this.txtVoornaam.getText())) {
							GebruikerToevoegenGui.this.lblVoornaamError
									.setText("Gelieve een juist voornaam in te vullen!");
							GebruikerToevoegenGui.this.txtVoornaam.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkvoornaam = false;
						}
						else {
							checkvoornaam=true;
						}
						if (!Validation.checkLastName(GebruikerToevoegenGui.this.txtAchternaam.getText())) {
							GebruikerToevoegenGui.this.lblAchternaamError
									.setText("Gelieve een juist achternaam in te vullen!");
							GebruikerToevoegenGui.this.txtAchternaam.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkachternaam = false;
						}
						else {
							checkachternaam=true;
						}
						if (!Validation.checkAlphabetical(GebruikerToevoegenGui.this.txtStraat.getText())) {
							GebruikerToevoegenGui.this.lblStraatError.setText("Gelieve een juist straat in te vullen!");
							GebruikerToevoegenGui.this.txtStraat.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkstraat = false;
						}
						else {
							checkstraat=true;
						}
						if (!Validation.checkHouseNumber(GebruikerToevoegenGui.this.txtHuisnr.getText())) {
							GebruikerToevoegenGui.this.lblHuisnrError
									.setText("Gelieve een juist huisnummer in te vullen!");
							GebruikerToevoegenGui.this.txtHuisnr.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkhuisnr = false;
						}
						else {
							checkhuisnr=true;
						}
						if (!Validation.checkAlphabetical(GebruikerToevoegenGui.this.txtGemeente.getText())) {
							GebruikerToevoegenGui.this.lblGemeenteError
									.setText("Gelieve een juiste gemeente in te vullen!");
							GebruikerToevoegenGui.this.txtGemeente.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkgemeente = false;
						}else {
							checkgemeente=true;
						}
						if (!Validation.checkPostalCode(GebruikerToevoegenGui.this.txtPostcode.getText())) {
							GebruikerToevoegenGui.this.lblPostcodeError
									.setText("Gelieve een juiste postocde in te vullen!");
							GebruikerToevoegenGui.this.txtPostcode.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkpostcode = false;
						}else {
							checkpostcode=true;
						}
						if (!GebruikerToevoegenGui.this.txtEmail.getText().isEmpty()
								&& !Validation.checkEmail(GebruikerToevoegenGui.this.txtEmail.getText())) {
							GebruikerToevoegenGui.this.lblEmailError
									.setText("Gelieve een juist emailadres in te vullen!");
							GebruikerToevoegenGui.this.txtEmail.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkemail = false;
						}else {
							checkemail=true;
						}
						if (!GebruikerToevoegenGui.this.txtBus.getText().isEmpty()
								&& !Validation.checkBoxNumber(GebruikerToevoegenGui.this.txtBus.getText())) {
							GebruikerToevoegenGui.this.lblBusError.setText("Gelieve een juiste bus in te vullen!");
							GebruikerToevoegenGui.this.txtBus.setBorder(GebruikerToevoegenGui.this.bordererror);
							checkbus = false;
						}else {
							checkbus=true;
						}
						if (checkvoornaam == false || checkachternaam == false || checkstraat == false || checkhuisnr == false || checkgemeente == false || checkpostcode == false || checkemail == false || checkbus == false){
							return;
						}
				
							try {
								GebruikerToevoegenGui.this.login = new Login(
										GebruikerToevoegenGui.this.txtUsername.getText().trim(),
										DualHash.hashString(GebruikerToevoegenGui.this.txtPassword.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							GebruikerToevoegenGui.this.login.toString();

							if (!GebruikerToevoegenGui.this.txtBus.getText().isEmpty()) {
								GebruikerToevoegenGui.this.adres = new Adres(
										GebruikerToevoegenGui.this.txtStraat.getText().trim(),
										Integer.parseInt(GebruikerToevoegenGui.this.txtHuisnr.getText()),
										GebruikerToevoegenGui.this.txtGemeente.getText().trim(),
										Integer.parseInt(GebruikerToevoegenGui.this.txtPostcode.getText()),
										GebruikerToevoegenGui.this.txtBus.getText());
							} else {
								GebruikerToevoegenGui.this.adres = new Adres(
										GebruikerToevoegenGui.this.txtStraat.getText().trim(),
										Integer.parseInt(GebruikerToevoegenGui.this.txtHuisnr.getText()),
										GebruikerToevoegenGui.this.txtGemeente.getText().trim(),
										Integer.parseInt(GebruikerToevoegenGui.this.txtPostcode.getText()), "");
							}
							GebruikerToevoegenGui.this.adres.toString();

							GebruikerToevoegenGui.this.persoon = new Persoon(
									GebruikerToevoegenGui.this.txtVoornaam.getText().trim(),
									GebruikerToevoegenGui.this.txtAchternaam.getText().trim(),
									GebruikerToevoegenGui.this.txtEmail.getText().trim(),
									GebruikerToevoegenGui.this.adres);
							GebruikerToevoegenGui.this.persoon.toString();

							int rolid;

							if (GebruikerToevoegenGui.this.rbtnAdmin.isSelected()) {
								rolid = 1;
							} else {
								rolid = 2;
							}

							GebruikerToevoegenGui.this.rol = new Rol(rolid);
							GebruikerToevoegenGui.this.rol.setRolId(rolid);
							MedewerkerDAO.addMedewerker(GebruikerToevoegenGui.this.login,
									GebruikerToevoegenGui.this.persoon, GebruikerToevoegenGui.this.rol,
									GebruikerToevoegenGui.this.adres);
							JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is toegevoegd!");
							if(huidigeRol==1){
								AdminGui.setHuidigeKeuze(new GebruikerBewerkenGui());
							} else {
								MedewerkerGui.setHuidigeKeuze(new GebruikerBewerkenGui());
							}
							
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}
		}
	}
}