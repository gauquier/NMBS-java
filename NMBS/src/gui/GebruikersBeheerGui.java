package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import com.jgoodies.forms.factories.DefaultComponentFactory;

import Hashing.DualHash;
import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Medewerker;
//Deze klasse wordt niet gebruikt. Gelieve te negeren. / This class is unused. Please ignore it.
public class GebruikersBeheerGui extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7156561362019683870L;

	private static ResourceBundle bundle;

	private JTextField txtZoekveld;
	private JButton btnZoeken;
	private JButton btnBewerken;
	private JList<Medewerker> list;
	private ArrayList<Medewerker> arrayLijst, arrayLijst2;
	private JButton btnVerwijderen;
	private JButton btnPasswordReset;

	public GebruikersBeheerGui() {
		bundle = ResourceBundle.getBundle("localization.GebruikerBewerkenGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblGebruikerBewerken = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblGebruikerBewerken"));
		lblGebruikerBewerken.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.arrayLijst = new ArrayList<Medewerker>();
		this.arrayLijst = MedewerkerDAO.getAllMedewerkers();

		final DefaultListModel<Medewerker> dlm = new DefaultListModel<Medewerker>();

		for (Medewerker m : this.arrayLijst) {
			dlm.addElement(m);
		}

		this.txtZoekveld = new JTextField();
		this.txtZoekveld.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.txtZoekveld.setColumns(10);
		this.txtZoekveld.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(final KeyEvent e) {
				Thread userRefresh = new Thread(new Runnable() {
					@Override
					public void run() {
						if (GebruikersBeheerGui.this.txtZoekveld.getText().isEmpty()) {
							dlm.removeAllElements();
							for (Medewerker m : GebruikersBeheerGui.this.arrayLijst) {
								dlm.addElement(m);
							}
							GebruikersBeheerGui.this.list.removeAll();
							GebruikersBeheerGui.this.list.revalidate();
							GebruikersBeheerGui.this.list.repaint();
							GebruikersBeheerGui.this.list = new JList<Medewerker>(dlm);
						} else if (GebruikersBeheerGui.this.txtZoekveld.getText().isEmpty()
								&& e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							dlm.removeAllElements();
							for (Medewerker m : GebruikersBeheerGui.this.arrayLijst) {
								dlm.addElement(m);
							}
							GebruikersBeheerGui.this.list.removeAll();
							GebruikersBeheerGui.this.list.revalidate();
							GebruikersBeheerGui.this.list.repaint();
							GebruikersBeheerGui.this.list = new JList<Medewerker>(dlm);
						} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							dlm.removeAllElements();
							for (Medewerker m : GebruikersBeheerGui.this.arrayLijst) {
								dlm.addElement(m);
							}
							GebruikersBeheerGui.this.list.removeAll();
							GebruikersBeheerGui.this.list.revalidate();
							GebruikersBeheerGui.this.list.repaint();
							GebruikersBeheerGui.this.list = new JList<Medewerker>(dlm);
						} else {

							dlm.removeAllElements();
							GebruikersBeheerGui.this.arrayLijst2 = MedewerkerDAO
									.getAllMedewerkersFromSearch(GebruikersBeheerGui.this.txtZoekveld.getText());
							for (Medewerker m : GebruikersBeheerGui.this.arrayLijst2) {
								dlm.addElement(m);
							}
							GebruikersBeheerGui.this.list.removeAll();
							GebruikersBeheerGui.this.list.revalidate();
							GebruikersBeheerGui.this.list.repaint();
							GebruikersBeheerGui.this.list = new JList<Medewerker>(dlm);
						}
					}
				});
				userRefresh.start();
			}
		});

		this.btnZoeken = new JButton(bundle.getString("btnZoeken"));
		this.btnZoeken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnZoeken.setBackground(Color.ORANGE);

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

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(37)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING, false).addComponent(lblGebruikerBewerken)
						.addGroup(groupLayout.createSequentialGroup().addComponent(this.btnZoeken)
								.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE).addComponent(
										this.txtZoekveld, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(this.btnBewerken, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
						.addComponent(this.btnVerwijderen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(this.btnPasswordReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addGap(31)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(lblGebruikerBewerken).addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.btnZoeken, GroupLayout.PREFERRED_SIZE,
										42, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.txtZoekveld, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(14)
										.addComponent(this.btnBewerken, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(this.btnVerwijderen, GroupLayout.PREFERRED_SIZE, 39,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(this.btnPasswordReset, GroupLayout.PREFERRED_SIZE, 39,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 469, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		this.list = new JList<Medewerker>(dlm);
		this.list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		scrollPane.setViewportView(this.list);
		this.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					AdminGui.setHuidigeKeuze(
							new GebruikerWeergevenGui(GebruikersBeheerGui.this.list.getSelectedValue()));
				}
			}
		});
		this.setLayout(groupLayout);
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

	public void close() {
		this.setVisible(false);
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

			if (e.getSource() == GebruikersBeheerGui.this.btnBewerken) {

				if (!GebruikersBeheerGui.this.unknownIndex()) {
					return;
				} else {
					System.out.println(GebruikersBeheerGui.this.list.getSelectedValue().getId());
					AdminGui.setHuidigeKeuze(
							new GebruikerBijwerkenGui(GebruikersBeheerGui.this.list.getSelectedValue()));
				}
			}

			if (e.getSource() == GebruikersBeheerGui.this.btnVerwijderen) {
				if (!GebruikersBeheerGui.this.unknownIndex()) {
					return;
				} else {
					int n = GebruikersBeheerGui.this.OkCancel("Ben je zeker dat je "
							+ GebruikersBeheerGui.this.list.getSelectedValue().getVoornaam() + " "
							+ GebruikersBeheerGui.this.list.getSelectedValue().getAchternaam() + " wil verwijderen?");

					if (n == 0) {
						MedewerkerDAO
								.removeMedewerker(GebruikersBeheerGui.this.list.getSelectedValue().getMedewerkerId());
						((DefaultListModel<Medewerker>) GebruikersBeheerGui.this.list.getModel())
								.remove(GebruikersBeheerGui.this.list.getSelectedIndex());
						JOptionPane.showMessageDialog(new JFrame(), "Gebruiker is succesvol verwijdert.");
					} else if (n == 1) {
						return;
					}
				}
			}
			if (e.getSource() == GebruikersBeheerGui.this.btnPasswordReset) {
				System.out.println("test");
				if (!GebruikersBeheerGui.this.unknownIndex()) {
					return;
				} else {

					String wachtwoord = new String("reset1");

					int n = GebruikersBeheerGui.this.OkCancel("Ben je zeker dat je een password reset wil uitvoeren op "
							+ GebruikersBeheerGui.this.list.getSelectedValue().getVoornaam() + " "
							+ GebruikersBeheerGui.this.list.getSelectedValue().getAchternaam() + "?");

					if (n == 0) {
						try {
							LoginDao.updateWachtwoordWhere(
									GebruikersBeheerGui.this.list.getSelectedValue().getLogin().getLoginId(),
									DualHash.hashString(wachtwoord));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(new JFrame(),
								"Het wachtwoord van " + GebruikersBeheerGui.this.list.getSelectedValue().getVoornaam()
										+ " " + GebruikersBeheerGui.this.list.getSelectedValue().getAchternaam()
										+ " is gereset naar 'reset1'.");
					} else if (n == 1) {
						return;
					}

				}

			}

		}
	}
}
