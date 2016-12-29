package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.AbonnementDAO;
import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.PeriodeDAO;
import dao.PrijsDAO;
import source.Abonnement;
import source.Login;
import source.Periode;
import source.VerkoopType;

public class AbonnementVerlengenGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6560637230362797041L;
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
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		this.doorgegevenAbonnement = abonnement;
		if (this.doorgegevenAbonnement.getP() != null) {
			this.verschilInJaren = this.doorgegevenAbonnement.getP().getEndDate().getYear()
					- this.doorgegevenAbonnement.getP().getStartDate().getYear();
			this.verschilInMaanden = this.verschilInJaren * 12
					+ this.doorgegevenAbonnement.getP().getEndDate().getMonth()
					- Calendar.getInstance().getTime().getMonth();
		}

		this.updateDatum();
		this.updatePrijs();

		JLabel lblAbonnementAanmaken = DefaultComponentFactory.getInstance().createTitle("Abonnement verlengen");
		lblAbonnementAanmaken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblStartdatum = new JLabel("Startdatum:");
		lblStartdatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStartdatum.setForeground(Color.WHITE);

		this.txtDuur = new JTextField();
		this.txtDuur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtDuur.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtDuur.setText("1");
		this.txtDuur.setColumns(10);
		this.txtDuur.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				AbonnementVerlengenGui.this.updateDatum();
				AbonnementVerlengenGui.this.updatePrijs();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				AbonnementVerlengenGui.this.updateDatum();
				AbonnementVerlengenGui.this.updatePrijs();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

		});

		this.btnOpslaan = new JButton("Opslaan");
		this.btnOpslaan.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnOpslaan.setBackground(Color.ORANGE);
		this.btnOpslaan.addActionListener(new MenuItemHandler());

		JLabel lblEinddatum = new JLabel("Einddatum:");
		lblEinddatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEinddatum.setForeground(Color.WHITE);

		JLabel lblDuur = new JLabel("Duur:");
		lblDuur.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDuur.setForeground(Color.WHITE);

		JLabel lblDatum1 = new JLabel("Niet van toepassing");
		lblDatum1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum1.setForeground(Color.WHITE);
		lblDatum1.setText(this.formatDatum.format(this.startdatum.getTime()));

		JLabel lblDatum2 = new JLabel("Niet van toepassing");
		lblDatum2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDatum2.setForeground(Color.WHITE);

		JLabel lblMaanden = new JLabel("maand(en)");
		lblMaanden.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblMaanden.setForeground(Color.WHITE);

		JLabel lblNieuweEinddatum = new JLabel("Nieuwe einddatum:");
		lblNieuweEinddatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNieuweEinddatum.setForeground(Color.WHITE);
		this.nieuweEinddatum.add(Calendar.MONTH, 1);

		this.lblNieuweEinddatumValue = new JLabel(this.formatDatum.format(this.nieuweEinddatum.getTime()));
		this.lblNieuweEinddatumValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblNieuweEinddatumValue.setForeground(Color.WHITE);

		JLabel lblBedrag = new JLabel("Prijs:");
		lblBedrag.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBedrag.setForeground(Color.WHITE);

		JLabel lblPrijsValue = new JLabel("Niet van toepassing");
		lblPrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPrijsValue.setForeground(Color.WHITE);

		JLabel lblNieuwePrijs = new JLabel("Nieuwe prijs:");
		lblNieuwePrijs.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNieuwePrijs.setForeground(Color.WHITE);

		this.lblNieuwePrijsValue = new JLabel("");
		this.lblNieuwePrijsValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.lblNieuwePrijsValue.setForeground(Color.WHITE);

		if (abonnement.getP() == null) {
			this.startdatum.add(Calendar.DAY_OF_MONTH, 1);
			this.nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
			lblDatum1.setText(this.formatDatum.format(this.startdatum.getTime()));
			lblDatum2.setText("Niet van toepassing");
			lblPrijsValue.setText("Niet van toepassing");
			this.lblNieuweEinddatumValue.setText(this.formatDatum.format(Calendar.getInstance().getTime()));
			this.lblNieuwePrijsValue.setText(Double.toString(PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)));
		} else {
			lblDatum1.setText(this.formatDatum.format(this.doorgegevenAbonnement.getP().getStartDate()));
			lblDatum2.setText(this.formatDatum.format(this.doorgegevenAbonnement.getP().getEndDate()));
			this.startdatum.setTime(PeriodeDAO.getPeriode(this.doorgegevenAbonnement).getStartDate());
			this.nieuweEinddatum.setTime(this.doorgegevenAbonnement.getP().getEndDate());
			lblPrijsValue.setText(Double.toString(this.doorgegevenAbonnement.getPrijs()) + " euro");
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(25).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING).addComponent(lblAbonnementAanmaken)
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addComponent(lblStartdatum)
								.addComponent(lblEinddatum, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBedrag, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDuur, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(this.txtDuur, GroupLayout.PREFERRED_SIZE,
														94, GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lblMaanden, GroupLayout.PREFERRED_SIZE, 169,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 146,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDatum2, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
												.addComponent(lblPrijsValue, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblNieuwePrijs)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.lblNieuwePrijsValue,
										GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.btnOpslaan, GroupLayout.PREFERRED_SIZE, 111,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE, 199,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(this.lblNieuweEinddatumValue, GroupLayout.PREFERRED_SIZE, 142,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(135, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblAbonnementAanmaken).addGap(32)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblStartdatum)
						.addComponent(lblDatum1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblEinddatum)
						.addComponent(lblDatum2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrijsValue, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBedrag))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblDuur)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtDuur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaanden)))
				.addGap(18)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNieuweEinddatum, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblNieuweEinddatumValue, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(this.lblNieuwePrijsValue, GroupLayout.PREFERRED_SIZE, 27,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNieuwePrijs, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(this.btnOpslaan, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(122, Short.MAX_VALUE)));

		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}

	public void updatePrijs() {
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				if (AbonnementVerlengenGui.this.doorgegevenAbonnement.getP() == null) {
					AbonnementVerlengenGui.this.nieuwePrijs = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT))
							* Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText());
				} else {
					AbonnementVerlengenGui.this.nieuwePrijs = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT))
							* (Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText())
									+ AbonnementVerlengenGui.this.verschilInMaanden);

				}
				AbonnementVerlengenGui.this.lblNieuwePrijsValue
						.setText(Double.toString(AbonnementVerlengenGui.this.nieuwePrijs));
			}
		};
		SwingUtilities.invokeLater(doHighlight);

	}

	public void updateDatum() {
		Runnable doHighlight = new Runnable() {
			@Override
			public void run() {
				if (AbonnementVerlengenGui.this.doorgegevenAbonnement.getP() == null) {
					AbonnementVerlengenGui.this.nieuweEinddatum = Calendar.getInstance();
					AbonnementVerlengenGui.this.nieuweEinddatum.add(Calendar.DAY_OF_MONTH, 1);
				} else {
					AbonnementVerlengenGui.this.nieuweEinddatum.setTime(
							PeriodeDAO.getPeriode(AbonnementVerlengenGui.this.doorgegevenAbonnement).getEndDate());
				}

				if (Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText()) <= 0
						|| Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText()) > 12) {
					JOptionPane.showMessageDialog(new JFrame(),
							"De duur kan niet kleiner dan 1 of groter dan 12 zijn.");
					AbonnementVerlengenGui.this.txtDuur.setText(Integer.toString(1));
				} else {
					AbonnementVerlengenGui.this.nieuweEinddatum.add(Calendar.MONTH,
							Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText()));

					AbonnementVerlengenGui.this.lblNieuweEinddatumValue.setText(AbonnementVerlengenGui.this.formatDatum
							.format(AbonnementVerlengenGui.this.nieuweEinddatum.getTime()));

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
		if ((Integer.parseInt(this.txtDuur.getText()) + this.verschilInMaanden) >= 12) {
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

			if (e.getSource() == AbonnementVerlengenGui.this.btnOpslaan) {
				if (!AbonnementVerlengenGui.this.txtDuur.getText().isEmpty()) {
					if (Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText()) <= 0
							|| Integer.parseInt(AbonnementVerlengenGui.this.txtDuur.getText()) > 12) {
						JOptionPane.showMessageDialog(new JFrame(),
								"De duur kan niet kleiner dan/gelijk aan 0 zijn of groter dan 12 zijn.");

					} else {

						Periode periode;

						if (AbonnementVerlengenGui.this.doorgegevenAbonnement.getP() == null) {
							periode = new Periode(0, AbonnementVerlengenGui.this.startdatum.getTime(),
									AbonnementVerlengenGui.this.nieuweEinddatum.getTime(),
									AbonnementVerlengenGui.this.verkoopdatum.getTime());

							AbonnementDAO.updatePrijs(AbonnementVerlengenGui.this.doorgegevenAbonnement,
									AbonnementVerlengenGui.this.nieuwePrijs);
							PeriodeDAO.addPeriode(periode, AbonnementVerlengenGui.this.doorgegevenAbonnement,
									huidigeMedewerkerId);
							JOptionPane.showMessageDialog(new JFrame(), "Het abonnement wordt actief binnen 24 uur.");

						} else {

							if (AbonnementVerlengenGui.this.limietVerlengingBereikt()) {
								int n = AbonnementVerlengenGui.this.OkCancel(
										"U bent van plan de limiet van 12 maanden te overschrijden. Bent u zeker dat u wilt doorgaan?");
								if (n > 0) {
									return;
								}

							}

							periode = new Periode(
									AbonnementVerlengenGui.this.doorgegevenAbonnement.getP().getPeriodeId(),
									AbonnementVerlengenGui.this.nieuweEinddatum.getTime());

							AbonnementDAO.updatePrijs(AbonnementVerlengenGui.this.doorgegevenAbonnement,
									AbonnementVerlengenGui.this.nieuwePrijs);
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
