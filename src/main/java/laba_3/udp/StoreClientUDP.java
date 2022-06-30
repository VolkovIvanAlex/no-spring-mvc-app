package laba_3.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import laba_1.Packet;


public class StoreClientUDP
{
	private DatagramSocket socket;
	private InetAddress address;

	private byte[] buf;

	public StoreClientUDP() throws SocketException, UnknownHostException
	{
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
	}

	public String sendEcho(Packet inputPacket) throws IOException
	{
		buf = inputPacket.getbMsg().getMessage();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);
		packet = new DatagramPacket(buf, buf.length);

		socket.receive(packet);

		//If received data invalid - repeat
		while (buf != packet.getData())
		{
			socket.send(packet);
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
		}
		String received = new String(
				packet.getData(), 0, packet.getLength());
		return received;
	}

	public void close()
	{
		socket.close();
	}
}
