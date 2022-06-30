import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import laba_1.Command;
import laba_1.Encoder;
import laba_1.Packet;
import laba_3.udp.StoreClientUDP;
import laba_3.udp.StoreServerUDP;


public class UDPTest {

	@BeforeClass
	public static void setup() throws SocketException, UnknownHostException
	{
		new StoreServerUDP().start();
	}

	@Test
	public void whenCanSendAndReceivePacket_thenCorrect() throws IOException
	{
		StoreClientUDP clientUDP = new StoreClientUDP();
		String message = "Hello server !";
		byte[] encryptedMessageBytes = message.getBytes();
		Command command = new Command(1, 1, encryptedMessageBytes);
		Packet packet = new Packet(Encoder.encode(command));
		String echo = clientUDP.sendEcho(packet);
		assertEquals("Hello server !", echo);

		StoreClientUDP clientUDP2 = new StoreClientUDP();
		String message2 = "working ...";
		byte[] encryptedMessageBytes2 = message2.getBytes();
		Command command2 = new Command(2, 1, encryptedMessageBytes2);
		Packet packet2 = new Packet(Encoder.encode(command2));
		echo = clientUDP2.sendEcho(packet2);
		assertFalse(echo.equals("hello server"));
	}

	@AfterClass
	public static void tearDown() throws IOException
	{
		StoreClientUDP clientUDP = new StoreClientUDP();

		String message = "end";
		byte[] encryptedMessageBytes = message.getBytes();

		Command command = new Command(1, 1, encryptedMessageBytes);
		Packet packet = new Packet(Encoder.encode(command));
		clientUDP.sendEcho(packet);
		clientUDP.close();
	}
}
