package source;

public class UserDb {
	int userId, rollId;
	String username, password;
	public UserDb(int userId, int rollId, String username, String password) {
		super();
		this.userId = userId;
		this.rollId = rollId;
		this.username = username;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRollId() {
		return rollId;
	}
	public void setRollId(int rollId) {
		this.rollId = rollId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDb [userId=" + userId + ", rollId=" + rollId + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	
}
