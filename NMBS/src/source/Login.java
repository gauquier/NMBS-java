package source;

public class Login {
	private String username;
	private String password;

	/**
	 * 
	 */
	public Login() {
		// default constructor
	}

	/**
	 * @param username
	 * @param password
	 */
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return username (gebruikersnaam)
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            gebruikersnaam
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password (wachtwoord)
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            wachtwoord
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
