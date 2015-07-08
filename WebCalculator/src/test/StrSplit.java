package test;

public class StrSplit {
	public static void main(String[]args){
		String constraint = "X-2*Y>=4";
		String[] cst =constraint.split(";");
		System.out.println(cst.length);
		System.out.println(cst[0]);
	}
	
	
}
