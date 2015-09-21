package genetic;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public class AlgorithmeGenetique{

	private final int nombreDeGenerations = 100000; //100*100
	private ArrayList<Individu> best;
	private int bestPeriode;

	public AlgorithmeGenetique() {
		best=new ArrayList<>();
		bestPeriode=0;
	}

	public ArrayList<Individu> calculer(){
		for(int i=0;i<nombreDeGenerations;i++){
			System.out.print(printProgress(i+1,nombreDeGenerations) +"periode : "+bestPeriode +"size : "+best.size());
			Individu individu=new Individu();
			if(individu.getPeriode()>bestPeriode){
				bestPeriode=individu.getPeriode();
				filtre();
				best.add(individu);
			}	

		}
		return best;
	}

	public void filtre(){
		for(int i=0;i<best.size();i++){
			if(best.get(i).getPeriode()<bestPeriode){
				best.remove(i);
				i--;
			}
		}
	}

	private int cpt=0;
	public String  printProgress(double indice,double borne){
		String[] icone={"\\","|","/","-"};
		String resulte=icone[cpt]+" "+100*indice/borne+"%";
		cpt=(cpt+1)%icone.length;
		return "\r"+resulte;
	}

}
