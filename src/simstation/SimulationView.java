package simstation;

import java.awt.*;
import java.util.*;
import mvc.*;

public class SimulationView extends View {
	
	public SimulationView(Model model) {
		super(model);
		setSize(Simulation.SIZE, Simulation.SIZE);
	}
	
	public void paintComponent(Graphics gc) {
		Simulation simulation = (Simulation) model;
		Color oldColor = gc.getColor();
		Iterator<Agent> it = simulation.iterator();
		while(it.hasNext()) {
			Agent agent = it.next();
			gc.setColor(Color.red);
			gc.fillOval(agent.getXc(), agent.getYc(), 5, 5);
		}
		gc.setColor(oldColor);
	}
		
}