package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = -1840455749314699755L;

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

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.klantId = k.getKlantId();
		this.persoonId = k.getId();
		this.adresId = k.getAdres().getAdresId();
		this.huidigeEmail = k.getEmail();

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
		this.txtVoornaam.setText(k.getVoornaam());

		this.txtAchternaam = new JTextField();
		this.txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtAchternaam.setColumns(10);
		this.txtAchternaam.setText(k.getAchternaam());

		this.txtStraat = new JTextField();
		this.txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtStraat.setColumns(10);
		this.txtStraat.setText(k.getAdres().getStraat());

		this.txtHuisnr = new JTextField();
		this.txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtHuisnr.setColumns(10);
		this.txtHuisnr.setText(new Integer(k.getAdres().getHuisnr()).toString());

		this.txtBus = new JTextField();
		this.txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtBus.setColumns(10);
		this.txtBus.setText(k.getAdres().getBus());

		this.txtGemeente = new JTextField();
		this.txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtGemeente.setColumns(10);
		this.txtGemeente.setText(k.getAdres().getWoonplaats());

		this.txtPostcode = new JTextField();
		this.txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtPostcode.setColumns(10);
		this.txtPostcode.setText(new Integer(k.getAdres().getPostcode()).toString());

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
		this.txtEmail.setText(k.getEmail());

		JLabel lblKlantBijwerken = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblKlantBijwerken"));
		lblKlantBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);

		this.lblExtraInformatie = new JLabel(bundle.getString("lblExtraInformatie"));
		this.lblExtraInformatie.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblExtraInformatie.setForeground(Color.WHITE);

		this.txtInfo = new JTextField();
		this.txtInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtInfo.setColumns(10);
		this.txtInfo.setText(k.getInfo());

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
		this.lblGemeenteError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblGemeenteError.setForeground(Color.RED);

		this.lblPostcodeError = new JLabel("");
		this.lblPostcodeError.setForeground(Color.RED);
		this.lblPostcodeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblEmailError = new JLabel("");
		this.lblEmailError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblEmailError.setForeground(Color.RED);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKlantBijwerken)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVoornaam)
										.addComponent(lblAchternaam)
										.addComponent(lblBus)
										.addComponent(lblPostcode)
										.addComponent(lblHuisnummer)
										.addComponent(lblGemeente)
										.addComponent(lblStraat)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblExtraInformatie, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblEmail, Alignment.LEADING)))
									.addGap(50)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblPostcodeError))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtBus, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(txtHuisnr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBusError)
												.addComponent(lblHuisnrError)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblVoornaamError))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblAchternaamError))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblStraatError))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(304)
											.addComponent(lblEmailError)
											.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(206)
													.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(86)
													.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblGemeenteError))))))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(174)
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblKlantBijwerken)
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
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExtraInformatie)
						.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmailError)
							.addGap(31)))
					.addGap(29))
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

			if (e.getSource() == KlantBijwerkenGui.this.btnBijwerken) {
				KlantBijwerkenGui.this.lblVoornaamError.setText("");
				KlantBijwerkenGui.this.txtVoornaam.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkvoornaam = true;
				KlantBijwerkenGui.this.lblAchternaamError.setText("");
				KlantBijwerkenGui.this.txtAchternaam.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkachternaam = true;
				KlantBijwerkenGui.this.lblStraatError.setText("");
				KlantBijwerkenGui.this.txtStraat.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkstraat = true;
				KlantBijwerkenGui.this.lblHuisnrError.setText("");
				KlantBijwerkenGui.this.txtHuisnr.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkhuisnr = true;
				KlantBijwerkenGui.this.lblGemeenteError.setText("");
				KlantBijwerkenGui.this.txtGemeente.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkgemeente = true;
				KlantBijwerkenGui.this.lblPostcodeError.setText("");
				KlantBijwerkenGui.this.txtPostcode.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkpostcode = true;
				KlantBijwerkenGui.this.lblEmailError.setText("");
				KlantBijwerkenGui.this.txtEmail.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkemail = true;
				KlantBijwerkenGui.this.lblBusError.setText("");
				KlantBijwerkenGui.this.txtBus.setBorder(KlantBijwerkenGui.this.border);
				KlantBijwerkenGui.this.checkbus = true;

				if (!KlantBijwerkenGui.this.txtVoornaam.getText().isEmpty()
						&& !KlantBijwerkenGui.this.txtAchternaam.getText().isEmpty()
						&& !KlantBijwerkenGui.this.txtStraat.getText().isEmpty()
						&& !KlantBijwerkenGui.this.txtGemeente.getText().isEmpty()
						&& !KlantBijwerkenGui.this.txtPostcode.getText().isEmpty()
						&& !KlantBijwerkenGui.this.txtHuisnr.getText().isEmpty()
						&& !KlantBijwerkenGui.this.txtEmail.getText().isEmpty()) {
					if (!Validation.checkFirstName(KlantBijwerkenGui.this.txtVoornaam.getText())) {
						KlantBijwerkenGui.this.lblVoornaamError.setText(bundle.getString("lblVoornaamError"));
						KlantBijwerkenGui.this.txtVoornaam.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkvoornaam = false;
					}
					if (!Validation.checkLastName(KlantBijwerkenGui.this.txtAchternaam.getText())) {
						KlantBijwerkenGui.this.lblAchternaamError.setText(bundle.getString("lblAchternaamError"));
						KlantBijwerkenGui.this.txtAchternaam.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkachternaam = false;
					}
					if (!Validation.checkAlphabetical(KlantBijwerkenGui.this.txtStraat.getText())) {
						KlantBijwerkenGui.this.lblStraatError.setText(bundle.getString("lblStraatError"));
						KlantBijwerkenGui.this.txtStraat.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkstraat = false;
					}
					if (!Validation.checkHouseNumber(KlantBijwerkenGui.this.txtHuisnr.getText())) {
						KlantBijwerkenGui.this.lblHuisnrError.setText(bundle.getString("lblHuisnrError"));
						KlantBijwerkenGui.this.txtHuisnr.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkhuisnr = false;
					}
					if (!Validation.checkAlphabetical(KlantBijwerkenGui.this.txtGemeente.getText())) {
						KlantBijwerkenGui.this.lblGemeenteError.setText(bundle.getString("lblGemeenteError"));
						KlantBijwerkenGui.this.txtGemeente.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkgemeente = false;
					}
					if (!Validation.checkPostalCode(KlantBijwerkenGui.this.txtPostcode.getText())) {
						KlantBijwerkenGui.this.lblPostcodeError.setText(bundle.getString("lblPostcodeError"));
						KlantBijwerkenGui.this.txtPostcode.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkpostcode = false;
					}
					if (!KlantBijwerkenGui.this.txtEmail.getText().isEmpty()
							&& !Validation.checkEmail(KlantBijwerkenGui.this.txtEmail.getText())) {
						KlantBijwerkenGui.this.lblEmailError.setText(bundle.getString("lblEmailError"));
						KlantBijwerkenGui.this.txtEmail.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkemail = false;
					}
					if (!KlantBijwerkenGui.this.txtBus.getText().isEmpty()
							&& !Validation.checkBoxNumber(KlantBijwerkenGui.this.txtBus.getText())) {
						KlantBijwerkenGui.this.lblBusError.setText(bundle.getString("lblBusError"));
						KlantBijwerkenGui.this.txtBus.setBorder(KlantBijwerkenGui.this.bordererror);
						KlantBijwerkenGui.this.checkbus = false;
					}
					if (KlantBijwerkenGui.this.checkvoornaam == false || KlantBijwerkenGui.this.checkachternaam == false
							|| KlantBijwerkenGui.this.checkstraat == false
							|| KlantBijwerkenGui.this.checkhuisnr == false
							|| KlantBijwerkenGui.this.checkgemeente == false
							|| KlantBijwerkenGui.this.checkpostcode == false
							|| KlantBijwerkenGui.this.checkemail == false || KlantBijwerkenGui.this.checkbus == false) {
						return;
					} else {
						KlantBijwerkenGui.this.email = KlantBijwerkenGui.this.txtEmail.getText().trim();

						if (KlantBijwerkenGui.this.persoonDao.checkEmail(KlantBijwerkenGui.this.email) > 0
								&& !(KlantBijwerkenGui.this.email.equals(KlantBijwerkenGui.this.huidigeEmail))) {
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerAlreadyExists"));
							KlantBijwerkenGui.this.txtEmail.setText("");
							return;
						} else {
							if (!KlantBijwerkenGui.this.txtBus.getText().isEmpty()) {
								KlantBijwerkenGui.this.adres = new Adres(
										KlantBijwerkenGui.this.txtStraat.getText().trim(),
										Integer.parseInt(KlantBijwerkenGui.this.txtHuisnr.getText()),
										KlantBijwerkenGui.this.txtGemeente.getText().trim(),
										Integer.parseInt(KlantBijwerkenGui.this.txtPostcode.getText()),
										KlantBijwerkenGui.this.txtBus.getText());
							} else {
								KlantBijwerkenGui.this.adres = new Adres(
										KlantBijwerkenGui.this.txtStraat.getText().trim(),
										Integer.parseInt(KlantBijwerkenGui.this.txtHuisnr.getText()),
										KlantBijwerkenGui.this.txtGemeente.getText().trim(),
										Integer.parseInt(KlantBijwerkenGui.this.txtPostcode.getText()), "");
							}
							KlantBijwerkenGui.this.adres.toString();
							KlantBijwerkenGui.this.persoon = new Persoon(
									KlantBijwerkenGui.this.txtVoornaam.getText().trim(),
									KlantBijwerkenGui.this.txtAchternaam.getText().trim(),
									KlantBijwerkenGui.this.txtEmail.getText().trim(), KlantBijwerkenGui.this.adres);
							KlantBijwerkenGui.this.persoon.toString();
							String info = KlantBijwerkenGui.this.txtInfo.getText().trim();
							KlantDAO.bijwerkenKlant(KlantBijwerkenGui.this.klantId, KlantBijwerkenGui.this.persoonId,
									KlantBijwerkenGui.this.persoon, info, KlantBijwerkenGui.this.adresId,
									KlantBijwerkenGui.this.adres);
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerEdited"));
							AdminGui.setHuidigeKeuze(new KlantBewerkenGui());
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}