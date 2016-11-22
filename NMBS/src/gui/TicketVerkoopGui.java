package gui;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.StationDAO;
import dao.VerlorenVoorwerpDAO;
import source.Station;
import source.Validation;
import source.VerlorenVoorwerp;

public class TicketVerkoopGui extends JPanel {
	private JTextField txtVan;
	private JTextField txtNaar;
	private JTextField txtDatum;
	private JButton btnVerkoop;
	private JLabel lblVanerror,lblNaarerror, lblDatumerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	
	public TicketVerkoopGui() {
		setBackground(new Color(0, 191, 255));
		
		JLabel lblVan = new JLabel("Van");
		lblVan.setForeground(Color.WHITE);
		
		txtVan = new JTextField();
		txtVan.setColumns(10);
		
		JLabel lblNaar = new JLabel("Naar");
		lblNaar.setForeground(Color.WHITE);
		
		txtNaar = new JTextField();
		txtNaar.setColumns(10);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblSoortBiljet = new JLabel("Soort Biljet");
		lblSoortBiljet.setForeground(Color.WHITE);
		
		btnVerkoop = new JButton("Verkoop");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnHeen = new JRadioButton("Heen");
		rdbtnHeen.setForeground(Color.WHITE);
		JRadioButton rdbtnHeenEnTerug = new JRadioButton("Heen en terug");
		rdbtnHeenEnTerug.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnHeen);
		buttonGroup.add(rdbtnHeenEnTerug);
		
		JLabel lblTicketVerkoop = DefaultComponentFactory.getInstance().createTitle("Ticket verkoop");
		
		lblVanerror = new JLabel("");
		lblVanerror.setForeground(Color.RED);
		
		lblNaarerror = new JLabel("");
		lblNaarerror.setForeground(Color.RED);
		
		lblDatumerror = new JLabel("");
		lblDatumerror.setForeground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(61)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnHeen)
										.addComponent(lblNaar)
										.addComponent(lblVan)
										.addComponent(lblDatum))
									.addGap(12))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSoortBiljet)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDatumerror))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblVanerror))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNaarerror))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(rdbtnHeenEnTerug))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(116)
							.addComponent(btnVerkoop))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(lblTicketVerkoop)))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTicketVerkoop)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVan)
						.addComponent(txtVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVanerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaar)
						.addComponent(txtNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNaarerror))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatum)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatumerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnHeenEnTerug)
						.addComponent(rdbtnHeen))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSoortBiljet))
					.addGap(18)
					.addComponent(btnVerkoop)
					.addGap(29))
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
			if (e.getSource() == btnVerkoop){
				
				lblVanerror.setText("");
				lblVanerror.setBorder(border);
				lblNaarerror.setText("");
				lblNaarerror.setBorder(border);
				lblDatumerror.setText("");
				lblDatumerror.setBorder(border);
				
				if(!txtVan.getText().isEmpty() && !txtDatum.getText().isEmpty() && !txtNaar.getText().isEmpty()){
					if (!Validation.checkDate(txtDatum.getText(), "dd/MM/yyyy")){
						lblDatumerror.setText("Gelieve een juiste datum in te vullen!");
						txtDatum.setBorder(bordererror);

					}
					if (!Validation.checkAlphabetical(txtVan.getText())){
						lblVanerror.setText("Gelieve een juist station in te vullen!");
						txtVan.setBorder(bordererror);

					}
					if (!Validation.checkAlphabetical(txtNaar.getText())){
						lblNaarerror.setText("Gelieve een juist station in te vullen!");
						txtNaar.setBorder(bordererror);

					}
					else{
						
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Gelieve alle velden in te vullen!");
				}
			}
		}		    	
    }
}
