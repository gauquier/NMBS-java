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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;
import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Medewerker;

public class GebruikerBewerkenGui extends JPanel {
	private JTextField txtZoekveld;
	private JButton btnBewerken;
	private JList<Medewerker> list;
	private ArrayList<Medewerker> arrayLijst;
	private DefaultListModel<Medewerker> dlm;
	private JButton btnVerwijderen;
	private JButton btnPasswordReset;
	public String navigation;
	private JLabel label;
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.GebruikerBewerkenGui");
	
	public GebruikerBewerkenGui() {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblGebruikerBewerken = DefaultComponentFactory.getInstance().createTitle(bundle.getString("lblGebruikerBewerken"));
		lblGebruikerBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.arrayLijst = new ArrayList<Medewerker>();

		this.arrayLijst = MedewerkerDAO.getAllMedewerkers();

		this.dlm = new DefaultListModel<Medewerker>();

		for (Medewerker m : this.arrayLijst) {
			this.dlm.addElement(m);
		}

		this.txtZoekveld = new JTextField();
		txtZoekveld.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtZoekveld.setColumns(10);
		this.txtZoekveld.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {

				GebruikerBewerkenGui.this.updateLijst();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				GebruikerBewerkenGui.this.updateLijst();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				GebruikerBewerkenGui.this.updateLijst();
			}

		});

		this.btnBewerken = new JButton(bundle.getString("btnBewerken"));
		this.btnBewerken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnBewerken.setBackground(Color.ORANGE);
		this.btnBewerken.addActionListener(new MenuItemHandler());

		this.btnVerwijderen = new JButton(bundle.getString("btnVerwijderen"));
		this.btnVerwijderen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnVerwijderen.setBackground(Color.ORANGE);
		this.btnVerwijderen.addActionListener(new MenuItemHandler());

		this.btnPasswordReset = new JButton(bundle.getString("btnPasswordReset"));
		this.btnPasswordReset.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnPasswordReset.setBackground(Color.ORANGE);
		this.btnPasswordReset.addActionListener(new MenuItemHandler());

		JScrollPane scrollPane = new JScrollPane(this.list);

		this.label = new JLabel(bundle.getString("searchByName"));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.label.setForeground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGebruikerBewerken)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
									.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnVerwijderen, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(btnBewerken, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(btnPasswordReset, GroupLayout.PREFERRED_SIZE, 130, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblGebruikerBewerken)
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtZoekveld, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(12)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(97)
							.addComponent(btnBewerken)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVerwijderen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPasswordReset, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		this.list = new JList<Medewerker>(this.dlm);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		scrollPane.setViewportView(this.list);
		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					AdminGui.setHuidigeKeuze(
							new GebruikerWeergevenGui(GebruikerBewerkenGui.this.list.getSelectedValue()));
				}
			}
		});
		this.setLayout(groupLayout);
	}

	public int OkCancel(String message) {
		int n = JOptionPane.showConfirmDialog(null, message, bundle.getString("confirmation"), JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			return n;
		} else if (n == JOptionPane.NO_OPTION) {
			return n;
		}
		return 1;

	}

	public void close() {
		this.setVisible(false);
	}

	public Boolean unknownIndex() {
		if (this.list.getSelectedValue() == null || this.list.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(new JFrame(), bundle.getString("noSelectedUserError"));
			return false;
		} else {
			return true;
		}
	}

	public void updateLijst() {
		ArrayList<Medewerker> t = new ArrayList<Medewerker>();
		if (!this.txtZoekveld.getText().isEmpty()) {

			for (int i = 0; i < this.arrayLijst.size(); i++) {
				if (this.arrayLijst.get(i).getNaam().toLowerCase().contains(this.txtZoekveld.getText().toLowerCase())) {
					t.add(this.arrayLijst.get(i));
				}

			}
		} else {
			t = this.arrayLijst;
		}

		this.dlm.clear();
		for (Medewerker m : t) {
			this.dlm.addElement(m);
		}

		this.list.setModel(this.dlm);
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == GebruikerBewerkenGui.this.btnBewerken) {

				if (!GebruikerBewerkenGui.this.unknownIndex()) {
					return;
				} else {
					GebruikerBewerkenGui.this.navigation = "gebruikerBijwerken";
					AdminGui.setHuidigeKeuze(
							new GebruikerBijwerkenGui(GebruikerBewerkenGui.this.list.getSelectedValue()));
				}
			}

			if (e.getSource() == GebruikerBewerkenGui.this.btnVerwijderen) {
				if (!GebruikerBewerkenGui.this.unknownIndex()) {

					return;
				} else {
					int n = GebruikerBewerkenGui.this.OkCancel(bundle.getString("wantToRemoveUserPart1") + " "
							+ GebruikerBewerkenGui.this.list.getSelectedValue().getVoornaam() + " "
							+ GebruikerBewerkenGui.this.list.getSelectedValue().getAchternaam() + " " + bundle.getString("wantToRemoveUserPart1"));

					if (n == 0) {
						MedewerkerDAO
								.removeMedewerker(GebruikerBewerkenGui.this.list.getSelectedValue().getMedewerkerId());
						((DefaultListModel<Medewerker>) GebruikerBewerkenGui.this.list.getModel())
								.remove(GebruikerBewerkenGui.this.list.getSelectedIndex());
						JOptionPane.showMessageDialog(new JFrame(), bundle.getString("userDeleted"));
					} else if (n == 1) {
						return;
					}
				}
			}
			if (e.getSource() == GebruikerBewerkenGui.this.btnPasswordReset) {
				if (!GebruikerBewerkenGui.this.unknownIndex()) {
					return;
				} else {

					String wachtwoord = new String("reset1");

					int n = GebruikerBewerkenGui.this
							.OkCancel(bundle.getString("confirmPassResetQuestion") + " "
									+ GebruikerBewerkenGui.this.list.getSelectedValue().getVoornaam() + " "
									+ GebruikerBewerkenGui.this.list.getSelectedValue().getAchternaam() + "?");

					if (n == 0) {
						try {
							LoginDao.updateWachtwoordWhere(
									GebruikerBewerkenGui.this.list.getSelectedValue().getLogin().getLoginId(),
									DualHash.hashString(wachtwoord));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(new JFrame(),
								bundle.getString("postPassResetMessagePart1") + " " + GebruikerBewerkenGui.this.list.getSelectedValue().getVoornaam()
										+ " " + GebruikerBewerkenGui.this.list.getSelectedValue().getAchternaam()
										+ " " + bundle.getString("postPassResetMessagePart2"));
					} else if (n == 1) {
						return;
					}

				}

			}

		}
	}
}
