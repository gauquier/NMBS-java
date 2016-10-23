package testing_source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.UserDb;

public class UserDbTest {

	private int userId, userId_test, rollId, rollId_test;
	private String username, username_test, password, password_test, jsonString;
	private UserDb userDb;

	@Before
	public void initialize() {
		userId = 1;
		rollId = 1;
		username = "username_test";
		password = "password_test";
		jsonString="UserDb [userId=" + userId + ", rollId=" + rollId + ", username=" + username + ", password=" + password+ "]";
		userId_test = 123;
		rollId_test = 456;
		username_test = "username_test2";
		password_test = "username_test2";

	}

	// ----------------------------------- UserDb Tests
	@Test
	public void UserDb_constructorTest() {
		userDb = new UserDb(userId, rollId, username, password);
		assertEquals(userId, userDb.getUserId());
		assertEquals(rollId, userDb.getRollId());
		assertEquals(username, userDb.getUsername());
		assertEquals(password, userDb.getPassword());
	}

	// ----------------------------------- setUserId Tests
	@Test
	public void setUserId_test() {
		userDb = new UserDb(userId, rollId, username, password);
		// fail
		// assertEquals(userId_test, userDb.getUserId());
		// success
		userDb.setUserId(userId_test);
		assertEquals(userId_test, userDb.getUserId());
	}

	// ----------------------------------- setRollId Tests
	@Test
	public void setRollId_test() {
		userDb = new UserDb(userId, rollId, username, password);
		// fail
		// assertEquals(rollId_test, userDb.getRollId());
		// success
		userDb.setRollId(rollId_test);
		assertEquals(rollId_test, userDb.getRollId());
	}

	// ----------------------------------- setUsername Tests
	@Test
	public void setUsername_test() {
		userDb = new UserDb(userId, rollId, username, password);
		// fail
		// assertEquals(username_test, userDb.getUsername());
		// success
		userDb.setUsername(username_test);
		assertEquals(username_test, userDb.getUsername());
	}

	// ----------------------------------- setPassword Tests
	@Test
	public void setPassword_test() {
		userDb = new UserDb(userId, rollId, username, password);
		// fail
		// assertEquals(password_test, userDb.getPassword());
		// success
		userDb.setPassword(password_test);
		assertEquals(password_test, userDb.getPassword());
	}
	// ----------------------------------- toString Tests
		@Test
		public void toString_jsonStringTest() {
			userDb = new UserDb(userId, rollId, username, password);
			assertEquals(jsonString, userDb.toString());
		}

}
