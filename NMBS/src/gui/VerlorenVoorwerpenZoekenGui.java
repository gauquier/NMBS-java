package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import source.Validation;
import javax.swing.JTextArea;

public class VerlorenVoorwerpenZoekenGui extends JPanel {
	private JTextField txtDatum;
	private JButton btnZoeken;
	private JLabel lblDatumerror;
	private JTextArea txtrBeschrijving;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);
	
	public VerlorenVoorwerpenZoekenGui() {
		setBackground(new Color(0, 191, 255));
		
		JLabel lblBeschrijving = new JLabel("Beschrijving");
		lblBeschrijving.setForeground(Color.WHITE);
		
		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setForeground(Color.WHITE);
		
		txtDatum = new JTextField();
		txtDatum.setColumns(10);
		
		btnZoeken = new JButton("Zoeken");
		
		JLabel lblVerlorenVoorwerpenZoeken = DefaultComponentFactory.getInstance().createTitle("Verloren voorwerpen zoeken");
		
		lblDatumerror = new JLabel("");
		lblDatumerror.setForeground(Color.RED);
		
		txtrBeschrijving = new JTextArea();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBeschrijving)
								.addComponent(lblDatum))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(347)
									.addComponent(lblDatumerror))
								.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(118)
							.addComponent(btnZoeken))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(lblVerlorenVoorwerpenZoeken)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblVerlorenVoorwerpenZoeken)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBeschrijving)
						.addComponent(txtrBeschrijving, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatum)
						.addComponent(lblDatumerror)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnZoeken)
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
			if (e.getSource() == btnZoeken){
				lblDatumerror.setText("");
				txtDatum.setBorder(border);
				
				if (!txtrBeschrijving.getText().isEmpty() && !txtDatum.getText().isEmpty()){
					if (!Validation.checkDate(txtDatum.getText(), "dd/MM/yyyy")){
						lblDatumerror.setText("Gelieve een juiste datum in te vullen!");
						txtDatum.setBorder(bordererror);
					}
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(),"Please fill in all required fields!");
				}
			}
		}
	}
}
