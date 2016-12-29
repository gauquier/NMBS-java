package gui;
/* http://zetcode.com/tutorials/javaswingtutorial/menusandtoolbars/ */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

/*import dao.AdresDAO;
import dao.KlantDAO;
import dao.MedewerkerDAO;*/

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

import handler.Controller;

public class OfflineGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 957887041197091243L;

	private static ResourceBundle bundle = ResourceBundle.getBundle("localization.OfflineGui");

	private Container c = this.getContentPane();

	private static JPanel HuidigeKeuze;
	private JMenuBar menubar;
	private JMenuItem home;
	private JMenu verkoop, instellingen;
	private JMenuItem uitloggen, ticketVerkoop;

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
			Controller.offlineInterface.getContentPane().add(OfflineGui.getHuidigeKeuze());
		} else {
			Controller.offlineInterface.dispose();
			Controller.offlineInterface.setVisible(false);
			LoginGui.start();
		}
	}

	public void setHome() {
		OfflineGui.setHuidigeKeuze(new HomeGui(true));
	}

	public Container getC() {
		return this.c;
	}

	public void setC(Container c) {
		this.c = c;
	}

	public OfflineGui() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/jonas/Desktop/NMBS-java/NMBS/lib/logo.png"));
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

		this.instellingen = new JMenu(bundle.getString("instellingen"));
		this.instellingen.setHorizontalAlignment(SwingConstants.CENTER);
		this.instellingen.setFont(new Font("Dialog", Font.BOLD, 20));
		this.instellingen.setBackground(Color.ORANGE);
		this.instellingen.setOpaque(true);

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
		this.menubar.add(this.instellingen);
		this.setJMenuBar(this.menubar);
	}

	public static void Home() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Controller.adminInterface.setHome();
			}
		});
		t1.start();
	}

	private class MenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == OfflineGui.this.uitloggen) {
				OfflineGui.setHuidigeKeuze(null);
			} else if (e.getSource() == OfflineGui.this.home) {

				OfflineGui.setHuidigeKeuze(new HomeGui(true));
			} else if (e.getSource() == OfflineGui.this.ticketVerkoop) {
				OfflineGui.setHuidigeKeuze(new TicketVerkoopGui(true));
			}

		}
	}

}