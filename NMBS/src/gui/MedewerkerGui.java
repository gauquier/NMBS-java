package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dao.Connection;
import handler.Controller;

public class MedewerkerGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5565695250792117145L;

	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.MedewerkerGui");

	private Container c = this.getContentPane();

	private static JPanel HuidigeKeuze;
	private JMenuBar menubar;
	private JMenuItem home, routeInfo, stationInfo;
	private JMenu verkoop, verlorenVoorwerpen, instellingen, klanten;
	private JMenuItem abonnementBeheer, uitloggen, ticketVerkoop,
			verlorenVoorwerpToevoegen, verlorenVoorwerpZoeken, wachtwoordVeranderen, klantenToevoegen, klantenBeheer;

	public static JPanel getHuidigeKeuze() {
		return HuidigeKeuze;
	}

	public static void setHuidigeKeuze(JPanel huidigeKeuze) {

		if (HuidigeKeuze != null) {
			HuidigeKeuze.setVisible(false);
		}
		HuidigeKeuze = huidigeKeuze;
		if (HuidigeKeuze != null) {
			HuidigeKeuze.setVisible(true);
			Controller.medewerkerInterface.getContentPane().add(MedewerkerGui.getHuidigeKeuze());
		} else {
			Controller.medewerkerInterface.dispose();
			Controller.medewerkerInterface.setVisible(false);
			LoginGui.start();
		}
	}

	public void setHome() {
		MedewerkerGui.setHuidigeKeuze(new HomeGui(false));
	}

	public Container getC() {
		return this.c;
	}

	public void setC(Container c) {
		this.c = c;
	}

	public MedewerkerGui() {

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));

		this.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.setResizable(true);
		this.menuGUI();
	}

	private void menuGUI() {
		this.createMenu();
		this.setTitle("NMBS");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		this.setMinimumSize(new Dimension(850, 550));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void createMenu() {
		this.menubar = new JMenuBar();
		this.menubar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.menubar.setForeground(Color.BLACK);
		this.menubar.setBackground(Color.ORANGE);
		this.home = new JMenuItem(bundle.getString("home"));

		this.home.setHorizontalAlignment(SwingConstants.LEFT);
		this.home.setFont(new Font("Dialog", Font.BOLD, 20));
		this.home.setHorizontalTextPosition(SwingConstants.CENTER);
		;
		this.home.setBackground(Color.ORANGE);
		this.home.setOpaque(true);
		this.home.addActionListener(new MenuItemHandler());

		this.verkoop = new JMenu(bundle.getString("verkoop"));
		this.verkoop.setHorizontalAlignment(SwingConstants.CENTER);
		this.verkoop.setFont(new Font("Dialog", Font.BOLD, 20));
		this.verkoop.setBackground(Color.ORANGE);
		this.verkoop.setOpaque(true);
		this.routeInfo = new JMenuItem(bundle.getString("routeInfo"));
		this.routeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.routeInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		this.routeInfo.setBackground(Color.ORANGE);
		this.routeInfo.setOpaque(true);
		this.routeInfo.addActionListener(new MenuItemHandler());

		this.stationInfo = new JMenuItem(bundle.getString("stationInfo"));
		this.stationInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.stationInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		this.stationInfo.setBackground(Color.ORANGE);
		this.stationInfo.setOpaque(true);
		this.stationInfo.addActionListener(new MenuItemHandler());

		this.klanten = new JMenu(bundle.getString("klanten"));
		this.klanten.setHorizontalAlignment(SwingConstants.CENTER);
		this.klanten.setFont(new Font("Dialog", Font.BOLD, 20));
		this.klanten.setBackground(Color.ORANGE);
		this.klanten.setOpaque(true);

		this.verlorenVoorwerpen = new JMenu(bundle.getString("verlorenVoorwerpen"));
		this.verlorenVoorwerpen.setHorizontalAlignment(SwingConstants.CENTER);
		this.verlorenVoorwerpen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.verlorenVoorwerpen.setBackground(Color.ORANGE);
		this.verlorenVoorwerpen.setOpaque(true);
		this.verlorenVoorwerpToevoegen = new JMenuItem(bundle.getString("verlorenVoorwerpToevoegen"));
		this.verlorenVoorwerpToevoegen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.verlorenVoorwerpToevoegen.addActionListener(new MenuItemHandler());
		this.verlorenVoorwerpZoeken = new JMenuItem(bundle.getString("verlorenVoorwerpZoeken"));
		this.verlorenVoorwerpZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.verlorenVoorwerpZoeken.addActionListener(new MenuItemHandler());
		this.verlorenVoorwerpen.add(this.verlorenVoorwerpToevoegen);
		this.verlorenVoorwerpen.add(this.verlorenVoorwerpZoeken);

		this.instellingen = new JMenu(bundle.getString("instellingen"));
		this.instellingen.setHorizontalAlignment(SwingConstants.CENTER);
		this.instellingen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.instellingen.setBackground(Color.ORANGE);
		this.instellingen.setOpaque(true);
		this.wachtwoordVeranderen = new JMenuItem(bundle.getString("wachtwoordVeranderen"));
		this.wachtwoordVeranderen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.wachtwoordVeranderen.addActionListener(new MenuItemHandler());

		this.instellingen.add(this.wachtwoordVeranderen);

		this.abonnementBeheer = new JMenuItem(bundle.getString("abonnementBeheer"));
		this.abonnementBeheer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.abonnementBeheer.setBackground(Color.WHITE);
		this.abonnementBeheer.addActionListener(new MenuItemHandler());
		this.verkoop.add(this.abonnementBeheer);


		this.klantenToevoegen = new JMenuItem(bundle.getString("klantenToevoegen"));
		this.klantenToevoegen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.klantenToevoegen.addActionListener(new MenuItemHandler());
		this.klantenBeheer = new JMenuItem(bundle.getString("klantenBeheer"));
		this.klantenBeheer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.klantenBeheer.addActionListener(new MenuItemHandler());
		this.klanten.add(this.klantenToevoegen);
		this.klanten.add(this.klantenBeheer);

		this.ticketVerkoop = new JMenuItem(bundle.getString("ticketVerkoop"));
		this.ticketVerkoop.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.ticketVerkoop.setBackground(Color.WHITE);
		this.ticketVerkoop.addActionListener(new MenuItemHandler());
		this.verkoop.add(this.ticketVerkoop);

		this.uitloggen = new JMenuItem(bundle.getString("uitloggen"));
		this.uitloggen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		this.uitloggen.addActionListener(new MenuItemHandler());
		this.instellingen.add(this.uitloggen);

		this.menubar.add(this.home);
		this.menubar.add(this.verkoop);
		this.menubar.add(this.klanten);
		this.menubar.add(this.routeInfo);
		this.menubar.add(this.stationInfo);
		this.menubar.add(this.verlorenVoorwerpen);
		this.menubar.add(this.instellingen);
		this.setJMenuBar(this.menubar);
	}

	public static void Home() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Controller.medewerkerInterface.setHome();
			}
		});
		t1.start();
	}

	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == MedewerkerGui.this.klantenToevoegen) {
				MedewerkerGui.setHuidigeKeuze(new KlantToevoegenGui());
			}

			else if (e.getSource() == MedewerkerGui.this.klantenBeheer) {
				MedewerkerGui.setHuidigeKeuze(new KlantBewerkenGui());
			}

			else if (e.getSource() == MedewerkerGui.this.uitloggen) {
				Connection.close();
				MedewerkerGui.setHuidigeKeuze(null);
			} else if (e.getSource() == MedewerkerGui.this.home) {

				MedewerkerGui.setHuidigeKeuze(new HomeGui(false));
			} else if (e.getSource() == MedewerkerGui.this.ticketVerkoop) {
				MedewerkerGui.setHuidigeKeuze(new TicketVerkoopGui(false));
			} else if (e.getSource() == MedewerkerGui.this.routeInfo) {
				MedewerkerGui.setHuidigeKeuze(new RouteZoekenGui());
			} else if (e.getSource() == MedewerkerGui.this.stationInfo) {
				MedewerkerGui.setHuidigeKeuze(new StationInfoGui());
			} else if (e.getSource() == MedewerkerGui.this.verlorenVoorwerpZoeken) {
				MedewerkerGui.setHuidigeKeuze(new VerlorenVoorwerpenZoekenGui());
			} else if (e.getSource() == MedewerkerGui.this.verlorenVoorwerpToevoegen) {
				MedewerkerGui.setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());
			} else if (e.getSource() == MedewerkerGui.this.wachtwoordVeranderen) {
				MedewerkerGui.setHuidigeKeuze(new WachtwoordVeranderenGui());
			} else if (e.getSource() == MedewerkerGui.this.abonnementBeheer) {
				MedewerkerGui.setHuidigeKeuze(new AbonnementBeheerGui());
			}

		}
	}

}