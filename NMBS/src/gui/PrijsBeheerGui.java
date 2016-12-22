package gui;

import dao.PrijsDAO;
import source.Prijs;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;


@SuppressWarnings("serial")
public class PrijsBeheerGui extends JPanel {
	private JButton btnBewerken;
	private JList<Prijs> list;
	private ArrayList<Prijs> arrayLijst;
	public String navigation;

	public PrijsBeheerGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblPrijsBewerken = DefaultComponentFactory.getInstance().createTitle("Prijzen beheren");
		lblPrijsBewerken.setFont(new Font("Tahoma", Font.PLAIN, 14));

		arrayLijst = new ArrayList<Prijs>();

		arrayLijst = PrijsDAO.getAllPrijzen();

		DefaultListModel<Prijs> dlm = new DefaultListModel<Prijs>();

		for (Prijs m : arrayLijst) {
			dlm.addElement(m);
		}

		list = new JList<Prijs>(dlm);

		btnBewerken = new JButton("Bewerken");
		btnBewerken.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnBewerken.setBackground(Color.ORANGE);
		btnBewerken.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(list, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnBewerken, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
								.addComponent(lblPrijsBewerken))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(lblPrijsBewerken).addGap(11)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnBewerken)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
						.addGap(49)));
		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	public int OkCancel(String message) {
		int n = JOptionPane.showConfirmDialog(null, message, "Bevestiging", JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			return n;
		} else if (n == JOptionPane.NO_OPTION) {
			return n;
		}
		return 1;

	}

	public Boolean unknownIndex() {
		if (list.getSelectedValue() == null || list.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Er is geen prijs aangeduid.");
			return false;
		} else {
			return true;
		}
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnBewerken) {

				if (!unknownIndex()) {
					return;
				} else {

					navigation = "gebruikerBijwerken";
					AdminGui.setHuidigeKeuze(new PrijsBewerkenGui(list.getSelectedValue()));

				}
			}

		}
	}
}
