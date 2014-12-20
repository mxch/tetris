package mxch.geometry;

public abstract class Shape {
	protected enum Type {I, J, L, O, S, T, Z};
	private Type t;

	public abstract boolean rotateRight();
	public abstract boolean rotateLeft();
}
