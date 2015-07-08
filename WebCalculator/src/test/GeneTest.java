package test;

import java.util.Arrays;

import org.jenetics.Float64Chromosome;
import org.jenetics.Float64Gene;
import org.jenetics.GeneticAlgorithm;
import org.jenetics.Genotype;
import org.jenetics.Mutator;
import org.jenetics.Optimize;
import org.jenetics.RouletteWheelSelector;
import org.jenetics.SinglePointCrossover;
import org.jenetics.util.Factory;
import org.jenetics.util.Function;


public class GeneTest {
	public static void main(String[] args) {
		org.jenetics.util.Concurrency.setConcurrency(0);
		Factory<Genotype<Float64Gene>> gtf = Genotype.valueOf(
					new Float64Chromosome(0,50,1),
					new Float64Chromosome(0,50,1)
				);  
		Function<Genotype<Float64Gene>, Double> ff = new testFunction();
		GeneticAlgorithm<Float64Gene,Double> ga = 
				 new GeneticAlgorithm<Float64Gene,Double>(gtf, ff, Optimize.MAXIMUM);
		ga.setPopulationSize(10);
		
		ga.setSelectors(
				new RouletteWheelSelector<Float64Gene,Double>()
		);
		ga.setAlterers(
	               new Mutator<Float64Gene>(0.05),
			       new SinglePointCrossover<Float64Gene>(0.5)
		);
		ga.setup();
		ga.evolve(10);
		double fit =ga.getBestPhenotype().getFitness();
		System.out.println("最佳适应值："+fit);
	}
	
	private static class testFunction
	implements Function<Genotype<Float64Gene>,Double>{
	
		public Double apply(Genotype<Float64Gene> genotype){
			int length=genotype.length();

			double X=0;
			Double[] genes =new Double[length];
			System.out.println("由染色体组成的数组:");
			for(int i=0;i<length;i++){
				genes[i]=genotype.getChromosome(i).getGene().doubleValue();
				System.out.println(genes[i]);
				X+=genes[i];
			}
			System.out.println(Arrays.toString(genes));
			
			return X;
		}
		
		
	
}



		
}

