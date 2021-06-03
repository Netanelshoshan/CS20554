import java.util.Random;

public class SecondProcess extends Thread {
	private Data DataReference;

	public SecondProcess(Data obj) {
		DataReference = obj;	
	}
	
	public void run() {
		for (int i=0; i < 10 ; i++) {
			DataReference.getDiff();
			try {
				Thread.sleep(new Random().nextInt(100));
			} 
			catch (InterruptedException e)	{
				e.printStackTrace();
				System.err.println(Thread.currentThread().getName());
			}
		}
	}
}
