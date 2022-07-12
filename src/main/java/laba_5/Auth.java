package laba_5;

import java.util.List;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import laba_5.service.DefaultUserService;


public class Auth extends BasicAuthenticator
{
	private DefaultUserService userService;

	public Auth(final String realm)
	{
		super(realm);
	}

	@Override
	public boolean checkCredentials(final String username, final String password)
	{
		User user = userService.findByLogin(username);
		if (user != null)
		{
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
			{
				return true;
			}
		}
		return false;
	}

	public void initUserService(DefaultUserService userService)
	{
		this.userService = userService;
	}
}
