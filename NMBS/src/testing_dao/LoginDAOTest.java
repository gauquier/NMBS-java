package testing_dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.LoginDao;

public class LoginDAOTest {
	@SuppressWarnings("unused")
	private int userId, fakeUserID, rollId, fakeRollId;
	private String username, password, fakeUsername, fakePassword;

	@Before
	public void Init() {
		// de volgende is de setup van/voor 1 user, namelijk de test user die al
		// bestaan in DB
		userId = 1;
		fakeUserID = 999;
		rollId = 1;
		fakeRollId = 999;

		username = "test";
		password = "test";
		fakeUsername = "fakeusername";
		fakePassword = "fakepassword";

	}
	// ----------------------------------- getUsername Tests

	@Test
	public void getUsernameUsingExistingUserId() {
		// fail test
		// assertEquals(username, LoginDao.getUsername(fakeUserID));
		// success test
		assertEquals(username, LoginDao.getUsername(userId));
	}

	@Test
	public void getUsernameUsingNonExistentUserId() {
		// fail test
		// assertEquals(username, LoginDao.getUsername(userId));
		// success test
		assertEquals(null, LoginDao.getUsername(fakeUserID));
	}

	@Test
	public void getUsernameUsingExistingUsername() {
		// fail test
		// assertEquals(username, LoginDao.getUserName(fakeusername));
		// success test
		assertEquals(username, LoginDao.getUserName(username));
	}

	@Test
	public void getUsernameUsingNonExistentUsername() {
		// fail test
		// assertEquals(null, LoginDao.getUserName(username));
		// success test
		assertEquals(null, LoginDao.getUserName(fakeUsername));
	}

	// ----------------------------------- getPassword Tests

	@Test
	public void getPasswordUsingExistingUserId() {
		// fail test
		// assertEquals(password, LoginDao.getPassword(fakeUserID));
		// success test
		assertEquals(password, LoginDao.getPassword(userId));
	}

	@Test
	public void getPasswordUsingNonExistentUserId() {
		// fail test
		// assertEquals(null, LoginDao.getPassword(userId));
		// success test
		assertEquals(null, LoginDao.getPassword(fakeUserID));
	}

	// ----------------------------------- getRoll Tests
	@Test
	public void getRollUsingExistingUsername() {
		// fail test
		// assertEquals(rollId, LoginDao.getRoll(fakeUsername));
		// success test
		assertEquals(rollId, LoginDao.getRoll(username));
	}

	@Test
	public void getRollUsingNonExistentUsername() {
		// fail test
		// assertEquals(null, LoginDao.getRoll(username));
		// success test
		assertEquals(0, LoginDao.getRoll(fakeUsername));
	}

	// ----------------------------------- getUserId Tests
	@Test
	public void getUserIdUsingExistingUsernameAndCorrectPassword() {
		// fail tests
		// assertEquals(userId, LoginDao.userId(fakeUsername, fakePassword));
		// assertEquals(userId, LoginDao.userId(username, fakePassword));
		// assertEquals(userId, LoginDao.userId(fakeUsername, password));
		// success test
		assertEquals(userId, LoginDao.userId(username, password));
	}

	@Test
	public void getUserIdUsingNonExistentUsernameAndWrongPassword() {
		// fail test
		// assertEquals(0, LoginDao.userId(username, password));
		// success test
		assertEquals(0, LoginDao.userId(fakeUsername, fakePassword));
	}

	@Test
	public void getUserIdUsingExistingUsernameAndWrongPassword() {
		// fail test
		// assertEquals(null, LoginDao.userId(username, password));
		// success test
		assertEquals(0, LoginDao.userId(username, fakePassword));
	}

	@Test
	public void getUserIdUsingNonExistentUsernameAndCorrectPassword() {
		// fail test
		// assertEquals(0, LoginDao.userId(username, password));
		// success test
		assertEquals(0, LoginDao.userId(fakeUsername, password));
	}

	// ----------------------------------- getWachtwoord Tests
	@Test
	public void getWachtwoordUsingExistingUsername() {
		// fail test
		// assertEquals(password, LoginDao.getWachtwoord(fakeUsername));
		// success test
		assertEquals(password, LoginDao.getWachtwoord(username));
	}

	@Test
	public void getWachtwoordNonExistentUsername() {
		// fail test
		// assertEquals(null, LoginDao.getWachtwoord(username));
		// success test
		assertEquals(null, LoginDao.getWachtwoord(fakeUsername));
	}

}
