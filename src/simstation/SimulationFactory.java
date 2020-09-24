package simstation;

import mvc.*;

public class SimulationFactory implements SimFactory {

	@Override
	public Model makeModel() {
		return new Simulation();
	}
	
	public View getView(Model model) { return new SimulationView(model); }

	@Override
	public String[] getEditCommands() {
		return new String[] { "Start", "Suspend", "Resume", "Stop", "Stats"};
	}

	@Override
	public Command makeEditCommand(Model model, String type) {
		if (type == "Start") return new StartCommand(model);
		if (type == "Stop") return new StopCommand(model);
		if (type == "Suspend") return new SuspendCommand(model);
		if (type == "Resume") return new ResumeCommand(model);
		if (type == "Stats") return new StatsCommand(model);
		return null;
	}

	@Override
	public String getTitle() {
		return "SimStation";
	}

	@Override
	public String[] getHelp() {
		String[] cmmds = new String[3];
		cmmds[0] = "Start: starts the simulation";
		cmmds[1] = "SetWidth(w): brick.width = w";
		cmmds[2] = "SetLength(l): brick.length = l";
		return cmmds;
	}

	@Override
	public String about() {
		return "SimStation version 1.0. Copyright 2020 by Cyberdellic Designs";
	}

}


