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
		//previousValue=(int)(73*previousValue+13)%1000;
	/*	int res=previousValue;
		int a=res/100;
		res%=100;
		int b=res/10;
		res%=10;
		previousValue=((a+b+33)*previousValue+(res+a+33))%1000;
		
		previousValue = previousValue*6854684+(previousValue%1354)*6461+156;
		String s = previousValue+"";
		int longueur = s.length();
		previousValue = Integer.valueOf(s.substring(longueur/2-1, longueur/2+2));
		return previousValue;
		*/
		int a = -11;
		int b = 21;
		int c = 1;
		int value = ((int) Math.pow((previousValue * a + b), c ) % 1000);
		previousValue = value;
		
		return value;
	}
	/*
	public int generateNumber2(){
		int value;
		if(n % 82 == 0 || previousValue == 988 || previousValue == 662 || previousValue == 326){
			value = method2();
		}
		else{
			value = method1();
		}
		n++;
		previousValue = value;
		return value;

	}
	/ Period = 82
	private int method1(){
		int x = previousValue % 10;
		previousValue /= 10;
		int y = previousValue % 100;
		previousValue /= 10;
		long z = previousValue % 10;
		int value = x * 100 + z * 10 + y;
		if(value == 0){
			value = seed + n;
		}
		return value % 1000;
	}
	/ Period = 52
	private int method2(){
		String binary = Long.toBinaryString(previousValue + 1);
		StringBuffer sb = new StringBuffer();
		int count = 0;
		for(int i = 0; i < binary.length(); ++i){
			if(binary.charAt(i) == '1'){
				count++;
				if((n +count) % 2 == 1){
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
*/
}
