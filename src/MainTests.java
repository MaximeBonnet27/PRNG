public class MainTests {

	public static void main(String ... args){
		long seed = (System.currentTimeMillis()) % 1000;
		Generator generator = new Generator(seed);
		long sum = 0;
		for(int i = 0; i < 1000; ++i){
			sum += generator.generateNumber();
		}
		System.out.println("Average : " + sum / 1000);
		System.out.println("Period : " + generator.getSeedPeriod());
		System.out.println("Unique numbers : " + generator.getUniqueCount());
	}
}
