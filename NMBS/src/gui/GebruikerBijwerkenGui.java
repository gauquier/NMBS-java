package gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.List;
import java.util.ResourceBundle;

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
		bundle = ResourceBundle.getBundle("localization.GebruikerBijwerkenGui");
		
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		medewerkerId = m.getMedewerkerId();
		persoonId = m.getId();
		adresId = m.getAdres().getAdresId();

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
		txtVoornaam.setText(m.getVoornaam());

		txtAchternaam = new JTextField();
		txtAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtAchternaam.setColumns(10);
		txtAchternaam.setText(m.getAchternaam());

		txtStraat = new JTextField();
		txtStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtStraat.setColumns(10);
		txtStraat.setText(m.getAdres().getStraat());

		txtHuisnr = new JTextField();
		txtHuisnr.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtHuisnr.setColumns(10);
		txtHuisnr.setText(new Integer(m.getAdres().getHuisnr()).toString());

		txtBus = new JTextField();
		txtBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtBus.setColumns(10);
		txtBus.setText(new Integer(m.getAdres().getBus()).toString());

		txtGemeente = new JTextField();
		txtGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtGemeente.setColumns(10);
		txtGemeente.setText(m.getAdres().getWoonplaats());

		txtPostcode = new JTextField();
		txtPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPostcode.setColumns(10);
		txtPostcode.setText(new Integer(m.getAdres().getPostcode()).toString());

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
		txtEmail.setText(m.getEmail());

		JLabel lblMedewerkerBijwerken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblMedewerkerBijwerken"));
		lblMedewerkerBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		radiobuttons = new ButtonGroup();
		rbtnAdmin = new JRadioButton(bundle.getString("rbtnAdmin"));
		rbtnAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rbtnAdmin.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		rbtnAdmin.setForeground(Color.WHITE);

		rbtnUser = new JRadioButton(bundle.getString("rbtnUser"));
		rbtnUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
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

		JLabel label = new JLabel(bundle.getString("label"));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);

		JLabel label_1 = new JLabel(bundle.getString("label_1"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVoornaam)
								.addComponent(lblAchternaam)
								.addComponent(lblBus)
								.addComponent(lblPostcode)
								.addComponent(lblHuisnummer)
								.addComponent(lblGemeente)
								.addComponent(lblStraat)
								.addComponent(lblEmail))
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
										.addComponent(txtVoornaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
										.addComponent(txtAchternaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
										.addComponent(txtStraat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
										.addComponent(txtGemeente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVoornaamError)
										.addComponent(lblAchternaamError)
										.addComponent(lblStraatError)
										.addComponent(lblGemeenteError)
										.addComponent(lblEmailError))
									.addGap(316))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtBus, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(txtHuisnr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHuisnrError)
										.addComponent(lblBusError))
									.addContainerGap(512, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblPostcodeError)
									.addContainerGap(511, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMedewerkerBijwerken)
							.addContainerGap(641, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rbtnUser)
							.addGap(18)
							.addComponent(rbtnAdmin))))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(622, Short.MAX_VALUE)
					.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblMedewerkerBijwerken)
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
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmailError))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(rbtnUser)
						.addComponent(rbtnAdmin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(138))
		);

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
						lblVoornaamError.setText(bundle.getString("lblVoornaamError"));
						txtVoornaam.setBorder(bordererror);
					}
					if (!Validation.checkLastName(txtAchternaam.getText())) {
						lblAchternaamError.setText(bundle.getString("lblAchternaamError"));
						txtAchternaam.setBorder(bordererror);
					}
					if (!Validation.checkAlphabetical(txtStraat.getText())) {
						lblStraatError.setText(bundle.getString("lblStraatError"));
						txtStraat.setBorder(bordererror);
					}
					if (!Validation.checkHouseNumber(txtHuisnr.getText())) {
						lblHuisnrError.setText(bundle.getString("lblHuisnrError"));
						txtHuisnr.setBorder(bordererror);
					}
					if (!Validation.checkAlphabetical(txtGemeente.getText())) {
						lblGemeenteError.setText(bundle.getString("lblGemeenteError"));
						txtGemeente.setBorder(bordererror);
					}
					if (!Validation.checkPostalCode(txtPostcode.getText())) {
						lblPostcodeError.setText(bundle.getString("lblPostcodeError"));
						txtPostcode.setBorder(bordererror);
					}
					if (!txtEmail.getText().isEmpty() && !Validation.checkEmail(txtEmail.getText())) {
						lblEmailError.setText(bundle.getString("lblEmailError"));
						txtEmail.setBorder(bordererror);
					}
					if (!txtBus.getText().isEmpty() && !Validation.checkBoxNumber(txtBus.getText())) {
						lblBusError.setText(bundle.getString("lblBusError"));
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
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userEdited"));
						AdminGui.setHuidigeKeuze(new GebruikersBeheerGui());
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), bundle.getString("requiredFieldsWarning"));
				}
			}
		}
	}
}