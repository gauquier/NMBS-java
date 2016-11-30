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

import Hashing.DualHash;

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
	private JLabel lblVoornaamerror;
	private JLabel lblAchternaamerror;
	private JLabel lblStraaterror;
	private JLabel lblHuisnrerror;
	private JLabel lblBusError;
	private JLabel lblGemeenteerror;
	private JLabel lblPostcodeerror;
	private JLabel lblemailerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private List<Persoon> mijnpersonen;
	private ButtonGroup radiobuttons;
	private JRadioButton rbtnAdmin;
	private JRadioButton rbtnUser;


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
		
		JLabel lblVoornaamerror = new JLabel("");
		lblVoornaamerror.setForeground(Color.RED);
		
		JLabel lblAchternaamerror = new JLabel("");
		lblAchternaamerror.setForeground(Color.RED);
		
		JLabel lblStraaterror = new JLabel("");
		lblStraaterror.setForeground(Color.RED);
		
		JLabel lblHuisnrerror = new JLabel("");
		lblHuisnrerror.setForeground(Color.RED);
		
		JLabel lblBuserror = new JLabel("");
		lblBuserror.setForeground(Color.RED);
		
		JLabel lblGemeenteerror = new JLabel("");
		lblGemeenteerror.setForeground(Color.RED);
		
		JLabel lblPostcodeerror = new JLabel("");
		lblPostcodeerror.setForeground(Color.RED);
		
		JLabel lblEmailerror = new JLabel("");
		lblEmailerror.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMedewerkerToevoegen)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVoornaam)
								.addComponent(lblAchternaam)
								.addComponent(lblBus)
								.addComponent(lblPostcode)
								.addComponent(lblEmail)
								.addComponent(lblHuisnummer)
								.addComponent(lblStraat)
								.addComponent(lblGemeente)
								.addComponent(lblPassword)
								.addComponent(lblUsername))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblPostcodeerror))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(txtGemeente, 165, 165, 165)
											.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addComponent(txtAchternaam, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addComponent(txtVoornaam, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtUsername, Alignment.LEADING)
												.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
												.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
											.addComponent(txtStraat, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblVoornaamerror)
											.addComponent(lblAchternaamerror)
											.addComponent(lblStraaterror)
											.addComponent(lblGemeenteerror)
											.addComponent(lblEmailerror))
										.addGap(107))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtHuisnr, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
											.addComponent(txtBus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblBuserror)
											.addComponent(lblHuisnrerror)))))))
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblMedewerkerToevoegen)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoornaam)
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVoornaamerror))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAchternaam)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAchternaamerror)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStraat)
						.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStraaterror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHuisnummer)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHuisnrerror)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblBuserror))
						.addComponent(lblBus, Alignment.TRAILING))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGemeente)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGemeenteerror))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPostcodeerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(lblEmailerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
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

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnToevoegen){
				lblVoornaamerror.setText("");
				lblAchternaamerror.setText("");
				lblStraaterror.setText("");
				lblHuisnrerror.setText("");
				lblBusError.setText("");
				lblPostcodeerror.setText("");
				lblGemeenteerror.setText("");
				lblemailerror.setText("");
				txtVoornaam.setBorder(border);
				txtAchternaam.setBorder(border);
				txtStraat.setBorder(border);
				txtHuisnr.setBorder(border);
				txtPostcode.setBorder(border);
				txtGemeente.setBorder(border);
				txtEmail.setBorder(border);
				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty() && !txtStraat.getText().isEmpty() && !txtHuisnr.getText().isEmpty()
						&& !txtGemeente.getText().isEmpty()&& !txtPostcode.getText().isEmpty() && !txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && !txtEmail.getText().isEmpty()){
					if (!Validation.checkFirstName(txtVoornaam.getText())){
						lblVoornaamerror.setText("Gelieve een juiste voornaam in te vullen!");
						txtVoornaam.setBorder(bordererror);

					}
					if (!Validation.checkLastName(txtAchternaam.getText())){
						lblAchternaamerror.setText("Gelieve een juiste Achternaam in te vullen!");
						txtAchternaam.setBorder(bordererror);

					}
					if (!Validation.checkAlphabetical(txtStraat.getText())){
						lblStraaterror.setText("Gelieve een bestaande straat in te vullen!");
						txtStraat.setBorder(bordererror);

					}
					if (!Validation.checkHouseNumber(txtHuisnr.getText())){
						lblHuisnrerror.setText("Gelieve een bestaand huisnummer in te vullen!");
						txtHuisnr.setBorder(bordererror);

					}
					if (!Validation.checkBoxNumber(txtBus.getText()) && !txtBus.getText().isEmpty()){
						lblBusError.setText("Gelieve een bestaande bus in te vullen!");
						txtBus.setBorder(bordererror);

					}
					if (!Validation.checkPostalCode(txtPostcode.getText())){
						lblPostcodeerror.setText("Gelieve een bestaande postcode in te vullen!");
						txtPostcode.setBorder(bordererror);

					}
					if (!Validation.checkAlphabetical(txtGemeente.getText())){
						lblGemeenteerror.setText("Gelieve een bestaande gemeente in te vullen!");
						txtGemeente.setBorder(bordererror);

					}
					if (!Validation.checkEmail(txtEmail.getText())){
						lblemailerror.setText("Gelieve een juist email in te vullen!");
						txtEmail.setBorder(bordererror);

					}
					else{
						login = new Login(txtUsername.getText().trim(), txtPassword.getText().trim(), txtEmail.getText().trim());
						login.toString();
						adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()), txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()), Integer.parseInt(txtBus.getText()));
						adres.toString();
						persoon = new Persoon(txtVoornaam.getText().trim(), txtAchternaam.getText().trim(), txtEmail.getText().trim(), persoon.getAdres());
						persoon.toString();
						int rolid = 2;
						rol = new Rol(rolid);
						rol.setRolId(rolid);
						MedewerkerDAO.addMedewerker(login, persoon, rol, adres);
						close();
						JOptionPane.showMessageDialog(new JFrame(),"Medewerker is toegevoegd!");
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Gelieve alle velden in te vullen!");
			// refresh();

			if (e.getSource() == btnToevoegen) {


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
						try {
							login = new Login(txtUsername.getText().trim(), DualHash.hashString(txtPassword.getText().trim()),
									txtEmail.getText().trim());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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

				else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}
		}
	}
}
}}