package main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Constraint {
	private String constraint="";
//	private String[] cst=constraint.split(";");   将无法接收传进来的constraint字符串
	private String[] cst;
	private String[] div;
	private String right;
	private String left;
	private String subract;
	private double[] X;
	private double[] Y;
	private double allPub=0;
	private double pub=0;
	private double M=1000000000;
	private boolean flag = true;
	
	
	public String getConstraint() {
		return constraint;
	}


	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public double[] getX() {
		return X;
	}


	public void setX(double[] x) {
		X = x;
	}

	public double[] getY() {
		return Y;
	}


	public void setY(double[] y) {
		Y = y;
	}


	public double getAllPub() {
		return allPub;
	}


	public void setAllPub(double allPub) {
		this.allPub = allPub;
	}


	public void calP(){
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		int Xlength = X.length;
		int Ylength = Y.length;
		
		try {
			for(int i=0;i<Xlength;i++){
				engine.put("X"+(i+1), X[i]);
			}
			
			for(int i=0;i<Ylength;i++){
				engine.put("Y"+(i+1), Y[i]);
			}
			
			cst =constraint.split(";");
			
			for(int i=0;i<cst.length;i++){
				if(cst[i].indexOf("<=")!=-1){
//					System.out.println("具有<=的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split("<=");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
//						 System.out.println("具有<=的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf(">=")!=-1){
//					System.out.println("具有>=的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split(">=");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
//						 System.out.println("具有>=的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf(">")!=-1){
//					System.out.println("具有>的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split(">");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
//						 System.out.println("具有>的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf("<")!=-1){
//					System.out.println("具有<的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split("<");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
//						 System.out.println("具有<的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf("=")!=-1){
					cst[i]=cst[i].replace("=", "==");
//					System.out.println("具有=的符号");
					
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split("==");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
//						 System.out.println("具有=的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}
			}
			
		} catch (ScriptException e) {
			e.printStackTrace(); 
		}
		
		allPub*=M;
	}
	
}
