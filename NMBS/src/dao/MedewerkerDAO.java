package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.Klant;
import source.Medewerker;
import source.Persoon;

public class MedewerkerDAO { 
	/**
	 * Medewerker Toevoegen Als Medewerker al bestaat in databank return null
	 * return Medewerker die een Medewerker id heeft als Medewerker toevoegen
	 * met success is volbracht
	 * 
	 * @param medewerker
	 *            - nieuw medewerker die moet toegevoegd worden
	 * @return een <b>medewerker</b> object die een id heeft van zowel
	 *         Persoon.persoonId en Medewerker.medewerkerId en alle info die
	 *         bijhoren <br>
	 *         <br>
	 *         <b>Werking: </b><br>
	 *         Medewerker wordt niet toegevoegd tenzij alle stapen in order zijn.
	 *         Er wordt bij elke stap verwacht dat een exception wordt gegooid 
	 *         als opdracht wordt niet met succes volbracht.
	 *         Als een exception voorkomt in een van de stapen wordt er een rol back
	 *         gedaan. vb als persoon al bestaat in DB en een exception wordt gegooid
	 *         wordt de login die in de vorige stap met succes wordt toegevoed verwijderd
	 * 
	 *         <ol>
	 *         <li><b>stap 1</b><br>
	 *         login toevoegen  </li>
	 *         <li><b>stap 2</b><br>
	 *         persoon toevoegen</li>
	 *         <li><b>stap 3</b><br>
	 *        rolId nagaan of die all in DB voorkomt zo niet throw Exception en doe roll back</li>
	 *         <li><b>stap 4</b><br>
	 *         medewerker toevoegen </li> 
	 *         </ol>
	 * @throws Exception
	 *             medewerkerException met een bericht van waar in het process
	 *             van werker toevoegen de exception komt bv. Login naam is all
	 *             in gebruik
	 */
	public static Medewerker medewerkerToevoegen(Medewerker medewerker) throws Exception {
		/*
		 * nieuwe manier van werken een array van success flags als het lukt met
		 * vb login toevoegen dan wordt flag 0 true als een van de flags niet
		 * lukt wordt een soort rol back gedaan dus bv als toevoegen van persoon
		 * niet lukt en login toevoegen is wel gelukt dan wordt de login
		 * verwijdert medewerkerToevoegen gooit een exception
		 */
		boolean[] medewerkerToevoegenSuccesArray = new boolean[4];
		/*
		 * [0] login toevoegen [1] persoon toevoegen [2] rol toevoegen [3]
		 * medewerker toevoegen
		 */
		for (boolean success : medewerkerToevoegenSuccesArray) {
			success = false;
		}
		if (medewerker == null) {
			throw new Exception("medewerkerToevoegen: medewerker is null");
		}
		// ik heb dit in stappen gedaan
		try {

			medewerker.setLogin(LoginDao.loginToevoegen(medewerker.getLogin()));
			medewerkerToevoegenSuccesArray[0] = true;

			Persoon persoon = PersoonDao.persoonToevoegen(medewerker);
			medewerker.setPersoonId(persoon.getPersoonId());
			// omdat nu adres een id zou moeten gekregen hebben toen persoon
			// werd toegevoegd
			medewerker.setAdres(persoon.getAdres());
			medewerkerToevoegenSuccesArray[1] = true;

			medewerker.setRol(RolDAO.zoekRolOpRolNaam(medewerker.getRol()));
			medewerkerToevoegenSuccesArray[2] = true;

			DBA dba = new DBA();
			dba.createInsert("Medewerker");
			dba.addValue(medewerker.getLogin().getLoginId());
			dba.addValue(medewerker.getPersoonId());
			dba.addValue(medewerker.getRol().getRolId());
			dba.addValue(medewerker.isActief() ? 1 : 0);
			dba.commit();
			dba.createSelect("Medewerker", "medewerkerId");
			dba.addWhere("loginId", medewerker.getLogin().getLoginId());
			dba.addWhere("persoonId", medewerker.getPersoonId());
			dba.addWhere("rolId", medewerker.getRol().getRolId());
			ResultSet rs = dba.commit();
			if(rs==null){
				throw new Exception("medewerkerId zoeken na het toevoegen van een nieuw medewerker is niet gelukt");
				
			}
			try {
				if (rs.next()) {
					medewerker.setMedewerkerId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null && !rs.isClosed())
					rs.close();
			}

			return medewerker;

		} catch (Exception e) {
			try {
				if (medewerkerToevoegenSuccesArray[0]) {
					LoginDao.loginVerwijderen(medewerker.getLogin());
				}
				if (medewerkerToevoegenSuccesArray[1]) {
					PersoonDao.persoonVerwijderen(medewerker);
				}
			} catch (Exception ex) {
				throw new Exception("medewerkerToevoegen: " + ex.getMessage());
			}
			throw new Exception("medewerkerToevoegen: " + e.getMessage());
		}
	}

