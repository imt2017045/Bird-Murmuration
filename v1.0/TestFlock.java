import java.util.ArrayList;

//sample main (which will be replaced by an interactive front-end. 
// You can use the following to test your code. 
// We will assume we have a 1000x1000 2D space in which the birds fly

public class TestFlock {
	public static void main(String[] args) {
		Flock f = new FlockX(); // where FlockX is a concrete derived class of Flock

		// add a bunch of birds

		// repeat the above for the different derived classes of bird
		Bird b = new BirdX(); // where BirdX is a derived concrete class of Bird
		b.setPos(0,10);
		f.addBird(b);
		f.setLeader(b);
		b.setTarget(800,600);
	

		// repeat the above for the different derived classes of bird
		Bird b2 = new BirdX(); // where BirdX is a derived concrete class of Bird
		b2.setPos(0,20);
		f.addBird(b2);
		
		Bird b3 = new BirdX(); // where BirdX is a derived concrete class of Bird
		b3.setPos(0,20);
		f.addBird(b3);
		
		
		ArrayList<Bird> birds = f.getBirds();

		for(Bird bird: birds) {
			Thread t = new Thread() {

				public void run() {
					bird.flap();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					}
				};
				t.start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		

		f.setLeader(b2);
		b2.setTarget(400, 800);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		Flock f2 = f.split(2);
		Bird f2lead = f2.getLeader();
		f2lead.setTarget(10, 20);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		f2.joinFlock(f);
		f.getLeader().setTarget(100, 900);	
	}
}
