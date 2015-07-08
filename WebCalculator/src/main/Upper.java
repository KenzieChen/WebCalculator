package main;

import java.util.Arrays;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


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

public class Upper {
	private double[] bestX;
	private double[] bestY;	
	private double uFitness;
	private double lFitness;
	private String uEquation;		//上层等式
	private String lEquation;		//下层等式
	private String strCons;         //下层约束
	private String ustrCons;

	public String getUstrCons() {
		return ustrCons;
	}

	public void setUstrCons(String ustrCons) {
		this.ustrCons = ustrCons;
	}

	public double getlFitness() {
		return lFitness;
	}

	public void setlFitness(double lFitness) {
		this.lFitness = lFitness;
	}

	public double[] getBestX() {
		return bestX;
	}

	public void setBestX(double[] bestX) {
		this.bestX = bestX;
	}

	public double[] getBestY() {
		return bestY;
	}

	public void setBestY(double[] bestY) {
		this.bestY = bestY;
	}

	public String getuEquation() {
		return uEquation;
	}

	public void setuEquation(String uEquation) {
		this.uEquation = uEquation;
	}

	public String getlEquation() {
		return lEquation;
	}

	public void setlEquation(String lEquation) {
		this.lEquation = lEquation;
	}

	public String getStrCons() {
		return strCons;
	}

	public void setStrCons(String strCons) {
		this.strCons = strCons;
	}
	
	public double getuFitness() {
		return uFitness;
	}

	public void setuFitness(double uFitness) {
		this.uFitness = uFitness;
	}

	public void UCal(){
		
		final int PopSize = 30;
		final int MaxGeneration = 20;
		
		Factory<Genotype<Float64Gene>> gtf=Genotype.valueOf(
				new Float64Chromosome(0,1.0,1),
				new Float64Chromosome(0,1.0,1)
		);
		

		UFunction.setuEquation(uEquation);
		UFunction.setlEquation(lEquation);
		UFunction.setStrCons(strCons);
		UFunction.setUstrCons(ustrCons);
		Function<Genotype<Float64Gene>,Double> ff=new UFunction();
		
		GeneticAlgorithm<Float64Gene,Double> ga = 
			 new GeneticAlgorithm<Float64Gene,Double>(gtf, ff, Optimize.MAXIMUM);
		
		ga.setPopulationSize(PopSize);
		
		ga.setSelectors(
				new RouletteWheelSelector<Float64Gene,Double>()
		);
		ga.setAlterers(
	               new Mutator<Float64Gene>(0.08),
			       new SinglePointCrossover<Float64Gene>(0.7)
		);
		ga.setup();
		for(int i=1;i<=MaxGeneration;i++){
			ga.evolve(1);
			int length = ga.getBestPhenotype().getGenotype().length();
			bestX =new double[length];
			for(int j = 0;j<length;j++){
				bestX[j]=ga.getBestPhenotype().getGenotype().getChromosome(j).getGene().doubleValue();
			}

			bestY=ga.getBestPhenotype().getGenotype().lowerSolution;
			/*System.out.print("第"+i+"代："+"--");
			System.out.print("得到的最优值X为"+Arrays.toString(bestX)+"--");
			System.out.println("得到的最优值Y为"+Arrays.toString(bestY));
			System.out.println("");*/
		}
		
		int length = ga.getBestPhenotype().getGenotype().length();
		for(int j = 0;j<length;j++){
			bestX[j]=ga.getBestPhenotype().getGenotype().getChromosome(j).getGene().doubleValue();
		}
		
		bestY=ga.getBestPhenotype().getGenotype().lowerSolution;
		uFitness=ga.getBestPhenotype().getFitness();
		lFitness =ga.getBestPhenotype().getGenotype().lowerfitness;
		System.out.println("最终的最优值X为"+Arrays.toString(bestX));
		System.out.println("最终的最优值Y为"+Arrays.toString(bestY));
	
	}
	


	private static class UFunction 
		implements Function<Genotype<Float64Gene>,Double>{
		
		private static String uEquation;
		private static String lEquation;
		private static String strCons;
		private static String ustrCons;

		private double[] X;
		private double[] Y;
		
		private double uFitness;
		
		
		public static String getUstrCons() {
			return ustrCons;
		}

		public static void setUstrCons(String ustrCons) {
			UFunction.ustrCons = ustrCons;
		}

		public static String getuEquation() {
			return uEquation;
		}

		public static void setuEquation(String uEquation) {
			UFunction.uEquation = uEquation;
		}

		public static String getlEquation() {
			return lEquation;
		}

		public static void setlEquation(String lEquation) {
			UFunction.lEquation = lEquation;
		}

		public static String getStrCons() {
			return strCons;
		}

		public static void setStrCons(String strCons) {
			UFunction.strCons = strCons;
		}
		@Override
		public Double apply(Genotype<Float64Gene> genotype){
			
			int Xlength=genotype.length();

			X=new double[Xlength];
			for(int i=0;i<Xlength;i++){
				X[i]=genotype.getChromosome(i).getGene().doubleValue();
			}
			
			Lower lower = new Lower();
			
			lower.setX(X);
			lower.setlEquation(lEquation);
			lower.setCons(strCons);
			lower.LCal();
			Y=lower.getBestY();
			genotype.lowerfitness=lower.getBestlFitness();
			genotype.lowerSolution=lower.getBestY();
			
			Constraint cons = new Constraint();
			cons.setX(X);
			cons.setY(Y);
			cons.setConstraint(ustrCons);
			cons.calP();
			double Pub = cons.getAllPub();
			
			System.out.print("X:"+Arrays.toString(X));
			System.out.print("--Y:"+Arrays.toString(Y));
			System.out.println("--Pub:"+Pub);
			
			int Ylength =Y.length;
			ScriptEngineManager manager = new ScriptEngineManager ();
			ScriptEngine engine = manager.getEngineByName ("js");
			try {
				for(int i=0;i<Xlength;i++){
					engine.put("X"+(i+1), X[i]);
				}
					
				for(int i=0;i<Ylength;i++){
					engine.put("Y"+(i+1), Y[i]);
				}
				
				uFitness=(Double) engine.eval (uEquation);
				
				} catch (ScriptException e) {
					e.printStackTrace();     //如果输入公式格式不正确会报错 例如公式-2*X+11*Y
				}
			
			

			System.out.print("X的值："+Arrays.toString(X));
			System.out.print("--Y的值："+Arrays.toString(Y));
			System.out.print("--uFitness的值："+uFitness);
			uFitness=uFitness-lower.getToUpPub()-Pub;
			System.out.println("--uFitness加罚值后的值："+uFitness);
			return uFitness;
		}
	}
	
}
