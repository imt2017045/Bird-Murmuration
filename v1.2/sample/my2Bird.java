import java.util.*;
import java.lang.Math;
import flockbase.*;

public class my2Bird extends Bird {
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

  my2Bird(){
    super();
    amLeader = false;
    prevPos = new ArrayList<Position>();
    call=0;
  }
  my2Bird(String n){
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

        // Bird leader = getFlock().getLeader();
        // double d = distance(leader.getPos());
        // Position p1 = getPos();
        // Position p2 = leader.getPos();
        // int x = (int)((double)p1.getX() + (double)(((double)radius)*((double)(p2.getX()-p1.getX())/d)));
        // int y = (int)((double)p1.getY() + (double)(((double)radius)*((double)(p2.getY()-p1.getY())/d)));
        // p1.setPos(x, y);
      }
      else{
        Bird nearest = getFlock().getLeader();
        double dist = distance(nearest.getPos());
        // for(Bird b: getFlock().getBirds()){
        //   if(b != this){
        //     double d = distance(b.getPos());
        //     if(d > radius || b == getFlock().getLeader()){
        //       if(d < dist){
        //         dist = d;
        //         nearest = b;
        //       }
        //     }
        //   }
        // }

        // Position leaderNextPos = null;
        ArrayList<Position> nextPos = new ArrayList<Position>();
        int i = 0;
        for(Bird b: getFlock().getBirds()){
          if(b != this){
            Position curr_pos = b.getPos();
            Position prev_pos = prevPos.get(i);
            int x = curr_pos.getX() + (curr_pos.getX() - prev_pos.getX());
            int y = curr_pos.getY() + (curr_pos.getY() - prev_pos.getY());
            nextPos.add(new Position(x, y));
            // if(b == getFlock().getLeader()){
            //   leaderNextPos = nextPos.get(nextPos.size()-1);
            // }
          }
          i += 1;
        }

        Position p1 = getPos();
        Position p2 = nearest.getPos();
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
        // else{
        //   // x = p1.getX();
        //   // y = p1.getY();
        //   // p2 = getFlock().getLeader().getPos();
        //   // x = x + (leaderNextPos.getX() - p2.getX());
        //   // y = y + (leaderNextPos.getY() - p2.getY());
        //   x = p2.getX();
        //   y = p2.getY();
        //   while(x != p1.getX() || y != p1.getY()){
        //     boolean collision = false;
        //     for(Position p: nextPos){
        //       if(x == p.getX() && y == p.getY()){
        //         collision = true;
        //         break;
        //       }
        //     }
        //     if(!collision){
        //       break;
        //     }
        //     else{
        //       x = (int)((double)x - (double)(p2.getX()-p1.getX())/dist);
        //       y = (int)((double)y - (double)(p2.getY()-p1.getY())/dist);
        //     }
        //   }
        // }
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
