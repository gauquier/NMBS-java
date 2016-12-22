package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.ComponentOrientation;
import java.awt.Container;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("NMBS/lib/logo-nmbs.png"));
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		this.setResizable(true);
		menuGUI();		
	}
	private void menuGUI() {
		createMenu();
		setTitle("NMBS");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createMenu() {
		menubar = new JMenuBar();
		menubar.setForeground(Color.BLACK);
		menubar.setBackground(Color.ORANGE);
		home = new JMenuItem("Home");

		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setFont(new Font("Segoe UI", Font.BOLD, 14));
		home.setHorizontalTextPosition(SwingConstants.CENTER);;
		home.setBackground(Color.ORANGE);
		home.setOpaque(true);
		home.addActionListener(new MenuItemHandler());
		
		verkoop = new JMenu("Verkoop");
		verkoop.setHorizontalAlignment(SwingConstants.CENTER);
		verkoop.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verkoop.setBackground(Color.ORANGE);
		verkoop.setOpaque(true);
		routeInfo = new JMenuItem("Route info");
		routeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		routeInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		routeInfo.setBackground(Color.ORANGE);
		routeInfo.setOpaque(true);
		routeInfo.addActionListener(new MenuItemHandler());
		
		stationInfo = new JMenuItem("Station info");
		stationInfo.setHorizontalAlignment(SwingConstants.CENTER);
		stationInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		stationInfo.setBackground(Color.ORANGE);
		stationInfo.setOpaque(true);
		stationInfo.addActionListener(new MenuItemHandler());
		gebruikers = new JMenu("Gebruikers");
		gebruikers.setHorizontalAlignment(SwingConstants.CENTER);
		gebruikers.setFont(new Font("Segoe UI", Font.BOLD, 14));
		gebruikers.setBackground(Color.ORANGE);
		gebruikers.setOpaque(true);
		
		klanten = new JMenu("Klanten");
		klanten.setHorizontalAlignment(SwingConstants.CENTER);
		klanten.setFont(new Font("Segoe UI", Font.BOLD, 14));
		klanten.setBackground(Color.ORANGE);
		klanten.setOpaque(true);
		
		verlorenVoorwerpen = new JMenu("Verloren voorwerpen");
		verlorenVoorwerpen.setHorizontalAlignment(SwingConstants.CENTER);
		verlorenVoorwerpen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		verlorenVoorwerpen.setBackground(Color.ORANGE);
		verlorenVoorwerpen.setOpaque(true);
		verlorenVoorwerpToevoegen = new JMenuItem("Voorwerpen toevoegen");
		verlorenVoorwerpToevoegen.addActionListener(new MenuItemHandler());
		verlorenVoorwerpZoeken = new JMenuItem("Voorwerpen zoeken");
		verlorenVoorwerpZoeken.addActionListener(new MenuItemHandler());
		verlorenVoorwerpen.add(verlorenVoorwerpToevoegen);
		verlorenVoorwerpen.add(verlorenVoorwerpZoeken);
		
		instellingen = new JMenu("Instellingen");
		instellingen.setHorizontalAlignment(SwingConstants.CENTER);
		instellingen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		instellingen.setBackground(Color.ORANGE);
		instellingen.setOpaque(true);
		wachtwoordVeranderen = new JMenuItem("Wachtwoord veranderen");
		wachtwoordVeranderen.addActionListener(new MenuItemHandler());
		instellingen.add(wachtwoordVeranderen);
		
		abonnementBeheer = new JMenuItem("Abonnementen beheren");
		abonnementBeheer.setBackground(Color.WHITE);
		abonnementBeheer.addActionListener(new MenuItemHandler());
		verkoop.add(abonnementBeheer);
		
		gebruikersToevoegen = new JMenuItem("Gebruiker toevoegen");
		gebruikersToevoegen.addActionListener(new MenuItemHandler());
		gebruikersBeheer = new JMenuItem("Gebruikers beheren");
		gebruikersBeheer.addActionListener(new MenuItemHandler());
		gebruikers.add(gebruikersToevoegen);
		gebruikers.add(gebruikersBeheer);
		
		klantenToevoegen = new JMenuItem("Klant toevoegen");
		klantenToevoegen.addActionListener(new MenuItemHandler());
		klantenBeheer = new JMenuItem("Klanten beheren");
		klantenBeheer.addActionListener(new MenuItemHandler());
		klanten.add(klantenToevoegen);
		klanten.add(klantenBeheer);
		
		ticketVerkoop = new JMenuItem("Ticketverkoop");
		ticketVerkoop.setBackground(Color.WHITE);
		ticketVerkoop.addActionListener(new MenuItemHandler());
		verkoop.add(ticketVerkoop);
		
		uitloggen = new JMenuItem("Uitloggen");
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		
		mntmPrijsbeheer = new JMenuItem("Prijsbeheer");
		mntmPrijsbeheer.addActionListener(new MenuItemHandler());
		verkoop.add(mntmPrijsbeheer);
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
				setHuidigeKeuze(new GebruikerBewerkenGui());
			}
		
			else if (e.getSource() == klantenToevoegen) {	
				navigation= "klantToevoegen";
				setHuidigeKeuze(new KlantToevoegenGui());
			}
			
			else if (e.getSource() == klantenBeheer) {	
				navigation= "klantenBeheer";
				setHuidigeKeuze(new KlantBewerkenGui());
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
			//	setHuidigeKeuze(new AbonnementBeheerGui());
			}
		
		}
	}
	
}