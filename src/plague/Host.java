package plague;

import java.awt.Color;
import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;


public class Host extends Agent {

	private boolean infected;
	private int resistence;

	public Host() {
		super();
		int luck = mvc.Utilities.rng.nextInt(100);
		resistence = mvc.Utilities.rng.nextInt(PlagueSimulation.PERCENT_RESISTENT);
		luck = mvc.Utilities.rng.nextInt(100);
		infected = luck < PlagueSimulation.INITIAL_PERCENT_INFECTED;
	}

	public synchronized boolean isInfected() { return infected; }
	// randomly infect
	public synchronized void infect() {
		if(!this.isInfected()) {
			int luck = mvc.Utilities.rng.nextInt(100);
			this.infected = (this.resistence < luck);
		}
	}
	public void update() {
		if (this.isInfected()) {
			Host neighbor = (Host)world.getNeighbor(this, 20.0);
		    if (neighbor != null && !neighbor.isInfected()) neighbor.infect();
		}
		setHeading(Heading.random());
		move(mvc.Utilities.rng.nextInt(8) + 1);
	}
}
