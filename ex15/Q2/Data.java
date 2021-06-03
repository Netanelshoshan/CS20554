
public class Data {
    private int x = 0;
    private int y = 0;
    private boolean mutex;

    public Data(int x, int y) {
        this.x = x;
        this.y = y;
        mutex = true;
    }

    /*
     This function returns delta x,y
     */
    public synchronized int getDiff() {
        // wait until canUpdate is false
        while (mutex) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Delta x,y is: " + Math.abs(x - y) + "\n");

        mutex = true;
        notifyAll(); // wake other threads
        return (Math.abs(x - y));
    }

    /*
    This function updates x and y to  x = x+dx , y =y+dy
     */
    public synchronized void update(int dx, int dy) {
        while (!mutex) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println(Thread.currentThread().getName());
            }
        }
        System.out.println("dx=" + dx + ", dy=" + dy + ". x=" + x + ", y =" + y + ". After changes: x=" + (x + dx) + ", y =" + (y + dy)+"\n");
        x = x + dx;
        y = y + dy;
        mutex = false;
        notifyAll(); // wake other threads
    }

}
