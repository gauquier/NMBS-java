package gui;

import dao.PrijsDAO;
import source.Prijs;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;


@SuppressWarnings("serial")
public class PrijsBewerkenGui extends JPanel {
	private static ResourceBundle bundle;
	
	private JTextField txtVerkooptype;
	private JTextField txtPrijs;
	private JButton btnBijwerken;
	private JButton btnTerug;


	public PrijsBewerkenGui(Prijs k) {
		bundle = ResourceBundle.getBundle("localization.PrijsBewerkenGui");
		
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblVerkooptype = new JLabel(bundle.getString("lblVerkooptype"));
		lblVerkooptype.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVerkooptype.setForeground(Color.WHITE);

		JLabel lblPrijs = new JLabel(bundle.getString("lblPrijs"));
		lblPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijs.setForeground(Color.WHITE);

		txtVerkooptype = new JTextField();
		txtVerkooptype.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtVerkooptype.setColumns(10);
		txtVerkooptype.setText(k.getVerkoopType());
		txtVerkooptype.setEditable(false);

		txtPrijs = new JTextField();
		txtPrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPrijs.setColumns(10);
		txtPrijs.setText(new Double(k.getPrijs()).toString());

		btnBijwerken = new JButton(bundle.getString("btnBijwerken"));
		btnBijwerken.setFont(new Font("Dialog", Font.BOLD, 20));
		btnBijwerken.setBackground(Color.ORANGE);
		btnBijwerken.addActionListener(new MenuItemHandler());

		JLabel lblPrijsBijwerken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblPrijsBijwerken"));
		lblPrijsBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblVerplicht = new JLabel(bundle.getString("lblVerplicht"));
		lblVerplicht.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVerplicht.setForeground(Color.WHITE);

		btnTerug = new JButton(bundle.getString("btnTerug"));
		btnTerug.setFont(new Font("Dialog", Font.BOLD, 20));
		btnTerug.setBackground(Color.ORANGE);
		btnTerug.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrijsBijwerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVerkooptype)
								.addComponent(lblPrijs))
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblVerplicht, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
									.addGap(370))
								.addComponent(txtVerkooptype, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrijs, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(387)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnTerug, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addGap(66))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblPrijsBijwerken)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVerkooptype)
						.addComponent(txtVerkooptype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrijs)
						.addComponent(txtPrijs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblVerplicht)
					.addGap(33)
					.addComponent(btnTerug, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(100, Short.MAX_VALUE))
		);

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();

			if (e.getSource() == btnTerug) {
				AdminGui.setHuidigeKeuze(new PrijsBeheerGui());
			}

			if (e.getSource() == btnBijwerken) {

				if (!txtVerkooptype.getText().isEmpty() && !txtPrijs.getText().isEmpty()) {
					String verkooptype = txtVerkooptype.getText().trim();
					Double prijs = Double.parseDouble(txtPrijs.getText());

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