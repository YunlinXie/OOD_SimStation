package simstation;

import javax.swing.JOptionPane;

import mvc.*;
import java.util.*;

public class StatsCommand extends Command{

	public StatsCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		Simulation simulation = (Simulation) model;
		String[] massages = simulation.getStats();
		/*
		String[] massages = new String[2];
		massages[0] = "#agent = " +Integer.toString(simulation.getAgentSize());
		massages[1] = "clock = " + Integer.toString(simulation.getClock());
		*/
		Utilities.inform(massages);
		simulation.changed();
	}
}

