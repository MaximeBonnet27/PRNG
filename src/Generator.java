public class Generator {

	private long seed;
	private long previousValue;
	private int n;
	public Generator(long seed){
		this.seed = seed;
		this.previousValue = seed;
		this.n = 0;
	}

	public long generateNumber(){
		long value;
		if(n % 82 == 0){
			value = method2();
		}
		else{
			value = method1();
		}
		n++;
		previousValue = value;
		return value;

	}
	// Period = 82
	private long method1(){
		long x = previousValue % 10;
		previousValue /= 10;
		long y = previousValue % 100;
		previousValue /= 10;
		long z = previousValue % 10;
		long value = x * 100 + z * 10 + y;
		return value % 1000;
	}
	// Period = 52
	private long method2(){
		String binary = Long.toBinaryString(previousValue + 1);
		StringBuffer sb = new StringBuffer();
		int count = 0;
		for(int i = 0; i < binary.length(); ++i){
			if(binary.charAt(i) == '1'){
				count++;
				if(count % 2 == 1){
					sb.append("1");
				}
				else{
					sb.append("2");
				}
			}
			else {
				sb.append("0");
			}
			
		}
		return Long.parseLong(sb.toString(), 3) % 1000;
	}
	public long getSeedPeriod(){
		long[] values = new long[1001];
		for(int i = 0; i < 1001; ++i){
			values[i] = generateNumber();
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

	public long getUniqueCount(){
		boolean[] present = new boolean[1000];
		for(int i = 0; i < 1000; ++i){
			present[i] = false;
		}
		for(int i = 0; i < 1000; ++i){
			present[(int) generateNumber()] = true;
		}
		int sum = 0;
		for(int i = 0; i < 1000; ++i){
			if(present[i]){
				sum++;
			}
		}
		return sum;
	}

}
