package pdt;

public class Reciprocate extends CooperateStrategy {
	public boolean cooperate() { return owner.getLastResponse(); }
}