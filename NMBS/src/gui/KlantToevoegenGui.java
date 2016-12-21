package gui;

import dao.PersoonDao;
import source.Adres;
import dao.KlantDAO;
import source.Klant;
import source.Persoon;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;



import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class KlantToevoegenGui extends JPanel {
	private JTextField txtVoornaam;
	private JTextField txtAchternaam;
	private JTextField txtStraat;
	private JTextField txtHuisnr;
	private JTextField txtBus;
	private JTextField txtGemeente;
	private JTextField txtPostcode;
	private JButton btnToevoegen;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private Adres adres;
	private Persoon persoon;
	private List<Persoon> mijnpersonen;
	private PersoonDao persoonDao= new PersoonDao();
	

	private JLabel lblExtraInformatie;
	private JTextField txtInfo;

	public KlantToevoegenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVoornaam = new JLabel("Voornaam*:");
		lblVoornaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVoornaam.setForeground(Color.WHITE);

		JLabel lblAchternaam = new JLabel("Achternaam*:");
		lblAchternaam.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAchternaam.setForeground(Color.WHITE);

		JLabel lblStraat = new JLabel("Straat*:");
		lblStraat.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStraat.setForeground(Color.WHITE);

		JLabel lblHuisnummer = new JLabel("Huisnummer*:");
		lblHuisnummer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHuisnummer.setForeground(Color.WHITE);

		JLabel lblGemeente = new JLabel("Gemeente*:");
		lblGemeente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblGemeente.setForeground(Color.WHITE);

		JLabel lblPostcode = new JLabel("Postcode*:");
		lblPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPostcode.setForeground(Color.WHITE);

		JLabel lblBus = new JLabel("Bus:");
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

		btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.setFont(new Font("Dialog", Font.BOLD, 20));
		btnToevoegen.setBackground(Color.ORANGE);
		btnToevoegen.addActionListener(new MenuItemHandler());

		lblEmail = new JLabel("Email*:");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmail.setForeground(Color.WHITE);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtEmail.setColumns(10);

		JLabel lblKlantToevoegen = DefaultComponentFactory.getInstance().createTitle("Klant toevoegen");
		lblKlantToevoegen.setFont(new Font("Tahoma", Font.PLAIN, 22));


		JLabel label_1 = new JLabel("* Verplichte velden");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setForeground(Color.WHITE);
		
		lblExtraInformatie = new JLabel("Extra informatie:");
		lblExtraInformatie.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblExtraInformatie.setForeground(Color.WHITE);
		
		txtInfo = new JTextField();
		txtInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtInfo.setColumns(10);
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
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtEmail, Alignment.LEADING)
											.addComponent(txtInfo, Alignment.LEADING)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addGap(19))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtGemeente, Alignment.LEADING)
										.addComponent(txtStraat, Alignment.LEADING)
										.addComponent(txtAchternaam, Alignment.LEADING)
										.addComponent(txtVoornaam, Alignment.LEADING))
									.addGap(242))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtBus, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(txtHuisnr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))))
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
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblAchternaam))
						.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStraat))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHuisnummer)
						.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBus)
						.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblGemeente)))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExtraInformatie)
						.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addGap(56))))
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

		
			if (e.getSource() == btnToevoegen) {


				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty()
						&& !txtStraat.getText().isEmpty() && !txtGemeente.getText().isEmpty()
						&& !txtPostcode.getText().isEmpty() && !txtHuisnr.getText().isEmpty() && !txtEmail.getText().isEmpty()) {
				
				String email = txtEmail.getText().trim();
				if(persoonDao.checkEmail(email) > 0){
					JOptionPane.showMessageDialog(new JFrame(), "Deze klant bestaat al.");
					txtEmail.setText("");
					return;
				}
				else {
						

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
						JOptionPane.showMessageDialog(new JFrame(), "Klant is toegevoegd!");
					}
				}

				else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}
		}
	}
}