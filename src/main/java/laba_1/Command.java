package laba_1;

import java.util.Arrays;


public class Command
{
	private int commandType;
	private int  bUserId;
	private byte [] commandBody;

	public Command(final int commandType, final int bUserId, final byte[] commandBody)
	{
		this.commandType = commandType;
		this.bUserId = bUserId;
		this.commandBody = commandBody;
	}

	public Command()
	{
	}

	public int getCommandType()
	{
		return commandType;
	}

	public void setCommandType(final int commandType)
	{
		this.commandType = commandType;
	}

	public byte[] getCommandBody()
	{
		return commandBody;
	}

	public void setCommandBody(final byte[] commandBody)
	{
		this.commandBody = commandBody;
	}

	public int getbUserId()
	{
		return bUserId;
	}

	public void setbUserId(final int bUserId)
	{
		this.bUserId = bUserId;
	}

	@Override
	public String toString()
	{
		return "laba_1.Command{" +
				"commandType=" + commandType +
				", bUserId=" + bUserId +
				", commandBody=" + Arrays.toString(commandBody) +
				'}';
	}
}
