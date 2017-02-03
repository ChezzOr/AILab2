import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class ColorDec implements Behavior{
	private LightSensor color;
    private boolean suppressed = false;
    private int found = 0;
    
	public ColorDec(SensorPort port){
		color = new LightSensor( port );
	}
	
	public boolean takeControl() {
       return color.getNormalizedLightValue() <= 290;
    }

    public void suppress() {
       suppressed = true;
    }

	@Override
	public void action() {
		found++;
		/**if(found==5){
			Sound.beep();
			Sound.beep();
			Sound.beep();
			System.exit(0);
		}**/
		suppressed = false;
		Sound.beep();
		/*Motor.A.backward();
		Motor.C.forward();*/
		Motor.A.stop();
		Motor.C.stop();
		LCD.clear();

		while( color.getNormalizedLightValue() <= 290 && !suppressed){
			if(Button.ESCAPE.isDown()){
	    		System.exit(0);
	    	}
			Sound.beep();
			LCD.drawString("Found color", 0, 1);
			LCD.drawString("Light:"+String.valueOf(color.getNormalizedLightValue()), 0, 2);
	    	Thread.yield();
		}
       
		Motor.A.stop();
		Motor.C.stop();
	}
}
