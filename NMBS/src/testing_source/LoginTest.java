package testing_source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Login;

public class LoginTest {
	private String usernameTest;
	//opmerking! als deze var
	//hetzelde naam heeft als die van Login(rollId)
	//en je wijzigd zijn waarde dan wijzigd die van 
	//login ook om die static is
	//nog een opmerking!
	//als een primitieve int geen waarde kreeg bij
	//dan kreeg die een automatisch 0 
	private int rollIdTest;  
	@Before
	public void initialize() { 
		usernameTest = "test";
		rollIdTest = 2; 
	}
	@Test
	public void setRollId_FailTest() {
		// fail
//		 assertNotEquals(0, Login.getRollId());
		// System.out.println(Login.getRollId());
		// success 
		assertEquals(0, Login.getRollId());
	}
	@Test
	public void setUsername_FailTest() {
		// fail
		//assertNotEquals(null, Login.getUsername());
		// success 
		assertEquals(null, Login.getUsername());
	} 
	@Test
	public void setUsername_SuccessTest() {
		// fail
		// assertEquals(null, Login.getUsername());
		// success
		Login.setUsername(usernameTest);
		assertEquals(usernameTest, Login.getUsername());
	}

	@Test
	public void setRollId_SuccessTest() {
		// fail
		// assertEquals(0, login.getRollId());
		// success
		Login.setRollId(rollIdTest);
		assertEquals(rollIdTest, Login.getRollId());
	}

}
