package gui;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import handler.VerkoopController;
import source.Ticket;
import source.VerkoopType;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import java.awt.Font;

@SuppressWarnings("serial")
public class TicketVerkoopGui extends JPanel {
	private JTextField txtVan;
	private JTextField txtNaar;
	private JTable table;
	private JSpinner heenDag = new JSpinner();
	private JSpinner heenMaand = new JSpinner();
	private JSpinner heenJaar = new JSpinner();
	private JSpinner terugDag = new JSpinner();
	private JSpinner terugMaand = new JSpinner();
	private JSpinner terugJaar = new JSpinner();
	
	private JSpinner klasse = new JSpinner();
	private JSpinner aantal = new JSpinner();
	private JButton btnVerkoop = new JButton("Verkoop");
	
	private JLabel lblVan = new JLabel("Van");
	private JLabel lblNaar = new JLabel("Naar");
	private JLabel lblDatum = new JLabel("Heen datum");
	private JLabel lblTerugDatum = new JLabel("terug datum");
	private JLabel lblKlasse = new JLabel("klasse");
	private JLabel lblAantal = new JLabel("aantal");
	private JTextPane paneTickettenVerkocht = new JTextPane();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnHeen = new JRadioButton("Heen");
	private JRadioButton rdbtnHeenEnTerug = new JRadioButton("Heen en terug");
	private JComboBox<String> comboVerkoopType = new JComboBox<String>();
	
	private Ticket ticket = null;

	public TicketVerkoopGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		lblVan.setForeground(Color.WHITE);
		
		txtVan = new JTextField();
		txtVan.setBackground(Color.WHITE);
		txtVan.setColumns(10);
		
		lblNaar.setForeground(Color.WHITE);
		
		txtNaar = new JTextField();
		txtNaar.setColumns(10);
		
		lblDatum.setForeground(Color.WHITE);
	
		comboVerkoopType.addItem("standaard");
		comboVerkoopType.addItem("student");
		comboVerkoopType.addItem("groep");
		comboVerkoopType.addItem("60+");
		
		JLabel lblSoortBiljet = new JLabel("Soort Biljet");
		lblSoortBiljet.setForeground(Color.WHITE);
		
		
		btnVerkoop.addActionListener(new ButtonHandler());
		
		rdbtnHeen.setForeground(Color.BLACK);
		rdbtnHeenEnTerug.setForeground(Color.BLACK);
		buttonGroup.add(rdbtnHeen);
		buttonGroup.add(rdbtnHeenEnTerug);
		buttonGroup.setSelected(rdbtnHeen.getModel(), true);
		
		JLabel lblTicketVerkoop = DefaultComponentFactory.getInstance().createTitle("Ticket verkoop");
		lblTicketVerkoop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		lblTerugDatum.setForeground(Color.WHITE);
		
		table = new JTable();
		SimpleDateFormat dagFormat = new SimpleDateFormat("dd");
		SimpleDateFormat maandFormat = new SimpleDateFormat("MM");
		SimpleDateFormat jaarFormat = new SimpleDateFormat("yyyy");
		
		heenDag.setValue(Integer.parseInt(dagFormat.format(new Date())));
		heenMaand.setValue(Integer.parseInt(maandFormat.format(new Date())));
		heenJaar.setValue(Integer.parseInt(jaarFormat.format(new Date())));
		terugDag.setValue(Integer.parseInt(dagFormat.format(new Date())));
		terugMaand.setValue(Integer.parseInt(maandFormat.format(new Date())));
		terugJaar.setValue(Integer.parseInt(jaarFormat.format(new Date())));
		lblKlasse.setForeground(Color.WHITE);
		
		klasse.setValue(2);
		
		lblAantal.setForeground(Color.WHITE);
		aantal.setValue(1);
		
		paneTickettenVerkocht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paneTickettenVerkocht.setBackground((UIManager.getColor("CheckBoxMenuItem.selectionBackground")));
		paneTickettenVerkocht.setVisible(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(77)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(155)
							.addComponent(lblTicketVerkoop))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(60)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblDatum)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblKlasse, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblTerugDatum)
											.addComponent(lblAantal, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblSoortBiljet)
												.addComponent(rdbtnHeen))))
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNaar)
										.addComponent(lblVan))
									.addGap(56)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnVerkoop)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(klasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(aantal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtnHeenEnTerug)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtNaar, Alignment.LEADING)
											.addComponent(comboVerkoopType, Alignment.LEADING, 0, 186, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtVan, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(terugDag)
												.addComponent(heenDag, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(terugMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(terugJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
													.addComponent(heenMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(heenJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(11)))))
									.addGap(175)
									.addComponent(paneTickettenVerkocht, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(136, Short.MAX_VALUE))
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
								.addComponent(txtVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNaar))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDatum)
								.addComponent(heenDag, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(heenMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(heenJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTerugDatum)
								.addComponent(terugDag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(terugMaand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(terugJaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKlasse)
								.addComponent(klasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(rdbtnHeen)
								.addComponent(rdbtnHeenEnTerug))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSoortBiljet)
								.addComponent(comboVerkoopType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(39)
							.addComponent(btnVerkoop))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(paneTickettenVerkocht, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(105, Short.MAX_VALUE))
		);
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
		paneTickettenVerkocht.setText(aantal.getValue() + " ticket(ten) verkocht\n\nVan: " + txtVan.getText() + "\nNaar: " + txtNaar.getText() +
				"\nHeen datum: " +  heenDag.getValue() + "-" + heenMaand.getValue() + "-" + heenJaar.getValue() + 
				"\nTerug datum: " + terugDag.getValue() + "-" + terugMaand.getValue() + "-" + terugJaar.getValue() + 
				"\nKlasse " + klasse.getValue() + "\n" + getSelectedButton() + "\nType: " +  comboVerkoopType.getSelectedItem());
	}
	private String getSelectedButton(){if(buttonGroup.isSelected(rdbtnHeen.getModel())) return "heen"; else return "heen en terug";}
	
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
	
	class ButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnVerkoop)
			{
				ticket = new Ticket(-1, -1, txtVan.getText(), txtNaar.getText(), -1, 2,VerkoopType.VerkoopTypeCasting((String) comboVerkoopType.getSelectedItem()), 0, (int)klasse.getValue(), (int)aantal.getValue()
						,Calendar.getInstance().getTime(), converter((int) heenDag.getValue(),(int) heenMaand.getValue(), (int)heenJaar.getValue()),converter((int) terugDag.getValue(), (int) terugMaand.getValue(), (int) terugJaar.getValue()) );
				VerkoopController.ticketValidate(ticket, TicketVerkoopGui.this);
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
	}
}
