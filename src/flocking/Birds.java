package flocking;

import mvc.*;
import simstation.*;

public class Birds extends Agent {
    private int speeds;

    public Birds() {
        super();
        speeds = Utilities.rng.nextInt(10)+1;
    }

    @Override
    public void update() {
    	int distance = 10;
        Birds neighbor = (Birds) world.getNeighbor(this, distance);
        if (neighbor != null) {
            this.heading = neighbor.heading;
            this.speeds = neighbor.speeds;
        }
        move(speeds);
    }

}