package flockbase;

import flockbase.Bird;
import java.util.ArrayList;

public class Flock2017019 extends flockbase.Flock
{
  private ArrayList<Bird> birds = new ArrayList<Bird>();

  Bird leader = null;

  public Flock2017019() {
    super();
    birds=new ArrayList<Bird>();

  }

  public void addBird(Bird b) {
    birds.add(b);
    b.setFlock(this);
  }

  public void setLeader(Bird next_leader)
  {
    if (leader != null) {
      leader.retireLead();
    }
    leader = next_leader;
    leader.becomeLeader();
  }

  public ArrayList<Bird> getBirds()
  {
    return birds;
  }


  public Bird getLeader()
  {
    return leader;
  }


  public flockbase.Flock split(int pos)
  {
    System.out.println(birds.size());
    Flock2017019 temp=new Flock2017019();
    for(int i=pos;i<birds.size();i++){
      System.out.print(i+" ");
      if(birds.get(i)!=getLeader()){
      temp.addBird(birds.get(i));

      }
    }
    for(int i=pos;i<birds.size();i++){
      birds.remove(pos);
    }
    temp.setLeader(temp.birds.get(0));
    temp.birds.get(0).setTarget(500,500);
    return temp;

  }

  public void joinFlock(flockbase.Flock f) {

    int co=0;
    while(getBirds().size()>0){
      if(getLeader()==getBirds().get(0)){
        getBirds().get(0).retireLead();
        co++;
      }
      f.addBird(getBirds().get(0));
      birds.remove(0);
    }

  }
}
