package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class RouteZoekenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1641860326664122840L;
	private JTextField txtVan;
	private JTextField txtNaar;
	private JTextField txtDatum;
	private JTextField txtUur;
	private JButton btnZoeken;
	private JLabel lblVanerror, lblNaarerror, lblDatumerror, lblUurerror;
	private Border border = BorderFactory.createEmptyBorder();
	private Border bordererror = BorderFactory.createLineBorder(Color.RED, 3);

	public RouteZoekenGui() {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVan = new JLabel("Van");
		lblVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVan.setForeground(Color.WHITE);

		this.txtVan = new JTextField();
		this.txtVan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtVan.setColumns(10);

		JLabel lblNaar = new JLabel("Naar");
		lblNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNaar.setForeground(Color.WHITE);

		this.txtNaar = new JTextField();
		this.txtNaar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtNaar.setColumns(10);

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum.setForeground(Color.WHITE);

		this.txtDatum = new JTextField();
		this.txtDatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtDatum.setColumns(10);

		JLabel lblUur = new JLabel("Uur");
		lblUur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUur.setForeground(Color.WHITE);

		this.txtUur = new JTextField();
		this.txtUur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtUur.setColumns(10);

		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnVertrek = new JRadioButton("Vertrek");
		rdbtnVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnVertrek.setForeground(Color.WHITE);
		JRadioButton rdbtnAankomst = new JRadioButton("Aankomst");
		rdbtnAankomst.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		rdbtnAankomst.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnVertrek);
		buttonGroup.add(rdbtnAankomst);

		this.btnZoeken = new JButton("Zoeken");
		this.btnZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel lblRouteZoeken = DefaultComponentFactory.getInstance().createTitle("Route zoeken");
		lblRouteZoeken.setFont(new Font("Lucida Grande", Font.BOLD, 20));

		this.lblVanerror = new JLabel("");
		this.lblVanerror.setForeground(Color.RED);

		this.lblNaarerror = new JLabel("");
		this.lblNaarerror.setForeground(Color.RED);

		this.lblDatumerror = new JLabel("");
		this.lblDatumerror.setForeground(Color.RED);

		this.lblUurerror = new JLabel("");
		this.lblUurerror.setForeground(Color.RED);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING,
								false)
						.addGroup(
								groupLayout
										.createSequentialGroup().addGap(
												59)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup().addComponent(rdbtnVertrek)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(rdbtnAankomst))
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblDatum).addComponent(lblUur)
																.addComponent(lblVan)
																.addComponent(lblNaar))
														.addGap(18)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING, false)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addGap(246).addGroup(groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(this.lblNaarerror)
																						.addComponent(
																								this.lblVanerror)))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(this.txtUur,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(this.lblUurerror)
																				.addPreferredGap(
																						ComponentPlacement.RELATED, 28,
																						Short.MAX_VALUE))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(this.txtDatum,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(this.lblDatumerror)
																				.addPreferredGap(
																						ComponentPlacement.RELATED, 28,
																						Short.MAX_VALUE)))
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(this.txtVan, Alignment.LEADING)
																		.addComponent(this.txtNaar,
																				Alignment.LEADING))))))
						.addGroup(groupLayout.createSequentialGroup().addGap(32).addComponent(lblRouteZoeken))
						.addGroup(groupLayout.createSequentialGroup().addGap(135).addComponent(this.btnZoeken)))
						.addGap(83)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(17).addComponent(lblRouteZoeken)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVan)
								.addComponent(this.lblVanerror).addComponent(this.txtVan, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNaar)
								.addComponent(this.lblNaarerror).addComponent(this.txtNaar, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblDatum)
								.addComponent(this.txtDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblDatumerror))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblUur)
								.addComponent(this.txtUur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblUurerror))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnVertrek)
								.addComponent(rdbtnAankomst))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnZoeken)
						.addContainerGap(79, Short.MAX_VALUE)));
		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}
}
