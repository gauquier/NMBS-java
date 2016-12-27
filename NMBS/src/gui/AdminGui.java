package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import handler.Controller;
import handler.VerkoopController;
import source.Klant;
import source.Medewerker;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

import dao.AdresDAO;
import dao.Connection;
import dao.KlantDAO;
import dao.MedewerkerDAO;

import java.awt.Toolkit;
import java.awt.Font;

public class AdminGui extends JFrame {
	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.AdminGui");
	
	private Container c = getContentPane();	
	
	private static JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenuItem home , routeInfo, stationInfo;
    JMenu verkoop, verlorenVoorwerpen, instellingen, gebruikers, klanten;
    JMenuItem abonnementBeheer, uitloggen, gebruikersToevoegen, gebruikersBeheer, ticketVerkoop
    , verlorenVoorwerpToevoegen, verlorenVoorwerpZoeken, wachtwoordVeranderen ,klantenToevoegen, klantenBeheer;
    private JMenuItem mntmPrijsbeheer;

	public static JPanel getHuidigeKeuze() {
		return HuidigeKeuze;
	}
	
	public static void setHuidigeKeuze(JPanel huidigeKeuze) {
		
		if (HuidigeKeuze!=null){HuidigeKeuze.setVisible(false);}
		vorigeKeuze= HuidigeKeuze;
		HuidigeKeuze = huidigeKeuze;
		if (HuidigeKeuze!=null)
		{
		HuidigeKeuze.setVisible(true);
		Controller.adminInterface.getContentPane().add(getHuidigeKeuze());
		}
		else {
		Controller.adminInterface.dispose();
		Controller.adminInterface.setVisible(false);
		LoginGui.start();
		}
	}

	public void setHome(){
		navigation= "home";
		setHuidigeKeuze(new HomeGui(false));
	}
	public Container getC() {
		return c;
	}
	public void setC(Container c) {
		this.c = c;
	}

