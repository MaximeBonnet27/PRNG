public class Generator {

	private int seed;
	private int previousValue;
	private int n;
	
	public Generator(int seed){
		this.seed = seed;
		this.previousValue = seed;
		this.n = 0;
	}

	public long generateNumber(){
		int a = 21;
		int b = 89;
		int value = Math.abs((previousValue * a + b)% 1000);
		previousValue = value;
		
		return value;
	}
	
}
