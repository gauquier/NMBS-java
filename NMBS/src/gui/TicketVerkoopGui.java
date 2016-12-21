package gui;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.MedewerkerDAO;
import dao.PrijsDAO;
import dao.StationDAO;
import handler.VerkoopController;
import javafx.scene.control.ComboBox;
import source.Login;
import source.Station;
import source.Ticket;
import source.VerkoopType;
import source.AutoComboBox;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class TicketVerkoopGui extends JPanel {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.TicketVerkoopGui");
	
	private JTable table;
	private JSpinner heenDag = new JSpinner();
	private JSpinner heenMaand = new JSpinner();
	private JSpinner heenJaar = new JSpinner();
	private JSpinner terugDag = new JSpinner();
	private JSpinner terugMaand = new JSpinner();
	private JSpinner terugJaar = new JSpinner();
	
	private JSpinner klasse = new JSpinner();
	private JSpinner aantal = new JSpinner();
	private JButton btnVerkoop = new JButton(bundle.getString("btnVerkoop"));
	
	private JLabel lblVan = new JLabel(bundle.getString("lblVan"));
	private JLabel lblNaar = new JLabel(bundle.getString("lblNaar"));
	private JLabel lblDatum = new JLabel(bundle.getString("lblDatum"));
	private JLabel lblTerugDatum = new JLabel(bundle.getString("lblTerugDatum"));
	private JLabel lblKlasse = new JLabel(bundle.getString("lblKlasse"));
	private JLabel lblAantal = new JLabel(bundle.getString("lblAantal"));
	private JTextPane paneTickettenVerkocht = new JTextPane();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnHeen = new JRadioButton(bundle.getString("rdbtnHeen"));
	private JRadioButton rdbtnHeenEnTerug = new JRadioButton(bundle.getString("rdbtnHeenEnTerug"));
	private JLabel lblPrijs = new JLabel(bundle.getString("lblPrijs"));
	private JLabel lblSoortBiljet = new JLabel(bundle.getString("lblSoortBiljet"));
	private JComboBox<String> comboVerkoopType = new JComboBox<String>();
	private AutoComboBox comboNaar = new AutoComboBox();
	private AutoComboBox comboVan = new AutoComboBox();

	private Ticket ticket = null;
	private JTextField txtPrijs;
	

	public TicketVerkoopGui(boolean isOffline) {
		this.setVisible(true);
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		lblVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblVan.setForeground(Color.WHITE);
		lblNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblNaar.setForeground(Color.WHITE);
		lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblDatum.setForeground(Color.WHITE);
		comboVerkoopType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboVerkoopType.setToolTipText("");

		comboVerkoopType.addItem("standaard");
		comboVerkoopType.addItem("student");
		comboVerkoopType.addItem("groep");
		comboVerkoopType.addItem("60+");

		if (!isOffline) {
			comboVerkoopType.addItemListener(new VerkoopTypeListener());
		}
		lblSoortBiljet.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblSoortBiljet.setForeground(Color.WHITE);
		btnVerkoop.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		btnVerkoop.addActionListener(new ButtonHandler(isOffline));
		rdbtnHeen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		rdbtnHeen.setForeground(Color.BLACK);
		rdbtnHeenEnTerug.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnHeenEnTerug.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnHeen);
		buttonGroup.add(rdbtnHeenEnTerug);
		buttonGroup.setSelected(rdbtnHeen.getModel(), true);

		JLabel lblTicketVerkoop = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblTicketVerkoop"));
		lblTicketVerkoop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTerugDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		lblTerugDatum.setForeground(Color.WHITE);

		table = new JTable();
		SimpleDateFormat dagFormat = new SimpleDateFormat("dd");
		SimpleDateFormat maandFormat = new SimpleDateFormat("MM");
		SimpleDateFormat jaarFormat = new SimpleDateFormat("yyyy");
		heenDag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		heenDag.setValue(Integer.parseInt(dagFormat.format(new Date())));
		heenMaand.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		heenMaand.setValue(Integer.parseInt(maandFormat.format(new Date())));
		heenJaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		heenJaar.setValue(Integer.parseInt(jaarFormat.format(new Date())));
		terugDag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		terugDag.setValue(Integer.parseInt(dagFormat.format(new Date())));
		terugMaand.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		terugMaand.setValue(Integer.parseInt(maandFormat.format(new Date())));
		terugJaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		terugJaar.setValue(Integer.parseInt(jaarFormat.format(new Date())));
		lblKlasse.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblKlasse.setForeground(Color.WHITE);
		klasse.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		klasse.setValue(2);
		lblAantal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblAantal.setForeground(Color.WHITE);
		aantal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		aantal.setValue(1);
		
		paneTickettenVerkocht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paneTickettenVerkocht.setBackground((UIManager.getColor("CheckBoxMenuItem.selectionBackground")));
		paneTickettenVerkocht.setVisible(false);

		if (!isOffline) {
			ArrayList<Station> stations = StationDAO.getAll();
			ArrayList<String> stationNamen = new ArrayList<String>();
			for(int i = 0; i < stations.size(); i++){
				stationNamen.add(stations.get(i).getNaam());
			}
			comboNaar.setKeyWord(stationNamen);
			comboVan.setKeyWord(stationNamen);
		}

		txtPrijs = new JTextField();
		txtPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPrijs.setColumns(10);

		if (!isOffline) {
			txtPrijs.setText(String.valueOf(PrijsDAO.getPrijsByVerkoopType(
					VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()))));
		}
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		
		lblPrijs.setForeground(Color.WHITE);
		
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNaar)
										.addComponent(lblVan)
										.addComponent(lblTerugDatum)
										.addComponent(lblSoortBiljet)
										.addComponent(lblPrijs)
										.addComponent(lblDatum)
										.addComponent(lblKlasse, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAantal, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(48)
									.addComponent(lblTicketVerkoop)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnVerkoop)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboNaar, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
										.addComponent(comboVan, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(rdbtnHeenEnTerug)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnHeen))
										.addComponent(comboVerkoopType, 0, 201, Short.MAX_VALUE)
										.addComponent(txtPrijs)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(heenDag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(terugDag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(terugMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(heenMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(terugJaar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(18)
													.addComponent(heenJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(klasse, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(aantal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(175)
									.addComponent(paneTickettenVerkocht, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTicketVerkoop)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVan)
								.addComponent(comboVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNaar))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(heenDag, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDatum)
								.addComponent(heenMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(heenJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTerugDatum)
								.addComponent(terugJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(terugDag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(terugMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKlasse)
								.addComponent(klasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(24)
									.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(aantal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAantal))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnHeenEnTerug)
								.addComponent(rdbtnHeen))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboVerkoopType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSoortBiljet))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrijs)
								.addComponent(txtPrijs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addComponent(btnVerkoop))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(paneTickettenVerkocht, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		comboVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		setLayout(groupLayout);
	}	

	public Ticket getTicket(){
		return ticket;
	}
	public void setTicket(Ticket ticket){
		this.ticket = ticket;
	}
	public void setTickettenVerkocht(boolean visible, Ticket ticket){
		paneTickettenVerkocht.setVisible(visible);

		paneTickettenVerkocht.setText(aantal.getValue() + " " + bundle.getString("ticketsSold") +
				"\n\n" + bundle.getString("lblVan") + " " + comboVan.getSelectedItem() +
				"\n" + bundle.getString("lblNaar") + " " + comboNaar.getSelectedItem() +
				"\n" + bundle.getString("lblDatum") + " " +  heenDag.getValue() + "-" + heenMaand.getValue() + "-" + heenJaar.getValue() + 
				"\n" + bundle.getString("lblTerugDatum") + " " + terugDag.getValue() + "-" + terugMaand.getValue() + "-" + terugJaar.getValue() + 
				"\n" + bundle.getString("lblKlasse") + " " + klasse.getValue() +
				"\n" + getSelectedButton() +
				"\n" + bundle.getString("lblSoortBiljet") + " " +  comboVerkoopType.getSelectedItem() +
				"\n" + bundle.getString("lblPrijs") + " " + ticket.getPrijs()*ticket.getAantal() + "");
	}
	private String getSelectedButton(){if(buttonGroup.isSelected(rdbtnHeen.getModel())) return bundle.getString("rdbtnHeen"); else return bundle.getString("rdbtnHeenEnTerug");}
	
	public void setColor(boolean depZone, boolean arrZone, boolean klasse, boolean aantal, boolean heenDatum, boolean terugDatum){
		if(depZone){
			lblVan.setForeground(Color.WHITE);
		}
		else{
			lblVan.setForeground(Color.RED);
		}
		if(arrZone){
			lblNaar.setForeground(Color.WHITE);
		}
		else{
			lblNaar.setForeground(Color.RED);
		}
		if(klasse){
			lblKlasse.setForeground(Color.WHITE);
		}
		else{
			lblKlasse.setForeground(Color.RED);
		}
		if(aantal){
			lblAantal.setForeground(Color.WHITE);
		}
		else{
			lblAantal.setForeground(Color.RED);
		}
		if(heenDatum){
			lblDatum.setForeground(Color.WHITE);
		}
		else{
			lblDatum.setForeground(Color.RED);
		}
		if(terugDatum){
			lblTerugDatum.setForeground(Color.WHITE);
		}
		else{
			lblTerugDatum.setForeground(Color.RED);
		}
	}

	class ButtonHandler implements ActionListener {
		private boolean isOffline;

		public ButtonHandler(boolean isOffline) {
			setOffline(isOffline);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		    if (e.getSource() == btnVerkoop) {
				if (txtPrijs.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden in!");
				} else {
					if (!isOffline) {
						
						 ticket = new Ticket(0, MedewerkerDAO.getMedewerkerIdByUsername(Login.getCurrentUser()),(String) comboVan.getSelectedItem(), (String) comboNaar.getSelectedItem(), StationDAO.checkStation(Station.getCurrentStation()), Double.parseDouble(txtPrijs.getText()),VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()), 0, (int)klasse.getValue(), (int)aantal.getValue()
						,Calendar.getInstance().getTime(), converter((int) heenDag.getValue(),(int) heenMaand.getValue(), (int)heenJaar.getValue()),converter((int) terugDag.getValue(), (int) terugMaand.getValue(), (int) terugJaar.getValue()) );
					VerkoopController.ticketValidate(ticket, TicketVerkoopGui.this, isOffline);
						
					}
					else {
						ticket = new Ticket(0, 0,(String) comboVan.getSelectedItem(), (String) comboNaar.getSelectedItem(), 0, Double.parseDouble(txtPrijs.getText()),VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()), 0, (int)klasse.getValue(), (int)aantal.getValue()
								,Calendar.getInstance().getTime(), converter((int) heenDag.getValue(),(int) heenMaand.getValue(), (int)heenJaar.getValue()),converter((int) terugDag.getValue(), (int) terugMaand.getValue(), (int) terugJaar.getValue()) );
							VerkoopController.ticketValidate(ticket, TicketVerkoopGui.this, isOffline);
					}
					VerkoopController.ticketValidate(ticket, TicketVerkoopGui.this, isOffline);
				}
			}
		}
		private Date converter(int dag, int maand, int jaar){
			SimpleDateFormat setFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = "";
			if(dag < 10){ dateString = dateString + "0";}
			dateString = dateString + dag + "-";
			if(maand < 10){ dateString = dateString + "0";}
			dateString = dateString + maand + "-" + jaar;
			try {
				return setFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public boolean isOffline() {
			return isOffline;
		}

		public void setOffline(boolean isOffline) {
			this.isOffline = isOffline;
		}

	}

	// Gebaseerd op
	// http://stackoverflow.com/questions/58939/jcombobox-selection-change-listener
	class VerkoopTypeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				txtPrijs.setText(String.valueOf(PrijsDAO.getPrijsByVerkoopType(
						VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()))));
			}
		}
	}
}