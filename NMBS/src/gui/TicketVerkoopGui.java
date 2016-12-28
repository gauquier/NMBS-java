package gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;
import javax.swing.border.Border;
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
import com.toedter.calendar.JDateChooser;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.MedewerkerDAO;
import dao.PrijsDAO;
import dao.StationDAO;
import handler.VerkoopController;
import javafx.scene.control.ComboBox;
import source.Login;
import source.Pdf;
import source.Station;
import source.StationCsv;
import source.Ticket;
import source.VerkoopType;
import source.AutoComboBox;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class TicketVerkoopGui extends JPanel {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.TicketVerkoopGui");
	
	private JTable table;
	
	JDateChooser heenDatum = new JDateChooser(new Date());
	JDateChooser terugDatum = new JDateChooser(new Date());

	
	private SpinnerModel klas = new SpinnerNumberModel(2, 1, 2, 1);
	private JSpinner klasse = new JSpinner(klas);
	private SpinnerModel aant = new SpinnerNumberModel(1, 1, null, 1);
	private JSpinner aantal = new JSpinner(aant);
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
	
	private JButton btnPdf;
	private Border border = BorderFactory.createEmptyBorder();
	private Border borderError = BorderFactory.createLineBorder(Color.RED, 3);

	private Ticket ticket = null;
	private JTextField txtPrijs;
	

	public TicketVerkoopGui(boolean isOffline) {
		this.setVisible(true);
		setBackground(new Color(51, 153, 255));
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
		
		ArrayList<Station> stations;
		
		if (!isOffline) {
			stations = StationDAO.getAll();
		}
		else {
			stations = StationCsv.readCsv();
		}
		ArrayList<String> stationNamen = new ArrayList<String>();
		
		for(int i = 0; i < stations.size(); i++){
			stationNamen.add(stations.get(i).getNaam());
		}
		comboNaar.setKeyWord(stationNamen);
		comboVan.setKeyWord(stationNamen);

		txtPrijs = new JTextField();
		txtPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPrijs.setColumns(10);

		if (!isOffline) {
			txtPrijs.setText(String.valueOf(PrijsDAO.getPrijsByVerkoopType(
					VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()))));
		}
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		
		lblPrijs.setForeground(Color.WHITE);
		
		btnPdf = new JButton(bundle.getString("TicketVerkoopGui.btnPdf.text")); //$NON-NLS-1$
		btnPdf.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnPdf.addActionListener(new PdfListener());
		btnPdf.setVisible(false);
		
		JPanel datePanel = new JPanel();
		datePanel.add(heenDatum);
		datePanel.add(terugDatum);
		datePanel.setLayout(new GridLayout(2, 1));
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNaar)
										.addComponent(lblVan)
										.addComponent(lblTerugDatum)
										.addComponent(lblSoortBiljet)
										.addComponent(lblPrijs)
										.addComponent(lblDatum)
										.addComponent(lblKlasse, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAantal, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(datePanel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnVerkoop)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnPdf))
											.addComponent(comboNaar, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
											.addComponent(comboVan, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(rdbtnHeenEnTerug)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(rdbtnHeen))
											.addComponent(comboVerkoopType, 0, 201, Short.MAX_VALUE)
											.addComponent(txtPrijs)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(klasse, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(aantal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)))))
								.addComponent(lblTicketVerkoop))
							.addGap(175)
							.addComponent(paneTickettenVerkocht, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
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
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDatum)
									.addGap(18)
									.addComponent(lblTerugDatum)
									.addGap(13))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(datePanel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
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
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVerkoop)
								.addComponent(btnPdf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(paneTickettenVerkocht, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(41, Short.MAX_VALUE))
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
	public void setTickettenVerkocht(boolean visible){
		
		paneTickettenVerkocht.setVisible(visible);

		paneTickettenVerkocht.setText(aantal.getValue() + " " + bundle.getString("ticketsSold") +
				"\n\n" + bundle.getString("lblVan") + " " + comboVan.getSelectedItem() +
				"\n" + bundle.getString("lblNaar") + " " + comboNaar.getSelectedItem() +
				"\n" + bundle.getString("lblDatum") + " " +  heenDatum.getDateFormatString() + 
				"\n" + bundle.getString("lblTerugDatum") + " " + terugDatum.getDateFormatString() + 
				"\n" + bundle.getString("lblKlasse") + " " + klasse.getValue() +
				"\n" + getSelectedButton() +
				"\n" + bundle.getString("lblSoortBiljet") + " " +  comboVerkoopType.getSelectedItem() +
				"\n" + bundle.getString("lblPrijs") + " " + ticket.getPrijs()*ticket.getAantal() + "");
		btnPdf.setVisible(visible);
		
		
	}
	private String getSelectedButton(){if(buttonGroup.isSelected(rdbtnHeen.getModel())) return bundle.getString("rdbtnHeen"); else return bundle.getString("rdbtnHeenEnTerug");}
	
	public void setColor(boolean depZone, boolean arrZone, boolean klasse, boolean aantal, boolean heenDatum, boolean terugDatum, boolean prijs){
		String errorText = "de volgende velden zijn niet correct ingevuld:";
		if(depZone){
			comboVan.setBorder(border);
		}
		else{
			comboVan.setBorder(borderError);
			errorText = errorText + "\nstart station";
		}
		if(arrZone){
			comboNaar.setBorder(border);
		}
		else{
			comboNaar.setBorder(borderError);
			errorText = errorText + "\neind station";
		}
		if(klasse){
			this.klasse.setBorder(border);
		}
		else{
			this.klasse.setBorder(borderError);
			errorText = errorText + "\nklasse";
		}
		if(aantal){
			this.aantal.setBorder(border);
		}
		else{
			this.aantal.setBorder(borderError);
			errorText = errorText + "\naantal";
		}
		if(heenDatum){
			this.heenDatum.setBorder(border);
		}
		else{
			this.heenDatum.setBorder(borderError);
			errorText = errorText + "\nheen datum";
		}
		if(terugDatum){
			this.terugDatum.setBorder(border);
		}
		else{
			this.terugDatum.setBorder(borderError);
			errorText = errorText + "\nterug datum";
		}
		if(prijs){
			txtPrijs.setBorder(border);
		}else{
			txtPrijs.setBorder(borderError);
			errorText = errorText + "\nprijs";
		}
		if(errorText != "de volgende velden zijn niet correct ingevuld:")
			JOptionPane.showMessageDialog(new JFrame(), errorText);
	}

	class ButtonHandler implements ActionListener {
		private boolean isOffline;

		public ButtonHandler(boolean isOffline) {
			setOffline(isOffline);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		    if (e.getSource() == btnVerkoop) {
				if (txtPrijs.getText().isEmpty() || comboNaar.getSelectedItem() == null || comboVan.getSelectedItem() == null ) {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden in!");
				} else {

					if (!isOffline) {
						
						 ticket = new Ticket(0, MedewerkerDAO.getMedewerkerIdByUsername(Login.getCurrentUser()),(String) comboVan.getSelectedItem(), (String) comboNaar.getSelectedItem(), StationDAO.checkStation(Station.getCurrentStation()), Double.parseDouble(txtPrijs.getText()),VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()), 0, (int)klasse.getValue(), (int)aantal.getValue()
						,Calendar.getInstance().getTime(), heenDatum.getDate(),terugDatum.getDate() );
					}
					else {
						ticket = new Ticket(0, 0,(String) comboVan.getSelectedItem(), (String) comboNaar.getSelectedItem(), 0, Double.parseDouble(txtPrijs.getText()),VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()), 0, (int)klasse.getValue(), (int)aantal.getValue()
								,Calendar.getInstance().getTime(), heenDatum.getDate(),terugDatum.getDate() );
				}
				VerkoopController.ticketValidate(ticket, TicketVerkoopGui.this, isOffline);
				}
			}
		}
		/*
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
*/
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
	
	class PdfListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("printed how many times?");
			Pdf.TicketGenerator(ticket);
			
		}
		
	}
}