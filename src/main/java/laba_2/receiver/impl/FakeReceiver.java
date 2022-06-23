package laba_2.receiver.impl;

import java.nio.charset.StandardCharsets;

import laba_1.Encoder;
import laba_1.Packet;
import laba_2.Decryptor;
import laba_2.receiver.Receiver;
import laba_1.Command;


public class FakeReceiver implements Receiver
{
	@Override
	public void receiveMessage()
	{
		byte [] askQuantity = "How much Bread".getBytes();
		Command command = new Command(1, 1 , askQuantity);
		Packet packet = new Packet(Encoder.encode(command));

		byte [] deleteProducts = "delete Potato".getBytes();
		Command command2 = new Command(2, 1 , deleteProducts);
		Packet packet2 = new Packet(Encoder.encode(command2));

		byte [] addProducts = "add Grechka".getBytes();
		Command command3 = new Command(3, 1 , addProducts);
		Packet packet3 = new Packet(Encoder.encode(command3));

		byte [] addCategory = "add Vegetables category".getBytes();
		Command command4 = new Command(4, 1 , addCategory);
		Packet packet4 = new Packet(Encoder.encode(command4));

		byte [] addCategoryName = "add Fruits category name".getBytes();
		Command command5 = new Command(5, 1 , addCategoryName);
		Packet packet5 = new Packet(Encoder.encode(command5));

		byte [] setPriceOnProduct = "set Fish|100$".getBytes();
		Command command6 = new Command(6, 1 , setPriceOnProduct);
		Packet packet6 = new Packet(Encoder.encode(command6));

		Decryptor decryptor = new Decryptor(packet);
		Decryptor decryptor2 = new Decryptor(packet2);
		Decryptor decryptor3 = new Decryptor(packet3);
		Decryptor decryptor4 = new Decryptor(packet4);
		Decryptor decryptor5 = new Decryptor(packet5);
		Decryptor decryptor6 = new Decryptor(packet6);
	}

	public void receiveTest(Packet packet)
	{
		Decryptor decryptor = new Decryptor(packet);
	}
}
