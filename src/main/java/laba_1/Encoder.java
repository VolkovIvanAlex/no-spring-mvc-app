package laba_1;

import java.nio.ByteBuffer;


public class Encoder
{
	public static byte[] encode(Command command)
	{
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
}
