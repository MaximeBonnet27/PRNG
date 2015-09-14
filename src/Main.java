public class Main {
	public static void main(String ... args){

		Generator generator = new Generator(System.currentTimeMillis());
		System.out.println("Generator's period : " + generator.getSeedPeriod());
	}
}

