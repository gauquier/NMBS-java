package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JDateChooser;

import dao.MedewerkerDAO;
import dao.PrijsDAO;
import dao.StationDAO;
import handler.VerkoopController;
import source.AutoComboBox;
import source.Login;
import source.Pdf;
import source.Station;
import source.StationCsv;
import source.Ticket;
import source.VerkoopType;

@SuppressWarnings("serial")
public class TicketVerkoopGui extends JPanel {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.TicketVerkoopGui");

	private JTable table;

	private JDateChooser heenDatum = new JDateChooser(new Date());
	private JDateChooser terugDatum = new JDateChooser(new Date());

	private SpinnerModel klas = new SpinnerNumberModel(2, 1, 2, 1);
	private JSpinner klasse = new JSpinner(this.klas);
	private SpinnerModel aant = new SpinnerNumberModel(1, 1, null, 1);
	private JSpinner aantal = new JSpinner(this.aant);
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
		this.setBackground(new Color(51, 153, 255));
		this.lblVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblVan.setForeground(Color.WHITE);
		this.lblNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblNaar.setForeground(Color.WHITE);
		this.lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblDatum.setForeground(Color.WHITE);
		this.comboVerkoopType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.comboVerkoopType.setToolTipText("");

		this.comboVerkoopType.addItem("standaard");
		this.comboVerkoopType.addItem("student");
		this.comboVerkoopType.addItem("groep");
		this.comboVerkoopType.addItem("60+");

		if (!isOffline) {
			this.comboVerkoopType.addItemListener(new VerkoopTypeListener());
		}
		this.lblSoortBiljet.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblSoortBiljet.setForeground(Color.WHITE);
		this.btnVerkoop.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.btnVerkoop.addActionListener(new ButtonHandler(isOffline));
		this.rdbtnHeen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.rdbtnHeen.setForeground(Color.BLACK);
		this.rdbtnHeenEnTerug.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.rdbtnHeenEnTerug.setForeground(Color.BLACK);
		this.buttonGroup.add(this.rdbtnHeen);
		this.buttonGroup.add(this.rdbtnHeenEnTerug);
		this.buttonGroup.setSelected(this.rdbtnHeen.getModel(), true);
		this.rdbtnHeen.setBackground(new Color(51, 153, 255));
		this.rdbtnHeenEnTerug.setBackground(new Color(51, 153, 255));
		JLabel lblTicketVerkoop = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblTicketVerkoop"));
		lblTicketVerkoop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblTerugDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblTerugDatum.setForeground(Color.WHITE);

		this.table = new JTable();
		new SimpleDateFormat("dd");
		new SimpleDateFormat("MM");
		new SimpleDateFormat("yyyy");
		this.lblKlasse.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblKlasse.setForeground(Color.WHITE);
		this.klasse.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.klasse.setValue(2);
		this.lblAantal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblAantal.setForeground(Color.WHITE);
		this.aantal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.aantal.setValue(1);

		this.paneTickettenVerkocht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.paneTickettenVerkocht.setBackground(new Color(51, 153, 255));
		this.paneTickettenVerkocht.setVisible(false);
		
		
		heenDatum.setDateFormatString("dd-MM-yyyy");
		terugDatum.setDateFormatString("dd-MM-yyyy");

		ArrayList<Station> stations;

		if (!isOffline) {
			stations = StationDAO.getAll();
		} else {
			stations = StationCsv.readCsv();
		}
		ArrayList<String> stationNamen = new ArrayList<String>();

		for (int i = 0; i < stations.size(); i++) {
			stationNamen.add(stations.get(i).getNaam());
		}
		this.comboNaar.setKeyWord(stationNamen);
		this.comboVan.setKeyWord(stationNamen);
		comboVan.setSelectedItem(Station.getCurrentStation());



		this.txtPrijs = new JTextField();
		this.txtPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtPrijs.setColumns(10);

