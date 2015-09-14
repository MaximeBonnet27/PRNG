public class Generator {

	private long seed;
	private long previousValue;
	
	public Generator(long seed){
		this.seed = seed;
		this.previousValue = seed;
	}

	public long generateDigit(){
		long value = (previousValue + 1) % 10;
		previousValue = value;
		return value;
	}

	public long generateNumber(){
		return (generateDigit() * generateDigit() * 100 
				+ generateDigit() * generateDigit() * 10 
				+ generateDigit() * generateDigit()) % 1000;
	}

	public long getSeedPeriod(){
		long[] values = new long[1001];
		for(int i = 0; i < 1001; ++i){
			values[i] = generateNumber();
			System.out.println(values[i]);
		}	
		for(int i = 0; i < 1001; ++i){
			for(int j = i + 1; j < 1001; ++j){
				if(values[i] == values[j]){
					return j - i;
				}
			}
		}
		return -1;
	}

}
