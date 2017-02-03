import java.util.Random;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;
	
public class Driving implements Behavior{
   private boolean suppressed = false;
   private Random rn = new Random();
   private long milis = System.currentTimeMillis();
   private long initial = System.currentTimeMillis();
   private int timer = 3000;
   
   public void random_move(){
	   if(milis < initial+timer){
		   milis = System.currentTimeMillis();
		   return;
	   }
	   initial = milis;
	   Motor.A.stop(); // clean up
	   Motor.C.stop();
	   int r = rn.nextInt(6);
	   LCD.drawString("Direction:"+String.valueOf(r), 0, 2);
	   switch(r){
	   	case 0:
	   		Motor.A.forward();
			Motor.C.backward();
			timer = 1000;
			break;
	   	case 1:
	   		Motor.A.forward();
			Motor.C.forward();
			timer = 2500;
			break;
	   	case 2:
	   		Motor.A.backward();
			Motor.C.forward();
			timer = 1000;
			break;
	   	case 3:
	   		Motor.A.forward();
			timer = 1000;
			break;
	   	case 4:
	   		Motor.C.forward();
			timer = 1000;
			break;
	   	case 5:
	   		Motor.A.forward();
			Motor.C.forward();
			timer = 2500;
			break;
		default:
			break;
	   }
	   return;
   }
   
   public boolean takeControl() {
      return true;
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
     suppressed = false;
     milis = System.currentTimeMillis();
     initial = milis-timer;
     LCD.clear();
     while( !suppressed ){
    	if(Button.ESCAPE.isDown()){
	   		System.exit(0);
	   	}
    	random_move();
    	LCD.drawString("Searching", 0, 1);
    	Thread.yield();
     }
     Motor.A.stop(); // clean up
     Motor.C.stop();
   }
}
