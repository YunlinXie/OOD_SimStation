package flocking;


import simstation.*;


public class Flocking extends Simulation {

    // constructor
    public Flocking() {
        super();
    }

    @Override
    public void populate() {
        for (int i = 0; i < 50; i++) {
            this.addAgent(new Birds());
        }
    }

    public static void main(String[] args) {
        SimulationPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }

}