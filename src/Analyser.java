import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;


public class Analyser {

	public static void main(String ... args) throws Exception {
		String programName = args[0];
		
		String outputFilename = "period.txt";
		String resultsFile = "results.txt";
		Scanner scanner;
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename), "utf-8"));

		ArrayList<Integer> output=new ArrayList<Integer>();

		int periode_min=10001;
		int seed_min=-1;
		int couvrance=1001;
		HashSet<Integer> unicite= new HashSet<Integer>();

		for(int i = 0; i <= 1000; ++i){
			String info=printProgress(i,1000);
			System.out.write(info.getBytes());

			ProcessBuilder builder = new ProcessBuilder("java", "-jar", "prng.jar", i + "", "1001", resultsFile);
			Process p = builder.start();
			
			BufferedReader stdIn=new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdErr=new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String line;
			while((line=stdIn.readLine())!=null){
				System.out.println(line);
			}
			stdIn.close();
			
			while((line=stdErr.readLine())!=null){
				System.out.println(line);
			}
			stdErr.close();
			p.waitFor();

			scanner = new Scanner(new File(resultsFile));

			int j = 0;
			while(scanner.hasNextInt()){
				j=scanner.nextInt();
				output.add(j);
				unicite.add(j);
			}

			scanner.close();

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
		}

		writer.close();
		System.out.println();
		System.out.println("[\u001B[36m PERIODE \u001B[0m ] 	"+periode_min);
		System.out.println("[\u001B[36m SEEDMIN \u001B[0m ] 	"+seed_min);
		System.out.println("[\u001B[36m COUVRANCE \u001B[0m] 	"+100*couvrance/1000.0+" %");

	}

	public static int getPeriod(ArrayList<Integer> list){
		for (int i = 0; i < list.size()-1; i++) {
			for(int j=i+1; j<list.size();j++){
				if(list.get(i).equals(list.get(j)))
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
