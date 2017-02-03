import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class Hits implements Behavior{
	private TouchSensor touch = new TouchSensor(SensorPort.S3);
    private UltrasonicSensor sonar;
    private boolean suppressed = false;
    
    public Hits(SensorPort port)
    {
       sonar = new UltrasonicSensor( port );
    }

    public boolean takeControl() {
       return touch.isPressed() || sonar.getDistance() < 25;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
       suppressed = false;
       
       Motor.A.backward();
       Motor.C.backward();
       LCD.clear();
       while( (sonar.getDistance() < 30 || touch.isPressed()) && !suppressed ){
    	   if(Button.ESCAPE.isDown()){
	    		System.exit(0);
	    	}
    	   LCD.drawString("Avoiding colision", 0, 1);
    	   LCD.drawString("Distance:"+String.valueOf(sonar.getDistance()), 0, 2);
    	   Thread.yield();
       }

       Motor.A.stop();
       Motor.C.stop();
    }
}