	/**
	 * Medewerker wijzigen
	 * 
	 * @param medewerker
	 *            de tewijzigen medewerker
	 * @return true als medewerker gegevens gewijzigd zijn
	 * @exception ExceptionMetBericht
	 *                medewerker gegevens niet juist bv. als een combinatie van
	 *                persoonId en medewerkerId en login(en zijn equals is niet
	 *                gelijk aan die van deze medewerker) <br>
	 *                <br>
	 *                <b>Werking:</b><br>
	 *                <ol>
	 *                <li><b>stap 1</b> checken of medewerker in DB staat</li>
	 *                <li><b>stap 2</b> checken of medewerker in DB = medewerker
	 *                die gegeven wordt als parameter. met behulp van
	 *                custom(aangepast) medewerker.equals</li>
	 *                <li><b>stap 3</b> persoon info. wijzigen mbv
	 *                PersoonDao.persoonWijzigen(medewerker);</li>
	 *                <li><b>stap 4</b> de rol van medewerker wijzigen. Als die
	 *                rolnaam die gegeven wordt niet in DB voorkomt wordt een
	 *                exception gegooid en verder wijzigingen stoppen daar maar
	 *                als de rol naam bestaat in DB wordt medewerker rol
	 *                gewijzigd</li>
	 *                <li><b>stap 5</b> de login van wedewerker wordt gewijzigd.
	 *                <ol>
	 *                <li>Als de login naam niet uniek is en dus bestaat al in
	 *                DB wordt er verwacht dat
	 *                LoginDao.loginWijzigen(medewerker.getLogin()) een
	 *                exception gooit.</li>
	 *                <li>Als de naam wel uniek is dan loopt medewerkerWijzigen
	 *                verder</li>
	 *                </ol>
	 *                </li>
	 *                <li><b>stap 6</b> Medewerker tabel wordt gewijzigd. De
	 *                kolom actief wordt gewijzigd</li>
	 *                </ol>
	 */
	public static boolean medewerkerWijzigen(Medewerker medewerker) throws Exception {
		// waarom gebruik ik zo veel thows Exception ?
		// omdat return van boolean niet veel informatie terug geeft
		// van wat er miss loopt

		boolean succes = false;
		if (medewerker == null) {
			throw new Exception("medewerkerWijzigen: gegeven medewerker parameter is null");
		} 
		try {
			Medewerker dbMedewerker = MedewerkerDAO.medewerkerZoekenOpMedewerkerId(medewerker.getMedewerkerId());
			if (dbMedewerker == medewerker) {
			PersoonDao.persoonWijzigen(medewerker);
			medewerker.setRol(RolDAO.zoekRolOpRolNaam(medewerker.getRol()));
			LoginDao.loginWijzigen(medewerker.getLogin());
			DBA dba= new DBA();
			dba.createUpdate("Medewerker", "actief",medewerker.isActief());
			dba.createUpdate("Medewerker", "rolId",medewerker.getRol().getRolId());
			dba.addWhere("medewerkerId",medewerker.getMedewerkerId());
			succes = true;
			} else{
				throw new Exception("medewerker bestaat niet in DB");
			} 
			 
		} catch (Exception e) {
			throw new Exception("medewerkerWijzigen: "+e.getMessage());
		}  
		return succes;
	}

