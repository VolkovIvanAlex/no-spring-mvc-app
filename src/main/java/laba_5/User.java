package laba_5;

public class User
{
	private Integer userId;
	private String username;
	private String password;

	public User(final Integer userId, final String username, final String password)
	{
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public User(final String username, final String password)
	{
		this.username = username;
		this.password = password;
	}

	public User()
	{
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(final Integer userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}
}
