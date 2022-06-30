package laba_3.tcp;

import java.io.IOException;
import java.net.ServerSocket;


public class StoreServerTCP implements Runnable
{
	public static boolean running = true;
	private ServerSocket serverSocket;

	public void start(int port) throws IOException, InterruptedException
	{
		serverSocket = new ServerSocket(port);
		startHandler();
	}

	private void startHandler(){
		running = true;
		while (running)
		{
			try
			{
				new EchoClientHandler(serverSocket.accept()).start();
			}
			catch (RuntimeException | IOException | InterruptedException e)
			{
				running = false;
			}
		}
		startHandler();
	}

	@Override
	public void run()
	{
		StoreServerTCP serverTCP = new StoreServerTCP();
		try
		{
			serverTCP.start(6666);
		}
		catch (IOException | InterruptedException ignored)
		{
		}
	}

	public boolean isRunning()
	{
		return running;
	}
}
