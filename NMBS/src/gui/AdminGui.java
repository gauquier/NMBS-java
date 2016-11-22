package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class AdminGui extends JFrame {
	private Container c = getContentPane();	
	
	JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenuItem home , routeInfo, stationInfo;
    JMenu verkoop, verlorenVoorwerpen, instellingen, gebruikers;
    JMenuItem abonnementVerkoop, abonnementBeheer, uitloggen, gebruikersToevoegen, gebruikersVerwijderen, ticketVerkoop
    , verlorenVoorwerpToevoegen, verlorenVoorwerpZoeken, adminToevoegen, wachtwoordVeranderen;

	public JPanel getHuidigeKeuze() {
		return HuidigeKeuze;
	}
	
	public void setHuidigeKeuze(JPanel huidigeKeuze) {
		
		if (HuidigeKeuze!=null){HuidigeKeuze.setVisible(false);}
		this.vorigeKeuze= this.HuidigeKeuze;
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
		setHuidigeKeuze(new HomeGui());
	}
	public Container getC() {
		return c;
	}
	public void setC(Container c) {
		this.c = c;
	}
	public AdminGui() {
		setBackground(new Color(0, 191, 255));
		getContentPane().setBackground(new Color(0, 191, 255));
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
		menubar.setBackground(Color.WHITE);
		home = new JMenuItem("Home");
		home.setBackground(Color.WHITE);
		home.addActionListener(new MenuItemHandler());
		verkoop = new JMenu("Verkoop");
		verkoop.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		routeInfo = new JMenuItem("Route info");
		routeInfo.addActionListener(new MenuItemHandler());
		
		stationInfo = new JMenuItem("Station info");
		stationInfo.addActionListener(new MenuItemHandler());
		gebruikers = new JMenu("Gebruikers");
		
		verlorenVoorwerpen = new JMenu("Verloren voorwerpen");
		verlorenVoorwerpToevoegen = new JMenuItem("Voorwerpen toevoegen");
		verlorenVoorwerpToevoegen.addActionListener(new MenuItemHandler());
		verlorenVoorwerpZoeken = new JMenuItem("Voorwerpen zoeken");
		verlorenVoorwerpZoeken.addActionListener(new MenuItemHandler());
		verlorenVoorwerpen.add(verlorenVoorwerpToevoegen);
		verlorenVoorwerpen.add(verlorenVoorwerpZoeken);
		
		instellingen = new JMenu("Instellingen");
		wachtwoordVeranderen = new JMenuItem("Wachtwoord veranderen");
		wachtwoordVeranderen.addActionListener(new MenuItemHandler());
		instellingen.add(wachtwoordVeranderen);
		
		abonnementBeheer = new JMenuItem("Abbonement beheer");
		abonnementBeheer.setBackground(Color.WHITE);
		abonnementVerkoop = new JMenuItem("Abbonement verkoop");
		abonnementVerkoop.setBackground(Color.WHITE);
		verkoop.add(abonnementVerkoop);
		verkoop.add(abonnementBeheer);
		
		gebruikersToevoegen = new JMenuItem("Gebruiker toevoegen");
		gebruikersToevoegen.addActionListener(new MenuItemHandler());
		gebruikersVerwijderen = new JMenuItem("Gebruiker verwijderen");
		gebruikersVerwijderen.addActionListener(new MenuItemHandler());
		adminToevoegen = new JMenuItem("Admin toevoegen");
		adminToevoegen.addActionListener(new MenuItemHandler());
		gebruikers.add(gebruikersToevoegen);
		gebruikers.add(gebruikersVerwijderen);
		gebruikers.add(adminToevoegen);
		
		ticketVerkoop = new JMenuItem("Ticketverkoop");
		ticketVerkoop.setBackground(Color.WHITE);
		ticketVerkoop.addActionListener(new MenuItemHandler());
		verkoop.add(ticketVerkoop);
		
		uitloggen = new JMenuItem("Uitloggen");
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
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
			else if (e.getSource() == gebruikersVerwijderen) {	
				navigation= "gebruikersVerwijderen";
				setHuidigeKeuze(new GebruikerVerwijderenGui());
			}
			else if (e.getSource() == adminToevoegen) {	
				navigation= "adminToevoegen";
				setHuidigeKeuze(new AdminToevoegenGui());
			}
			else if (e.getSource()==uitloggen)
			{
				setHuidigeKeuze(null);
				LoginGui.start();
			}
			else if (e.getSource() == home){
				setHuidigeKeuze(new HomeGui());
			}
			else if (e.getSource() == ticketVerkoop){
				setHuidigeKeuze(new TicketVerkoopGui());
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
		}
	}
	
}