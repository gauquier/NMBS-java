package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.KlantDAO;
import dao.PersoonDao;
import source.Adres;
import source.Klant;
import source.Persoon;
import source.Validation;

public class KlantBijwerkenGui extends JPanel {
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
	private List<Persoon> mijnpersonen;
	private PersoonDao persoonDao = new PersoonDao();

	private JLabel lblExtraInformatie;
	private JTextField txtInfo;

	private int klantId = 0;
	private int persoonId = 0;
	private int adresId = 0;
	private String huidigeEmail;
	private String email;
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

	public KlantBijwerkenGui(Klant k) {
		bundle = ResourceBundle.getBundle("localization.KlantBijwerkenGui");

		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		klantId = k.getKlantId();
		persoonId = k.getId();
		adresId = k.getAdres().getAdresId();
		huidigeEmail = k.getEmail();

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

		txtVoornaam = new JTextField();
		txtVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtVoornaam.setColumns(10);
		txtVoornaam.setText(k.getVoornaam());

		txtAchternaam = new JTextField();
		txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtAchternaam.setColumns(10);
		txtAchternaam.setText(k.getAchternaam());

		txtStraat = new JTextField();
		txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtStraat.setColumns(10);
		txtStraat.setText(k.getAdres().getStraat());

		txtHuisnr = new JTextField();
		txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtHuisnr.setColumns(10);
		txtHuisnr.setText(new Integer(k.getAdres().getHuisnr()).toString());

		txtBus = new JTextField();
		txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtBus.setColumns(10);
		txtBus.setText(k.getAdres().getBus());

		txtGemeente = new JTextField();
		txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtGemeente.setColumns(10);
		txtGemeente.setText(k.getAdres().getWoonplaats());

		txtPostcode = new JTextField();
		txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPostcode.setColumns(10);
		txtPostcode.setText(new Integer(k.getAdres().getPostcode()).toString());

		btnBijwerken = new JButton(bundle.getString("btnBijwerken"));
		btnBijwerken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBijwerken.setBackground(Color.ORANGE);
		btnBijwerken.addActionListener(new MenuItemHandler());

		lblEmail = new JLabel(bundle.getString("lblEmail"));
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmail.setForeground(Color.WHITE);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setText(k.getEmail());

		JLabel lblKlantBijwerken = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblKlantBijwerken"));
		lblKlantBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);

		lblExtraInformatie = new JLabel(bundle.getString("lblExtraInformatie"));
		lblExtraInformatie.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblExtraInformatie.setForeground(Color.WHITE);

		txtInfo = new JTextField();
		txtInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtInfo.setColumns(10);
		txtInfo.setText(k.getInfo());

		lblVoornaamError = new JLabel("voornaam error");
		lblVoornaamError.setForeground(Color.RED);
		lblVoornaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblAchternaamError = new JLabel("achternaam error");
		lblAchternaamError.setForeground(Color.RED);
		lblAchternaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblStraatError = new JLabel("straat error");
		lblStraatError.setForeground(Color.RED);
		lblStraatError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblHuisnrError = new JLabel("huisnr error");
		lblHuisnrError.setForeground(Color.RED);
		lblHuisnrError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblBusError = new JLabel("bus error");
		lblBusError.setForeground(Color.RED);
		lblBusError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblGemeenteError = new JLabel("gemeente error");
		lblGemeenteError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGemeenteError.setForeground(Color.RED);

