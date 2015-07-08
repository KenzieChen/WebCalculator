package main;

import java.util.Arrays;

public class UpTest {
	
	public static void main(String []args){
		
		org.jenetics.util.Concurrency.setConcurrency(0);
		double[] bestX;
		
		double[] bestY;
		Upper up = new Upper();
		
		
		
/*		String strCons="X1-2*Y1<=6;-X1-2*Y1<=(-10);2*X1-Y1<=21;X1+2*Y1<=38;-X1+2*Y1<=18";
		up.setuEquation("X1+3*Y1");
		up.setlEquation("X1-3*Y1");
		up.setUstrCons("X1-2*Y1<=6");*/
		
		String strCons="-Y1+Y2+Y3<=1;4*X1-2*Y1+4*Y2-Y3<=2;4*X2+4*Y1-2*Y2-Y3<=2";
		up.setuEquation("8*X1+4*X2-4*Y1+40*Y2+4*Y3");
		up.setlEquation("-2*Y1-Y2-2*Y3");
		up.setUstrCons("X1+2*X2-Y3<=1.3");
		
/*		String strCons="X1-2*Y1<=4;2*X1-Y1<=24;3*X1+4*Y1<=96;X1+7*Y1<=126;-4*X1+5*Y1<=65;X1+4*Y1>=8";
		up.setuEquation("-2*X1+11*Y1");
		up.setlEquation("-X1-2*Y1");*/
		
		
/*		String strCons="-Y1+Y2+Y3<=1;2*X1-Y1+2*Y2-0.5*Y3<=1;2*X2+2*Y1-Y2-0.5*Y3<=1";
		up.setuEquation("-8*X1-4*X2+4*Y1-40*Y2-4*Y3");
		up.setlEquation("X1+2*X2+Y1+Y2+2*Y3");
		*/
	    up.setStrCons(strCons);
	    up.UCal();
	    bestX=up.getBestX();
	    bestY=up.getBestY();
	    double uF=up.getuFitness();
	    double lF=up.getlFitness();
	    System.out.println("X1的最佳值："+Arrays.toString(bestX));
	    System.out.println("Y1的最佳值："+Arrays.toString(bestY));
	    System.out.println("上层最佳函数值："+uF);
	    System.out.println("下层最佳函数："+lF);
	}
     

}