	public AdminGui() {

		setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo.png"));

		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.setResizable(true);
		menuGUI();		
	}
	private void menuGUI() {
		createMenu();
		setTitle("NMBS");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setMinimumSize(new Dimension(850, 550));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenu() {
		menubar = new JMenuBar();
		menubar.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		menubar.setForeground(Color.BLACK);
		menubar.setBackground(Color.ORANGE);
		home = new JMenuItem(bundle.getString("home"));

		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setFont(new Font("Dialog", Font.BOLD, 20));
		home.setHorizontalTextPosition(SwingConstants.CENTER);;
		home.setBackground(Color.ORANGE);
		home.setOpaque(true);
		home.addActionListener(new MenuItemHandler());
		
		verkoop = new JMenu(bundle.getString("verkoop"));
		verkoop.setHorizontalAlignment(SwingConstants.CENTER);
		verkoop.setFont(new Font("Dialog", Font.BOLD, 20));
		verkoop.setBackground(Color.ORANGE);
		verkoop.setOpaque(true);
		routeInfo = new JMenuItem(bundle.getString("routeInfo"));
		routeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		routeInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		routeInfo.setBackground(Color.ORANGE);
		routeInfo.setOpaque(true);
		routeInfo.addActionListener(new MenuItemHandler());
		
		stationInfo = new JMenuItem(bundle.getString("stationInfo"));
		stationInfo.setHorizontalAlignment(SwingConstants.CENTER);
		stationInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		stationInfo.setBackground(Color.ORANGE);
		stationInfo.setOpaque(true);
		stationInfo.addActionListener(new MenuItemHandler());
		gebruikers = new JMenu(bundle.getString("gebruikers"));
		gebruikers.setHorizontalAlignment(SwingConstants.CENTER);
		gebruikers.setFont(new Font("Dialog", Font.BOLD, 20));
		gebruikers.setBackground(Color.ORANGE);
		gebruikers.setOpaque(true);
		
		klanten = new JMenu(bundle.getString("klanten"));
		klanten.setHorizontalAlignment(SwingConstants.CENTER);
		klanten.setFont(new Font("Dialog", Font.BOLD, 20));
		klanten.setBackground(Color.ORANGE);
		klanten.setOpaque(true);
		
		verlorenVoorwerpen = new JMenu(bundle.getString("verlorenVoorwerpen"));
		verlorenVoorwerpen.setHorizontalAlignment(SwingConstants.CENTER);
		verlorenVoorwerpen.setFont(new Font("Dialog", Font.BOLD, 20));
		verlorenVoorwerpen.setBackground(Color.ORANGE);
		verlorenVoorwerpen.setOpaque(true);
		verlorenVoorwerpToevoegen = new JMenuItem(bundle.getString("verlorenVoorwerpToevoegen"));
		verlorenVoorwerpToevoegen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		verlorenVoorwerpToevoegen.addActionListener(new MenuItemHandler());
		verlorenVoorwerpZoeken = new JMenuItem(bundle.getString("verlorenVoorwerpZoeken"));
		verlorenVoorwerpZoeken.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		verlorenVoorwerpZoeken.addActionListener(new MenuItemHandler());
		verlorenVoorwerpen.add(verlorenVoorwerpToevoegen);
		verlorenVoorwerpen.add(verlorenVoorwerpZoeken);
		
		instellingen = new JMenu(bundle.getString("instellingen"));
		instellingen.setHorizontalAlignment(SwingConstants.CENTER);
		instellingen.setFont(new Font("Dialog", Font.BOLD, 20));
		instellingen.setBackground(Color.ORANGE);
		instellingen.setOpaque(true);
		wachtwoordVeranderen = new JMenuItem(bundle.getString("wachtwoordVeranderen"));
		wachtwoordVeranderen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		wachtwoordVeranderen.addActionListener(new MenuItemHandler());
		
		mntmPrijsbeheer = new JMenuItem(bundle.getString("mntmPrijsbeheer"));
		mntmPrijsbeheer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		mntmPrijsbeheer.addActionListener(new MenuItemHandler());
		instellingen.add(mntmPrijsbeheer);
		instellingen.add(wachtwoordVeranderen);
		
		abonnementBeheer = new JMenuItem(bundle.getString("abonnementBeheer"));
		abonnementBeheer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		abonnementBeheer.setBackground(Color.WHITE);
		abonnementBeheer.addActionListener(new MenuItemHandler());
		verkoop.add(abonnementBeheer);
		
		gebruikersToevoegen = new JMenuItem(bundle.getString("gebruikersToevoegen"));
		gebruikersToevoegen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		gebruikersToevoegen.addActionListener(new MenuItemHandler());
		gebruikersBeheer = new JMenuItem(bundle.getString("gebruikersBeheer"));
		gebruikersBeheer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		gebruikersBeheer.addActionListener(new MenuItemHandler());
		gebruikers.add(gebruikersToevoegen);
		gebruikers.add(gebruikersBeheer);
		
		klantenToevoegen = new JMenuItem(bundle.getString("klantenToevoegen"));
		klantenToevoegen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		klantenToevoegen.addActionListener(new MenuItemHandler());
		klantenBeheer = new JMenuItem(bundle.getString("klantenBeheer"));
		klantenBeheer.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		klantenBeheer.addActionListener(new MenuItemHandler());
		klanten.add(klantenToevoegen);
		klanten.add(klantenBeheer);
		
		ticketVerkoop = new JMenuItem(bundle.getString("ticketVerkoop"));
		ticketVerkoop.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ticketVerkoop.setBackground(Color.WHITE);
		ticketVerkoop.addActionListener(new MenuItemHandler());
		verkoop.add(ticketVerkoop);
		
		uitloggen = new JMenuItem(bundle.getString("uitloggen"));
		uitloggen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		menubar.add(klanten);
		menubar.add(routeInfo);
		menubar.add(stationInfo);
		menubar.add(gebruikers);
		menubar.add(verlorenVoorwerpen);
		menubar.add(instellingen);
		setJMenuBar(menubar);
	}
	
	public static void Home()
	{
		 Thread t1 = new Thread(new Runnable() {
			    public void run()
			    {
			    Controller.adminInterface.setHome();     
			    }});  
			    t1.start();
	}
	
	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == gebruikersToevoegen) {	
				navigation= "gebruikerToevoegen";
				setHuidigeKeuze(new GebruikerToevoegenGui());
			}
			else if (e.getSource() == gebruikersBeheer) {	
				navigation= "gebruikersBeheer";
				setHuidigeKeuze(new GebruikersBeheerGui());
			}
		
			else if (e.getSource() == klantenToevoegen) {	
				navigation= "klantToevoegen";
				setHuidigeKeuze(new KlantToevoegenGui());
			}
			
			else if (e.getSource() == klantenBeheer) {	
				navigation= "klantenBeheer";
				setHuidigeKeuze(new KlantenBeheerGui());
			}
			
			else if (e.getSource()==uitloggen)
			{
				Connection.close();
				setHuidigeKeuze(null);
			}
			else if (e.getSource() == home){

				setHuidigeKeuze(new HomeGui(false));
			}
			else if (e.getSource() == ticketVerkoop){
				setHuidigeKeuze(new TicketVerkoopGui(false));
			}
			else if (e.getSource() == mntmPrijsbeheer){
				setHuidigeKeuze(new PrijsBeheerGui());
			}
			else if (e.getSource() == routeInfo){
				setHuidigeKeuze(new RouteZoekenGui());
			}
			else if (e.getSource() == stationInfo){
				setHuidigeKeuze(new StationInfoGui());
			}
			else if (e.getSource() == verlorenVoorwerpZoeken){
				setHuidigeKeuze(new VerlorenVoorwerpenZoekenGui());
			}
			else if (e.getSource() == verlorenVoorwerpToevoegen){
				setHuidigeKeuze(new VerlorenVoorwerpenToevoegenGui());
			}
			else if (e.getSource() == wachtwoordVeranderen){
				setHuidigeKeuze(new WachtwoordVeranderenGui());
			}
			else if (e.getSource() == abonnementBeheer){
			setHuidigeKeuze(new AbonnementBeheerGui());
			}
		
		}
	}
	
}