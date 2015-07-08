package test;

import java.util.Arrays;

public class Send {
	static double[] s = new double[]{1,2};
	
	public static void main(String[] args) {
		Receive oR=new Receive();
		oR.setR(s);
		double[] r=oR.r;
		System.out.println(Arrays.toString(r));
		
	}
}
