package source;

public class Login {
	private int loginId;
	private String username;
	private String password;
	private String email;

	/**
	 * 
	 */
	public Login() {
		// default constructor
	} 
	/**
	 * @param loginId
	 * @param username
	 * @param password
	 * @param email
	 */
	public Login(int loginId, String username, String password, String email) {
		this.loginId = loginId;
		this.username = username;
		this.password = password;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return loginId
	 */
	public int getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId 
	 */
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

}
