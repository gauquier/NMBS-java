package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.Container;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import handler.Controller;

public class AdminGui extends JFrame {
	private Container c = getContentPane();	
	
	JPanel vorigeKeuze, HuidigeKeuze;
    public String navigation;
    

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
		Controller.adminInterface.add(getHuidigeKeuze());
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
	
}