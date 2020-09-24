package pdt;

public abstract class CooperateStrategy {
	protected Prisoner owner;
	public CooperateStrategy(Prisoner owner) { this.owner = owner; }
	public CooperateStrategy() { this(null); }
	public Prisoner getOwner() { return owner; }
	public void setOwner(Prisoner owner) { this.owner = owner; }
	public abstract boolean cooperate();
}