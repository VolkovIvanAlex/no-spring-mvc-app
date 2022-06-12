import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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