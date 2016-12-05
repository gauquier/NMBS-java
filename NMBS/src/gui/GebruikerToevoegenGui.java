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
import javax.swing.border.Border;

import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.PersoonDao;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;
import source.Validation;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JRadioButton;

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
	private JTextField txtPassword = new JTextField();
	private JTextField txtUsername;
	private JTextField txtEmail;
	private Adres adres;
	private Login login;
	private LoginDao loginDao= new LoginDao();
	private Persoon persoon;
	private Rol rol;
	private List<Persoon> mijnpersonen;
	private ButtonGroup radiobuttons;
	private JRadioButton rbtnAdmin;
	private JRadioButton rbtnUser;
	private JLabel lblVoornaamError;
	private JLabel lblAchternaamError;
	private JLabel lblStraatError;
	private JLabel lblHuisnrError;
	private JLabel lblGemeenteError;
	private JLabel lblPostcodeError;
	private JLabel lblEmailError;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private JLabel lblBusError;
	private JLabel lblUsernameError;


	public GebruikerToevoegenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVoornaam = new JLabel("Voornaam*:");
		lblVoornaam.setForeground(Color.WHITE);

		JLabel lblAchternaam = new JLabel("Achternaam*:");
		lblAchternaam.setForeground(Color.WHITE);

		JLabel lblStraat = new JLabel("Straat*:");
		lblStraat.setForeground(Color.WHITE);

		JLabel lblHuisnummer = new JLabel("Huisnummer*:");
		lblHuisnummer.setForeground(Color.WHITE);

		JLabel lblGemeente = new JLabel("Gemeente*:");
		lblGemeente.setForeground(Color.WHITE);

		JLabel lblPostcode = new JLabel("Postcode*:");
		lblPostcode.setForeground(Color.WHITE);

		JLabel lblBus = new JLabel("Bus:");
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

		btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnToevoegen.setBackground(Color.ORANGE);
		btnToevoegen.addActionListener(new MenuItemHandler());

		lblUsername = new JLabel("Username*:");
		lblUsername.setForeground(Color.WHITE);

		lblPassword = new JLabel("Password*:");
		lblPassword.setForeground(Color.WHITE);

		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);

		//txtUsername = new JTextField();
		txtPassword.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle("Medewerker toevoegen");
		lblMedewerkerToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 14));

		radiobuttons = new ButtonGroup();
		rbtnAdmin = new JRadioButton("Administrator");
		rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnAdmin.setForeground(Color.WHITE);

		rbtnUser = new JRadioButton("Medewerker");
		rbtnUser.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnUser.setForeground(Color.WHITE);

		radiobuttons.add(rbtnAdmin);
		radiobuttons.add(rbtnUser);
		rbtnUser.setSelected(true);

		JLabel label = new JLabel("Type*:");
		label.setForeground(Color.WHITE);

		JLabel label_1 = new JLabel("* Verplichte velden");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_1.setForeground(Color.WHITE);
		
		lblVoornaamError = new JLabel("");
		lblVoornaamError.setForeground(Color.RED);
		
		lblAchternaamError = new JLabel("");
		lblAchternaamError.setForeground(Color.RED);
		
		lblStraatError = new JLabel("");
		lblStraatError.setForeground(Color.RED);
		
		lblHuisnrError = new JLabel("");
		lblHuisnrError.setForeground(Color.RED);
		
		lblGemeenteError = new JLabel("");
		lblGemeenteError.setForeground(Color.RED);
		
		lblPostcodeError = new JLabel("");
		lblPostcodeError.setForeground(Color.RED);
		
		lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);
		
		lblBusError = new JLabel("");
		lblBusError.setForeground(Color.RED);
		
		lblUsernameError = new JLabel("");
		lblUsernameError.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rbtnAdmin)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rbtnUser)))
							.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
							.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(174))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtBus, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(txtHuisnr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBusError)
												.addComponent(lblHuisnrError)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblPostcodeError))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtEmail, Alignment.LEADING)
												.addComponent(txtGemeente, Alignment.LEADING)
												.addComponent(txtPassword, Alignment.LEADING)
												.addComponent(txtUsername, Alignment.LEADING)
												.addComponent(txtStraat, Alignment.LEADING)
												.addComponent(txtAchternaam, Alignment.LEADING)
												.addComponent(txtVoornaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEmailError)
												.addComponent(lblGemeenteError)
												.addComponent(lblStraatError)
												.addComponent(lblAchternaamError)
												.addComponent(lblVoornaamError)
												.addComponent(lblUsernameError)))))
								.addComponent(lblMedewerkerToevoegen))
							.addContainerGap(452, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMedewerkerToevoegen)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVoornaam)
								.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVoornaamError))
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
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBus)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBusError)))
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
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblUsernameError)))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmailError))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(rbtnAdmin)
								.addComponent(rbtnUser))
							.addGap(18)
							.addComponent(label_1)))
					.addContainerGap(34, Short.MAX_VALUE))
		);

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

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
						&& !txtHuisnr.getText().isEmpty() && !txtGemeente.getText().isEmpty()
						&& !txtPostcode.getText().isEmpty() && !txtHuisnr.getText().isEmpty()) {
				
					String username = txtUsername.getText().trim();
					if(loginDao.checkUsername(username) > 0){
						JOptionPane.showMessageDialog(new JFrame(), "Deze gebruikernaam bestaat al.");
						txtPassword.setText("");
						return;
					}
					else {
						if (!Validation.checkFirstName(txtVoornaam.getText())){
							lblVoornaamError.setText("Gelieve een juist voornaam in te vullen!");
							txtVoornaam.setBorder(bordererror);
						}
						if (!Validation.checkLastName(txtAchternaam.getText())){
							lblAchternaamError.setText("Gelieve een juist achternaam in te vullen!");
							txtAchternaam.setBorder(bordererror);
						}
						if (!Validation.checkAlphabetical(txtStraat.getText())){
							lblStraatError.setText("Gelieve een juist straat in te vullen!");
							txtStraat.setBorder(bordererror);
						}
						if (!Validation.checkHouseNumber(txtHuisnr.getText())){
							lblHuisnrError.setText("Gelieve een juist huisnummer in te vullen!");
							txtHuisnr.setBorder(bordererror);
						}
						if (!Validation.checkAlphabetical(txtGemeente.getText())){
							lblGemeenteError.setText("Gelieve een juiste gemeente in te vullen!");
							txtGemeente.setBorder(bordererror);
						}
						if (!Validation.checkPostalCode(txtPostcode.getText())){
							lblPostcodeError.setText("Gelieve een juiste postocde in te vullen!");
							txtPostcode.setBorder(bordererror);
						}
						if (!txtEmail.getText().isEmpty() && !Validation.checkEmail(txtEmail.getText())){
							lblEmailError.setText("Gelieve een juist emailadres in te vullen!");
							txtEmail.setBorder(bordererror);
						}
						if (!txtBus.getText().isEmpty() && !Validation.checkBoxNumber(txtBus.getText())){
							lblBusError.setText("Gelieve een juiste bus in te vullen!");
							txtBus.setBorder(bordererror);
						}
						if (!Validation.checkUsername(txtUsername.getText())){
							lblUsernameError.setText("Een username mag niet enkel nummers bevatten!");
							txtUsername.setBorder(bordererror);
						}
						else{
							login = new Login(txtUsername.getText().trim(), txtPassword.getText().trim(),
									txtEmail.getText().trim());
							login.toString();
	
							if (!txtBus.getText().isEmpty()) {
								adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()),
										txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()),
										Integer.parseInt(txtBus.getText()));
							} else {
								adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()),
										txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()), 0);
							}
							adres.toString();
	
							persoon = new Persoon(txtVoornaam.getText().trim(), txtAchternaam.getText().trim(),
									txtEmail.getText().trim(), persoon.getAdres());
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
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}
		}
	}
}