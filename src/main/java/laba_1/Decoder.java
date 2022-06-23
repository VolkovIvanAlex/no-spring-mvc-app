package laba_1;

public class Decoder
{
	public static Command decode(Packet packet)
	{
		Command command = new Command();
		command.setCommandType(packet.getbMsg().getcType());
		command.setbUserId(packet.getbMsg().getbUserId());
		command.setCommandBody(packet.getbMsg().getMessage());
		return command;
	}
}
