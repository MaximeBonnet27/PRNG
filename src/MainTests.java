public class MainTests {

	public static void main(String ... args){
		int seed = 998;
		Generator generator = new Generator(seed);
		long sum = 0;
		for(int i = 0; i < 1000; ++i){
			System.out.println(generator.generateNumber());
		}
	}
}
