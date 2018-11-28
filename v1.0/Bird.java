abstract public class Bird {
	Bird() {
		amLeader = false;
	}

	// Set the position of the bird
	public void setPos(int x, int y) {
		pos.setPos(x, y);
	}

	// invoked during the "run" process by the thread
	public void flap() {
		updatePos();
	}

	// this bird is now nominated as the leader
	abstract void becomeLeader();

	// this bird is asked to move out of the leader position
	abstract void retireLead();

	// point the bird to a target it should fly towards
	// relevant only if leader
	// The leader also gets to decide the speed at which the flock will fly -
	// how far it will fly with one "flap"
	public void setTarget(int xt, int yt) {
		if (amLeader)
			target.setPos(xt, yt);
	}

	// to be implemented by derived classes to make the bird move for one
	// timestep
	abstract protected void updatePos();

	private Position pos;
	boolean amLeader;
	private Position target;
}

// helper class
class Position {
	Position(int px, int py) {
		x = px;
		y = py;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	void setPos(int px, int py) {
		x = px;
		y = py;
	}

	int x, y;
}
