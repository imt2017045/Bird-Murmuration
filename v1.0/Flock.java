import java.util.ArrayList;

abstract public class Flock {
	Flock() {

	}

	// add a Bird to this Flock
	abstract public void addBird(Bird b);

	// identify the Bird that should become leader of the flock
	abstract public void setLeader(Bird leader);

	// get current list of birds in the flock
	abstract public ArrayList<Bird> getBirds();

	abstract public Bird getLeader();
	// split with the bird at pos as the leader of new flock
	// Returns the new flock formed
	abstract public Flock split(int pos); 

	// merges current flock with flock f - should join at end of f
	abstract public void joinFlock(Flock f); 

}
