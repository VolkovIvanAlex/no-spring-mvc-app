package laba_2;

import laba_1.Command;
import laba_1.Encoder;
import laba_1.Packet;
import laba_2.receiver.impl.FakeReceiver;


public class TestSender extends Thread
{
	byte [] addProducts = "add Grechka".getBytes();
	Command command3 = new Command(3, 1 , addProducts);
	Packet packet = new Packet(Encoder.encode(command3));

	@Override
	public void run()
	{
		FakeReceiver receiver = new FakeReceiver();
		receiver.receiveTest(packet);
	}
}
