package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.KlantDAO;
import source.Klant;

public class KlantenBeheerGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5220308317573994715L;

	private static ResourceBundle bundle;

	private JTextField textField;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Klant> list;
	private ArrayList<Klant> arrayLijst;
	private JButton btnVerwijderen;

	public KlantenBeheerGui() {
		bundle = ResourceBundle.getBundle("localization.KlantBewerkenGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblKlantBewerken = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblKlantBewerken"));
		lblKlantBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.arrayLijst = new ArrayList<Klant>();

		this.arrayLijst = KlantDAO.getAllKlanten();

		DefaultListModel<Klant> dlm = new DefaultListModel<Klant>();

		for (Klant m : this.arrayLijst) {
			dlm.addElement(m);
		}

		this.list = new JList<Klant>(dlm);
		this.list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					AdminGui.setHuidigeKeuze(new KlantWeergevenGui(KlantenBeheerGui.this.list.getSelectedValue()));
				}
			}
		});

		this.textField = new JTextField();
		this.textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.textField.setColumns(10);

		this.btnZoeken = new JButton(bundle.getString("btnZoeken"));
		this.btnZoeken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.btnZoeken.setBackground(Color.ORANGE);

		this.btnBewerken = new JButton(bundle.getString("btnBewerken"));
		this.btnBewerken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		this.btnBewerken.setBackground(Color.ORANGE);
		this.btnBewerken.addActionListener(new MenuItemHandler());

		this.btnVerwijderen = new JButton(bundle.getString("btnVerwijderen"));
		this.btnVerwijderen.setFont(new Font("Segoe UI", Font.BOLD, 20));

		this.btnVerwijderen.setBackground(Color.ORANGE);
		this.btnVerwijderen.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(37)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(lblKlantBewerken)
						.addGroup(groupLayout.createSequentialGroup().addComponent(this.btnZoeken)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.textField))
						.addComponent(this.list, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.btnBewerken, GroupLayout.PREFERRED_SIZE, 169,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.btnVerwijderen))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(12).addComponent(lblKlantBewerken).addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.btnZoeken)
						.addComponent(this.textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.list, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(this.btnBewerken).addGap(12)
								.addComponent(this.btnVerwijderen)))
				.addContainerGap()));
		this.setLayout(groupLayout);
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
		if (this.list.getSelectedValue() == null || this.list.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(new JFrame(), bundle.getString("noSelectedCustomer"));
			return false;
		} else {
			return true;
		}
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == KlantenBeheerGui.this.btnBewerken) {

				if (!KlantenBeheerGui.this.unknownIndex()) {
					return;
				} else {

					AdminGui.setHuidigeKeuze(new KlantBijwerkenGui(KlantenBeheerGui.this.list.getSelectedValue()));

				}
			}

			if (e.getSource() == KlantenBeheerGui.this.btnVerwijderen) {
				if (!KlantenBeheerGui.this.unknownIndex()) {
					return;
				} else {
					int n = KlantenBeheerGui.this.OkCancel(bundle.getString("betUZekerDatU") + " "
							+ KlantenBeheerGui.this.list.getSelectedValue().getVoornaam() + " "
							+ KlantenBeheerGui.this.list.getSelectedValue().getAchternaam() + " "
							+ bundle.getString("wiltVerwijderen"));

					if (n == 0) {
						KlantDAO.removeKlant(KlantenBeheerGui.this.list.getSelectedValue().getKlantId());
						((DefaultListModel<Klant>) KlantenBeheerGui.this.list.getModel())
								.remove(KlantenBeheerGui.this.list.getSelectedIndex());
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("customerDeleted"));
					} else if (n == 1) {
						return;
					}

				}
			}

		}
	}
}
