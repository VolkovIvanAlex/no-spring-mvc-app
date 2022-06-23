package laba_1;

import java.nio.ByteBuffer;


public class Packet
{
	private byte bMagic = 0x13;
	private byte bSrc;
	private long bPktId;
	private int wLen;
	private short wCrc16;
	private Message bMsg;
	private short wCrc16_end;

	public Packet(byte[] bytes)
	{
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		if (byteBuffer.get() != bMagic)
		{
			throw new RuntimeException();
		}
		this.bSrc = byteBuffer.get();
		this.bPktId = byteBuffer.getLong();
		this.wLen = byteBuffer.getInt();
		this.wCrc16 = byteBuffer.getShort();
		this.bMsg = new Message(byteBuffer, this.wLen);
		this.wCrc16_end = byteBuffer.getShort();
	}

	public Packet()
	{
	}

	public byte getbMagic()
	{
		return bMagic;
	}

	public byte getbSrc()
	{
		return bSrc;
	}

	public long getbPktId()
	{
		return bPktId;
	}

	public int getwLen()
	{
		return wLen;
	}

	public short getwCrc16()
	{
		return wCrc16;
	}

	public Message getbMsg()
	{
		return bMsg;
	}

	public short getwCrc16_end()
	{
		return wCrc16_end;
	}

	public void setbMsg(final Message message)
	{
		this.bMsg = message;
	}

	@Override
	public String toString()
	{
		return "laba_1.Packet{" +
				"bMagic=" + bMagic +
				", bSrc=" + bSrc +
				", bPktId=" + bPktId +
				", wLen=" + wLen +
				", wCrc16=" + wCrc16 +
				", bMsg=" + bMsg +
				", wCrc16_end=" + wCrc16_end +
				'}';
	}
}
