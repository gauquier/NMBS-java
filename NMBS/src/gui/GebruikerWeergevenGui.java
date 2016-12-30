package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import dao.LoginDao;
import dao.MedewerkerDAO;
import source.Login;
import source.Medewerker;

public class GebruikerWeergevenGui extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -878328862101208141L;
	private int huidigeRol=MedewerkerDAO.getMedewerkerByLogin(LoginDao.getLoginId(Login.getCurrentUser()))
			.getRol().getRolId();
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.GebruikerWeergevenGui");
	
	public GebruikerWeergevenGui(Medewerker medewerker) {
		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));

		JLabel lblGebruiker = DefaultComponentFactory.getInstance().createTitle("<dynamic>");
		lblGebruiker.setFont(new Font("Tahoma", Font.PLAIN, 20));
		if (!medewerker.isActief()) {
			lblGebruiker.setText(medewerker.getNaam() + " " + bundle.getString("inactiefBetweenBrackets"));
		} else {
			lblGebruiker.setText(medewerker.getNaam());
		}

		JLabel lblEmail = new JLabel(bundle.getString("lblEmail"));
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmail.setForeground(Color.WHITE);

		JLabel lblEmailValue = new JLabel("");
		lblEmailValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmailValue.setForeground(Color.WHITE);
		lblEmailValue.setText(medewerker.getEmail());

		JLabel lblVertrek = new JLabel(bundle.getString("lblVertrek"));
		lblVertrek.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVertrek.setForeground(Color.WHITE);

		JLabel lblAdresValue = new JLabel("");
		lblAdresValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAdresValue.setForeground(Color.WHITE);
		lblAdresValue.setText(medewerker.getAdres().toString());

		JLabel lblBus = new JLabel(bundle.getString("lblBus"));
		lblBus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBus.setForeground(Color.WHITE);

		JLabel lblBusValue = new JLabel("");
		lblBusValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBusValue.setForeground(Color.WHITE);
		if (medewerker.getAdres().getBus() == null || medewerker.getAdres().getBus() == ""
				|| medewerker.getAdres().getBus() == " ") {
			lblBusValue.setText(bundle.getString("NA"));//tekst: "Niet van toepassing"
		} else {
			lblBusValue.setText(medewerker.getAdres().getBus());
		}

		JLabel lblUsername = new JLabel(bundle.getString("username"));
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUsername.setForeground(Color.WHITE);

		JLabel lblUsernameValue = new JLabel("");
		lblUsernameValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUsernameValue.setForeground(Color.WHITE);
		lblUsernameValue.setText(medewerker.getLogin().getUsername());

		JLabel lblType = new JLabel(bundle.getString("type"));
		lblType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblType.setForeground(Color.WHITE);

		JLabel lblTypeValue = new JLabel("<dynamic>");
		lblTypeValue.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTypeValue.setForeground(Color.WHITE);
		lblTypeValue.setText(medewerker.getRol().getRol());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addGap(45))
							.addComponent(lblTypeValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblGebruiker)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEmail)
										.addComponent(lblBus, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVertrek, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
									.addGap(45))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblBusValue)
								.addComponent(lblEmailValue, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(lblAdresValue, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(lblUsernameValue, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblGebruiker)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(lblEmailValue, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVertrek)
						.addComponent(lblAdresValue, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBus)
						.addComponent(lblBusValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblUsernameValue, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType)
						.addComponent(lblTypeValue))
					.addGap(41))
		);

		this.setLayout(groupLayout);
	}

	public void close() {
		this.setVisible(false);
	}
}