		if (!isOffline) {
			this.txtPrijs.setText(String.valueOf(PrijsDAO.getPrijsByVerkoopType(
					VerkoopType.VerkoopTypeCasting((String) this.comboVerkoopType.getSelectedItem()))));
		}
		this.lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.lblPrijs.setForeground(Color.WHITE);

		this.btnPdf = new JButton(bundle.getString("TicketVerkoopGui.btnPdf.text")); //$NON-NLS-1$
		this.btnPdf.setFont(new Font("Dialog", Font.PLAIN, 20));
		this.btnPdf.addActionListener(new PdfListener());
		this.btnPdf.setVisible(false);

		JPanel datePanel = new JPanel();
		datePanel.add(this.heenDatum);
		datePanel.add(this.terugDatum);
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
													.addComponent(klasse, Alignment.LEADING)
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
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		this.comboVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.comboNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.setLayout(groupLayout);
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setTickettenVerkocht(boolean visible) {

		this.paneTickettenVerkocht.setVisible(visible);
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		this.paneTickettenVerkocht.setText(this.aantal.getValue() + " " + bundle.getString("ticketsSold") + "\n\n"
				+ bundle.getString("lblVan") + " " + this.comboVan.getSelectedItem() + "\n"
				+ bundle.getString("lblNaar") + " " + this.comboNaar.getSelectedItem() + "\n"
				+ bundle.getString("lblDatum") + " " + format.format(this.heenDatum.getDate()) + "\n"
				+ bundle.getString("lblTerugDatum") + " " + format.format(this.terugDatum.getDate()) + "\n"
				+ bundle.getString("lblKlasse") + " " + this.klasse.getValue() + "\n" + this.getSelectedButton() + "\n"
				+ bundle.getString("lblSoortBiljet") + " " + this.comboVerkoopType.getSelectedItem() + "\n"
				+ bundle.getString("lblPrijs") + " " + this.ticket.getPrijs() * this.ticket.getAantal() + "");
		this.btnPdf.setVisible(visible);
		if(visible)
		{
			this.reset();
		}
	}

	private String getSelectedButton() {
		if (this.rdbtnHeen.isSelected()) {
			return bundle.getString("rdbtnHeen");
		} else {
			return bundle.getString("rdbtnHeenEnTerug");
		}
	}

	public void setColor(boolean depZone, boolean arrZone, boolean klasse, boolean aantal, boolean heenDatum,
			boolean terugDatum, boolean prijs) {
		String errorText = "de volgende velden zijn niet correct ingevuld:";
		if (depZone) {
			this.comboVan.setBorder(this.border);
		} else {
			this.comboVan.setBorder(this.borderError);
			errorText = errorText + "\nstart station";
		}
		if (arrZone) {
			this.comboNaar.setBorder(this.border);
		} else {
			this.comboNaar.setBorder(this.borderError);
			errorText = errorText + "\neind station";
		}
		if (klasse) {
			this.klasse.setBorder(this.border);
		} else {
			this.klasse.setBorder(this.borderError);
			errorText = errorText + "\nklasse";
		}
		if (aantal) {
			this.aantal.setBorder(this.border);
		} else {
			this.aantal.setBorder(this.borderError);
			errorText = errorText + "\naantal";
		}
		if (heenDatum) {
			this.heenDatum.setBorder(this.border);
		} else {
			this.heenDatum.setBorder(this.borderError);
			errorText = errorText + "\nheen datum";
		}
		if (terugDatum) {
			this.terugDatum.setBorder(this.border);
		} else {
			this.terugDatum.setBorder(this.borderError);
			errorText = errorText + "\nterug datum";
		}
		if (prijs) {
			this.txtPrijs.setBorder(this.border);
		} else {
			this.txtPrijs.setBorder(this.borderError);
			errorText = errorText + "\nprijs";
		}
		if (errorText != "de volgende velden zijn niet correct ingevuld:") {
			JOptionPane.showMessageDialog(new JFrame(), errorText);
		}
	}
	
	
	
