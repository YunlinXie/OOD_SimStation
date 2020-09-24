package simstation;

import mvc.*;
public class ResumeCommand extends Command {

	public ResumeCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		Simulation simulation = (Simulation) model;
		simulation.resume();
		simulation.changed();
	}
}
