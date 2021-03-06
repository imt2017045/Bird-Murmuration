// package sample;

import java.util.*;
import java.lang.Math;
import flockbase.*;

public class Bird045 extends Bird {
  private String name;
  private boolean amLeader; // true if bird is a leader
  private ArrayList<Position> prevPos; // this is used to calculate velocity
  private static int time = 1;
  private static int radius = getMaxSpeed();

  private int call;

  private double distance(Position p2){
    Position p1 = getPos();

    double ans = Math.sqrt(((p1.getX() - p2.getX())*(p1.getX() - p2.getX())) + ((p1.getY() - p2.getY())*(p1.getY() - p2.getY())));
    return ans;
  }

  Bird045(){
    super();
    name = "Bird045";
    amLeader = false;
    prevPos = new ArrayList<Position>();
    call=0;
  }
  Bird045(String n){
    super();
    name = n;
    amLeader = false;
    prevPos = new ArrayList<Position>();
    call=0;
  }

  public void flap(){
    call += 1;
    updatePos();
    System.out.println(call + ": " + name + '-' + getPos());
  }

  public void becomeLeader(){
    amLeader = true;
  }

  public void retireLead(){
    amLeader = false;
  }

  protected void updatePos(){
    if(!amLeader){
      if(prevPos.isEmpty()){
        for(Bird b: getFlock().getBirds()){
          prevPos.add(b.getPos());
        }
      }
      else{
        Bird leader = getFlock().getLeader();
        double dist = distance(leader.getPos());

        ArrayList<Position> nextPos = new ArrayList<Position>();
        int i = 0;
        for(Bird b: getFlock().getBirds()){
          if(b != this){
            Position curr_pos = b.getPos();
            Position prev_pos = prevPos.get(i);
            int x = curr_pos.getX() + (curr_pos.getX() - prev_pos.getX());
            int y = curr_pos.getY() + (curr_pos.getY() - prev_pos.getY());
            nextPos.add(new Position(x, y));
          }
          i += 1;
        }

        Position p1 = getPos();
        Position p2 = leader.getPos();
        int x=p1.getX(), y=p1.getY();
        if(dist > radius){
          x = (int)((double)p1.getX() + (double)(((double)radius)*((double)(p2.getX()-p1.getX())/dist)));
          y = (int)((double)p1.getY() + (double)(((double)radius)*((double)(p2.getY()-p1.getY())/dist)));

          while(x != p1.getX() || y != p1.getY()){
            boolean collision = false;
            for(Position p: nextPos){
              if(x == p.getX() && y == p.getY()){
                collision = true;
                break;
              }
            }
            if(!collision){
              break;
            }
            else{
              x = (int)((double)x - (double)2*(p2.getX()-p1.getX())/dist);
              y = (int)((double)y - (double)2*(p2.getY()-p1.getY())/dist);
            }
          }
        }
        p1.setPos(x, y);

        i = 0;
        for(Bird b: getFlock().getBirds()){
          prevPos.set(i, b.getPos());
        }
      }
    }

    else{
      Position p1 = getPos();
      Position p2 = getTarget();
      double dist = distance(p2);
      int x=0, y=0;
      if(dist > radius){
        x = (int)((double)p1.getX() + (double)(((double)radius)*((double)(p2.getX()-p1.getX())/dist)));
        y = (int)((double)p1.getY() + (double)(((double)radius)*((double)(p2.getY()-p1.getY())/dist)));
      }
      else{
        x = p2.getX();
        y = p2.getY();
      }
      p1.setPos(x, y);
    }
  }
}
