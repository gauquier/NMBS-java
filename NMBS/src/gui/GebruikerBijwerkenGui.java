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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.MedewerkerDAO;
import source.Adres;
import source.Medewerker;
import source.Persoon;
import source.Rol;
import source.Validation;

public class GebruikerBijwerkenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3992250264094765608L;

	private static ResourceBundle bundle;

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
	private Persoon persoon;
	private Rol rol;
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
	private boolean checkvoornaam, checkachternaam, checkstraat, checkhuisnr, checkbus, checkgemeente, checkpostcode,
			checkemail;

	public GebruikerBijwerkenGui(Medewerker m) {
		bundle = ResourceBundle.getBundle("localization.GebruikerBijwerkenGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.medewerkerId = m.getMedewerkerId();
		this.persoonId = m.getId();
		this.adresId = m.getAdres().getAdresId();

		JLabel lblVoornaam = new JLabel(bundle.getString("lblVoornaam"));
		lblVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVoornaam.setForeground(Color.WHITE);

		JLabel lblAchternaam = new JLabel(bundle.getString("lblAchternaam"));
		lblAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAchternaam.setForeground(Color.WHITE);

		JLabel lblStraat = new JLabel(bundle.getString("lblStraat"));
		lblStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStraat.setForeground(Color.WHITE);

		JLabel lblHuisnummer = new JLabel(bundle.getString("lblHuisnummer"));
		lblHuisnummer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuisnummer.setForeground(Color.WHITE);

		JLabel lblGemeente = new JLabel(bundle.getString("lblGemeente"));
		lblGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGemeente.setForeground(Color.WHITE);

		JLabel lblPostcode = new JLabel(bundle.getString("lblPostcode"));
		lblPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPostcode.setForeground(Color.WHITE);

		JLabel lblBus = new JLabel(bundle.getString("lblBus"));
		lblBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBus.setForeground(Color.WHITE);

		this.txtVoornaam = new JTextField();
		this.txtVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtVoornaam.setColumns(10);
		this.txtVoornaam.setText(m.getVoornaam());

		this.txtAchternaam = new JTextField();
		this.txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtAchternaam.setColumns(10);
		this.txtAchternaam.setText(m.getAchternaam());

		this.txtStraat = new JTextField();
		this.txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtStraat.setColumns(10);
		this.txtStraat.setText(m.getAdres().getStraat());

		this.txtHuisnr = new JTextField();
		this.txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtHuisnr.setColumns(10);
		this.txtHuisnr.setText(new Integer(m.getAdres().getHuisnr()).toString());

		this.txtBus = new JTextField();
		this.txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtBus.setColumns(10);
		this.txtBus.setText(m.getAdres().getBus());

		this.txtGemeente = new JTextField();
		this.txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtGemeente.setColumns(10);
		this.txtGemeente.setText(m.getAdres().getWoonplaats());

		this.txtPostcode = new JTextField();
		this.txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtPostcode.setColumns(10);
		this.txtPostcode.setText(new Integer(m.getAdres().getPostcode()).toString());

		this.btnBijwerken = new JButton(bundle.getString("btnBijwerken"));
		this.btnBijwerken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.btnBijwerken.setBackground(Color.ORANGE);
		this.btnBijwerken.addActionListener(new MenuItemHandler());

		this.lblEmail = new JLabel(bundle.getString("lblEmail"));
		this.lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblEmail.setForeground(Color.WHITE);

		this.txtEmail = new JTextField();
		this.txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtEmail.setColumns(10);
		this.txtEmail.setText(m.getEmail());

		JLabel lblMedewerkerBijwerken = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblMedewerkerBijwerken"));
		lblMedewerkerBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.radiobuttons = new ButtonGroup();
		this.rbtnAdmin = new JRadioButton(bundle.getString("rbtnAdmin"));
		this.rbtnAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.rbtnAdmin.setForeground(Color.WHITE);

		this.rbtnUser = new JRadioButton(bundle.getString("rbtnUser"));
		this.rbtnUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.rbtnUser.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.rbtnUser.setForeground(Color.WHITE);

		this.radiobuttons.add(this.rbtnAdmin);
		this.radiobuttons.add(this.rbtnUser);

		int rolid = m.getRol().getRolId();

		if (rolid == 1) {
			this.rbtnAdmin.setSelected(true);
		} else {
			this.rbtnUser.setSelected(true);
		}

		JLabel label = new JLabel(bundle.getString("label"));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setForeground(Color.WHITE);

		this.lblVoornaamError = new JLabel("");
		this.lblVoornaamError.setForeground(Color.RED);
		this.lblVoornaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblAchternaamError = new JLabel("");
		this.lblAchternaamError.setForeground(Color.RED);
		this.lblAchternaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblStraatError = new JLabel("");
		this.lblStraatError.setForeground(Color.RED);
		this.lblStraatError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblHuisnrError = new JLabel("");
		this.lblHuisnrError.setForeground(Color.RED);
		this.lblHuisnrError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblBusError = new JLabel("");
		this.lblBusError.setForeground(Color.RED);
		this.lblBusError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblGemeenteError = new JLabel("");
		this.lblGemeenteError.setForeground(Color.RED);
		this.lblGemeenteError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblPostcodeError = new JLabel("");
		this.lblPostcodeError.setForeground(Color.RED);
		this.lblPostcodeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblEmailError = new JLabel("");
		this.lblEmailError.setForeground(Color.RED);
		this.lblEmailError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 738,
																Short.MAX_VALUE)
														.addContainerGap())
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblVoornaam).addComponent(lblAchternaam)
														.addComponent(lblBus).addComponent(lblPostcode)
														.addComponent(lblHuisnummer).addComponent(lblGemeente)
														.addComponent(lblStraat).addComponent(this.lblEmail))
												.addGap(50)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
														groupLayout.createSequentialGroup().addGroup(groupLayout
																.createParallelGroup(Alignment.TRAILING)
																.addComponent(this.txtEmail, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
																.addComponent(this.txtVoornaam, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
																.addComponent(this.txtAchternaam, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
																.addComponent(this.txtStraat, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
																.addComponent(this.txtGemeente, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
																.addGap(18)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(this.lblVoornaamError)
																		.addComponent(this.lblAchternaamError)
																		.addComponent(this.lblStraatError)
																		.addComponent(this.lblGemeenteError)
																		.addComponent(this.lblEmailError))
																.addGap(316))
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(this.txtBus, Alignment.LEADING, 0,
																				0, Short.MAX_VALUE)
																		.addComponent(this.txtHuisnr, Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE, 51,
																				Short.MAX_VALUE))
																.addGap(18)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(this.lblHuisnrError)
																		.addComponent(this.lblBusError))
																.addContainerGap(512, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(this.txtPostcode,
																		GroupLayout.PREFERRED_SIZE, 52,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(this.lblPostcodeError)
																.addContainerGap(511, Short.MAX_VALUE))))
										.addGroup(
												groupLayout.createSequentialGroup().addComponent(lblMedewerkerBijwerken)
														.addContainerGap(641, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.rbtnUser)
												.addGap(18).addComponent(this.rbtnAdmin))))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup()
										.addContainerGap(622, Short.MAX_VALUE).addComponent(this.btnBijwerken,
												GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(14).addComponent(lblMedewerkerBijwerken).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVoornaam)
						.addComponent(this.txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblVoornaamError))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAchternaam)
						.addComponent(this.txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblAchternaamError))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStraat).addComponent(this.lblStraatError))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblHuisnummer)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblHuisnrError)))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblBus)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblBusError)))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGemeente).addComponent(this.lblGemeenteError))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPostcode)
						.addComponent(this.txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblPostcodeError))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.lblEmail)
						.addComponent(this.txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblEmailError))
				.addGap(32)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label)
						.addComponent(this.rbtnUser).addComponent(this.rbtnAdmin))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_1)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(this.btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				.addGap(138)));

		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == GebruikerBijwerkenGui.this.btnBijwerken) {
				GebruikerBijwerkenGui.this.lblVoornaamError.setText("");
				GebruikerBijwerkenGui.this.txtVoornaam.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkvoornaam = true;
				GebruikerBijwerkenGui.this.lblAchternaamError.setText("");
				GebruikerBijwerkenGui.this.txtAchternaam.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkachternaam = true;
				GebruikerBijwerkenGui.this.lblStraatError.setText("");
				GebruikerBijwerkenGui.this.txtStraat.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkstraat = true;
				GebruikerBijwerkenGui.this.lblHuisnrError.setText("");
				GebruikerBijwerkenGui.this.txtHuisnr.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkhuisnr = true;
				GebruikerBijwerkenGui.this.lblGemeenteError.setText("");
				GebruikerBijwerkenGui.this.txtGemeente.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkgemeente = true;
				GebruikerBijwerkenGui.this.lblPostcodeError.setText("");
				GebruikerBijwerkenGui.this.txtPostcode.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkpostcode = true;
				GebruikerBijwerkenGui.this.lblEmailError.setText("");
				GebruikerBijwerkenGui.this.txtEmail.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkemail = true;
				GebruikerBijwerkenGui.this.lblBusError.setText("");
				GebruikerBijwerkenGui.this.txtBus.setBorder(GebruikerBijwerkenGui.this.border);
				GebruikerBijwerkenGui.this.checkbus = true;

				if (!GebruikerBijwerkenGui.this.txtVoornaam.getText().isEmpty()
						&& !GebruikerBijwerkenGui.this.txtAchternaam.getText().isEmpty()
						&& !GebruikerBijwerkenGui.this.txtStraat.getText().isEmpty()
						&& !GebruikerBijwerkenGui.this.txtGemeente.getText().isEmpty()
						&& !GebruikerBijwerkenGui.this.txtPostcode.getText().isEmpty()
						&& !GebruikerBijwerkenGui.this.txtHuisnr.getText().isEmpty()) {
					if (!Validation.checkFirstName(GebruikerBijwerkenGui.this.txtVoornaam.getText())) {
						GebruikerBijwerkenGui.this.lblVoornaamError.setText(bundle.getString("lblVoornaamError"));
						GebruikerBijwerkenGui.this.txtVoornaam.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkvoornaam = false;
					}
					if (!Validation.checkLastName(GebruikerBijwerkenGui.this.txtAchternaam.getText())) {
						GebruikerBijwerkenGui.this.lblAchternaamError.setText(bundle.getString("lblAchternaamError"));
						GebruikerBijwerkenGui.this.txtAchternaam.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkachternaam = false;
					}
					if (!Validation.checkAlphabetical(GebruikerBijwerkenGui.this.txtStraat.getText())) {
						GebruikerBijwerkenGui.this.lblStraatError.setText(bundle.getString("lblStraatError"));
						GebruikerBijwerkenGui.this.txtStraat.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkstraat = false;
					}
					if (!Validation.checkHouseNumber(GebruikerBijwerkenGui.this.txtHuisnr.getText())) {
						GebruikerBijwerkenGui.this.lblHuisnrError.setText(bundle.getString("lblHuisnrError"));
						GebruikerBijwerkenGui.this.txtHuisnr.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkhuisnr = false;
					}
					if (!Validation.checkAlphabetical(GebruikerBijwerkenGui.this.txtGemeente.getText())) {
						GebruikerBijwerkenGui.this.lblGemeenteError.setText(bundle.getString("lblGemeenteError"));
						GebruikerBijwerkenGui.this.txtGemeente.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkgemeente = false;
					}
					if (!Validation.checkPostalCode(GebruikerBijwerkenGui.this.txtPostcode.getText())) {
						GebruikerBijwerkenGui.this.lblPostcodeError.setText(bundle.getString("lblPostcodeError"));
						GebruikerBijwerkenGui.this.txtPostcode.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkpostcode = false;
					}
					if (!GebruikerBijwerkenGui.this.txtEmail.getText().isEmpty()
							&& !Validation.checkEmail(GebruikerBijwerkenGui.this.txtEmail.getText())) {
						GebruikerBijwerkenGui.this.lblEmailError.setText(bundle.getString("lblEmailError"));
						GebruikerBijwerkenGui.this.txtEmail.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkemail = false;
					}
					if (!GebruikerBijwerkenGui.this.txtBus.getText().isEmpty()
							&& !Validation.checkBoxNumber(GebruikerBijwerkenGui.this.txtBus.getText())) {
						GebruikerBijwerkenGui.this.lblBusError.setText(bundle.getString("lblBusError"));
						GebruikerBijwerkenGui.this.txtBus.setBorder(GebruikerBijwerkenGui.this.bordererror);
						GebruikerBijwerkenGui.this.checkbus = false;
					}
					if (GebruikerBijwerkenGui.this.checkvoornaam == false
							|| GebruikerBijwerkenGui.this.checkachternaam == false
							|| GebruikerBijwerkenGui.this.checkstraat == false
							|| GebruikerBijwerkenGui.this.checkhuisnr == false
							|| GebruikerBijwerkenGui.this.checkgemeente == false
							|| GebruikerBijwerkenGui.this.checkpostcode == false
							|| GebruikerBijwerkenGui.this.checkemail == false
							|| GebruikerBijwerkenGui.this.checkbus == false) {
						return;
					} else {
						if (!GebruikerBijwerkenGui.this.txtBus.getText().isEmpty()) {
							GebruikerBijwerkenGui.this.adres = new Adres(
									GebruikerBijwerkenGui.this.txtStraat.getText().trim(),
									Integer.parseInt(GebruikerBijwerkenGui.this.txtHuisnr.getText()),
									GebruikerBijwerkenGui.this.txtGemeente.getText().trim(),
									Integer.parseInt(GebruikerBijwerkenGui.this.txtPostcode.getText()),
									GebruikerBijwerkenGui.this.txtBus.getText());
						} else {
							GebruikerBijwerkenGui.this.adres = new Adres(
									GebruikerBijwerkenGui.this.txtStraat.getText().trim(),
									Integer.parseInt(GebruikerBijwerkenGui.this.txtHuisnr.getText()),
									GebruikerBijwerkenGui.this.txtGemeente.getText().trim(),
									Integer.parseInt(GebruikerBijwerkenGui.this.txtPostcode.getText()), "");
						}
						GebruikerBijwerkenGui.this.adres.toString();
						GebruikerBijwerkenGui.this.persoon = new Persoon(
								GebruikerBijwerkenGui.this.txtVoornaam.getText().trim(),
								GebruikerBijwerkenGui.this.txtAchternaam.getText().trim(),
								GebruikerBijwerkenGui.this.txtEmail.getText().trim(), GebruikerBijwerkenGui.this.adres);
						GebruikerBijwerkenGui.this.persoon.toString();
						int rolid;
						if (GebruikerBijwerkenGui.this.rbtnAdmin.isSelected()) {
							rolid = 1;
						} else {
							rolid = 2;
						}
						GebruikerBijwerkenGui.this.rol = new Rol(rolid);
						GebruikerBijwerkenGui.this.rol.setRolId(rolid);
						MedewerkerDAO.bijwerkenMedewerker(GebruikerBijwerkenGui.this.medewerkerId,
								GebruikerBijwerkenGui.this.persoonId, GebruikerBijwerkenGui.this.persoon,
								GebruikerBijwerkenGui.this.rol, GebruikerBijwerkenGui.this.adresId,
								GebruikerBijwerkenGui.this.adres);
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userEdited"));
						AdminGui.setHuidigeKeuze(new GebruikerBewerkenGui());
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}