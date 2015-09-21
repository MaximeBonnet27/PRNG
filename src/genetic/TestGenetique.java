package genetic;

import java.util.ArrayList;

public class TestGenetique{

	public static void main(String ... args){

		AlgorithmeGenetique algorithme = new AlgorithmeGenetique();
		ArrayList<Individu> resultat = algorithme.calculer();
		System.out.println();
		for(int i = 0; i < resultat.size(); ++i){
			System.out.println(resultat.get(i));
		}
	}
}
