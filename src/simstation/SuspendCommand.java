
package simstation;

import mvc.*;

public class SuspendCommand extends Command {

	public SuspendCommand(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	public void execute() {
		Simulation simulation = (Simulation) model;
		simulation.suspend();
		simulation.changed();
	}
}
