package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;
import source.Validation;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class AdminToevoegenGui extends JPanel {
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
	private JTextField txtPassword;
	private JTextField txtEmail;
	private Adres adres;
	private Login login;
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

	
	public AdminToevoegenGui()
	{
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblVoornaam = new JLabel("Voornaam");
		lblVoornaam.setForeground(Color.WHITE);
		
		JLabel lblAchternaam = new JLabel("Achternaam");
		lblAchternaam.setForeground(Color.WHITE);
		
		JLabel lblStraat = new JLabel("Straat");
		lblStraat.setForeground(Color.WHITE);
		
		JLabel lblHuisnummer = new JLabel("Huisnummer");
		lblHuisnummer.setForeground(Color.WHITE);
		
		JLabel lblGemeente = new JLabel("Gemeente");
		lblGemeente.setForeground(Color.WHITE);
		
		JLabel lblPostcode = new JLabel("Postcode");
		lblPostcode.setForeground(Color.WHITE);
		
		JLabel lblBus = new JLabel("Bus");
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
		btnToevoegen.addActionListener(new MenuItemHandler());
		
		lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		JLabel lblAdminToevoegen = DefaultComponentFactory.getInstance().createTitle("Admin toevoegen");
		
		lblVoornaamerror = new JLabel("");
		lblVoornaamerror.setForeground(Color.RED);
		
		lblAchternaamerror = new JLabel("");
		lblAchternaamerror.setForeground(Color.RED);
		
		lblStraaterror = new JLabel("");
		lblStraaterror.setForeground(Color.RED);
		
		lblHuisnrerror = new JLabel("");
		lblHuisnrerror.setForeground(Color.RED);
		
		lblBusError = new JLabel("");
		lblBusError.setForeground(Color.RED);
		
		lblGemeenteerror = new JLabel("");
		lblGemeenteerror.setForeground(Color.RED);
		
		lblPostcodeerror = new JLabel("");
		lblPostcodeerror.setForeground(Color.RED);
		
		lblemailerror = new JLabel("");
		lblemailerror.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblUsername)
								.addComponent(lblPostcode)
								.addComponent(lblGemeente)
								.addComponent(lblBus)
								.addComponent(lblHuisnummer)
								.addComponent(lblAchternaam)
								.addComponent(lblStraat)
								.addComponent(lblAdminToevoegen)
								.addComponent(lblVoornaam)
								.addComponent(lblEmail))
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtAchternaam, Alignment.LEADING)
								.addComponent(txtVoornaam, Alignment.LEADING)
								.addComponent(txtStraat, Alignment.LEADING)
								.addComponent(txtHuisnr, Alignment.LEADING)
								.addComponent(txtBus, Alignment.LEADING)
								.addComponent(txtGemeente, Alignment.LEADING)
								.addComponent(txtPostcode, Alignment.LEADING)
								.addComponent(txtPassword, Alignment.LEADING)
								.addComponent(txtUsername, Alignment.LEADING)
								.addComponent(txtEmail, Alignment.LEADING))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPostcodeerror)
										.addComponent(lblBusError)
										.addComponent(lblHuisnrerror)
										.addComponent(lblStraaterror)
										.addComponent(lblAchternaamerror)
										.addComponent(lblVoornaamerror)
										.addComponent(lblGemeenteerror)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblemailerror))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(btnToevoegen)))
					.addContainerGap(490, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(7, Short.MAX_VALUE)
					.addComponent(lblAdminToevoegen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoornaam)
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVoornaamerror))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAchternaam)
						.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAchternaamerror))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStraat)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblStraaterror)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHuisnummer)
						.addComponent(lblHuisnrerror))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBus)
						.addComponent(lblBusError))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGemeente)
						.addComponent(lblGemeenteerror))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPostcode)
						.addComponent(lblPostcodeerror))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(lblemailerror))
					.addGap(18)
					.addComponent(btnToevoegen)
					.addGap(96))
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
						adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()), txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()), txtBus.getText().trim().charAt(0));
						adres.toString();
						persoon = new Persoon(txtVoornaam.getText().trim(), txtAchternaam.getText().trim(), txtEmail.getText().trim(), persoon.getAdres());
						persoon.toString();
						int rolid = 1;
						rol = new Rol(rolid);
						rol.setRolId(rolid);
						MedewerkerDAO.addMedewerker(login, persoon, rol, adres);
						close();
						JOptionPane.showMessageDialog(new JFrame(),"Medewerker is toegevoegd!");
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Please fill in all required fields!");
				}
			}
		}		    	
    }
}
