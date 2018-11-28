import java.util.*;
import flockbase.*;

public class myFlock extends Flock {
  // private int total_birds;
  private ArrayList<Bird> birds;
  private Bird leader;

  myFlock(){
    super();
    total_birds = 0;
    birds = new ArrayList<Bird>();
  }

  // public int getTotal_birds(){
  //   return total_birds;
  // }
  public void setLeader(Bird leader){
    this.leader = leader;
  }
  public Bird getLeader(){
    return leader;
  }
  public void addBird(Bird b){
    total_birds += 1;
    birds.add(b);
    b.setFlock(this);
  }
  public ArrayList<Bird> getBirds(){
    return birds;
  }

}
