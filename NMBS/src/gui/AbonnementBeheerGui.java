package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.AbonnementDAO;
import dao.KlantDAO;
import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.PeriodeDAO;
import dao.PrijsDAO;
import source.Abonnement;
import source.Login;
import source.VerkoopType;

public class AbonnementBeheerGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2914230901692734881L;
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();
	private JTextField txtZoekveld;
	private JButton btnVerlengen;
	private JButton btnVerwijderen;
	private JList<Abonnement> list;
	private ArrayList<Abonnement> arrayLijst;

	private DefaultListModel<Abonnement> dlm;
	private JButton btnAnnuleren;
	private JButton btnNieuwAbonnement;
	private String newline = System.getProperty("line.separator");
	private JLabel label;

	public AbonnementBeheerGui() {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblAbonnementenBeheren = DefaultComponentFactory.getInstance().createTitle("Abonnementen beheren");
		lblAbonnementenBeheren.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.arrayLijst = new ArrayList<Abonnement>();

		this.arrayLijst = AbonnementDAO.getAllAbonnementen();

		this.dlm = new DefaultListModel<Abonnement>();

		for (Abonnement a : this.arrayLijst) {
			this.dlm.addElement(a);
		}

		this.list = new JList<Abonnement>(this.dlm);
		this.list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane(this.list);
		scrollPane.setViewportView(this.list);
		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {

					if(huidigeRol==1){
						AdminGui.setHuidigeKeuze(
								new AbonnementWeergevenGui(AbonnementBeheerGui.this.list.getSelectedValue()));
					} else {
						MedewerkerGui.setHuidigeKeuze(
								new AbonnementWeergevenGui(AbonnementBeheerGui.this.list.getSelectedValue()));
					}
				}
			}
		});

		this.txtZoekveld = new JTextField();
		this.txtZoekveld.setColumns(10);
		this.txtZoekveld.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {

				AbonnementBeheerGui.this.updateLijst();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				AbonnementBeheerGui.this.updateLijst();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				AbonnementBeheerGui.this.updateLijst();
			}

		});

		this.btnVerlengen = new JButton("Verlengen");
		this.btnVerlengen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnVerlengen.setBackground(Color.ORANGE);
		this.btnVerlengen.addActionListener(new MenuItemHandler());

		this.btnAnnuleren = new JButton("Annuleren");
		this.btnAnnuleren.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnAnnuleren.setBackground(Color.ORANGE);
		this.btnAnnuleren.addActionListener(new MenuItemHandler());

		this.btnNieuwAbonnement = new JButton("Aanmaken");
		this.btnNieuwAbonnement.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnNieuwAbonnement.setBackground(Color.ORANGE);
		this.btnNieuwAbonnement.addActionListener(new MenuItemHandler());

		this.btnVerwijderen = new JButton("Verwijderen");
		this.btnVerwijderen.addActionListener(new MenuItemHandler());
		this.btnVerwijderen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnVerwijderen.setBackground(Color.ORANGE);

		this.label = new JLabel("Zoeken op naam:");
		this.label.setForeground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(37).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAbonnementenBeheren).addGroup(
										groupLayout
												.createSequentialGroup()
												.addGroup(groupLayout
														.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
																.createSequentialGroup()
																.addComponent(this.label, GroupLayout.PREFERRED_SIZE,
																		132, GroupLayout.PREFERRED_SIZE)
																.addGap(72).addComponent(this.txtZoekveld,
																		GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
														.addComponent(this.list, GroupLayout.DEFAULT_SIZE, 246,
																Short.MAX_VALUE))
												.addGap(10)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(this.btnVerwijderen, GroupLayout.DEFAULT_SIZE,
																147, Short.MAX_VALUE)
														.addComponent(this.btnAnnuleren, GroupLayout.DEFAULT_SIZE, 147,
																Short.MAX_VALUE)
														.addComponent(this.btnVerlengen, GroupLayout.DEFAULT_SIZE, 147,
																Short.MAX_VALUE)
														.addComponent(this.btnNieuwAbonnement, Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblAbonnementenBeheren)
						.addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txtZoekveld, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.label))
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(this.btnNieuwAbonnement, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnVerlengen)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnAnnuleren)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnVerwijderen,
												GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addComponent(this.list, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
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

	public void updateLijst() {
		ArrayList<Abonnement> t = new ArrayList<Abonnement>();
		if (!this.txtZoekveld.getText().isEmpty()) {

			for (int i = 0; i < this.arrayLijst.size(); i++) {
				if (this.arrayLijst.get(i).getKlant().getNaam().toLowerCase()
						.contains(this.txtZoekveld.getText().toLowerCase())) {
					t.add(this.arrayLijst.get(i));
				}

			}
		} else {
			t = this.arrayLijst;
		}

		this.dlm.clear();
		for (Abonnement m : t) {
			this.dlm.addElement(m);
		}

		this.list.setModel(this.dlm);
	}

	public Boolean unknownIndex() {
		if (this.list.getSelectedValue() == null || this.list.getSelectedIndex() < 0) {
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

			if (e.getSource() == AbonnementBeheerGui.this.btnNieuwAbonnement) {
				if(huidigeRol==1){
					AdminGui.setHuidigeKeuze(new AbonnementToevoegenGui());
				} else {
					MedewerkerGui.setHuidigeKeuze(new AbonnementToevoegenGui());
				}
				
				
			}

			if (e.getSource() == AbonnementBeheerGui.this.btnVerlengen) {
				if (!AbonnementBeheerGui.this.unknownIndex()) {
					return;
				} else {
					if(huidigeRol==1){
						AdminGui.setHuidigeKeuze(
							new AbonnementVerlengenGui(AbonnementBeheerGui.this.list.getSelectedValue()));
					} else {
						MedewerkerGui.setHuidigeKeuze(
								new AbonnementVerlengenGui(AbonnementBeheerGui.this.list.getSelectedValue()));
					}
					
				}

			}

			if (e.getSource() == AbonnementBeheerGui.this.btnAnnuleren) {
				if (!AbonnementBeheerGui.this.unknownIndex()) {
					return;
				} else {

					if (AbonnementBeheerGui.this.list.getSelectedValue().getP() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Dit abonnement heeft geen actieve periode.");
					} else {
						if (AbonnementBeheerGui.this.list.getSelectedValue().getResterendeDagen() <= 31) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Abonnementen kunnen niet geannuleerd worden binnen 31 dagen voor vervalling.");
							return;
						}

						int n = AbonnementBeheerGui.this.OkCancel("Let op! U bent van plan om het abonnement van "
								+ AbonnementBeheerGui.this.list.getSelectedValue().getKlant().getVoornaam() + " "
								+ AbonnementBeheerGui.this.list.getSelectedValue().getKlant().getAchternaam()
								+ " te annuleren." + AbonnementBeheerGui.this.newline
								+ "Dit abonnement heeft nog een actieve periode van "
								+ AbonnementBeheerGui.this.list.getSelectedValue().getResterendeDagen()
								+ " dagen. Bent u zeker dat u wilt doorgaan?");

						if (n == 0) {
							double terugTeBetalen = (PrijsDAO.getPrijsByVerkoopType(VerkoopType.ABONNEMENT)
									/ Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
									* AbonnementBeheerGui.this.list.getSelectedValue().getResterendeDagen();

							AbonnementBeheerGui.this.list.getSelectedValue().getP()
									.setEndDate(Calendar.getInstance().getTime());
							AbonnementDAO.updatePrijs(AbonnementBeheerGui.this.list.getSelectedValue(), 0);
							PeriodeDAO.updatePeriode(AbonnementBeheerGui.this.list.getSelectedValue().getP(),
									huidigeMedewerkerId);
							JOptionPane.showMessageDialog(new JFrame(), "Het abonnement van "
									+ AbonnementBeheerGui.this.list.getSelectedValue().getKlant().getVoornaam() + " "
									+ AbonnementBeheerGui.this.list.getSelectedValue().getKlant().getAchternaam()
									+ " wordt binnen de 24 uur geannuleerd. Het terug te betalen bedrag bedraagt "
									+ terugTeBetalen + " euro.");
							
							if(huidigeRol==1){
								AdminGui.setHuidigeKeuze(new AbonnementBeheerGui());
							} else {
								MedewerkerGui.setHuidigeKeuze(new AbonnementBeheerGui());
							}
							
						}

					}
				}

			}

			if (e.getSource() == AbonnementBeheerGui.this.btnVerwijderen) {
				if (!AbonnementBeheerGui.this.unknownIndex()) {
					return;
				} else {
					if (AbonnementBeheerGui.this.list.getSelectedValue().getP() == null) {
						int n = AbonnementBeheerGui.this.OkCancel("Ben je zeker dat je het abonnement van "
								+ AbonnementBeheerGui.this.list.getSelectedValue().getKlant().getVoornaam() + " "
								+ AbonnementBeheerGui.this.list.getSelectedValue().getKlant().getAchternaam()
								+ " wil verwijderen?");
						if (n == 0) {
							AbonnementDAO.removeAbonnement(
									AbonnementBeheerGui.this.list.getSelectedValue().getAbonnementId());
							((DefaultListModel<Abonnement>) AbonnementBeheerGui.this.list.getModel())
									.remove(AbonnementBeheerGui.this.list.getSelectedIndex());
							arrayLijst = AbonnementDAO.getAllAbonnementen();
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
