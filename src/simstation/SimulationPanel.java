package simstation;

import mvc.*;


import java.awt.*;
import javax.swing.*;

public class SimulationPanel extends AppPanel{
	public SimulationPanel(SimFactory factory) {
		super(factory);
		
		this.setLayout(new GridLayout(1, 2));
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(5, 1));
		
		Panel p = new Panel();
		JButton b = new JButton("Start");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);
		
		p = new Panel();
		b = new JButton("Suspend");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);
		
		p = new Panel();
		b = new JButton("Resume");
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);
		
		p = new Panel();
		b = new JButton("Stop");
		p.setLayout(new FlowLayout());
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);
		
		p = new Panel();
		b = new JButton("Stats");
		p.setLayout(new FlowLayout());
		b.addActionListener(this);
		p.add(b);
		buttons.add(p);
		
		this.add(buttons);
		this.add(factory.getView(model));
	}
}