	/**
	 * Zoekt medewerker op medewerker id
	 * 
	 * @param medewerkerId
	 * @return Medewerker met alle informatie van deze medewerker ingevuld
	 * @throws ExceptionmedewerkerZoekenOpMedewerkerId
	 *             als:<br>
	 *             <br>
	 *             <ol>
	 *             <li>medewerkerId = 0</li>
	 *             <li>medewerker wordt niet gevonden in DB</li>
	 *             <li>een van de onderligende subklassen zoals LoginDAO heeft
	 *             een exception gegooid</li>
	 *             </ol>
	 *             <br>
	 *             <b>Werking:</b>
	 *             <ol>
	 *             <li>stap 1 zoeken in DB voor medewerker op medewerker Id.
	 *             Niet persoonId</li>
	 *             <li>stap 2 als medewerker wordt gevonden dan vul local
	 *             medewerker object met medewerker tabel info
	 *             <ol>
	 *             <li>als medewerker niet gevonden dan wordt er een exception
	 *             gegooid</li>
	 *             </ol>
	 *             </li>
	 *             <li>stap 3 alle andere medewerker gegevens worden ingevuld
	 *             als volgt:
	 *             <ol>
	 *             <li>Persoon gegevens dat medewerker class overerft van
	 *             Persson worden ingevuld mbv PersoonDAO. Het is de job van
	 *             PersoonDAO om te zorgen dat alles goed ingevuld wordt van
	 *             persoon</li>
	 *             <li>Login gegevens van medewerker worden goed ingevuld mbv
	 *             LoginDAO</li>
	 *             <li>Rol gegeven van medewerker worden goed ingevuld mbv.
	 *             RolDAO</li>
	 *             </ol>
	 *             </li> 
	 *             </ol>
	 */
	public static Medewerker medewerkerZoekenOpMedewerkerId(int medewerkerId) throws Exception {
		if (medewerkerId == 0) {
			throw new Exception("medewerkerZoekenOpMedewerkerId: gegeven medewerkerId parameter is 0");
		}
		Medewerker medewerker = null;
		DBA dba = new DBA(); 
		dba.createSelect("Medewerker");
		dba.addWhere("medewerkerId", medewerkerId); 
		ResultSet resultSet = dba.commit();
		if(resultSet==null){
			throw new Exception("medewerkerId zoeken na het toevoegen van een nieuw medewerker is niet gelukt"); 
		}
		try {
			if (resultSet.next()) {
				medewerker=(Medewerker) PersoonDao.zoekPersoonOpPersoonId(resultSet.getInt(3));
				medewerker.setMedewerkerId(resultSet.getInt(1));
				medewerker.setLogin(LoginDao.loginZoekenOpLoginId(resultSet.getInt(2))); 
				medewerker.setRol(RolDAO.zoekRolOpRolId(resultSet.getInt(4)));
				medewerker.setActief(resultSet.getInt(5) == 0 ? false : true);
			}else{
				throw new Exception("medewerkerId zoeken na het toevoegen van een nieuw medewerker is niet gelukt");				
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e){
			throw new Exception("medewerkerZoekenOpMedewerkerId: "+e.getMessage());			
		}
		finally {
			if (resultSet != null && !resultSet.isClosed())
				resultSet.close();
		}
		return medewerker; 
	}
/**
 * Alle medewerkers halen 
 * @return List<Medewerker> met alle overligende gegevens(dus geen lazy loading)
 * @exception getAlleMedewerkers
 * 
 * */
 public static List<Medewerker> getAlleMedewerkers() throws Exception{
	 DBA dba= new DBA();
	 dba.createSelect("Medewerker");
	 ResultSet resultSet=dba.commit();
	 List<Medewerker>medewerkers= new ArrayList<>();
	 try {
		while(resultSet.next()){
			Medewerker medewerker= (Medewerker) PersoonDao.zoekPersoonOpPersoonId(resultSet.getInt(2));
			medewerker.setLogin(LoginDao.loginZoekenOpLoginId(resultSet.getInt(1)));
			medewerker.setRol(RolDAO.zoekRolOpRolId(resultSet.getInt(4)));
			medewerker.setActief(resultSet.getInt(5)==0?false:true);
			medewerkers.add(medewerker);
			 
			 
		 }
	} catch (SQLException e) { 
		e.printStackTrace();
	} catch (Exception e) {
		 throw new Exception("getAlleMedewerkers: "+e.getMessage());
	}finally {
		if (resultSet != null && !resultSet.isClosed())
			resultSet.close();
	}
	 return medewerkers; 
 } 
}
