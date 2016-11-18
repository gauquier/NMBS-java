package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.MedewerkerDAO;
import source.Adres;
import source.Login;
import source.Persoon;
import source.Rol;

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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVoornaam)
								.addComponent(lblAchternaam)
								.addComponent(lblStraat)
								.addComponent(lblHuisnummer)
								.addComponent(lblBus)
								.addComponent(lblGemeente)
								.addComponent(lblPostcode)
								.addComponent(lblUsername)
								.addComponent(lblPassword)
								.addComponent(lblEmail))
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(154)
							.addComponent(btnToevoegen)))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoornaam)
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAchternaam)
							.addGap(18)
							.addComponent(lblStraat)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHuisnummer)
						.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBus)
						.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGemeente)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPostcode)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addComponent(btnToevoegen)
					.addGap(171))
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
					int rolid = 1;
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
