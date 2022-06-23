package laba_2;

import laba_2.receiver.impl.FakeReceiver;


public class Sender
{
	private byte[] message;

	public Sender(final byte[] message)
	{
		this.message = message;
		showMsg(message);
	}

	public Sender()
	{
	}

	private void showMsg(byte[] message)
	{
		String msg = new String(message);
		System.out.println(msg);
	}
}
