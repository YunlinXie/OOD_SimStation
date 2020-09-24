package plague;

import simstation.*;
import java.util.*;
import mvc.AppPanel;

public class PlagueSimulation extends Simulation {
	public static int INITIAL_PERCENT_INFECTED = 2;
	public static int PERCENT_RESISTENT = 2;
	public void populate() {
		for(int i = 0; i < 50; i++) {
			addAgent(new Host());
		}
	}
	public String[] getStats() {
		String[] basicStats = super.getStats();
		String[] stats = new String[3];
		stats[0] = basicStats[0];
		stats[1] = basicStats[1];
		stats[2] = "% infected = " + 100 * percentInfected();
		return stats;
	}
	private double percentInfected() {
		int count = 0;
		Iterator<Agent> p = iterator();
		while(p.hasNext()) {
			Host host = (Host)p.next();
			if (host.isInfected()) count++;
		}
		return ((double)count)/size();
	}
	public static void main(String[] args) {
		AppPanel panel = new SimulationPanel(new PlagueFactory());
		panel.display();
	}
}