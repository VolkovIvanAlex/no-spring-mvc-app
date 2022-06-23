package laba_2;

import laba_1.Message;


public class Processor extends Thread
{
	private static int sum = 0;
	private static int commandType = 0;
	private final Message message;

	public Processor(final int commandType, Message message)
	{
		this.commandType = commandType;
		this.message = message;
		start();
	}

	@Override
	public void run()
	{
		synchronized (message)
		{
			try
			{
				process(message);
			}
			catch (InterruptedException ignored)
			{
			}
		}
	}

	private void process(Message message) throws InterruptedException
	{
		System.out.println("OK");
		if (commandType == 3)sum++;
		if (commandType ==2)sum--;
		System.out.println(sum);
		Encryptor encryptor = new Encryptor(message);
	}
}
