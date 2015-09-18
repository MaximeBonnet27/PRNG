public class TestGenetique{

	public static void main(String ... args){

		AlgorithmeGenetique algorithme = new AlgorithmeGenetique();
		Individu[] resultat = algorithme.calculer();
		for(int i = 0; i < resultat.length; ++i){
			System.out.println(resultat[i].getFitness());
		}
		System.out.println(resultat[0]);
	}
}
