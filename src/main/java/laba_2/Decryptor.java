package laba_2;

import laba_1.Message;
import laba_1.Packet;


public class Decryptor extends Thread
{
	private final Packet packet;
	public Decryptor(Packet packet)
	{
		this.packet = packet;
		start();
	}

	@Override
	public void run()
	{
		System.out.println("In decryptor : " + super.getName());
		decrypt(packet.getbMsg().getMessage());
	}

	private void decrypt(byte [] message){
		Message result = new Message(packet.getbMsg().getcType() , packet.getbMsg().getbUserId() , message);
		Processor processor = new Processor(packet.getbMsg().getcType(), result);
	}
}
