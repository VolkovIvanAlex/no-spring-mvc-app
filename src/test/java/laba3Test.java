import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import laba_1.Command;
import laba_1.Encoder;
import laba_1.Packet;
import laba_3.tcp.StoreClientTCP;
import laba_3.tcp.StoreServerTCP;


public class laba3Test
{
	static Thread server;

	@BeforeClass
	public static void init(){
		server = new Thread(new StoreServerTCP());
		server.start();
	}

	@Test
	public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException
	{
		String message = "hello server";
		byte[] encryptedMessageBytes = message.getBytes();
		Command command = new Command(1, 1, encryptedMessageBytes);
		Packet packet = new Packet(Encoder.encode(command));

		StoreClientTCP client = new StoreClientTCP();
		client.startConnection("127.0.0.1", 6666);
		client.sendMessage(packet);
		String response = client.readMessage();
		assertEquals("hello client", response);

		String message2 = "stop server";
		byte[] encryptedMessageBytes2 = message2.getBytes();
		Command command2 = new Command(1, 1, encryptedMessageBytes2);
		Packet packet2 = new Packet(Encoder.encode(command2));
		StoreClientTCP client2 = new StoreClientTCP();
		client2.startConnection("127.0.0.1", 6666);
		client2.sendMessage(packet2);
		String response2 = client2.readMessage();
		assertEquals(response2 , "server stopped.");
	}

	@AfterClass
	public static void stop() throws InterruptedException
	{
		server.join();
	}
}
