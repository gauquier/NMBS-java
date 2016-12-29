package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.PrijsDAO;
import source.Prijs;

@SuppressWarnings("serial")
public class PrijsBewerkenGui extends JPanel {
	private static ResourceBundle bundle;

	private JTextField txtVerkooptype;
	private JTextField txtPrijs;
	private JButton btnBijwerken;
	private JButton btnTerug;

	public PrijsBewerkenGui(Prijs k) {
		bundle = ResourceBundle.getBundle("localization.PrijsBewerkenGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVerkooptype = new JLabel(bundle.getString("lblVerkooptype"));
		lblVerkooptype.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVerkooptype.setForeground(Color.WHITE);

		JLabel lblPrijs = new JLabel(bundle.getString("lblPrijs"));
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijs.setForeground(Color.WHITE);

		this.txtVerkooptype = new JTextField();
		this.txtVerkooptype.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtVerkooptype.setColumns(10);
		this.txtVerkooptype.setText(k.getVerkoopType());
		this.txtVerkooptype.setEditable(false);

		this.txtPrijs = new JTextField();
		this.txtPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtPrijs.setColumns(10);
		this.txtPrijs.setText(new Double(k.getPrijs()).toString());

		this.btnBijwerken = new JButton(bundle.getString("btnBijwerken"));
		this.btnBijwerken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnBijwerken.setBackground(Color.ORANGE);
		this.btnBijwerken.addActionListener(new MenuItemHandler());

		JLabel lblPrijsBijwerken = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblPrijsBijwerken"));
		lblPrijsBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblVerplicht = new JLabel(bundle.getString("lblVerplicht"));
		lblVerplicht.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVerplicht.setForeground(Color.WHITE);

		this.btnTerug = new JButton(bundle.getString("btnTerug"));
		this.btnTerug.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnTerug.setBackground(Color.ORANGE);
		this.btnTerug.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(30).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPrijsBijwerken).addGroup(groupLayout
										.createSequentialGroup().addGroup(
												groupLayout.createParallelGroup(Alignment.LEADING).addComponent(
														lblVerkooptype).addComponent(lblPrijs))
										.addGap(50)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblVerplicht, GroupLayout.DEFAULT_SIZE, 70,
																Short.MAX_VALUE)
														.addGap(370))
												.addComponent(this.txtVerkooptype, GroupLayout.PREFERRED_SIZE, 170,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(this.txtPrijs, GroupLayout.PREFERRED_SIZE, 87,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup().addGap(387)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(this.btnTerug, GroupLayout.PREFERRED_SIZE, 186,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(this.btnBijwerken, GroupLayout.PREFERRED_SIZE, 186,
												GroupLayout.PREFERRED_SIZE))
								.addGap(66)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addGap(19).addComponent(lblPrijsBijwerken).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVerkooptype)
								.addComponent(this.txtVerkooptype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPrijs)
								.addComponent(this.txtPrijs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblVerplicht).addGap(33)
						.addComponent(this.btnTerug, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(this.btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(100, Short.MAX_VALUE)));

		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == PrijsBewerkenGui.this.btnTerug) {
				AdminGui.setHuidigeKeuze(new PrijsBeheerGui());
			}

			if (e.getSource() == PrijsBewerkenGui.this.btnBijwerken) {

				if (!PrijsBewerkenGui.this.txtVerkooptype.getText().isEmpty()
						&& !PrijsBewerkenGui.this.txtPrijs.getText().isEmpty()) {
					String verkooptype = PrijsBewerkenGui.this.txtVerkooptype.getText().trim();
					Double prijs = Double.parseDouble(PrijsBewerkenGui.this.txtPrijs.getText());

					PrijsDAO.updatePrijsByVerkoopType(verkooptype, prijs);

					JOptionPane.showMessageDialog(new JFrame(), "Prijs is bijgewerkt!");
					AdminGui.setHuidigeKeuze(new PrijsBeheerGui());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle verplichte velden in!");

				}
			}

		}
	}
}