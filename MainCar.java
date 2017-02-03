import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainCar {
	public static void main(String [] args) {
		Behavior b1 = new Driving();
		Behavior b2 = new Hits(SensorPort.S2);
		Behavior b3 = new ColorDec(SensorPort.S4);
		Behavior [] bArray = {b1, b2, b3};
		Arbitrator arby = new Arbitrator(bArray);
		arby.start();
	}
}