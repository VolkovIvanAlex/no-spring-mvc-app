package laba_5;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import laba_5.handlers.GoodHandler;
import laba_5.handlers.LoginHandler;
import laba_5.service.DefaultUserService;


public class Main
{
	public static void main(String[] args) throws Exception {
		DefaultUserService userService = new DefaultUserService();
		userService.init("Store");

		userService.createTableUsers();

		User john = new User("john" , "pass");
		userService.addUser(john);

		HttpServer server = HttpServer.create();
		server.bind(new InetSocketAddress(8765), 0);

		HttpContext loginContext = server.createContext("/login", new LoginHandler());
		HttpContext homeContext = server.createContext("/", new LoginHandler());
		HttpContext goodsContext = server.createContext("/api/good", new GoodHandler());

		Auth auth = new Auth("hello");
		auth.initUserService(userService);

		loginContext.setAuthenticator(auth);
		homeContext.setAuthenticator(auth);
		goodsContext.setAuthenticator(auth);

		server.setExecutor(null);
		server.start();
	}
}
