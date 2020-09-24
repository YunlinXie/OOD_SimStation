package pdt;

public class RandomlyCheat extends CooperateStrategy {
	public boolean cooperate() { 
		return (mvc.Utilities.rng.nextInt(2) == 0);
    }
}