package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class GebruikerToevoegenGui extends JPanel{
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
	
	public GebruikerToevoegenGui()
	{
		setBackground(new Color(0, 191, 255));
		
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
		
		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle("Medewerker toevoegen");
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
								.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(txtGemeente)
											.addComponent(txtPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addComponent(txtAchternaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addComponent(txtVoornaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
											.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtUsername, Alignment.LEADING)
												.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
											.addComponent(txtStraat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
										.addGap(30)
										.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
										.addGap(39))
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtHuisnr, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(txtBus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))))))
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
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAchternaam)
						.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStraat)
						.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHuisnummer)
						.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtBus, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBus, Alignment.TRAILING))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGemeente)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
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
				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty() && !txtStraat.getText().isEmpty() && !txtHuisnr.getText().isEmpty()&& !txtBus.getText().isEmpty() 
						&& !txtGemeente.getText().isEmpty()&& !txtPostcode.getText().isEmpty() && !txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && !txtEmail.getText().isEmpty()){
					
					login = new Login(txtUsername.getText().trim(), txtPassword.getText().trim(), txtEmail.getText().trim());
					login.toString();
					adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()), txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()), txtBus.getText().trim().charAt(0));
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
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Please fill in all required fields!");
				}
			}
		}		    	
    }
}
