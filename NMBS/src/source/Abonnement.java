package source;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Abonnement extends Aankoop {
	private Klant klant;
	private String depZone, arrZone;
	private boolean actief;
	private int abonnementId;
	private Periode p;

	public Abonnement(double korting, double prijs, VerkoopType verkoop, Klant klant, String depZone, String arrZone) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
	}

	public Abonnement(int abonnementId, Klant klant, String depZone, String arrZone, double prijs, VerkoopType verkoop,
			double korting, Periode periode, boolean actief) {
		super(korting, prijs, verkoop);
		this.klant = klant;
		this.depZone = depZone;
		this.arrZone = arrZone;
		this.actief = actief;
		this.abonnementId = abonnementId;
		this.p = periode;
	}

	public Periode getP() {
		return this.p;
	}

	public void setP(Periode p) {
		this.p = p;
	}

	public Klant getKlant() {
		return this.klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public String getDepZone() {
		return this.depZone;
	}

	public void setDepZone(String depZone) {
		this.depZone = depZone;
	}

	public String getArrZone() {
		return this.arrZone;
	}

	public void setArrZone(String arrZone) {
		this.arrZone = arrZone;
	}

	public boolean isActief() {
		return this.actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	public int getAbonnementId() {
		return this.abonnementId;
	}

	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}

	public long getResterendeDagen() {
		long resterendeDagen = 0;

		if (this.p != null) {
			resterendeDagen = TimeUnit.DAYS.convert(
					(this.p.getEndDate().getTime() - Calendar.getInstance().getTime().getTime()),
					TimeUnit.MILLISECONDS);
			resterendeDagen += 1;
		}

		return resterendeDagen;
	}

	@Override
	public String toString() {
		String resultaat = null;
		SimpleDateFormat formatDatum = new SimpleDateFormat("dd-MM-yyyy");
		long resterendeDagen = this.getResterendeDagen();

		if (resterendeDagen > 0) {
			resultaat = this.getKlant().getVoornaam() + " " + this.getKlant().getAchternaam() + " / "
					+ formatDatum.format(this.p.getStartDate()) + " -> " + formatDatum.format(this.p.getEndDate())
					+ " / " + resterendeDagen + " dag(en) resterend";

		} else {
			this.setP(null);
			resultaat = this.getKlant().getVoornaam() + " " + this.getKlant().getAchternaam()
					+ " / Geen actieve periode gelinkt aan dit abonnement";

		}

		return resultaat;
	}

}