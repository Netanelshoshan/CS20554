import java.util.Random;

public class FirstProcess extends Thread {
	private int [] xArr = new int [10];
	private int [] yArr = new int [10];
	private Data DataReference;

	
	public FirstProcess(Data obj) {
		for (int i=0; i < xArr.length ; i++) {
			xArr[i] = (int)(100*Math.random()) ;
			yArr[i] = (int)(100*Math.random()) ;
		}	
		DataReference = obj;	

	}
	
	public void run() {
		for (int i=0; i < 10 ; i++) {
			DataReference.update(xArr[i],yArr[i]);
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
