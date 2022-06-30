package laba_3.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import laba_1.Packet;


public class StoreClientTCP
{
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection(String ip, int port) throws IOException
	{
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public void sendMessage(Packet packet) throws IOException
	{
		String msg = new String(packet.getbMsg().getMessage());
		if (!StoreServerTCP.running)
		{
			out.println("waiting...");
		}
		else out.println(msg);
	}

	public String readMessage() throws IOException
	{
		return in.readLine();
	}

	public void stopConnection() throws IOException
	{
		in.close();
		out.close();
		clientSocket.close();
	}
}
