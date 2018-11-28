// package sample;

import java.util.*;
import flockbase.*;

public class Flock045 extends Flock {
  private ArrayList<Bird> birds;
  private Bird leader;

  Flock045(){
    super();
    birds = new ArrayList<Bird>();
    leader = null;
  }
  Flock045(ArrayList<Bird> bl){
    super();
    birds = bl;
    for(Bird b: bl){
      b.setFlock(this);
    }
    leader = null;
  }

  public void setLeader(Bird leader){
    this.leader = leader;
    if(leader != null){
      leader.retireLead();
    }
    leader.becomeLeader();
  }
  public Bird getLeader(){
    return leader;
  }
  public void addBird(Bird b){
    birds.add(b);
    b.setFlock(this);
  }
  public ArrayList<Bird> getBirds(){
    return birds;
  }

  public Flock split(int pos){
    ArrayList<Bird> stay = new ArrayList<Bird>();
    ArrayList<Bird> go = new ArrayList<Bird>();

    for(int i=0; i<birds.size(); i++){
      if(i<pos){
        stay.add(birds.get(i));
      }
      else{
        go.add(birds.get(i));
      }
    }

    System.out.println(stay.size() + " " + go.size());
    Flock ret = new Flock045(go);
    ret.setLeader(birds.get(pos));

    birds = stay;
    return ret;
  }

  public void joinFlock(Flock f){
    f.getLeader().retireLead();
    for(Bird b: f.getBirds()){
      addBird(b);
    }
  }
}
