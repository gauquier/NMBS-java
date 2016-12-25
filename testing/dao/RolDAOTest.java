package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.Rol;

public class RolDAOTest {
	private String testClassName = "RolDAOTest";
	private Rol rol;

	@Before
	public void initialize() {
		rol=new Rol(0, testClassName);
	}

	@After
	public void terminate() {
		rolVerwijderen();
	}

	@Test
	public void testAddRol() { 
		int nieuwRolId=RolDAO.addRol(rol);
		assertEquals(Help.rolOphalen(rol).getRolId(), nieuwRolId);
	}
	@Test(expected= Exception.class)
	public void testAddRolMetBestaandeRol() { 
		RolDAO.addRol(rol);
		RolDAO.addRol(rol);
	}
	@Test
	public void testGetRolId() { 
		Rol rolTmp=Help.rolToevoegen(rol); 
		assertEquals(rolTmp.getRolId(), RolDAO.getRolId(rol));
	}
	@Test
	public void testGetRolIdMetOnbestaandeRolId() {  
		assertEquals(0, RolDAO.getRolId(rol));
	}
	@Test
	public void testGetRol() { 
		Rol rolTmp=Help.rolToevoegen(rol); 
		assertEquals(rolTmp, RolDAO.getRol(rolTmp.getRolId()));
	}
	@Test
	public void testGetRolMetOnbestaandeRolId() { 
		int onbestaandeRolId=9119;
		assertNull(RolDAO.getRol(onbestaandeRolId));
	}
	private void rolVerwijderen() {
		String rolVerwijderen = "DELETE FROM Rol WHERE rol LIKE '%" + testClassName + "%'";
		Help.executeQuery(rolVerwijderen, true);
	}

}
