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
import source.Persoon;
import source.Validation;

public class KlantToevoegenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2290954843880133511L;

	private static ResourceBundle bundle;

	private JTextField txtVoornaam;
	private JTextField txtAchternaam;
	private JTextField txtStraat;
	private JTextField txtHuisnr;
	private JTextField txtBus;
	private JTextField txtGemeente;
	private JTextField txtPostcode;
	private JButton btnToevoegen;
	private JLabel lblEmail, lblEmailError, lblvoornaamError, lblAchternaamError, lblStraatError, lblHuisnrError,
			lblBusError, lblGemeenteError, lblPostcodeError;
	private JTextField txtEmail;
	private Adres adres;
	private Persoon persoon;
	private PersoonDao persoonDao = new PersoonDao();
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private boolean checkvoornaam, checkachternaam, checkstraat, checkhuisnr, checkbus, checkgemeente, checkpostcode,
			checkemail;

	private JLabel lblExtraInformatie;
	private JTextField txtInfo;

	public KlantToevoegenGui() {
		bundle = ResourceBundle.getBundle("localization.KlantToevoegenGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

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

		this.lblEmail = new JLabel(bundle.getString("lblEmail"));
		this.lblEmail.setForeground(Color.WHITE);
		this.lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.btnToevoegen = new JButton(bundle.getString("btnToevoegen"));
		this.btnToevoegen.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.btnToevoegen.setBackground(Color.ORANGE);
		this.btnToevoegen.addActionListener(new MenuItemHandler());

		this.txtEmail = new JTextField();
		this.txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtEmail.setColumns(10);

		JLabel lblKlantToevoegen = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblKlantToevoegen"));
		lblKlantToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);

		this.lblExtraInformatie = new JLabel(bundle.getString("lblExtraInformatie"));
		this.lblExtraInformatie.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblExtraInformatie.setForeground(Color.WHITE);

		this.txtInfo = new JTextField();
		this.txtInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtInfo.setColumns(10);

		this.lblvoornaamError = new JLabel("");
		this.lblvoornaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblvoornaamError.setForeground(Color.RED);

		this.lblAchternaamError = new JLabel("");
		this.lblAchternaamError.setForeground(Color.RED);
		this.lblAchternaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblStraatError = new JLabel("");
		this.lblStraatError.setForeground(Color.RED);
		this.lblStraatError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout
										.createSequentialGroup().addGap(
												30)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(this.lblEmail)
												.addComponent(
														lblKlantToevoegen)
												.addGroup(
														groupLayout.createSequentialGroup()
																.addGroup(
																		groupLayout
																				.createParallelGroup(Alignment.LEADING,
																						false)
																				.addComponent(lblVoornaam)
																				.addComponent(lblAchternaam)
																				.addComponent(lblBus)
																				.addComponent(lblPostcode)
																				.addComponent(lblGemeente)
																				.addComponent(lblStraat)
																				.addComponent(lblHuisnummer,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(this.lblExtraInformatie,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addGap(18)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.TRAILING)
																		.addGroup(Alignment.LEADING, groupLayout
																				.createSequentialGroup()
																				.addComponent(this.txtBus,
																						GroupLayout.PREFERRED_SIZE, 88,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(this.lblBusError,
																						GroupLayout.PREFERRED_SIZE, 555,
																						GroupLayout.PREFERRED_SIZE))
																		.addGroup(Alignment.LEADING, groupLayout
																				.createSequentialGroup()
																				.addComponent(this.txtHuisnr,
																						GroupLayout.PREFERRED_SIZE,
																						91, GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(this.lblHuisnrError,
																						GroupLayout.PREFERRED_SIZE, 524,
																						GroupLayout.PREFERRED_SIZE))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addGroup(groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(this.txtGemeente,
																								GroupLayout.PREFERRED_SIZE,
																								298,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								this.txtAchternaam,
																								GroupLayout.PREFERRED_SIZE,
																								298,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(this.txtVoornaam,
																								GroupLayout.PREFERRED_SIZE,
																								298,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(this.txtStraat,
																								GroupLayout.PREFERRED_SIZE,
																								298,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(groupLayout
																								.createSequentialGroup()
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addGroup(groupLayout
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addComponent(
																												this.txtEmail)
																										.addComponent(
																												this.txtInfo)
																										.addComponent(
																												label_1,
																												GroupLayout.DEFAULT_SIZE,
																												298,
																												Short.MAX_VALUE)
																										.addGroup(
																												groupLayout
																														.createSequentialGroup()
																														.addComponent(
																																this.txtPostcode,
																																GroupLayout.PREFERRED_SIZE,
																																79,
																																GroupLayout.PREFERRED_SIZE)
																														.addGap(18)
																														.addComponent(
																																this.lblPostcodeError,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)))))
																				.addGap(18)
																				.addGroup(groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(Alignment.LEADING,
																								groupLayout
																										.createSequentialGroup()
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																this.lblvoornaamError)
																														.addComponent(
																																this.lblAchternaamError)
																														.addComponent(
																																this.lblGemeenteError)
																														.addComponent(
																																this.lblStraatError))
																										.addGap(379))
																						.addGroup(groupLayout
																								.createSequentialGroup()
																								.addComponent(
																										this.lblEmailError)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										272,
																										Short.MAX_VALUE)
																								.addComponent(
																										this.btnToevoegen,
																										GroupLayout.PREFERRED_SIZE,
																										150,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(19)))))))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24).addComponent(lblKlantToevoegen).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblVoornaam))
								.addComponent(this.txtVoornaam, GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblvoornaamError))
						.addGap(15)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblAchternaam))
								.addComponent(this.txtAchternaam, GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblAchternaamError))
						.addGap(15)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStraat).addComponent(this.lblStraatError))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblHuisnummer)
								.addComponent(this.txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblHuisnrError))
						.addGap(16)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblBus)
										.addComponent(this.txtBus, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblBusError))
						.addGap(15)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(this.txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblGemeente))
								.addComponent(this.lblGemeenteError))
						.addGap(25)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPostcode)
								.addComponent(this.txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblPostcodeError))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.lblEmail)
								.addComponent(this.lblEmailError).addComponent(this.txtEmail,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.lblExtraInformatie).addComponent(this.txtInfo,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(label_1).addGap(11)
						.addComponent(this.btnToevoegen, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == KlantToevoegenGui.this.btnToevoegen) {
				KlantToevoegenGui.this.lblvoornaamError.setText("");
				KlantToevoegenGui.this.txtVoornaam.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkvoornaam = true;
				KlantToevoegenGui.this.lblAchternaamError.setText("");
				KlantToevoegenGui.this.txtAchternaam.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkachternaam = true;
				KlantToevoegenGui.this.lblStraatError.setText("");
				KlantToevoegenGui.this.txtStraat.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkstraat = true;
				KlantToevoegenGui.this.lblHuisnrError.setText("");
				KlantToevoegenGui.this.txtHuisnr.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkhuisnr = true;
				KlantToevoegenGui.this.lblGemeenteError.setText("");
				KlantToevoegenGui.this.txtGemeente.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkgemeente = true;
				KlantToevoegenGui.this.lblPostcodeError.setText("");
				KlantToevoegenGui.this.txtPostcode.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkpostcode = true;
				KlantToevoegenGui.this.lblEmailError.setText("");
				KlantToevoegenGui.this.txtEmail.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkemail = true;
				KlantToevoegenGui.this.lblBusError.setText("");
				KlantToevoegenGui.this.txtBus.setBorder(KlantToevoegenGui.this.border);
				KlantToevoegenGui.this.checkbus = true;

				if (!KlantToevoegenGui.this.txtVoornaam.getText().isEmpty()
						&& !KlantToevoegenGui.this.txtAchternaam.getText().isEmpty()
						&& !KlantToevoegenGui.this.txtStraat.getText().isEmpty()
						&& !KlantToevoegenGui.this.txtGemeente.getText().isEmpty()
						&& !KlantToevoegenGui.this.txtPostcode.getText().isEmpty()
						&& !KlantToevoegenGui.this.txtHuisnr.getText().isEmpty()
						&& !KlantToevoegenGui.this.txtEmail.getText().isEmpty()) {
					if (!Validation.checkFirstName(KlantToevoegenGui.this.txtVoornaam.getText())) {
						KlantToevoegenGui.this.lblvoornaamError.setText(bundle.getString("lblVoornaamError"));
						KlantToevoegenGui.this.txtVoornaam.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkvoornaam = false;
					}
					if (!Validation.checkLastName(KlantToevoegenGui.this.txtAchternaam.getText())) {
						KlantToevoegenGui.this.lblAchternaamError.setText(bundle.getString("lblAchternaamError"));
						KlantToevoegenGui.this.txtAchternaam.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkachternaam = false;
					}
					if (!Validation.checkAlphabetical(KlantToevoegenGui.this.txtStraat.getText())) {
						KlantToevoegenGui.this.lblStraatError.setText(bundle.getString("lblStraatError"));
						KlantToevoegenGui.this.txtStraat.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkstraat = false;
					}
					if (!Validation.checkHouseNumber(KlantToevoegenGui.this.txtHuisnr.getText())) {
						KlantToevoegenGui.this.lblHuisnrError.setText(bundle.getString("lblHuisnrError"));
						KlantToevoegenGui.this.txtHuisnr.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkhuisnr = false;
					}
					if (!Validation.checkAlphabetical(KlantToevoegenGui.this.txtGemeente.getText())) {
						KlantToevoegenGui.this.lblGemeenteError.setText(bundle.getString("lblGemeenteError"));
						KlantToevoegenGui.this.txtGemeente.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkgemeente = false;
					}
					if (!Validation.checkPostalCode(KlantToevoegenGui.this.txtPostcode.getText())) {
						KlantToevoegenGui.this.lblPostcodeError.setText(bundle.getString("lblPostcodeError"));
						KlantToevoegenGui.this.txtPostcode.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkpostcode = false;
					}
					if (!KlantToevoegenGui.this.txtEmail.getText().isEmpty()
							&& !Validation.checkEmail(KlantToevoegenGui.this.txtEmail.getText())) {
						KlantToevoegenGui.this.lblEmailError.setText(bundle.getString("lblEmailError"));
						KlantToevoegenGui.this.txtEmail.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkemail = false;
					}
					if (!KlantToevoegenGui.this.txtBus.getText().isEmpty()
							&& !Validation.checkBoxNumber(KlantToevoegenGui.this.txtBus.getText())) {
						KlantToevoegenGui.this.lblBusError.setText(bundle.getString("lblBusError"));
						KlantToevoegenGui.this.txtBus.setBorder(KlantToevoegenGui.this.bordererror);
						KlantToevoegenGui.this.checkbus = false;
					}
					if (KlantToevoegenGui.this.checkvoornaam == false || KlantToevoegenGui.this.checkachternaam == false
							|| KlantToevoegenGui.this.checkstraat == false
							|| KlantToevoegenGui.this.checkhuisnr == false
							|| KlantToevoegenGui.this.checkgemeente == false
							|| KlantToevoegenGui.this.checkpostcode == false
							|| KlantToevoegenGui.this.checkemail == false || KlantToevoegenGui.this.checkbus == false) {
						return;
					} else {
						String email = KlantToevoegenGui.this.txtEmail.getText().trim();
						if (KlantToevoegenGui.this.persoonDao.checkEmail(email) > 0) {
							JOptionPane.showMessageDialog(new JFrame(), "Deze klant bestaat al.");
							KlantToevoegenGui.this.txtEmail.setText("");
							return;
						} else {
							if (!KlantToevoegenGui.this.txtBus.getText().isEmpty()) {
								KlantToevoegenGui.this.adres = new Adres(
										KlantToevoegenGui.this.txtStraat.getText().trim(),
										Integer.parseInt(KlantToevoegenGui.this.txtHuisnr.getText()),
										KlantToevoegenGui.this.txtGemeente.getText().trim(),
										Integer.parseInt(KlantToevoegenGui.this.txtPostcode.getText()),
										KlantToevoegenGui.this.txtBus.getText());
							} else {
								KlantToevoegenGui.this.adres = new Adres(
										KlantToevoegenGui.this.txtStraat.getText().trim(),
										Integer.parseInt(KlantToevoegenGui.this.txtHuisnr.getText()),
										KlantToevoegenGui.this.txtGemeente.getText().trim(),
										Integer.parseInt(KlantToevoegenGui.this.txtPostcode.getText()), "");
							}
							KlantToevoegenGui.this.adres.toString();
							KlantToevoegenGui.this.persoon = new Persoon(
									KlantToevoegenGui.this.txtVoornaam.getText().trim(),
									KlantToevoegenGui.this.txtAchternaam.getText().trim(),
									KlantToevoegenGui.this.txtEmail.getText().trim(), KlantToevoegenGui.this.adres);
							KlantToevoegenGui.this.persoon.toString();
							String info = KlantToevoegenGui.this.txtInfo.getText().trim();
							KlantDAO.addKlant(KlantToevoegenGui.this.persoon, KlantToevoegenGui.this.adres, info);
							KlantToevoegenGui.this.close();
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerAdded"));
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}