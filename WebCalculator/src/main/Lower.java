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

public class Lower {
	private  double[] X,bestY;                       //�õ���X�Լ���������õ�Y
	private String lEquation;                      //�²㺯��
	private String cons;                           //Լ��
	private double bestlFitness;

//	private static int allNub;
//	private static int pubNub;
	
	private double toUpPub;
	
	
	public double getBestlFitness() {
		return bestlFitness;
	}

	public void setBestlFitness(double bestlFitness) {
		this.bestlFitness = bestlFitness;
	}

	public double getToUpPub() {
		return toUpPub;
	}

	public void setToUpPub(double toUpPub) {
		this.toUpPub = toUpPub;
	}

	public Lower(){
		//allNub=0;
		//pubNub=0;
		toUpPub=0;
	}
	/*	
	public static int getAllNub() {
		return allNub;
	}

	public static void setAllNub(int allNub) {
		Lower.allNub = allNub;
	}

	public static int getPubNub() {
		return pubNub;
	}

	public static void setPubNub(int pubNub) {
		Lower.pubNub = pubNub;
	}*/

	public double[] getX() {
		return X;
	}

	public void setX(double[] x) {
		X = x;
	}

	public double[] getBestY() {
		return bestY;
	}

	public void setBestY(double[] bestY) {
		this.bestY = bestY;
	}

	public String getlEquation() {
		return lEquation;
	}

	public void setlEquation(String lEquation) {
		this.lEquation = lEquation;
	}

	public String getCons() {
		return cons;
	}

	public void setCons(String cons) {
		this.cons = cons;
	}

	public void LCal(){
/*		System.out.println("�����²��X:"+Arrays.toString(X));
		System.out.println("�����²�Ĺ�ʽ:"+lEquation);
		System.out.println("�����²��Լ��:"+cons);*/
		final int PopSize = 50;
		final int MaxGeneration = 30;
		
		Factory<Genotype<Float64Gene>> gtf=Genotype.valueOf(
				new Float64Chromosome(0,1.0,1),
				new Float64Chromosome(0,1.0,1),
				new Float64Chromosome(0,1.0,1)
		);
		
		LFunction.setX(X);
		LFunction.setlEquation(lEquation);
		LFunction.setStrCons(cons);
		Function<Genotype<Float64Gene>,Double> ff=new LFunction();
		
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

	/*	for(int i=1;i<=MaxGeneration;i++){
			ga.evolve(1);
			int length= ga.getBestPhenotype().getGenotype().length();
			bestY = new double[length];
			
			System.out.println("��XΪ"+Arrays.toString(X));
			for(int j=0;j<length;j++){
				bestY[j]=ga.getBestPhenotype().getGenotype().getChromosome(j).getGene().doubleValue();
			}
			System.out.println("��"+i+"����");
			System.out.println("�õ�������ֵYΪ"+Arrays.toString(bestY));
			System.out.println("");
		}*/
		
		ga.evolve(MaxGeneration);
		int length= ga.getBestPhenotype().getGenotype().length();
		bestY = new double[length];
		for(int i=0;i<length;i++){
			bestY[i]=ga.getBestPhenotype().getGenotype().getChromosome(i).getGene().doubleValue();
		}
		bestlFitness=ga.getBestPhenotype().getFitness();
		
		toUpPub = ga.getBestPhenotype().getGenotype().punish;
		//System.out.println("����²����Ÿ���ĳͷ�ֵ"+toUpPub);
	//	System.out.println("���յ�����ֵYΪ"+bestY);
	}
	
	private static  class LFunction 
		implements Function<Genotype<Float64Gene>,Double>{
		
			private static String lEquation;  //��Ҫ�����ⲿ��ֵ��static
			private static String strCons;
			private static double[] X;          //X��Ҫ�����ⲿ��ֵ��static
			private double[] Y;
			private double allPub;

			private double lFitness;

			public double[] getY() {
				return Y;
			}

			public void setY(double[] y) {
				Y = y;
			}

			public static String getlEquation(){
				return lEquation;
			}

			public static void setlEquation(String lEquation) {
				LFunction.lEquation = lEquation;
			}


			public static double[] getX() {
				return X;
			}

			public static void setX(double[] x) {
				X = x;
			}

			public static String getStrCons(){
				return strCons;
			}

			public static void setStrCons(String strCons) {
				LFunction.strCons = strCons;
			}

			@Override
			public  Double apply(Genotype<Float64Gene> genotype){
				int Ylength = genotype.length();
				Y = new double[Ylength];
				for(int i=0;i<Ylength;i++){
					Y[i]=genotype.getChromosome(i).getGene().doubleValue();
				}
				int Xlength=X.length;
				
				Constraint cons = new Constraint();
				cons.setX(X);
				cons.setY(Y);
				cons.setConstraint(strCons);
				cons.calP();
				allPub = cons.getAllPub();
				genotype.punish=allPub;
/*				if(allPub>0){
					pubNub++;
				}
				
				++allNub;*/
				
				ScriptEngineManager manager = new ScriptEngineManager ();
				ScriptEngine engine = manager.getEngineByName ("js");
				try {
					
						for(int i=0;i<Xlength;i++){
							engine.put("X"+(i+1), X[i]);
						}
						
						for(int i=0;i<Ylength;i++){
							engine.put("Y"+(i+1), Y[i]);
						}
					lFitness=(Double) engine.eval (lEquation);
				} catch (ScriptException e) {
					e.printStackTrace();     
				}
				
				lFitness-=allPub;

/*				System.out.print("X:"+Arrays.toString(X));
				System.out.print("����Y:"+Arrays.toString(Y));
				System.out.print("����lFitness:"+lFitness);
				System.out.println("����AllPub:"+allPub);*/
				return lFitness;
				
/*				
				double P=0;
				double M=100000;
				Y=genotype.getChromosome().getGene().doubleValue();
				
				if(X-2*Y>4){
					P+=Math.abs((X-2*Y)-4);
				}
				
				if(2*X-Y>24){
					P+=Math.abs((2*X-Y)-24);
				}
				
				if(3*X+4*Y>96){
					P+=Math.abs((3*X+4*Y)-96);
				}
				
				if(X+7*Y>126){
					P+=Math.abs((X+7*Y)-126);
				}
				
				if(-4*X+5*Y>65){
					P+=Math.abs((-4*X+5*Y)-65);
				}
				
				if(X+4*Y<8){
					P+=Math.abs((X+4*Y)-8);
				}

				ScriptEngineManager manager = new ScriptEngineManager ();
				ScriptEngine engine = manager.getEngineByName ("js");
				try {
					engine.put("X", X);
					engine.put("Y", Y);
					lFitness=(Double) engine.eval (lEquation);
				} catch (ScriptException e) {
					e.printStackTrace();     //������빫ʽ��ʽ����ȷ�ᱨ�� ���繫ʽ-2*X+11*Y
				}
			//	System.out.println("��ǰ��X"+X+"--��ǰ��Y"+Y);
			//	System.out.print("ԭ����ֵ��"+lFitness+"---");
			//	System.out.print("��ֵ��"+M*P+"---");
				
				lFitness-=M*P;
				
			//	System.out.println("��Ӧ��ֵ��"+M*P);
				return lFitness;
				*/
				
			}
			
	}
}
