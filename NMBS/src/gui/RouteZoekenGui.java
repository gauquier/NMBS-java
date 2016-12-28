package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import source.Validation;

public class RouteZoekenGui extends JPanel {
	private JTextField txtVan;
	private JTextField txtNaar;
	private JTextField txtDatum;
	private JTextField txtUur;
	private JButton btnZoeken;
	private JLabel lblVanerror, lblNaarerror,lblDatumerror, lblUurerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	public RouteZoekenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
		JLabel lblVan = new JLabel("Van");
		lblVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVan.setForeground(Color.WHITE);
		
		txtVan = new JTextField();
		txtVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtVan.setColumns(10);
		
		JLabel lblNaar = new JLabel("Naar");
		lblNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNaar.setForeground(Color.WHITE);
		
		txtNaar = new JTextField();
		txtNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtNaar.setColumns(10);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtDatum.setColumns(10);
		
		JLabel lblUur = new JLabel("Uur");
		lblUur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUur.setForeground(Color.WHITE);
		
		txtUur = new JTextField();
		txtUur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtUur.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnVertrek.setForeground(Color.WHITE);
		JRadioButton rdbtnAankomst = new JRadioButton("Aankomst");
		rdbtnAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnAankomst.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnVertrek);
		buttonGroup.add(rdbtnAankomst);
		
		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblRouteZoeken = DefaultComponentFactory.getInstance().createTitle("Route zoeken");
		lblRouteZoeken.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		
		lblVanerror = new JLabel("");
		lblVanerror.setForeground(Color.RED);
		
		lblNaarerror = new JLabel("");
		lblNaarerror.setForeground(Color.RED);
		
		lblDatumerror = new JLabel("");
		lblDatumerror.setForeground(Color.RED);
		
		lblUurerror = new JLabel("");
		lblUurerror.setForeground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnVertrek)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnAankomst))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDatum)
										.addComponent(lblUur)
										.addComponent(lblVan)
										.addComponent(lblNaar))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addGap(246)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(lblNaarerror)
													.addComponent(lblVanerror)))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblUurerror)
												.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblDatumerror)
												.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)))
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtVan, Alignment.LEADING)
											.addComponent(txtNaar, Alignment.LEADING))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(lblRouteZoeken))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(btnZoeken)))
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblRouteZoeken)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVan)
						.addComponent(lblVanerror)
						.addComponent(txtVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaar)
						.addComponent(lblNaarerror)
						.addComponent(txtNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatum)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatumerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUur)
						.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUurerror))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnVertrek)
						.addComponent(rdbtnAankomst))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnZoeken)
					.addContainerGap(79, Short.MAX_VALUE))
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
			if (e.getSource() == btnZoeken){
				
				lblVanerror.setText("");
				lblVanerror.setBorder(border);
				lblNaarerror.setText("");
				lblNaarerror.setBorder(border);
				lblDatumerror.setText("");
				lblDatumerror.setBorder(border);
				lblUurerror.setText("");
				lblUurerror.setBorder(border);
				
				if(!txtVan.getText().isEmpty() && !txtDatum.getText().isEmpty() && !txtNaar.getText().isEmpty() && !txtUur.getText().isEmpty()){
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
					if (!Validation.checkTime(txtUur.getText())){
						lblUurerror.setText("Gelieve een juist uur in te vullen!");
						txtUur.setBorder(bordererror);

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
