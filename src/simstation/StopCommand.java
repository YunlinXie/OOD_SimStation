package simstation;

import mvc.*;

public class StopCommand extends Command{

	public StopCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		Simulation simulation = (Simulation) model;
		simulation.stop();
		simulation.changed();
	}
}