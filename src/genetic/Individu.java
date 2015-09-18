public class Individu implements Comparable<Individu>{


	private int fitness;
	private int a, b, c;
	private int previousValue;
	private int seed;

	public Individu(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int getFitness(){
		return fitness;
	}

	public void calculFitness(){
		fitness = 1001;
		for(int seed = 0; seed < 1000; ++seed){
			int [] output = new int[1001];
			previousValue = seed;
			for(int i = 0; i < 1001; ++i){
				output[i] = generateNextNumber();
			}
			boolean done = false;
			for(int i = 0; !done && i < 1000; ++i){
				for(int j = i + 1; !done && j < 1001; ++j){
					if(output[i] == output[j]){
						if(j - i < fitness){
							fitness = j - i;
						}
						done = true;
					}
				}

			}		
		}
	}

	@Override
	public int compareTo(Individu autre){
		return new Integer(autre.fitness).compareTo(new Integer(fitness));
	}

	public int generateNextNumber(){
		int value = Math.abs(((int) Math.pow((previousValue * a + b), c )) % 1000);
		previousValue = value;
		return value;
	}
	@Override
	public String toString(){
		return "Individu : "+ fitness + "\n" 
			+ "xn = (( xn-1 * " + a + " + " + b + " ) ^ "+ c +" ) % 1000)";
	}

	public int getA(){ return a; }
	public int getB(){ return b; }
	public int getC(){ return c; }

}
