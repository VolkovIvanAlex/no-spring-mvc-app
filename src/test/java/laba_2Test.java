import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import laba_2.TestSender;


class laba_2Test
{

	@Test
	void main() throws InterruptedException
	{
		for (int i=0; i<5;i++){
			TestSender sender = new TestSender();
			sender.start();
		}
	}
}
