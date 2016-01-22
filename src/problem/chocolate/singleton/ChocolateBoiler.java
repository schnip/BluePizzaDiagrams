package problem.chocolate.singleton;


public class ChocolateBoiler {
	private boolean empty;
	private boolean boiled;
	
	public ChocolateBoiler() {
		this.empty = true;
		this.boiled = false;
	}
	
	public void fill() {
		if(isEmpty()) {
			// fill the boiler with a milk/chocolate mixture
			this.empty = false;
			this.boiled = false;
		}
	}
	
	public void drain() {
		if(!isEmpty() && isBoiled()) {
			// drain the boiled milk and chocolate
			this.empty = true;
		}
	}
	
	public void boil() {
		if(!isEmpty() && !isBoiled()) {
			// boil the contents
			this.boiled = true;
		}
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	public boolean isBoiled() {
		return this.boiled;
	}
}