		lblPostcodeError = new JLabel("postcode error");
		lblPostcodeError.setForeground(Color.RED);
		lblPostcodeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblEmailError = new JLabel("email error");
		lblEmailError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmailError.setForeground(Color.RED);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
										.addGap(30).addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblVoornaam).addComponent(lblAchternaam)
																.addComponent(lblBus)
																.addComponent(lblPostcode).addComponent(lblHuisnummer)
																.addComponent(lblGemeente)
																.addComponent(lblStraat))
														.addGap(50)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(txtPostcode,
																				GroupLayout.PREFERRED_SIZE, 79,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblPostcodeError))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.TRAILING,
																						false)
																				.addComponent(txtBus, Alignment.LEADING,
																						0, 0, Short.MAX_VALUE)
																				.addComponent(txtHuisnr,
																						Alignment.LEADING,
																						GroupLayout.DEFAULT_SIZE, 81,
																						Short.MAX_VALUE))
																		.addGap(18)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblBusError)
																				.addComponent(lblHuisnrError)))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(txtVoornaam,
																				GroupLayout.PREFERRED_SIZE, 286,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblVoornaamError))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(txtAchternaam,
																				GroupLayout.PREFERRED_SIZE, 286,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblAchternaamError))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(txtStraat,
																				GroupLayout.PREFERRED_SIZE, 286,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblStraatError))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(label_1,
																						GroupLayout.DEFAULT_SIZE, 347,
																						Short.MAX_VALUE)
																				.addComponent(txtInfo,
																						GroupLayout.PREFERRED_SIZE, 286,
																						GroupLayout.PREFERRED_SIZE)
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addComponent(txtEmail,
																								GroupLayout.PREFERRED_SIZE,
																								286,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(18)
																						.addComponent(lblEmailError)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addGap(206)
																						.addComponent(btnBijwerken,
																								GroupLayout.PREFERRED_SIZE,
																								111,
																								GroupLayout.PREFERRED_SIZE))
																				.addGroup(groupLayout
																						.createSequentialGroup()
																						.addGap(86)
																						.addComponent(btnBijwerken,
																								GroupLayout.PREFERRED_SIZE,
																								111,
																								GroupLayout.PREFERRED_SIZE))))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(txtGemeente,
																				GroupLayout.PREFERRED_SIZE, 286,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18).addComponent(lblGemeenteError))))
												.addComponent(lblKlantBijwerken)))
								.addGroup(groupLayout.createSequentialGroup().addGap(32).addGroup(groupLayout
										.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblExtraInformatie, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnBijwerken,
								GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(19).addComponent(lblKlantBijwerken)
								.addGap(18)
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
								.addGap(138)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
														.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblEmailError)).addGap(31))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43,
																GroupLayout.PREFERRED_SIZE)
														.addGap(29)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblExtraInformatie).addComponent(txtInfo,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(label_1)))
				.addGap(29)));

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
				checkvoornaam = true;
				lblAchternaamError.setText("");
				txtAchternaam.setBorder(border);
				checkachternaam = true;
				lblStraatError.setText("");
				txtStraat.setBorder(border);
				checkstraat = true;
				lblHuisnrError.setText("");
				txtHuisnr.setBorder(border);
				checkhuisnr = true;
				lblGemeenteError.setText("");
				txtGemeente.setBorder(border);
				checkgemeente = true;
				lblPostcodeError.setText("");
				txtPostcode.setBorder(border);
				checkpostcode = true;
				lblEmailError.setText("");
				txtEmail.setBorder(border);
				checkemail = true;
				lblBusError.setText("");
				txtBus.setBorder(border);
				checkbus = true;

				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty()
						&& !txtStraat.getText().isEmpty() && !txtGemeente.getText().isEmpty()
						&& !txtPostcode.getText().isEmpty() && !txtHuisnr.getText().isEmpty()
						&& !txtEmail.getText().isEmpty()) {
					if (!Validation.checkFirstName(txtVoornaam.getText())) {
						lblVoornaamError.setText(bundle.getString("lblVoornaamError"));
						txtVoornaam.setBorder(bordererror);
						checkvoornaam = false;
					}
					if (!Validation.checkLastName(txtAchternaam.getText())) {
						lblAchternaamError.setText(bundle.getString("lblAchternaamError"));
						txtAchternaam.setBorder(bordererror);
						checkachternaam = false;
					}
					if (!Validation.checkAlphabetical(txtStraat.getText())) {
						lblStraatError.setText(bundle.getString("lblStraatError"));
						txtStraat.setBorder(bordererror);
						checkstraat = false;
					}
					if (!Validation.checkHouseNumber(txtHuisnr.getText())) {
						lblHuisnrError.setText(bundle.getString("lblHuisnrError"));
						txtHuisnr.setBorder(bordererror);
						checkhuisnr = false;
					}
					if (!Validation.checkAlphabetical(txtGemeente.getText())) {
						lblGemeenteError.setText(bundle.getString("lblGemeenteError"));
						txtGemeente.setBorder(bordererror);
						checkgemeente = false;
					}
					if (!Validation.checkPostalCode(txtPostcode.getText())) {
						lblPostcodeError.setText(bundle.getString("lblPostcodeError"));
						txtPostcode.setBorder(bordererror);
						checkpostcode = false;
					}
					if (!txtEmail.getText().isEmpty() && !Validation.checkEmail(txtEmail.getText())) {
						lblEmailError.setText(bundle.getString("lblEmailError"));
						txtEmail.setBorder(bordererror);
						checkemail = false;
					}
					if (!txtBus.getText().isEmpty() && !Validation.checkBoxNumber(txtBus.getText())) {
						lblBusError.setText(bundle.getString("lblBusError"));
						txtBus.setBorder(bordererror);
						checkbus = false;
					}
					if (checkvoornaam == false || checkachternaam == false || checkstraat == false
							|| checkhuisnr == false || checkgemeente == false || checkpostcode == false
							|| checkemail == false || checkbus == false) {
						return;
					} else {
						email = txtEmail.getText().trim();

						if (persoonDao.checkEmail(email) > 0 && !(email.equals(huidigeEmail))) {
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerAlreadyExists"));
							txtEmail.setText("");
							return;
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
							String info = txtInfo.getText().trim();
							KlantDAO.bijwerkenKlant(klantId, persoonId, persoon, info, adresId, adres);
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerEdited"));
							AdminGui.setHuidigeKeuze(new KlantenBeheerGui());
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}