package gui;

import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class RouteZoekenGui extends JPanel {
	private JTextField txtVan;
	private JTextField txtNaar;
	private JTextField txtDatum;
	private JTextField txtUur;
	public RouteZoekenGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		
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
		
		JLabel lblUur = new JLabel("Uur");
		lblUur.setForeground(Color.WHITE);
		
		txtUur = new JTextField();
		txtUur.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setForeground(Color.WHITE);
		JRadioButton rdbtnAankomst = new JRadioButton("Aankomst");
		rdbtnAankomst.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnVertrek);
		buttonGroup.add(rdbtnAankomst);
		
		JButton btnZoeken = new JButton("Zoeken");
		
		JLabel lblRouteZoeken = DefaultComponentFactory.getInstance().createTitle("Route zoeken");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnVertrek)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnAankomst))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVan)
										.addComponent(lblNaar))
									.addGap(28)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDatum)
										.addComponent(lblUur))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(112)
							.addComponent(btnZoeken))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(lblRouteZoeken)))
					.addContainerGap(201, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblRouteZoeken)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVan)
						.addComponent(txtVan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNaar)
						.addComponent(txtNaar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatum)
						.addComponent(txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUur)
						.addComponent(txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnVertrek)
						.addComponent(rdbtnAankomst))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnZoeken)
					.addContainerGap(39, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void close()
	{
		this.setVisible(false);
	}
}
