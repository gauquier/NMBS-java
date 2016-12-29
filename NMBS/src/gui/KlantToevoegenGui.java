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
	private JLabel lblEmail, lblEmailError, lblvoornaamError, lblAchternaamError, lblStraatError, lblHuisnrError, lblBusError, lblGemeenteError, lblPostcodeError;
	private JTextField txtEmail;
	private Adres adres;
	private Persoon persoon;
	private PersoonDao persoonDao = new PersoonDao();
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	private boolean checkvoornaam, checkachternaam, checkstraat, checkhuisnr, checkbus, checkgemeente, checkpostcode, checkemail;

	private JLabel lblExtraInformatie;
	private JTextField txtInfo;

	public KlantToevoegenGui() {
		bundle = ResourceBundle.getBundle("localization.KlantToevoegenGui");

		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

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

		txtAchternaam = new JTextField();
		txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtAchternaam.setColumns(10);

		txtStraat = new JTextField();
		txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtStraat.setColumns(10);

		txtHuisnr = new JTextField();
		txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtHuisnr.setColumns(10);

		txtBus = new JTextField();
		txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtBus.setColumns(10);

		txtGemeente = new JTextField();
		txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtGemeente.setColumns(10);

		txtPostcode = new JTextField();
		txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPostcode.setColumns(10);

		lblEmail = new JLabel(bundle.getString("lblEmail"));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		btnToevoegen = new JButton(bundle.getString("btnToevoegen"));
		btnToevoegen.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnToevoegen.setBackground(Color.ORANGE);
		btnToevoegen.addActionListener(new MenuItemHandler());

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtEmail.setColumns(10);

		JLabel lblKlantToevoegen = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblKlantToevoegen"));
		lblKlantToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);

		lblExtraInformatie = new JLabel(bundle.getString("lblExtraInformatie"));
		lblExtraInformatie.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblExtraInformatie.setForeground(Color.WHITE);

		txtInfo = new JTextField();
		txtInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtInfo.setColumns(10);
		
		lblvoornaamError = new JLabel("");
		lblvoornaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblvoornaamError.setForeground(Color.RED);
		
		lblAchternaamError = new JLabel("");
		lblAchternaamError.setForeground(Color.RED);
		lblAchternaamError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblStraatError = new JLabel("");
		lblStraatError.setForeground(Color.RED);
		lblStraatError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblHuisnrError = new JLabel("");
		lblHuisnrError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuisnrError.setForeground(Color.RED);
		
		lblBusError = new JLabel("");
		lblBusError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBusError.setForeground(Color.RED);
		
		lblGemeenteError = new JLabel("");
		lblGemeenteError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGemeenteError.setForeground(Color.RED);
		
		lblPostcodeError = new JLabel("");
		lblPostcodeError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPostcodeError.setForeground(Color.RED);
		
		lblEmailError = new JLabel("");
		lblEmailError.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmailError.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(lblKlantToevoegen)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblVoornaam)
								.addComponent(lblAchternaam)
								.addComponent(lblBus)
								.addComponent(lblPostcode)
								.addComponent(lblGemeente)
								.addComponent(lblStraat)
								.addComponent(lblHuisnummer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblExtraInformatie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblBusError, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblHuisnrError, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtEmail)
												.addComponent(txtInfo)
												.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(lblPostcodeError, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblvoornaamError)
												.addComponent(lblAchternaamError)
												.addComponent(lblGemeenteError)
												.addComponent(lblStraatError))
											.addGap(379))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblEmailError)
											.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
											.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
											.addGap(19)))))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblKlantToevoegen)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblVoornaam))
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblvoornaamError))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblAchternaam))
						.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAchternaamError))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStraat)
						.addComponent(lblStraatError))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHuisnummer)
						.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHuisnrError))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBus)
						.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBusError))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblGemeente))
						.addComponent(lblGemeenteError))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPostcodeError))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(lblEmailError)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExtraInformatie)
						.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_1)
					.addGap(11)
					.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
				lblvoornaamError.setText("");
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
						&& !txtEmail.getText().isEmpty()) 
				{
					if (!Validation.checkFirstName(txtVoornaam.getText())) {
						lblvoornaamError.setText(bundle.getString("lblVoornaamError"));
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
					if (checkvoornaam == false || checkachternaam == false || checkstraat == false || checkhuisnr == false || checkgemeente == false || checkpostcode == false || checkemail == false || checkbus == false){
						return;
					}
					else{
						String email = txtEmail.getText().trim();
						if (persoonDao.checkEmail(email) > 0) {
							JOptionPane.showMessageDialog(new JFrame(), "Deze klant bestaat al.");
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
							KlantDAO.addKlant(persoon, adres, info);
							close();
							JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerAdded"));
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}