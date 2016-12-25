package dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Adres;
import source.Persoon;

public class PersoonDAOTest {
	private String testClassName = "PersoonDAOTest";
	private Adres adres;
	private Persoon persoon;

	@Before
	public void initialize() throws ParseException {
		adres = new Adres("straatnaam" + testClassName, 170, "woonplaats" + testClassName, 1070, "6");
		persoon = new Persoon("voornaam" + testClassName, "achternaam" + testClassName, "email" + testClassName, adres);
	}

	@After
	public void terminate() {
		persoonVerwijderen();
	}

	@Test
	public void testAddPersoon() {
		int id = PersoonDao.addPersoon(persoon);
		int idVanDB = Help.persoonOphalen(persoon).getId();
		assertEquals(id, idVanDB);
	}
	@Test(expected=Exception.class)
	public void testAddPersoonBestaandePersoon() {
		 PersoonDao.addPersoon(persoon);
		 PersoonDao.addPersoon(persoon); 
	}
	@Test
	public void testGetPersoonId(){
		persoon=Help.persoonToevoegen(persoon);
		assertEquals(persoon.getId(), PersoonDao.getPersoonId(persoon));
	}
	@Test
	public void testGetPersoonIdMetOnbestaandePersoon(){ 
		persoon.setId(9113);
		assertEquals(0, PersoonDao.getPersoonId(persoon));
	}
	@Test
	public void testGetPersoon(){
		persoon=Help.persoonToevoegen(persoon);
		assertEquals(persoon, PersoonDao.getPersoon(persoon.getId()));
	}
	@Test
	public void testGetPersoonMetObenstaandeId(){
		persoon.setId(9113);
		assertNull(PersoonDao.getPersoon(persoon.getId()));
	}
	@Test
	public void testCheckEmail(){
		persoon=Help.persoonToevoegen(persoon);
		PersoonDao persoonDao=new PersoonDao();
		assertEquals(persoon.getId(), persoonDao.checkEmail(persoon.getEmail()));
	}
	@Test
	public void testCheckEmailMetOnbestaandePersoonEmail(){ 
		PersoonDao persoonDao=new PersoonDao();
		assertEquals(-1, persoonDao.checkEmail(persoon.getEmail()));
	}

	private void persoonVerwijderen() {
		String verwijderPersoon = "DELETE FROM Persoon WHERE voornaam LIKE '%"+testClassName+"%'";
		String verwijderAdres = "DELETE FROM Adres WHERE straat LIKE '%"+testClassName+"%'";
		Help.executeQuery(verwijderPersoon, true);
		Help.executeQuery(verwijderAdres, true);
	}

}
