import java.io.*;
import java.util.Scanner;

public class Analyser {

	public static void main(String ... args) throws Exception {
		String programName = args[0];
		System.out.println(programName);
		String outputFilename = "period.txt";
		String resultsFile = "results.txt";
		Scanner scanner;
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename), "utf-8"));
		long [] output = new long[1001];
		for(int i = 0; i < 1000; ++i){
			ProcessBuilder builder = new ProcessBuilder("java", "-jar", "prng.jar", i + "", "1001", resultsFile);
			Process p = builder.start();
			p.waitFor();
			scanner = new Scanner(new File(resultsFile));
			int j = 0;
			while(scanner.hasNextInt()){
				output[j++] = scanner.nextInt();
			}
			scanner.close();
			int period = -1;
			boolean found = false;
			for(j = 0; j < 1000 && !found; ++j){
				for(int k = j + 1; k < 1001 && !found; ++k){
					if(output[j] == output[k]){
						period = k - j;
						if(period < 10){
							System.out.println(output[j]);
						}
						found = true;
					}
				}
			}
			writer.write(period + "\n");
			
			boolean[] present = new boolean[1000];
			for(j = 0; j < 1000; ++j){
				present[j] = false;
			}
			int sum = 0;
			for(j = 0; j < 1000; ++j){
				sum += output[j];
				present[(int) output[j]] = true;
			}
			int uniques = 0;
			for(j = 0; j < 1000; ++j){
				if(present[j]){
					uniques++;
				}
			}
			System.out.println("Period : " + period + ", Uniques : " + uniques + ", Average : " + (sum / 1000));
		}
		writer.close();
	}

}
