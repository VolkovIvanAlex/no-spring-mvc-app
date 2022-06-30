package laba_3;

import java.io.IOException;

import laba_1.Command;
import laba_1.Encoder;
import laba_1.Packet;
import laba_3.tcp.StoreClientTCP;
import laba_3.tcp.StoreServerTCP;
import laba_3.udp.StoreClientUDP;
import laba_3.udp.StoreServerUDP;


public class Main
{

	public static void main(String[] args) throws IOException
	{
		System.out.println("TCP ---------------------------------");

		Thread server=new Thread(new StoreServerTCP());
		server.start();

		String message = "hello server";
		byte[] encryptedMessageBytes = message.getBytes();
		Command command = new Command(1, 1, encryptedMessageBytes);
		Packet packet = new Packet(Encoder.encode(command));

		StoreClientTCP clientTCP = new StoreClientTCP();
		clientTCP.startConnection("127.0.0.1", 6666);
		clientTCP.sendMessage(packet);
		String response = clientTCP.readMessage();
		System.out.println(response);

		String message2 = "stop server";
		byte[] encryptedMessageBytes2 = message2.getBytes();
		Command command2 = new Command(1, 1, encryptedMessageBytes2);
		Packet packet2 = new Packet(Encoder.encode(command2));

		StoreClientTCP clientTCP2 = new StoreClientTCP();
		clientTCP2.startConnection("127.0.0.1", 6666);
		clientTCP2.sendMessage(packet2);
		String response2 = clientTCP2.readMessage();
		System.out.println(response2);

		String message3 = "stop server";
		byte[] encryptedMessageBytes3 = message3.getBytes();
		Command command3 = new Command(1, 1, encryptedMessageBytes3);
		Packet packet3 = new Packet(Encoder.encode(command3));

		StoreClientTCP clientTCP3 = new StoreClientTCP();
		clientTCP3.startConnection("127.0.0.1", 6666);
		clientTCP3.sendMessage(packet3);
		String response3 = clientTCP3.readMessage();
		System.out.println(response3);

		System.out.println("UDP ---------------------------------");
		new StoreServerUDP().start();
		String message4 = "Hello server !";
		byte[] encryptedMessageBytes4 = message4.getBytes();

		Command command4 = new Command(1, 1, encryptedMessageBytes4);
		Packet packet4 = new Packet(Encoder.encode(command4));

		StoreClientUDP clientUDP = new StoreClientUDP();
		String echo = clientUDP.sendEcho(packet4);

		String message5 = "Server is working !";
		byte[] encryptedMessageBytes5 = message5.getBytes();

		Command command5 = new Command(1, 1, encryptedMessageBytes5);
		Packet packet5 = new Packet(Encoder.encode(command5));
		clientUDP.sendEcho(packet5);

		StoreClientUDP clientUDP2 = new StoreClientUDP();
		String message6 = "end";
		byte[] encryptedMessageBytes6 = message6.getBytes();

		Command command6 = new Command(1, 1, encryptedMessageBytes6);
		Packet packet6 = new Packet(Encoder.encode(command6));
		clientUDP2.sendEcho(packet6);
		clientUDP2.close();

		String message7 = "This message will not be sent.";
		byte[] encryptedMessageBytes7 = message4.getBytes();

		Command command7 = new Command(1, 1, encryptedMessageBytes7);
		Packet packet7 = new Packet(Encoder.encode(command7));
		clientUDP.sendEcho(packet7);
		clientUDP.close();
	}
}
