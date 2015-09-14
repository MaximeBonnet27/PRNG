import java.io.*;

public class Main {
	public static void main(String ... args) throws Exception{
		System.out.println("Generator : Start !");
		long seed = Long.parseLong(args[0]);
		long repeats = Long.parseLong(args[1]);
		String filename = args[2];
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
		Generator generator = new Generator(seed);
		for(int i = 0; i < repeats; ++i){
			writer.write(generator.generateNumber() + "\n");
		}
		writer.close();
		System.out.println("Generator : Done !");
	}
}

