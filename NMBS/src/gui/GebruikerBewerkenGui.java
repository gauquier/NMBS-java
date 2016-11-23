package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import source.Validation;

public class GebruikerBewerkenGui extends JPanel {
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
	private JLabel lblVoornaamerror;
	private JLabel lblAchternaamerror;
	private JLabel lblStraaterror;
	private JLabel lblHuisnrerror;
	private JLabel lblBusError;
	private JLabel lblGemeenteerror;
	private JLabel lblPostcodeerror;
	private JLabel lblemailerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	
	public GebruikerBewerkenGui() {
		setBackground(new Color(0, 191, 255));
		
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
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		JLabel lblMedewerkerToevoegen = DefaultComponentFactory.getInstance().createTitle("Medewerker bewerken");
		
		JLabel lblVoornaamerror = new JLabel("");
		lblVoornaamerror.setForeground(Color.RED);
		
		JLabel lblAchternaamerror = new JLabel("");
		lblAchternaamerror.setForeground(Color.RED);
		
		JLabel lblStraaterror = new JLabel("");
		lblStraaterror.setForeground(Color.RED);
		
		JLabel lblHuisnrerror = new JLabel("");
		lblHuisnrerror.setForeground(Color.RED);
		
		JLabel lblBuserror = new JLabel("");
		lblBuserror.setForeground(Color.RED);
		
		JLabel lblGemeenteerror = new JLabel("");
		lblGemeenteerror.setForeground(Color.RED);
		
		JLabel lblPostcodeerror = new JLabel("");
		lblPostcodeerror.setForeground(Color.RED);
		
		JLabel lblEmailerror = new JLabel("");
		lblEmailerror.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmail)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMedewerkerToevoegen)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVoornaam)
										.addComponent(lblAchternaam)
										.addComponent(lblBus)
										.addComponent(lblPostcode)
										.addComponent(lblHuisnummer)
										.addComponent(lblStraat)
										.addComponent(lblGemeente))
									.addGap(40)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblPostcodeerror))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtHuisnr, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(txtBus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblBuserror)
												.addComponent(lblHuisnrerror)))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
												.addComponent(txtGemeente, Alignment.LEADING, 165, 165, Short.MAX_VALUE)
												.addComponent(txtAchternaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
												.addComponent(txtVoornaam, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
												.addComponent(txtStraat, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
												.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblVoornaamerror)
												.addComponent(lblAchternaamerror)
												.addComponent(lblStraaterror)
												.addComponent(lblGemeenteerror)
												.addComponent(lblEmailerror))
											.addGap(107)))))
							.addGap(83))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblMedewerkerToevoegen)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoornaam)
						.addComponent(txtVoornaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVoornaamerror))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAchternaam)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtAchternaam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAchternaamerror)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStraat)
						.addComponent(txtStraat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStraaterror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblHuisnummer)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtHuisnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHuisnrerror)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtBus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblBuserror))
						.addComponent(lblBus))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGemeente)
						.addComponent(txtGemeente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGemeenteerror))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(txtPostcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPostcodeerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmailerror))
					.addGap(18)
					.addComponent(btnToevoegen, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(102, Short.MAX_VALUE))
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
				lblVoornaamerror.setText("");
				lblAchternaamerror.setText("");
				lblStraaterror.setText("");
				lblHuisnrerror.setText("");
				lblBusError.setText("");
				lblPostcodeerror.setText("");
				lblGemeenteerror.setText("");
				lblemailerror.setText("");
				txtVoornaam.setBorder(border);
				txtAchternaam.setBorder(border);
				txtStraat.setBorder(border);
				txtHuisnr.setBorder(border);
				txtPostcode.setBorder(border);
				txtGemeente.setBorder(border);
				txtEmail.setBorder(border);
				if (!txtVoornaam.getText().isEmpty() && !txtAchternaam.getText().isEmpty() && !txtStraat.getText().isEmpty() && !txtHuisnr.getText().isEmpty()
						&& !txtGemeente.getText().isEmpty()&& !txtPostcode.getText().isEmpty() && !txtEmail.getText().isEmpty()){
					if (!Validation.checkFirstName(txtVoornaam.getText())){
						lblVoornaamerror.setText("Gelieve een juiste voornaam in te vullen!");
						txtVoornaam.setBorder(bordererror);

					}
					if (!Validation.checkLastName(txtAchternaam.getText())){
						lblAchternaamerror.setText("Gelieve een juiste Achternaam in te vullen!");
						txtAchternaam.setBorder(bordererror);

					}
					if (!Validation.checkAlphabetical(txtStraat.getText())){
						lblStraaterror.setText("Gelieve een bestaande straat in te vullen!");
						txtStraat.setBorder(bordererror);

					}
					if (!Validation.checkHouseNumber(txtHuisnr.getText())){
						lblHuisnrerror.setText("Gelieve een bestaand huisnummer in te vullen!");
						txtHuisnr.setBorder(bordererror);

					}
					if (!Validation.checkBoxNumber(txtBus.getText()) && !txtBus.getText().isEmpty()){
						lblBusError.setText("Gelieve een bestaande bus in te vullen!");
						txtBus.setBorder(bordererror);

					}
					if (!Validation.checkPostalCode(txtPostcode.getText())){
						lblPostcodeerror.setText("Gelieve een bestaande postcode in te vullen!");
						txtPostcode.setBorder(bordererror);

					}
					if (!Validation.checkAlphabetical(txtGemeente.getText())){
						lblGemeenteerror.setText("Gelieve een bestaande gemeente in te vullen!");
						txtGemeente.setBorder(bordererror);

					}
					if (!Validation.checkEmail(txtEmail.getText())){
						lblemailerror.setText("Gelieve een juist email in te vullen!");
						txtEmail.setBorder(bordererror);

					}
					else{
						close();
						JOptionPane.showMessageDialog(new JFrame(),"Medewerker is bewerkt!");
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Please fill in all required fields!");
				}
			}
		}
	}
}
