package simstation;

import java.util.*;
import mvc.*;
import java.io.Serializable;

public class Simulation extends Model {
	public static int SIZE = 250;
	protected List<Agent> agents;
	private Timer timer;
	protected int clock;

	public Simulation() {
		agents = new LinkedList<Agent>();
		clock = 0;
	}
	// override in a subclass if desired:
	public void populate() {}

	public synchronized Agent getNeighbor(Agent asker, double radius) {
		Agent neighbor = null;
		boolean found = false;
		int i = Utilities.rng.nextInt(agents.size());
		int start = i;
		while(!found) {
			Agent candidate = agents.get(i);
			if (candidate != asker && asker.distance(candidate) < radius) {
				neighbor = agents.get(i);
				found = true;
			} else {
				i = (i + 1) % agents.size();
				if (i == start) break;
			}
		}
		return neighbor;
	}

	// bulk thread methods
	public void start() {
		agents = new LinkedList<Agent>();
		clock = 0;
		populate();
		startTimer();
		for(Agent a: agents) a.start();
	}
	public synchronized void suspend() {
	  for(Agent a: agents) { a.suspend(); }
	  stopTimer();
    }
	public synchronized void resume() {
		startTimer();
		for(Agent a: agents) { a.resume(); }
	}
	public synchronized void stop() {
		for(Agent a: agents) { a.stop(); }
		stopTimer();
	}

	// timer stuff:
	public synchronized int getClock() { return clock; }
	private synchronized void incClock() { clock++; }
	private void startTimer() {
	   timer = new Timer();
	   timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
	}
	private void stopTimer() {
		timer.cancel();
		timer.purge();
	}
	private class ClockUpdater extends TimerTask {
		public void run() { incClock(); }
	}

	// delegators
	public int size() { return agents.size(); }
	public synchronized Iterator<Agent> iterator() {
		return agents.iterator();
	}
	public void addAgent(Agent a) {
		agents.add(a);
		a.setWorld(this); // very important!
	}
	// override to display more interesting statistics
	public String[] getStats() {
		String[] stats = new String[2];
		stats[0] = "#agents = " + this.size();
		stats[1] = "clock = " + this.getClock();
		return stats;
	}
}

