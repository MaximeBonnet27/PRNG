package genetic;

public class Individu implements Comparable<Individu>{

	private static int A=0;
	private static int B=0;
	
	
	private int periode;
	private int a, b;
	private int previousValue;
	private int seed;

	public Individu(int a, int b){
		this.a = a;
		this.b = b;
	}

	public Individu(){
		a=A;
		b=B;
		calculPeriode();
		B++;
		A+=B/100;
		B%=100;
	}
	
	public int getPeriode(){
		return periode;
	}

	public void calculPeriode(){
		periode = 1001;
		
		for(int seed = 0; seed < 1000; ++seed){
			int [] output = new int[1001];
			previousValue = seed;
			
			for(int i = 0; i < 1001; ++i)
				output[i] = generateNextNumber();

			boolean find=false;
			for(int i = 0;i < 1000 && !find; ++i){
				for(int j = i + 1;j <= 1000 & !find; ++j){
					if(output[i] == output[j]){
						if(j - i < periode)
							periode = j - i;
						find=true;
					}
				}
			}		
		}
	}

	@Override
	public int compareTo(Individu autre){
		return new Integer(autre.periode).compareTo(new Integer(periode));
	}

	public int generateNextNumber(){
		int value = Math.abs((previousValue * a + b)% 1000);
		previousValue = value;
		return value;
	}
	@Override
	public String toString(){
		return "Individu : xn = ( xn-1 * " + a + " + " + b + ") % 1000)\n"
				+ "Periode : "+periode;
	}

	
}
