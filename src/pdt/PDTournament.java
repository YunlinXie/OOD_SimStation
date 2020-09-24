package pdt;

import mvc.*;
import simstation.*;

public class PDTournament extends Simulation {
	public static int numAgents = 10;
	public void populate() {
		for(int i = 0; i < numAgents; i++) {
			this.addAgent(new Prisoner(new AlwaysCheat()));
		}
		for(int i = 0; i < numAgents; i++) {
		   addAgent(new Prisoner(new AlwaysCooperate()));
		}
		for(int i = 0; i < numAgents; i++) {
		   addAgent(new Prisoner(new RandomlyCheat()));
		}
		for(int i = 0; i < numAgents; i++) {
			addAgent(new Prisoner(new Reciprocate()));
		}
	}
	
	public String[] getStats() {
		int cheaterScore = 0;
		int numCheaters = 0;
		int cooperatorScore = 0;
		int numCooperators = 0;
		int randomScore = 0;
		int numRandoms = 0;
		int reciprocatorScore = 0;
		int numReciprocators = 0;
		double cheaterAvg = 0.0;
		double cooperatorAvg = 0.0;
		double randomAvg = 0.0;
		double reciprocatorAvg = 0.0;
		
		for(Agent a: agents) {
			Prisoner p = (Prisoner)a;
			if (p.getStrategy() instanceof AlwaysCheat) {
				cheaterScore += p.getFitness();
				numCheaters++;
			} else if (p.getStrategy() instanceof AlwaysCooperate) {
				cooperatorScore += p.getFitness();
				numCooperators++;
			} else if (p.getStrategy() instanceof Reciprocate) {
				reciprocatorScore += p.getFitness();
				numReciprocators++;
			} else if (p.getStrategy() instanceof RandomlyCheat) {
				randomScore += p.getFitness();
				numRandoms++;
			} 
		}
		if (numCheaters > 0) cheaterAvg = ((double)cheaterScore)/numCheaters;
		if (numCooperators > 0) cooperatorAvg = ((double)cooperatorScore)/numCooperators;
		if (numRandoms > 0) randomAvg = ((double)randomScore)/numRandoms;
		if (numReciprocators > 0) reciprocatorAvg = ((double)reciprocatorScore)/numReciprocators;
		
		String[] stats1 = super.getStats();
		String[] stats = new String[6];
		stats[0] = stats1[0];
		stats[1] = stats1[1];
		stats[2] = "Cheater's average = " + cheaterAvg;
		stats[3] = "Cooperator's average = " + cooperatorAvg;
		stats[4] = "Reciproicator's average = " + reciprocatorAvg;
		stats[5] = "Random's average = " + randomAvg;
		return stats;
	}
	
	public static void main(String[] args) {
		AppPanel panel = new SimulationPanel(new PDFactory());
		panel.display();
	}

}


