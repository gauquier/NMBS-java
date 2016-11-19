package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import handler.Controller;
import javax.swing.UIManager;

public class MedewerkerGui  extends JFrame {
	private Container c = getContentPane();	
	
	JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    
    JMenuBar menubar;
    JMenuItem home , routeInfo, stationInfo;
    JMenu verkoop, verlorenVoorwerpen, instellingen;
    JMenuItem abonnementVerkoop, abonnementBeheer, uitloggen, ticketVerkoop, verlorenVoorwerpToevoegen, verlorenVoorwerpZoeken, wachtwoordVeranderen;
    
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
		Controller.medewerkerInterface.getContentPane().add(getHuidigeKeuze());
		}
		else {
		Controller.medewerkerInterface.dispose();
		Controller.medewerkerInterface.setVisible(false);
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
	
	MedewerkerGui(){
		getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
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
		home = new JMenuItem("Home");
		home.addActionListener(new MenuItemHandler());
		verkoop = new JMenu("Verkoop");
		routeInfo = new JMenuItem("Route info");
		routeInfo.addActionListener(new MenuItemHandler());
		
		stationInfo = new JMenuItem("Station info");
		stationInfo.addActionListener(new MenuItemHandler());
		
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
		abonnementVerkoop = new JMenuItem("Abbonement verkoop");
		verkoop.add(abonnementVerkoop);
		verkoop.add(abonnementBeheer);
		
		ticketVerkoop = new JMenuItem("Ticketverkoop");
		ticketVerkoop.addActionListener(new MenuItemHandler());
		verkoop.add(ticketVerkoop);
		
		uitloggen = new JMenuItem("Uitloggen");
		uitloggen.addActionListener(new MenuItemHandler());
		instellingen.add(uitloggen);
		
		menubar.add(home);
		menubar.add(verkoop);
		menubar.add(routeInfo);
		menubar.add(stationInfo);
		menubar.add(verlorenVoorwerpen);
		menubar.add(instellingen);
		setJMenuBar(menubar);
	}
	
	public static void Home()
	{
		 Thread t1 = new Thread(new Runnable() {
			    public void run()
			    {
			    Controller.medewerkerInterface.setHome();     
			    }});  
			    t1.start();
	}
	
	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==uitloggen)
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
