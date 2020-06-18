package Client;

public class Stoper implements Runnable{
	Thread kicker = null;
	public int godziny=0;
	public int minuty=0;
	public int sekundy=0;
	
	/*
	 * Klasa pozwalaj¹ca odliczaæ czas w formie stopera
	 */
	public Stoper()
	{
		(this.kicker= new Thread(this)).start();
	}
	
	public void run()
	{
		while (kicker == Thread.currentThread()) {
			//System.out.print(godziny+":"+minuty+":"+sekundy);
			try {
	            Thread.sleep(1000);
	        } catch (InterruptedException ie) {
	        	//System.out.println("przrwanie sleep");
	        }
			sekundy+=1;
			if(sekundy==60)
			{
				sekundy=0;
				minuty+=1;
			}
			if(minuty==60)
			{
				minuty=0;
				godziny=+1;
			}
		}
	}
}
