import java.nio.ByteBuffer;


public class Message
{
	private int cType;
	private int bUserId;
	private byte[] message;

	public Message(ByteBuffer byteBuffer, int wLen)
	{
		this.cType = byteBuffer.getInt();
		this.bUserId = byteBuffer.getInt();
		this.message = new byte [wLen - Integer.BYTES * 2];
		byteBuffer.get(message , 0, wLen - Integer.BYTES * 2 );
	}

	public Message()
	{
	}

	public void setcType(final int cType)
	{
		this.cType = cType;
	}

	public void setbUserId(final int bUserId)
	{
		this.bUserId = bUserId;
	}

	public void setMessage(final byte[] message)
	{
		this.message = message;
	}

	public int getcType()
	{
		return cType;
	}

	public int getbUserId()
	{
		return bUserId;
	}

	public byte[] getMessage()
	{
		return message;
	}
}
