package ch.fhnw.richards.lecture02.mvc;

public class ButtonClickModel {
	private int value;
	
	
	//Mit dem Zugriffsmodifizierer protected ist der Zugriff nicht nur Klassen aus dem selben Package (wie "default"), sondern auch Subklassen der Klasse erlaubt. Dies gilt auch, 
	//wenn die betreffenden Subklassen aus einem anderen Package sind als die Klasse des betreffenden Members.
	
	protected ButtonClickModel() {
		value = 0;
	}
	
	public int getValue() {
		return value;
	}
	
	public int incrementValue() {
		value++;
		return value;
	}
}
