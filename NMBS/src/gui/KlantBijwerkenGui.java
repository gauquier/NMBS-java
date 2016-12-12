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

public class KlantBijwerkenGui extends JPanel {
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
	private PersoonDao persoonDao= new PersoonDao();
	
	private JLabel lblExtraInformatie;
	private JTextField txtInfo;

	private int klantId=0;
	private int persoonId = 0;
	private int adresId = 0;
	private String huidigeEmail;
	private String email;
	
	public KlantBijwerkenGui(Klant k) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		klantId=k.getKlantId();
		persoonId =k.getId();
		adresId=k.getAdres().getAdresId();
		huidigeEmail=k.getEmail();
		
		
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
		txtVoornaam.setText(k.getVoornaam());
		
		txtAchternaam = new JTextField();
		txtAchternaam.setColumns(10);
		txtAchternaam.setText(k.getAchternaam());
		
		txtStraat = new JTextField();
		txtStraat.setColumns(10);
		txtStraat.setText(k.getAdres().getStraat());

		txtHuisnr = new JTextField();
		txtHuisnr.setColumns(10);
		txtHuisnr.setText(new Integer(k.getAdres().getHuisnr()).toString());
		
		txtBus = new JTextField();
		txtBus.setColumns(10);
		txtBus.setText(new Integer(k.getAdres().getBus()).toString());

		txtGemeente = new JTextField();
		txtGemeente.setColumns(10);
		txtGemeente.setText(k.getAdres().getWoonplaats());

		txtPostcode = new JTextField();
		txtPostcode.setColumns(10);
		txtPostcode.setText(new Integer(k.getAdres().getPostcode()).toString());

		btnBijwerken = new JButton("Bijwerken");
		btnBijwerken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBijwerken.setBackground(Color.ORANGE);
		btnBijwerken.addActionListener(new MenuItemHandler());

		lblEmail = new JLabel("Email*:");
		lblEmail.setForeground(Color.WHITE);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setText(k.getEmail());

		JLabel lblKlantBijwerken = DefaultComponentFactory.getInstance().createTitle("Klant bijwerken");
		lblKlantBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 14));


		JLabel label_1 = new JLabel("* Verplichte velden");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label_1.setForeground(Color.WHITE);
		
		lblExtraInformatie = new JLabel("Extra informatie:");
		lblExtraInformatie.setForeground(Color.WHITE);
		
		txtInfo = new JTextField();
		txtInfo.setColumns(10);
		txtInfo.setText(k.getInfo());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVoornaam)
								.addComponent(lblAchternaam)
								.addComponent(lblBus)
								.addComponent(lblPostcode)
								.addComponent(lblHuisnummer)
								.addComponent(lblGemeente)
								.addComponent(lblStraat))
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtBus, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtHuisnr, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
									.addGap(510))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(250, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtGemeente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtStraat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtAchternaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtVoornaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(label_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(206)
											.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(86)
											.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
									.addContainerGap())))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblKlantBijwerken)
							.addContainerGap(320, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblExtraInformatie, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addContainerGap(567, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(lblKlantBijwerken)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVoornaam)
								.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAchternaam)
								.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStraat))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblHuisnummer)
								.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblBus)
								.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGemeente))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPostcode)
								.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEmail)
									.addGap(31))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
									.addGap(29)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblExtraInformatie)
								.addComponent(txtInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_1)))
					.addGap(29))
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


				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty()
						&& !txtStraat.getText().isEmpty() && !txtGemeente.getText().isEmpty()
						&& !txtPostcode.getText().isEmpty() && !txtHuisnr.getText().isEmpty() && !txtEmail.getText().isEmpty()) {
				
				email = txtEmail.getText().trim();
				
				
				if(persoonDao.checkEmail(email) > 0 && !(email.equals(huidigeEmail))){
					JOptionPane.showMessageDialog(new JFrame(), "Deze klant bestaat al.");
					txtEmail.setText("");
					return;
					
				}
				else {
				

						if (!txtBus.getText().isEmpty()) {
							adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()),
									txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()),
									Integer.parseInt(txtBus.getText()));
						} else {
							adres = new Adres(txtStraat.getText().trim(), Integer.parseInt(txtHuisnr.getText()),
									txtGemeente.getText().trim(), Integer.parseInt(txtPostcode.getText()), 0);
						}
						adres.toString();

						persoon = new Persoon(txtVoornaam.getText().trim(), txtAchternaam.getText().trim(),
								txtEmail.getText().trim(), adres);
						persoon.toString();

						String info = txtInfo.getText().trim();

						
						KlantDAO.bijwerkenKlant(klantId, persoonId, persoon, info, adresId, adres);
				
						JOptionPane.showMessageDialog(new JFrame(), "Klant is bijgewerkt!");
						AdminGui.setHuidigeKeuze(new KlantBewerkenGui());
					}
				}

				else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}
		}
	}
}