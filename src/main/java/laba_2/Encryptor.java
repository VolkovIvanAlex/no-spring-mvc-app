package laba_2;

import java.nio.ByteBuffer;

import laba_1.Message;
import laba_1.Packet;


public class Encryptor extends Thread
{
	private final Message message;
	public Encryptor(Message message)
	{
		this.message = message;
		start();
	}

	@Override
	public void run()
	{
		System.out.println("In encryptor : " + super.getName());
		Sender sender = new Sender(encrypt(message));
	}

	private byte[] encrypt(Message message){
		byte[] msg = ByteBuffer.allocate(4 + 4 + message.getMessage().length)
				.putInt(message.getcType())
				.putInt(message.getbUserId())
				.put(message.getMessage())
				.array();
		return msg;
	}
}
