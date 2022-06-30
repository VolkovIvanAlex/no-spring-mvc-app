package laba_3.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EchoClientHandler
{
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;


	public EchoClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	public void start() throws IOException, InterruptedException
	{
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String greeting = in.readLine();
		if ("hello server".equals(greeting)) {
			out.println("hello client");
		}
		else if ("stop server".equals(greeting)) {
			out.println("server stopped.");
			throw new RuntimeException();
		}
		else if ("hello again !".equals(greeting)) {
			out.println("hello client again!");
		}
		else {
			out.println("unrecognised greeting");
		}
	}

	public void stop() throws IOException
	{
		in.close();
		out.close();
		clientSocket.close();
	}
}