	public void reset(){
		comboVan.setSelectedItem(Station.getCurrentStation());
		comboNaar.setSelectedIndex(-1);
		heenDatum.setDate(new Date());
		terugDatum.setDate(new Date());
		comboVerkoopType.setSelectedIndex(0);
		TicketVerkoopGui.this.txtPrijs.setText(String.valueOf(PrijsDAO.getPrijsByVerkoopType(VerkoopType
				.VerkoopTypeCasting((String) TicketVerkoopGui.this.comboVerkoopType.getSelectedItem()))));
		klasse.setValue(2);
		aantal.setValue(1);
		rdbtnHeen.setSelected(true);
	}
	
	
	class ButtonHandler implements ActionListener {
		private boolean isOffline;

		public ButtonHandler(boolean isOffline) {
			this.setOffline(isOffline);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == TicketVerkoopGui.this.btnVerkoop) {
				if (TicketVerkoopGui.this.txtPrijs.getText().isEmpty()
						|| TicketVerkoopGui.this.comboNaar.getSelectedItem() == null
						|| TicketVerkoopGui.this.comboVan.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden in!");
				} else {

					if (!this.isOffline) {

						TicketVerkoopGui.this.ticket = new Ticket(0,
								MedewerkerDAO.getMedewerkerIdByUsername(Login.getCurrentUser()),
								(String) TicketVerkoopGui.this.comboVan.getSelectedItem(),
								(String) TicketVerkoopGui.this.comboNaar.getSelectedItem(),
								StationDAO.checkStation(Station.getCurrentStation()),
								Double.parseDouble(TicketVerkoopGui.this.txtPrijs.getText()),
								VerkoopType.VerkoopTypeCasting(
										(String) TicketVerkoopGui.this.comboVerkoopType.getSelectedItem()),
								0, (int) TicketVerkoopGui.this.klasse.getValue(),
								(int) TicketVerkoopGui.this.aantal.getValue(), Calendar.getInstance().getTime(),
								TicketVerkoopGui.this.heenDatum.getDate(), TicketVerkoopGui.this.terugDatum.getDate());
					} else {
						TicketVerkoopGui.this.ticket = new Ticket(0, 0,
								(String) TicketVerkoopGui.this.comboVan.getSelectedItem(),
								(String) TicketVerkoopGui.this.comboNaar.getSelectedItem(), 0,
								Double.parseDouble(TicketVerkoopGui.this.txtPrijs.getText()),
								VerkoopType.VerkoopTypeCasting(
										(String) TicketVerkoopGui.this.comboVerkoopType.getSelectedItem()),
								0, (int) TicketVerkoopGui.this.klasse.getValue(),
								(int) TicketVerkoopGui.this.aantal.getValue(), Calendar.getInstance().getTime(),
								TicketVerkoopGui.this.heenDatum.getDate(), TicketVerkoopGui.this.terugDatum.getDate());
					}
					VerkoopController.ticketValidate(TicketVerkoopGui.this.ticket, TicketVerkoopGui.this,
							this.isOffline);
				}
			}
		}

		/*
		 * private Date converter(int dag, int maand, int jaar){
		 * SimpleDateFormat setFormat = new SimpleDateFormat("dd-MM-yyyy");
		 * String dateString = ""; if(dag < 10){ dateString = dateString + "0";}
		 * dateString = dateString + dag + "-"; if(maand < 10){ dateString =
		 * dateString + "0";} dateString = dateString + maand + "-" + jaar; try
		 * { return setFormat.parse(dateString); } catch (ParseException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } return null; }
		 */
		public boolean isOffline() {
			return this.isOffline;
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
				TicketVerkoopGui.this.txtPrijs.setText(String.valueOf(PrijsDAO.getPrijsByVerkoopType(VerkoopType
						.VerkoopTypeCasting((String) TicketVerkoopGui.this.comboVerkoopType.getSelectedItem()))));
			}
		}
	}

	class PdfListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Pdf.TicketGenerator(TicketVerkoopGui.this.ticket);

		}

	}
}