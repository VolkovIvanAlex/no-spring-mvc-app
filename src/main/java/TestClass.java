import java.nio.ByteBuffer;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestClass
{
	static byte[] encode(Command command)
	{
		System.out.println(command.getCommandBody().length);
		byte[] msg = ByteBuffer.allocate(4 + 4 + command.getCommandBody().length)
				.putInt(command.getCommandType())
				.putInt(command.getbUserId())
				.put(command.getCommandBody())
				.array();

		byte[] header = ByteBuffer.allocate(1 + 1 + 8 + 4)
				.put((byte) 0x13)
				.put((byte) 15)
				.putLong(123L)
				.putInt(msg.length)
				.array();

		return ByteBuffer.allocate(header.length + 2 + msg.length + 2)
				.put(header)
				.putShort(CRC16.crc16(header))
				.put(msg)
				.putShort(CRC16.crc16(msg))
				.array();
}

	static Command decode(Packet packet)
	{
		Command command = new Command();
		command.setCommandType(packet.getbMsg().getcType());
		command.setbUserId(packet.getbMsg().getbUserId());
		command.setCommandBody(packet.getbMsg().getMessage());
		return command;
	}

	public static void main(String[] args)
	{
		String message = "Hello World !";
		byte[] encryptedMessageBytes = message.getBytes();

		Command command = new Command(1, 1, encryptedMessageBytes);
		Packet packet = new Packet(encode(command));
		System.out.println("encoded packet : " + packet);

		String msg = new String(Base64.getEncoder().encode(packet.getbMsg().getMessage()));
		System.out.println("encoded message : " + msg);

		Command decodedCommand = decode(packet);
		String str2 = new String(decodedCommand.getCommandBody());
		System.out.println("decoded message : " + str2);

		System.out.println(command);
		System.out.println(decodedCommand);
		System.out.println(command.toString().equals(decodedCommand.toString()));
	}
}
