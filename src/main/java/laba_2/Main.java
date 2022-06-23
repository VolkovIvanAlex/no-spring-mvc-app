package laba_2;

import laba_2.receiver.impl.FakeReceiver;


public class Main
{
	public static void main(String[] args)
	{
		FakeReceiver fakeReceiver = new FakeReceiver();
		fakeReceiver.receiveMessage();
	}
}
