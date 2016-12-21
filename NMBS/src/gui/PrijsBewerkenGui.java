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
		lblVerkooptype.setForeground(Color.WHITE);

		JLabel lblPrijs = new JLabel(bundle.getString("lblPrijs"));
		lblPrijs.setForeground(Color.WHITE);

		txtVerkooptype = new JTextField();
		txtVerkooptype.setColumns(10);
		txtVerkooptype.setText(k.getVerkoopType());
		txtVerkooptype.setEditable(false);

		txtPrijs = new JTextField();
		txtPrijs.setColumns(10);
		txtPrijs.setText(new Double(k.getPrijs()).toString());

		btnBijwerken = new JButton(bundle.getString("btnBijwerken"));
		btnBijwerken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBijwerken.setBackground(Color.ORANGE);
		btnBijwerken.addActionListener(new MenuItemHandler());

		JLabel lblPrijsBijwerken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblPrijsBijwerken"));
		lblPrijsBijwerken.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblVerplicht = new JLabel(bundle.getString("lblVerplicht"));
		lblVerplicht.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVerplicht.setForeground(Color.WHITE);

		btnTerug = new JButton(bundle.getString("btnTerug"));
		btnTerug.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnTerug.setBackground(Color.ORANGE);
		btnTerug.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblPrijsBijwerken)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVerkooptype).addComponent(lblPrijs))
								.addGap(50)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtPrijs, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 81,
												Short.MAX_VALUE)
										.addComponent(txtVerkooptype, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 81,
												Short.MAX_VALUE)
										.addComponent(lblVerplicht, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addGap(178)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnTerug, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnBijwerken, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(19).addComponent(lblPrijsBijwerken).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVerkooptype).addComponent(
						txtVerkooptype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPrijs).addComponent(
						txtPrijs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblVerplicht)
				.addPreferredGap(ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnTerug)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnBijwerken, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE).addGap(29)));

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