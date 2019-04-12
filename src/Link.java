
public class Link {
	
	private int d;
	private boolean linked;
	private double gain;
	public boolean visited;
	
	public Link(int d, double gain) {
		this.d = d;
		this.linked = true;
		this.gain = gain;
	}

	public int getD () {
		return this.d;
	}
	public boolean isLinked(int d) {
		return this.d == d;
	}

	public double getGain() {
		return gain;
	}

	public void addGain (double gain) {
		this.gain += gain;
	}
	
}
