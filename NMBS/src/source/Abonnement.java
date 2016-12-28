package source;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import dao.PeriodeDAO;

public class Abonnement extends Aankoop {
	private Klant klant;
	private String depZone, arrZone;
	private boolean actief;
	private int abonnementId;
	private Periode p;
	
	public Abonnement(double korting, double prijs, VerkoopType verkoop, Klant klant, String depZone, String arrZone,
			boolean actief) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
		this.actief = actief;
	}

	public Abonnement(double korting, double prijs, VerkoopType verkoop, Klant klant, String depZone, String arrZone) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
	}
	

	public Abonnement(int abonnementId, Klant klant, String depZone, String arrZone , double prijs ,  VerkoopType verkoop,double korting, 
			boolean actief) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
		this.actief = actief;
		this.abonnementId = abonnementId;
		this.p=PeriodeDAO.getPeriode(this);
	}
	public Abonnement(int abonnementId, Klant klant, String depZone, String arrZone , double prijs ,  VerkoopType verkoop,double korting 
			, Periode periode, boolean actief) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
		this.actief = actief;
		this.abonnementId = abonnementId;
		this.p=periode;
	}
	
	public Periode getP() {
		return p;
	}

	public void setP(Periode p) {
		this.p = p;
	}

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public String getDepZone() {
		return depZone;
	}

	public void setDepZone(String depZone) {
		this.depZone = depZone;
	}

	public String getArrZone() {
		return arrZone;
	}

	public void setArrZone(String arrZone) {
		this.arrZone = arrZone;
	}

	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}
	
	public int getAbonnementId() {
		return abonnementId;
	}

	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}

	public long getResterendeDagen(){
		long resterendeDagen=0;
		
		if(p!=null){
			resterendeDagen= TimeUnit.DAYS.convert((p.getEndDate().getTime()-Calendar.getInstance().getTime().getTime()), TimeUnit.MILLISECONDS);
			resterendeDagen+=1;
		}
		
		return resterendeDagen;
	}
	
	
	public String toString()
	{
		String resultaat=null;
		SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
		long resterendeDagen=getResterendeDagen();

		
		
		if(resterendeDagen > 0){
		resultaat = getKlant().getVoornaam() + " " + getKlant().getAchternaam() + " / " + formatDatum.format(p.getStartDate()) + " -> " + formatDatum.format(p.getEndDate()) + " / " + resterendeDagen + " dag(en) resterend";
		
		}else {
		this.setP(null);
		resultaat= getKlant().getVoornaam() + " " + getKlant().getAchternaam() + " / Geen actieve periode gelinkt aan dit abonnement";
			
		}
		
		return resultaat;
	}
	
	
	
}