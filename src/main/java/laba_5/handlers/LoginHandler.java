package laba_5.handlers;

import com.sun.net.httpserver.*;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;


public class LoginHandler implements HttpHandler
{
	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		StringBuilder builder = new StringBuilder();

		builder.append("<h1>URI: ").append(exchange.getRequestURI()).append("</h1>");

		Headers headers = exchange.getRequestHeaders();
		for (String header : headers.keySet())
		{
			builder.append("<p>").append(header).append("=")
					.append(headers.getFirst(header)).append("</p>");
		}

		byte[] bytes = builder.toString().getBytes();
		exchange.getResponseHeaders().add("Set-Cookie" , "some cookie");
		exchange.sendResponseHeaders(200, bytes.length);

		OutputStream os = exchange.getResponseBody();
		os.write(bytes);
		os.close();
	}
}

