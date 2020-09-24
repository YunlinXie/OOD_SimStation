package pdt;

import mvc.*;
import simstation.*;

public class PDFactory extends SimulationFactory {
	public Model makeModel() { return new PDTournament(); }
}