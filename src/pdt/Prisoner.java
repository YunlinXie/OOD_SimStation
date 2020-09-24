package pdt;

import simstation.*;



public class Prisoner extends Agent{
	CooperateStrategy strategy;
	boolean lastResponse;
	int fitness;
	public Prisoner(CooperateStrategy strategy) {
		super();
		this.strategy = strategy;
		strategy.setOwner(this);
		lastResponse = true;
		fitness = 0;
	}
	
	public synchronized boolean getLastResponse() { return lastResponse; }
    public synchronized void setLastResponse(boolean lastResponse) {
		this.lastResponse = lastResponse;
	}
    public CooperateStrategy getStrategy() { return strategy; }
	public synchronized int getFitness() { return fitness; }
	// strategy used here:
	public synchronized boolean cooperate() { return strategy.cooperate(); }
	// update my score:
	public synchronized void updateFitness(boolean myChoice, boolean nbrChoice) {
		if (myChoice) {
			if (nbrChoice) {
				fitness += 3;
			} else {
				// no points
			}
		} else {
			if (nbrChoice) {
				fitness += 5;
			} else {
				fitness += 1;
			}
		}
	}
	
	public void update() {
		Prisoner neighbor = (Prisoner)world.getNeighbor(this, 20.0);
		if (neighbor != null) {
			// order important here:
			boolean myChoice = cooperate();
			lastResponse = neighbor.cooperate();
			neighbor.setLastResponse(myChoice);
			this.updateFitness(myChoice, lastResponse);
			neighbor.updateFitness(lastResponse, myChoice);
		}
		setHeading(Heading.random());
		move(10);
	}
}





