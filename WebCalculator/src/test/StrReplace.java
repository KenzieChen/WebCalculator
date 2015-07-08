package test;

public class StrReplace {
	public static void main(String[]args){
		String constraint = "X-2*Y=4";
		constraint =constraint.replace("=", "==");
		System.out.println(constraint);

	}
}
