package plague;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import mvc.*;
import simstation.*;


public class PlagueView extends View {

	public PlagueView(Model model) {
		super(model);
	}

	public void paintComponent(Graphics gc) {
		Color oldColor = gc.getColor();
		//gc.setColor(Color.RED);
		PlagueSimulation sim = (PlagueSimulation)model;
		Iterator<Agent> it = sim.iterator();
		while(it.hasNext()) {
			Agent a = it.next();
			if (((Host)a).isInfected())
				gc.setColor(Color.RED);
			else
				gc.setColor(Color.green);
			gc.fillOval(a.getXc(), a.getYc(), 5, 5);
		}
		gc.setColor(oldColor);
	}
}

