package Client;

public class Timer implements Runnable{
	Thread kicker = null;
	public int flag=0;
	public int duration;
	public boolean startuj=false;
	public Timer(int d)
	{
		duration = d;
		(this.kicker= new Thread(this)).start();
	}
	
	public void wlacz() {
		flag=1;
	}
	public void wylacz()
	{
		flag=0;
	}
	public void run()
	{
		while (kicker == Thread.currentThread()) {
			if(startuj)
			{
				wlacz();
				startuj=false;
				try {
		            Thread.sleep(duration);
		        } catch (InterruptedException ie) {
		        	//System.out.println("przrwanie sleep");
		        }
				
			}
			wylacz();
		}
	}
}
