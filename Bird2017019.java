//package flockbase;
import flockbase.*;
import java.io.PrintStream;
import java.util.*;
import java.lang.Math;
public class Bird2017019 extends flockbase.Bird
{
  private int speed = 10;
  private boolean currLeader;
  private String name;

  public void becomeLeader()
  {
    this.currLeader = true;
  }

  public void retireLead()
  {
    this.currLeader = false;
  }
  public Bird2017019() {//constructor for my bird class
    super();
    setName();
  }
  void setName(){
    this.name="IMT2017019";
  }
  public String getName()
  {
    return name;
  }

  protected void updatePos()
  {
    Position myPos = getPos();
    int x = myPos.getX();
    int y = myPos.getY();

    if (!currLeader) {
      Position leaderpos = getFlock().getLeader().getPos();
      setTarget(leaderpos.getX(), leaderpos.getY());
    }


    int tar_x = getTarget().getX();
    int tar_y = getTarget().getY();
    double change_x=0;
    double change_y=0;
    //collision management
    ArrayList<Bird> c=getFlock().getBirds();
    ArrayList<int[] > crash=new ArrayList<int[] >();
    double radius=(3)*(speed);

    if(getFlock().getLeader()!=this){
    for(Bird bird:c){
      if(bird!=this){
        Position pos=bird.getPos();
        int posx=pos.getX();
        int posy=pos.getY();

        double hold;
        hold=Math.pow(x-posx,2)+Math.pow(y-posy,2);
        double dist=Math.pow(hold,0.5);
        if(dist<=radius){
          if(posx>x){
            posx=-(int)radius;
          }
          else if(posx<x){
            posx=(int)radius;
          }
          if(posy>y){
            posy=-(int)radius;
          }
          else if(posy<y){
            posy=(int)radius;
          }

          int arr[]={posx,posy};
          crash.add(arr);
        }
      }
    }
    int movex=0;
    int movey=0;
    for(int i=0;i<crash.size();i++){
      int temp[]=crash.get(i);
      movex+=temp[0];
      movey+=temp[1];
    }

    change_x=movex;
    change_y=movey;
    int sp=getMaxSpeed();
    if(change_x>sp || change_x<-sp){
      change_x=change_x%sp;
    }
    if(change_y>sp || change_y<-sp){
      change_y=change_y%sp;
    }
}
    //no collision go to target
    if(crash.size()==0){
    if ((tar_x == x) && (tar_y == y)) {
      change_x = 0;
      change_y = 0;
     }
    else {
      if (tar_x == x) {
        if (tar_y > y) {
          change_y = 1;
        }
        else{
            change_y =-1;
          }
         change_x = 0;
     }
        else {
        if (tar_y == y) {
          if (tar_x > x) {
            change_x += 1;
          }
          else
          {
            change_x = -1;
          }
          change_y = 0;
        }
        else {
          double slope = (tar_y - y) / (tar_x - x);
           if (tar_x > x) {
            change_x = 1;
          } else
            {change_x = -1;}
          change_x *= speed*(0.44); //splitting velocity into components
          change_y = slope * change_x*(0.777);
        } }
      }}

    //the below part corresponds to putting change in x and y direction in bounds
    int sp=getMaxSpeed();
    if(change_x>sp || change_x<sp){
      change_x=change_x%sp;
    }
    if(change_y>sp || change_y<sp){
      change_y=change_y%sp;
    }

    setPos(x + (int)change_x, y + (int)change_y);

}

}
