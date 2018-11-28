import flockbase.Bird;
import flockbase.Flock;
import sample.*;

//sample main (which will be replaced by an interactive front-end.
// You can use the following to test your code.
// We will assume we have a 1000x1000 2D space in which the birds fly

public class TestFlock {
	public static void main(String[] args) {
		Flock f = new FlockY(); // where FlockX is a concrete derived class of Flock
		String[] colors = {"Blue", "Red", "Green", "Yellow", "Magenta", "Black"};
		// add a bunch of birds

		// repeat the above for the different derived classes of bird
		Bird b1 = new my2Bird("Blue"); // where BirdX is a derived concrete class of Bird
		b1.setPos(10,10);
		f.addBird(b1);

		for(int i=0;i<5;i++){
			Bird b = new my2Bird(colors[i+1]);
			b.setPos(i*100,  10);
			f.addBird(b);
		}
		// Bird b = new myBird();
		// b.setPos(400, 10);
		// f.addBird(b);


		FlockDisplay disp = new SwingDisplay();
		//FlockDisplay disp = new TextDisplay();

		App app = new App(disp);
		disp.setApp(app);
		app.init(f);

		app.setLeader(b1);
		app.setTarget(800,300);

		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		app.setLeader(b2);
		app.setTarget(400, 200);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		/*
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

		*/
	}
}
