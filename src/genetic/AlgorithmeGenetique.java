import java.util.Random;
import java.util.Arrays;
public class AlgorithmeGenetique{

	private static final int TAILLE_POPULATION = 10;
	private static final int NOMBRE_GENERATIONS = 100;
	private static final int NOMBRE_MEILLEURS = 1;
	private static final double BRUIT = 0.5;

	private int a, b;

	private Individu[] population;

	public AlgorithmeGenetique() {
		// Génération de la population initiale
		this.population = new Individu[TAILLE_POPULATION];
		Random random = new Random();
		for(int i = 0; i < TAILLE_POPULATION; ++i){
			int a = random.nextInt() % 100;
			int b = random.nextInt() % 100;
			int c = random.nextInt() % 10;
			population[i] = (new Individu(a, b, c));
		}
	}

	private void calculerGeneration(){
		calculFitness();
		selectionEtReproduction();
	}

	public Individu[] calculer(){
		for(int i = 0; i < NOMBRE_GENERATIONS; ++i){
			System.out.println("Generation : " + i);
			calculerGeneration();
			for(int j = 0; j < TAILLE_POPULATION; ++j){
				System.out.println(population[j]);
			}

		}
		return population;
	}

	private void calculFitness(){
		for(Individu individu : population){
			individu.calculFitness();
		}
	}

	private void selectionEtReproduction(){
		Arrays.sort(population);
		for(int i = NOMBRE_MEILLEURS; i < population.length; ++i){
			population[i] = reproduction();
		}

	}

	private Individu reproduction(){
		Random random = new Random();
		/*int indexA = random.nextInt(NOMBRE_MEILLEURS);
		int indexB = random.nextInt(NOMBRE_MEILLEURS);
		System.out.println("indexA : " + indexA +", indexB : " + indexB);
		Individu parentA = population[indexA];
		Individu parentB = population[indexB];
		Individu enfant;
		double moyenneA = (parentA.getA() + parentB.getA()) / 2;
		double moyenneB = (parentA.getB() + parentB.getB()) / 2;
		double moyenneC = (parentA.getC() + parentB.getC()) / 2;

		moyenneA = bruiter(moyenneA);
		moyenneB = bruiter(moyenneB);
		moyenneC = bruiter(moyenneC);
		*/
		return new Individu(random.nextInt() % 100, random.nextInt() % 100 , 1);
	}

	private double bruiter(double valeur){
		double tirage = Math.random();
		tirage = (tirage - 0.5) * 2;
		return valeur + BRUIT * tirage * valeur;
	}



}
