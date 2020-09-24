package plague;


import mvc.*;
import simstation.*;


public class PlagueFactory extends SimulationFactory {
	public Model makeModel() { return new PlagueSimulation(); }
	public View getView(Model model) { return new PlagueView(model); }
}