import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import laba_1.Command;


class TestClassTest
{

	@Test
	void main()
	{
		String message = "Hello World !";
		byte[] encryptedMessageBytes = message.getBytes();
		Command command = new Command(1, 1, encryptedMessageBytes);

		String decodedMsg = new String(encryptedMessageBytes);
		Assertions.assertEquals(message, decodedMsg);
	}
}