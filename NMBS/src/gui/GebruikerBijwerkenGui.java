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
import source.Medewerker;
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

public class GebruikerBijwerkenGui extends JPanel {
	private JTextField txtVoornaam;
	private JTextField txtAchternaam;
	private JTextField txtStraat;
	private JTextField txtHuisnr;
	private JTextField txtBus;
	private JTextField txtGemeente;
	private JTextField txtPostcode;
	private JButton btnBijwerken;
	private JLabel lblEmail;
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

	private int medewerkerId = 0;
	private int persoonId = 0;
	private int adresId = 0;
	private JLabel lblVoornaamError;
	private JLabel lblAchternaamError;
	private JLabel lblStraatError;
	private JLabel lblHuisnrError;
	private JLabel lblBusError;
	private JLabel lblGemeenteError;
	private JLabel lblPostcodeError;
	private JLabel lblEmailError;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);

	public GebruikerBijwerkenGui(Medewerker m) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		medewerkerId = m.getMedewerkerId();
		persoonId = m.getId();
		adresId = m.getAdres().getAdresId();

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
		txtVoornaam.setText(m.getVoornaam());

		txtAchternaam = new JTextField();
		txtAchternaam.setColumns(10);
		txtAchternaam.setText(m.getAchternaam());

		txtStraat = new JTextField();
		txtStraat.setColumns(10);
		txtStraat.setText(m.getAdres().getStraat());

		txtHuisnr = new JTextField();
		txtHuisnr.setColumns(10);
		txtHuisnr.setText(new Integer(m.getAdres().getHuisnr()).toString());

		txtBus = new JTextField();
		txtBus.setColumns(10);
		txtBus.setText(new Integer(m.getAdres().getBus()).toString());

		txtGemeente = new JTextField();
		txtGemeente.setColumns(10);
		txtGemeente.setText(m.getAdres().getWoonplaats());

		txtPostcode = new JTextField();
		txtPostcode.setColumns(10);
		txtPostcode.setText(new Integer(m.getAdres().getPostcode()).toString());

		btnBijwerken = new JButton("Bijwerken");
		btnBijwerken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBijwerken.setBackground(Color.ORANGE);
		btnBijwerken.addActionListener(new MenuItemHandler());

		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setText(m.getEmail());

		JLabel lblMedewerkerBijwerken = DefaultComponentFactory.getInstance().createTitle("Medewerker bijwerken");
		lblMedewerkerBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 14));

		radiobuttons = new ButtonGroup();
		rbtnAdmin = new JRadioButton("Administrator");
		rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnAdmin.setForeground(Color.WHITE);

		rbtnUser = new JRadioButton("Medewerker");
		rbtnUser.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnUser.setForeground(Color.WHITE);

		radiobuttons.add(rbtnAdmin);
		radiobuttons.add(rbtnUser);

		int rolid = m.getRol().getRolId();

		if (rolid == 1) {
			rbtnAdmin.setSelected(true);
		} else {
			rbtnUser.setSelected(true);
		}

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

		lblBusError = new JLabel("");
		lblBusError.setForeground(Color.RED);

		lblGemeenteError = new JLabel("");
		lblGemeenteError.setForeground(Color.RED);

		lblPostcodeError = new JLabel("");
		lblPostcodeError.setForeground(Color.RED);

		lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout
												.createSequentialGroup().addComponent(label_1, GroupLayout.DEFAULT_SIZE,
														130, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblVoornaam).addComponent(lblAchternaam)
														.addComponent(lblBus).addComponent(lblPostcode)
														.addComponent(lblHuisnummer).addComponent(lblGemeente)
														.addComponent(lblStraat)
														.addComponent(lblEmail))
												.addGap(50)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
														groupLayout.createSequentialGroup().addGroup(groupLayout
																.createParallelGroup(Alignment.TRAILING)
																.addComponent(txtEmail, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
																.addComponent(txtVoornaam, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
																.addComponent(txtAchternaam, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
																.addComponent(txtStraat, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
																.addComponent(txtGemeente, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
																.addGap(18)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblVoornaamError)
																		.addComponent(lblAchternaamError)
																		.addComponent(lblStraatError)
																		.addComponent(lblGemeenteError)
																		.addComponent(lblEmailError))
																.addGap(316))
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(txtBus, Alignment.LEADING, 0, 0,
																				Short.MAX_VALUE)
																		.addComponent(txtHuisnr, Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE, 51,
																				Short.MAX_VALUE))
																.addGap(18)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblHuisnrError)
																		.addComponent(lblBusError))
																.addContainerGap(460, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE,
																		52, GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(lblPostcodeError)
																.addContainerGap(472, Short.MAX_VALUE))))
										.addGroup(
												groupLayout.createSequentialGroup().addComponent(lblMedewerkerBijwerken)
														.addContainerGap(607, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 48,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(rbtnUser)
												.addGap(18)
												.addComponent(rbtnAdmin).addGap(310).addComponent(btnBijwerken,
														GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
												.addGap(25)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(14).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
								.createSequentialGroup().addComponent(lblMedewerkerBijwerken).addGap(18)
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
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmailError))
								.addGap(32)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label)
										.addComponent(rbtnUser).addComponent(rbtnAdmin)))
						.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_1).addGap(187)));

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == btnBijwerken) {
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
						&& !txtStraat.getText().isEmpty() && !txtGemeente.getText().isEmpty()
						&& !txtPostcode.getText().isEmpty() && !txtHuisnr.getText().isEmpty()) {
					
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
					} else {
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
						MedewerkerDAO.bijwerkenMedewerker(medewerkerId, persoonId, persoon, rol, adresId, adres);
						JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is bijgewerkt!");
						AdminGui.setHuidigeKeuze(new GebruikerBewerkenGui());
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");
				}
			}
		}
	}
}