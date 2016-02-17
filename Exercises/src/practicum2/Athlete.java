package practicum2;

import java.io.Serializable;

public class Athlete implements Serializable, Comparable<Athlete> {
	
	private static final long serialVersionUID = 1L;
	
	String name;
	Double result;

	@Override
	public String toString() {
		return String.format("%7.2f %s", result, name);
	}

	@Override
	public int compareTo(Athlete other) {
		return result.compareTo(other.result);
	}
	
}
