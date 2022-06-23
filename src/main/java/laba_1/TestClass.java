package laba_1;

import java.nio.ByteBuffer;
import java.util.Base64;


public class TestClass
{
	public static void main(String[] args)
	{
		String message = "Hello World !";
		byte[] encryptedMessageBytes = message.getBytes();

		Command command = new Command(1, 1, encryptedMessageBytes);
		Packet packet = new Packet(Encoder.encode(command));
		System.out.println("encoded packet : " + packet);

		String msg = new String(Base64.getEncoder().encode(packet.getbMsg().getMessage()));
		System.out.println("encoded message : " + msg);

		Command decodedCommand = Decoder.decode(packet);
		String str2 = new String(decodedCommand.getCommandBody());
		System.out.println("decoded message : " + str2);

		System.out.println(command);
		System.out.println(decodedCommand);
		System.out.println(command.toString().equals(decodedCommand.toString()));
	}
}
