package source;

public class Login {
	private static int loginId;
	private static String username;
	private String password;
	private String email;
	private static String currentUser;

	/**
	 * @return the currentUser
	 */
	public static String getCurrentUser() {
		return currentUser;
	}
	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	public Login(String currentUser ) {
		this.currentUser = currentUser;
	}
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
	public Login(int loginId, String username, String password) {
		this.loginId = loginId;
		this.username = username;
		this.password = password;
	}

	public Login(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/**
	 * @return username (gebruikersnaam)
	 */
	public static String getUsername() {
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
	public static int getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId 
	 */
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + loginId;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (loginId != other.loginId)
			return false;
		return true;
	}

}
