package gui;

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
import javax.swing.UIManager;

import dao.Connection;
import handler.Controller;

public class MedewerkerGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5340584543659480631L;

	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.MedewerkerGui");

	private Container c = this.getContentPane();

	private JPanel HuidigeKeuze;
	private JMenuBar menubar;
	private JMenuItem home, routeInfo, stationInfo;
	private JMenu verkoop, verlorenVoorwerpen, instellingen;
	private JMenuItem abonnementVerkoop, abonnementBeheer, uitloggen, ticketVerkoop, verlorenVoorwerpToevoegen,
			verlorenVoorwerpZoeken, wachtwoordVeranderen;

	public JPanel getHuidigeKeuze() {
		return this.HuidigeKeuze;
	}

	public void setHuidigeKeuze(JPanel huidigeKeuze) {

		if (this.HuidigeKeuze != null) {
			this.HuidigeKeuze.setVisible(false);
		}
		this.HuidigeKeuze = huidigeKeuze;
		if (this.HuidigeKeuze != null) {
			this.HuidigeKeuze.setVisible(true);
			Controller.medewerkerInterface.getContentPane().add(this.getHuidigeKeuze());
		} else {
			Controller.medewerkerInterface.dispose();
			Controller.medewerkerInterface.setVisible(false);
			LoginGui.start();
		}
	}

	public void setHome() {
		this.setHuidigeKeuze(new HomeGui(false));
	}

	public Container getC() {
		return this.c;
	}

	public void setC(Container c) {
		this.c = c;
	}

	MedewerkerGui() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));
		this.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.setBackground(new Color(0, 191, 255));
		this.setResizable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		this.setMinimumSize(new Dimension(850, 550));
		this.menuGUI();
	}

	private void menuGUI() {
		this.createMenu();
		this.setTitle("NMBS");
		this.setSize(800, 500);
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
		this.home.setBackground(Color.ORANGE);
		this.home.setFont(new Font("Dialog", Font.BOLD, 20));
		this.home.setOpaque(true);
		this.home.addActionListener(new MenuItemHandler());

		this.verkoop = new JMenu(bundle.getString("verkoop"));
		this.verkoop.setBackground(Color.ORANGE);
		this.verkoop.setFont(new Font("Dialog", Font.BOLD, 20));
		this.verkoop.setOpaque(true);

		this.routeInfo = new JMenuItem(bundle.getString("routeInfo"));
		this.routeInfo.setBackground(Color.ORANGE);
		this.routeInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		this.routeInfo.setOpaque(true);
		this.routeInfo.addActionListener(new MenuItemHandler());

		this.stationInfo = new JMenuItem(bundle.getString("stationInfo"));
		this.stationInfo.setBackground(Color.ORANGE);
		this.stationInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		this.stationInfo.setOpaque(true);
		this.stationInfo.addActionListener(new MenuItemHandler());

		this.verlorenVoorwerpen = new JMenu(bundle.getString("verlorenVoorwerpen"));
		this.verlorenVoorwerpen.setBackground(Color.ORANGE);
		this.verlorenVoorwerpen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.verlorenVoorwerpen.setOpaque(true);

		this.verlorenVoorwerpToevoegen = new JMenuItem(bundle.getString("verlorenVoorwerpToevoegen"));
		this.verlorenVoorwerpToevoegen.setBackground(Color.ORANGE);
		this.verlorenVoorwerpToevoegen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.verlorenVoorwerpToevoegen.setOpaque(true);
		this.verlorenVoorwerpToevoegen.addActionListener(new MenuItemHandler());

		this.verlorenVoorwerpZoeken = new JMenuItem(bundle.getString("verlorenVoorwerpZoeken"));
		this.verlorenVoorwerpZoeken.setBackground(Color.ORANGE);
		this.verlorenVoorwerpZoeken.setFont(new Font("Dialog", Font.BOLD, 20));
		this.verlorenVoorwerpZoeken.setOpaque(true);
		this.verlorenVoorwerpZoeken.addActionListener(new MenuItemHandler());

		this.verlorenVoorwerpen.add(this.verlorenVoorwerpToevoegen);
		this.verlorenVoorwerpen.add(this.verlorenVoorwerpZoeken);

		this.instellingen = new JMenu("instellingen");
		this.instellingen.setBackground(Color.ORANGE);
		this.instellingen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.instellingen.setOpaque(true);

		this.wachtwoordVeranderen = new JMenuItem(bundle.getString("wachtwoordVeranderen"));
		this.wachtwoordVeranderen.setBackground(Color.ORANGE);
		this.wachtwoordVeranderen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.wachtwoordVeranderen.setOpaque(true);
		this.wachtwoordVeranderen.addActionListener(new MenuItemHandler());
		this.instellingen.add(this.wachtwoordVeranderen);

		this.abonnementBeheer = new JMenuItem(bundle.getString("abonnementBeheer"));
		this.abonnementBeheer.setBackground(Color.ORANGE);
		this.abonnementBeheer.setFont(new Font("Dialog", Font.BOLD, 20));
		this.abonnementBeheer.setOpaque(true);
		this.abonnementVerkoop = new JMenuItem(bundle.getString("abonnementVerkoop"));
		this.abonnementVerkoop.setFont(new Font("Dialog", Font.BOLD, 20));
		this.abonnementVerkoop.setBackground(Color.ORANGE);
		this.abonnementVerkoop.setOpaque(true);
		this.verkoop.add(this.abonnementVerkoop);
		this.verkoop.add(this.abonnementBeheer);

		this.ticketVerkoop = new JMenuItem(bundle.getString("ticketVerkoop"));
		this.ticketVerkoop.setFont(new Font("Dialog", Font.BOLD, 20));
		this.ticketVerkoop.setBackground(Color.ORANGE);
		this.ticketVerkoop.setOpaque(true);
		this.ticketVerkoop.addActionListener(new MenuItemHandler());
		this.verkoop.add(this.ticketVerkoop);

		this.uitloggen = new JMenuItem(bundle.getString("uitloggen"));
		this.uitloggen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.uitloggen.setBackground(Color.ORANGE);
		this.uitloggen.setOpaque(true);
		this.uitloggen.addActionListener(new MenuItemHandler());
		this.instellingen.add(this.uitloggen);

		this.menubar.add(this.home);
		this.menubar.add(this.verkoop);
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
			if (e.getSource() == MedewerkerGui.this.uitloggen) {
				Connection.close();
				MedewerkerGui.this.setHuidigeKeuze(null);
				LoginGui.start();
			} else if (e.getSource() == MedewerkerGui.this.home) {
				MedewerkerGui.this.setHuidigeKeuze(new HomeGui(false));
			} else if (e.getSource() == MedewerkerGui.this.ticketVerkoop) {
				MedewerkerGui.this.setHuidigeKeuze(new TicketVerkoopGui(false));
			} else if (e.getSource() == MedewerkerGui.this.routeInfo) {
				MedewerkerGui.this.setHuidigeKeuze(new RouteZoekenGui());
			} else if (e.getSource() == MedewerkerGui.this.stationInfo) {
				MedewerkerGui.this.setHuidigeKeuze(new StationInfoGui());
			} else if (e.getSource() == MedewerkerGui.this.verlorenVoorwerpZoeken) {
				MedewerkerGui.this.setHuidigeKeuze(new VerlorenVoorwerpenZoekenGui());
			} else if (e.getSource() == MedewerkerGui.this.verlorenVoorwerpToevoegen) {
				MedewerkerGui.this.setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());
			} else if (e.getSource() == MedewerkerGui.this.wachtwoordVeranderen) {
				MedewerkerGui.this.setHuidigeKeuze(new WachtwoordVeranderenGui());
			}
		}
	}
}
