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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import dao.*;
import source.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class AbonnementVerlengenGui extends JPanel {
	private Klant klant;
	private JTextField txtDuur;
	private JLabel lblNieuweEinddatumValue;
	private JLabel lblNieuwePrijsValue;
	private JButton btnOpslaan;
	private Abonnement doorgegevenAbonnement;
	private Calendar startdatum = Calendar.getInstance();
	private Calendar nieuweEinddatum = Calendar.getInstance();
	private Calendar verkoopdatum = Calendar.getInstance();
	private double nieuwePrijs;
	private SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
	private int verschilInJaren, verschilInMaanden;

	public AbonnementVerlengenGui(Abonnement abonnement) {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		doorgegevenAbonnement = abonnement;
		if (doorgegevenAbonnement.getP() != null) {
			verschilInJaren = doorgegevenAbonnement.getP().getEndDate().getYear()
					- doorgegevenAbonnement.getP().getStartDate().getYear();
			verschilInMaanden = verschilInJaren * 12 + doorgegevenAbonnement.getP().getEndDate().getMonth()
					- Calendar.getInstance().getTime().getMonth();
		}

		updateDatum();
		updatePrijs();

		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement verlengen");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblStartdatum = new JLabel("Startdatum:");
		lblStartdatum.setForeground(Color.WHITE);

		txtDuur = new JTextField();
		txtDuur.setHorizontalAlignment(SwingConstants.CENTER);
		txtDuur.setText("1");
		txtDuur.setColumns(10);
		txtDuur.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateDatum();
				updatePrijs();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateDatum();
				updatePrijs();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

		});

		btnOpslaan = new JButton("Opslaan");
		btnOpslaan.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnOpslaan.setBackground(Color.ORANGE);
		btnOpslaan.addActionListener(new MenuItemHandler());

		JLabel lblEinddatum = new JLabel("Einddatum:");
		lblEinddatum.setForeground(Color.WHITE);

		JLabel lblDuur = new JLabel("Duur:");
		lblDuur.setForeground(Color.WHITE);

		JLabel lblDatum1 = new JLabel("Niet van toepassing");
		lblDatum1.setForeground(Color.WHITE);
		lblDatum1.setText(formatDatum.format(startdatum.getTime()));

		JLabel lblDatum2 = new JLabel("Niet van toepassing");
		lblDatum2.setForeground(Color.WHITE);

		JLabel lblMaanden = new JLabel("maand(en)");
		lblMaanden.setForeground(Color.WHITE);

		JLabel lblNieuweEinddatum = new JLabel("Nieuwe einddatum:");
		lblNieuweEinddatum.setForeground(Color.WHITE);
		nieuweEinddatum.add(Calendar.MONTH, 1);

		lblNieuweEinddatumValue = new JLabel(formatDatum.format(nieuweEinddatum.getTime()));
		lblNieuweEinddatumValue.setForeground(Color.WHITE);

		JLabel lblBedrag = new JLabel("Prijs:");
		lblBedrag.setForeground(Color.WHITE);

		JLabel lblPrijsValue = new JLabel("Niet van toepassing");
		lblPrijsValue.setForeground(Color.WHITE);

		JLabel lblNieuwePrijs = new JLabel("Nieuwe prijs:");
		lblNieuwePrijs.setForeground(Color.WHITE);

		lblNieuwePrijsValue = new JLabel("");
		lblNieuwePrijsValue.setForeground(Color.WHITE);

		if (abonnement.getP() == null) {
			startdatum.add(Calendar.DAY_OF_MONTH, 1);
			nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
			lblDatum1.setText(formatDatum.format(startdatum.getTime()));
			lblDatum2.setText("Niet van toepassing");
			lblPrijsValue.setText("Niet van toepassing");
			lblNieuweEinddatumValue.setText(formatDatum.format(nieuweEinddatum.getInstance().getTime()));
			lblNieuwePrijsValue.setText(Double.toString(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)));
		} else {
			lblDatum1.setText(formatDatum.format(PeriodeDAO.getPeriode(abonnement).getStartDate()));
			lblDatum2.setText(formatDatum.format(PeriodeDAO.getPeriode(abonnement).getEndDate()));
			startdatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getStartDate());
			nieuweEinddatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getEndDate());
			lblPrijsValue.setText(Double.toString(doorgegevenAbonnement.getPrijs()) + " euro");
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(354, Short.MAX_VALUE)
										.addComponent(btnOpslaan, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE)
										.addGap(31))
								.addGroup(groupLayout.createSequentialGroup().addGap(25).addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNieuwePrijs, GroupLayout.PREFERRED_SIZE, 104,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNieuwePrijsValue, GroupLayout.PREFERRED_SIZE, 142,
														GroupLayout.PREFERRED_SIZE)
												.addGap(219))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblDuur, GroupLayout.PREFERRED_SIZE, 58,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(52)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(txtDuur, GroupLayout.DEFAULT_SIZE,
																				84, Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblMaanden,
																				GroupLayout.PREFERRED_SIZE, 58,
																				GroupLayout.PREFERRED_SIZE)))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblNieuweEinddatum,
																		GroupLayout.PREFERRED_SIZE, 104,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(lblNieuweEinddatumValue,
																		GroupLayout.PREFERRED_SIZE, 142,
																		GroupLayout.PREFERRED_SIZE)))
														.addContainerGap())
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblBedrag, GroupLayout.PREFERRED_SIZE, 58,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblAbonnementAanmaken)
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblEinddatum,
																						GroupLayout.PREFERRED_SIZE, 58,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblStartdatum,
																						GroupLayout.PREFERRED_SIZE, 58,
																						GroupLayout.PREFERRED_SIZE))
																		.addGap(52)
																		.addGroup(groupLayout
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblDatum1,
																						GroupLayout.PREFERRED_SIZE, 146,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblDatum2,
																						GroupLayout.PREFERRED_SIZE, 142,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblPrijsValue,
																						GroupLayout.PREFERRED_SIZE, 142,
																						GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(ComponentPlacement.RELATED, 0,
																				Short.MAX_VALUE)))
														.addContainerGap(215, Short.MAX_VALUE))))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblAbonnementAanmaken)
						.addGap(32)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(306).addComponent(btnOpslaan, GroupLayout.PREFERRED_SIZE, 43,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblStartdatum)
														.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 26,
																GroupLayout.PREFERRED_SIZE))
												.addGap(31)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblEinddatum).addComponent(lblDatum2,
																GroupLayout.PREFERRED_SIZE, 27,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup().addGap(26)
																.addComponent(lblBedrag))
														.addGroup(groupLayout.createSequentialGroup().addGap(18)
																.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE,
																		27, GroupLayout.PREFERRED_SIZE)))
												.addGap(41)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblDuur)
														.addComponent(txtDuur, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMaanden))
												.addGap(28)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE,
																27, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNieuweEinddatumValue,
																GroupLayout.PREFERRED_SIZE, 27,
																GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNieuwePrijsValue, GroupLayout.PREFERRED_SIZE,
																27, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNieuwePrijs, GroupLayout.PREFERRED_SIZE, 27,
																GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	public void updatePrijs() {
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				if (doorgegevenAbonnement.getP() == null) {
					nieuwePrijs = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT))
							* Integer.parseInt(txtDuur.getText());
				} else {
					nieuwePrijs = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT))
							* (Integer.parseInt(txtDuur.getText()) + verschilInMaanden);

				}
				lblNieuwePrijsValue.setText(Double.toString(nieuwePrijs));
			}
		};
		SwingUtilities.invokeLater(doHighlight);

	}

	public void updateDatum() {
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				if (doorgegevenAbonnement.getP() == null) {
					nieuweEinddatum = Calendar.getInstance();
					nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
				} else {
					nieuweEinddatum.setTime(PeriodeDAO.getPeriode(doorgegevenAbonnement).getEndDate());
				}

				if (Integer.parseInt(txtDuur.getText()) <= 0 || Integer.parseInt(txtDuur.getText()) > 12) {
					JOptionPane.showMessageDialog(new JFrame(),
							"De duur kan niet kleiner dan 1 of groter dan 12 zijn.");
					txtDuur.setText(Integer.toString(1));
				} else {
					nieuweEinddatum.add(Calendar.MONTH, Integer.parseInt(txtDuur.getText()));

					lblNieuweEinddatumValue.setText(formatDatum.format(nieuweEinddatum.getTime()));

				}
			}
		};
		SwingUtilities.invokeLater(doHighlight);

	}

	private int OkCancel(String message) {
		int n = JOptionPane.showConfirmDialog(null, message, "Bevestiging", JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			return n;
		} else if (n == JOptionPane.NO_OPTION) {
			return n;
		}
		return 1;

	}

	private boolean limietVerlengingBereikt() {
		if ((Integer.parseInt(txtDuur.getText()) + verschilInMaanden) >= 12) {
			return true;
		} else {
			return false;
		}
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// refresh();
			int huidigeMedewerkerId = MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
					.getMedewerkerId();

			if (e.getSource() == btnOpslaan) {
				if (!txtDuur.getText().isEmpty()) {
					if (Integer.parseInt(txtDuur.getText()) <= 0 || Integer.parseInt(txtDuur.getText()) > 12) {
						JOptionPane.showMessageDialog(new JFrame(),
								"De duur kan niet kleiner dan/gelijk aan 0 zijn of groter dan 12 zijn.");

					} else {

						Periode periode;

						if (doorgegevenAbonnement.getP() == null) {
							periode = new Periode(0, startdatum.getTime(), nieuweEinddatum.getTime(),
									verkoopdatum.getTime());

							AbonnementDAO.updatePrijs(doorgegevenAbonnement, nieuwePrijs);
							PeriodeDAO.addPeriode(periode, doorgegevenAbonnement, huidigeMedewerkerId);
							JOptionPane.showMessageDialog(new JFrame(), "Het abonnement wordt actief binnen 24 uur.");

						} else {

							if (limietVerlengingBereikt()) {
								int n = OkCancel(
										"U bent van plan de limiet van 12 maanden te overschrijden. Bent u zeker dat u wilt doorgaan?");
								if (n > 0) {
									return;
								}

							}

							periode = new Periode(doorgegevenAbonnement.getP().getPeriodeId(),
									nieuweEinddatum.getTime());

							AbonnementDAO.updatePrijs(doorgegevenAbonnement, nieuwePrijs);
							PeriodeDAO.updatePeriode(periode, huidigeMedewerkerId);
							JOptionPane.showMessageDialog(new JFrame(), "Het abonnement is verlengd.");

						}

						AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());

					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vul alle velden in!");
				}

			}

		}
	}
}