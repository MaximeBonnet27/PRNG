import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;


public class Analyser {

	public static void main(String ... args) throws Exception {
		String programName = args[0];
		//System.out.println(programName);
		String outputFilename = "period.txt";
		String resultsFile = "results.txt";
		Scanner scanner;
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename), "utf-8"));

		//long [] output = new long[1001]; //1001 pas tro pertinant?!
		ArrayList<Integer> output=new ArrayList<Integer>();

		int periode_min=10001;
		int seed_min=-1;
		int couvrance=1001;
		HashSet<Integer> unicite= new HashSet<Integer>();

		for(int i = 0; i < 1000; ++i){
			String info=printProgress(i+1,1000);
			System.out.write(info.getBytes());

			ProcessBuilder builder = new ProcessBuilder("java", "-jar", "prng.jar", i + "", "1001", resultsFile);
			Process p = builder.start();
			p.waitFor();

			scanner = new Scanner(new File(resultsFile));

			int j = 0;
			while(scanner.hasNextInt()){
				//output[j++] = scanner.nextInt();
				j=scanner.nextInt();
				output.add(j);
				unicite.add(j);
			}

			scanner.close();

			/*int period = -1;
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
			}*/

			int period=getPeriod(output);
			output.clear();
			writer.write(period + "\n");

			if(periode_min>period){
				periode_min=period;
				seed_min=i;
			}

			if(couvrance>unicite.size())
				couvrance=unicite.size();
			unicite.clear();
			/*
			boolean[] present = new boolean[1000];
			for(j = 0; j < 1000; ++j){ /tu  oublie le 1001 ième
				present[j] = false;
			}

			int sum = 0;
			for(j = 0; j < 1000; ++j){ /tu  oublie le 1001 ième
				sum += output[j];
				present[(int) output[j]] = true;
			}

			int uniques = 0;
			for(j = 0; j < 1000; ++j){ /tu  oublie le 1001 ième
				if(present[j]){
					uniques++;
				}
			}*/

			//System.out.println("Period : " + period + ", Uniques : " + uniques + ", Average : " + (sum / 1000));
		}

		writer.close();
		System.out.println();
		System.out.println("[\u001B[36m PERIODE \u001B[0m ] 	"+periode_min);//+" (seed "+seed_min+")");
		System.out.println("[\u001B[36m COUVRANCE \u001B[0m] 	"+100*couvrance/1000+" %");

	}

	public static int getPeriod(ArrayList<Integer> list){
		for (int i = 0; i < list.size()-1; i++) {
			for(int j=i+1; j<list.size();j++){
				if(list.get(i)==list.get(j))
					return j-i;
			}
		}
		return -1;
	}

	private static int cpt=0;
	public static String  printProgress(double indice,double borne){
		String[] icone={"\\","|","/","-"};
		String resulte=icone[cpt]+" "+100*indice/borne+"%";
		cpt=(cpt+1)%icone.length;
		return "\r"+resulte;
	}

}
