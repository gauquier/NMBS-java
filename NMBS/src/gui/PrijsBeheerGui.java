package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.LoginDao;
import dao.MedewerkerDAO;
import dao.PrijsDAO;
import source.Login;
import source.Prijs;

@SuppressWarnings("serial")
public class PrijsBeheerGui extends JPanel {
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();
	private static ResourceBundle bundle;

	private JButton btnBewerken;
	private JList<Prijs> list;
	private ArrayList<Prijs> arrayLijst;

	public PrijsBeheerGui() {
		bundle = ResourceBundle.getBundle("localization.PrijsBeheerGui");

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblPrijzenBeheren = DefaultComponentFactory.getInstance()
				.createTitle(bundle.getString("lblPrijzenBeheren"));
		lblPrijzenBeheren.setFont(new Font("Tahoma", Font.PLAIN, 20));

		this.arrayLijst = new ArrayList<Prijs>();

		this.arrayLijst = PrijsDAO.getAllPrijzen();

		DefaultListModel<Prijs> dlm = new DefaultListModel<Prijs>();

		for (Prijs m : this.arrayLijst) {
			dlm.addElement(m);
		}

		this.list = new JList<Prijs>(dlm);
		this.list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		this.btnBewerken = new JButton(bundle.getString("btnBewerken"));
		this.btnBewerken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.btnBewerken.setBackground(Color.ORANGE);
		this.btnBewerken.addActionListener(new MenuItemHandler());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(this.list, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btnBewerken,
												GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblPrijzenBeheren))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(lblPrijzenBeheren).addGap(11)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(this.btnBewerken)
								.addComponent(this.list, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
						.addGap(49)));
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
			JOptionPane.showMessageDialog(new JFrame(), "Er is geen prijs aangeduid.");
			return false;
		} else {
			return true;
		}
	}

	private class MenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == PrijsBeheerGui.this.btnBewerken) {

				if (!PrijsBeheerGui.this.unknownIndex()) {
					return;
				} else {
					if(huidigeRol==1){
						AdminGui.setHuidigeKeuze(new PrijsBewerkenGui(PrijsBeheerGui.this.list.getSelectedValue()));
					}else{
						MedewerkerGui.setHuidigeKeuze(new PrijsBewerkenGui(PrijsBeheerGui.this.list.getSelectedValue()));
					}
					

				}
			}

		}
	}
}
