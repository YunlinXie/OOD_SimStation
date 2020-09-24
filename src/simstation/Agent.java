package simstation;

import java.io.*;
import mvc.*;

public abstract class Agent implements Serializable, Runnable {
	
	private Thread myThread;
	protected String name;
	protected Simulation world;
	private AgentState state;
	// for mobile agents:
	protected Heading heading;
	protected int xc, yc;

	public Agent(String name) {
		this.name = name;
		state = AgentState.READY;
		myThread = null;
		xc = Utilities.rng.nextInt(Simulation.SIZE);
		yc = Utilities.rng.nextInt(Simulation.SIZE);
		heading = Heading.random();
	}
    // assigns unique name
	public Agent() {
		this("Agent_" + mvc.Utilities.getID());
	}

	// called repeatedly by run:
	public abstract void update();

	public void run() {
		try {
			while(!finished()) {
				state = AgentState.RUNNING;
				update();
				Thread.sleep(20); // be cooperative
				synchronized(this) {
					while(state == AgentState.SUSPENDED) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			onInterrupted();
		}
		onExit();
	}

	// state changing functions:
	public synchronized void start() {
		onStart();
		state = AgentState.READY;
		if (myThread == null)
			myThread = new Thread(this, name);
		myThread.start(); // calls run()
	}
	public synchronized void suspend() {
		state = AgentState.SUSPENDED;
	}
	public synchronized void resume() {
		state = AgentState.READY;
		notify();
	}
	public synchronized void stop() {
		state = AgentState.STOPPED;
	}

	// tests:

	public synchronized boolean finished() {
		return (state == AgentState.STOPPED) || myThread != null && myThread.getState() == Thread.State.TERMINATED;
	}
	public synchronized boolean isSuspended() {
		return state == AgentState.SUSPENDED;
	}
	public void join() throws InterruptedException {
	     if (myThread != null) myThread.join();
	}
	// getters & setters:
	public synchronized String getName() { return name; }
	public synchronized Simulation getWorld() { return world; }
	public synchronized void setWorld(Simulation world) { this.world = world; }
	public synchronized Heading getHeading() { return heading; }
	public synchronized void setHeading(Heading heading) { this.heading = heading; }
	public synchronized int getXc() { return xc; }
	public synchronized int getYc() { return yc; }

	public synchronized void setXc(int xc) { this.xc = xc; }
	public synchronized void setYc(int yc) { this.yc = yc; }
	public synchronized AgentState getAgentState() { return state; }

	public synchronized void move(int steps) {
		switch(heading) {
		case NORTH:
			yc = (yc - steps);
			if (yc < 0) yc = Simulation.SIZE + yc;
			break;
		case SOUTH:
			yc = (yc + steps) % Simulation.SIZE;
			break;
		case EAST:
			xc = (xc + steps) % Simulation.SIZE;
			break;
		case WEST:
			xc = (xc - steps);
			if (xc < 0) xc = Simulation.SIZE + xc;
			break;
		}
		world.changed();
	}

	public synchronized double distance(Agent other) {
		return Math.sqrt((this.xc - other.xc) * (this.xc - other.xc) + (this.yc - other.yc) * (this.yc - other.yc));
	}

	// overridables:
	protected synchronized void onStart() {}
	protected synchronized void onExit() {}
	protected synchronized void onInterrupted() {}
}



