package gui;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import dao.*;
import source.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class AbonnementBeheerGui extends JPanel {
	private JTextField textField;
	private JButton btnZoeken;
	private JButton btnVerlengen;
	private JButton btnVerwijderen;
	private JList<Abonnement> list;
	private ArrayList<Abonnement> arrayLijst;
	private ArrayList<Object> objecten;
	private JButton btnAnnuleren;
	public String navigation;
	private JButton btnNieuwAbonnement;
	public String newline = System.getProperty("line.separator");

	public AbonnementBeheerGui() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblAbonnementenBeheren = DefaultComponentFactory.getInstance().createTitle("Abonnementen beheren");
		lblAbonnementenBeheren.setFont(new Font("Tahoma", Font.PLAIN, 20));

		arrayLijst = new ArrayList<Abonnement>();

		arrayLijst = AbonnementDAO.getAllAbonnementen();

		DefaultListModel<Abonnement> dlm = new DefaultListModel<Abonnement>();

		for (Abonnement a : arrayLijst) {
			dlm.addElement(a);
		}

		list = new JList<Abonnement>(dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textField.setColumns(10);

		btnZoeken = new JButton("Zoeken");
		btnZoeken.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnZoeken.setBackground(Color.ORANGE);

		btnVerlengen = new JButton("Verlengen");
		btnVerlengen.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerlengen.setBackground(Color.ORANGE);
		btnVerlengen.addActionListener(new MenuItemHandler());

		btnAnnuleren = new JButton("Annuleren");
		btnAnnuleren.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAnnuleren.setBackground(Color.ORANGE);
		btnAnnuleren.addActionListener(new MenuItemHandler());

		btnNieuwAbonnement = new JButton("Aanmaken");
		btnNieuwAbonnement.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNieuwAbonnement.setBackground(Color.ORANGE);
		btnNieuwAbonnement.addActionListener(new MenuItemHandler());

		btnVerwijderen = new JButton("Verwijderen");
		btnVerwijderen.addActionListener(new MenuItemHandler());
		btnVerwijderen.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnVerwijderen.setBackground(Color.ORANGE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAbonnementenBeheren)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(btnZoeken)
									.addGap(34)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAnnuleren, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnVerlengen, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addComponent(btnNieuwAbonnement, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))))
									.addGap(11))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(btnVerwijderen, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
									.addGap(11)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAbonnementenBeheren)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZoeken)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNieuwAbonnement)
							.addGap(8)
							.addComponent(btnVerlengen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAnnuleren)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerwijderen))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
					.addContainerGap())
		);
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
			JOptionPane.showMessageDialog(new JFrame(), "Er is geen gebruiker aangeduid.");
			return false;
		} else {
			return true;
		}
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int huidigeMedewerkerId = MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
					.getMedewerkerId();

			if (e.getSource() == btnNieuwAbonnement) {
				AdminGui.setHuidigeKeuze(new AbonnementToevoegenGui());
			}

			if (e.getSource() == btnVerlengen) {
				if (!unknownIndex()) {
					return;
				} else {

					AdminGui.setHuidigeKeuze(new AbonnementVerlengenGui(list.getSelectedValue()));

				}

			}

			if (e.getSource() == btnAnnuleren) {
				if (!unknownIndex()) {
					return;
				} else {

					if (list.getSelectedValue().getP() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Dit abonnement heeft geen actieve periode.");
					} else {
						if (list.getSelectedValue().getResterendeDagen() <= 31) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Abonnementen kunnen niet geannuleerd worden binnen 31 dagen voor vervalling.");
							return;
						}

						int n = OkCancel("Let op! U bent van plan om het abonnement van "
								+ list.getSelectedValue().getKlant().getVoornaam() + " "
								+ list.getSelectedValue().getKlant().getAchternaam() + " te annuleren." + newline
								+ "Dit abonnement heeft nog een actieve periode van "
								+ list.getSelectedValue().getResterendeDagen()
								+ " dagen. Bent u zeker dat u wilt doorgaan?");

						if (n == 0) {
							double terugTeBetalen = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)
									/ Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
									* list.getSelectedValue().getResterendeDagen();

							list.getSelectedValue().getP().setEndDate(Calendar.getInstance().getTime());
							AbonnementDAO.updatePrijs(list.getSelectedValue(), 0);
							PeriodeDAO.updatePeriode(list.getSelectedValue().getP(), huidigeMedewerkerId);
							JOptionPane.showMessageDialog(new JFrame(),
									"Het abonnement van " + list.getSelectedValue().getKlant().getVoornaam() + " "
											+ list.getSelectedValue().getKlant().getAchternaam()
											+ " wordt binnen de 24 uur geannuleerd. Het terug te betalen bedrag bedraagt "
											+ terugTeBetalen + " euro.");
							AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());
						}

					}
				}

			}

			if (e.getSource() == btnVerwijderen) {
				if (!unknownIndex()) {
					return;
				} else {
					if (list.getSelectedValue().getP() == null) {
						int n = OkCancel("Ben je zeker dat je het abonnement van "
								+ list.getSelectedValue().getKlant().getVoornaam() + " "
								+ list.getSelectedValue().getKlant().getAchternaam() + " wil verwijderen?");
						if (n == 0) {
							AbonnementDAO.removeAbonnement(list.getSelectedValue().getAbonnementId());
							((DefaultListModel<Abonnement>) list.getModel()).remove(list.getSelectedIndex());
							JOptionPane.showMessageDialog(new JFrame(), "Abonnement is succesvol verwijdert.");
						} else if (n == 1) {
							return;
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"Abonnement kan niet verwijdert worden omdat dit gekoppeld is met een actieve periode.");
						return;
					}

				}

			}

		}
	}
}