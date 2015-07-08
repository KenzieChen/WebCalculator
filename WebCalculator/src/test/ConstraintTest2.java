package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ConstraintTest2 {
	

	public static void main(String[]args){
		
		String constraint = "X-2*Y<=4;2*X-Y<=4;3*X+4*Y<=96;X+7*Y<=126;-4*X+5*Y<=65;X+4*Y>=8";
		String[] cst =constraint.split(";");
		System.out.println(cst[0]);
		System.out.println(cst[1]);
		String[] div;
		String right;
		String left;
		String subract;
		double X=0;
		double Y=100;
		double allPub=0;
		double pub=0;
		boolean flag = true;
		double M=100;
		
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		
		try {
			engine.put("X", X);
			engine.put("Y", Y);
			
			for(int i=0;i<cst.length;i++){
				if(cst[i].indexOf("<=")!=-1){
					System.out.println("具有<=的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split("<=");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
						 System.out.println("具有<=的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf(">=")!=-1){
					System.out.println("具有>=的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split(">=");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
						 System.out.println("具有>=的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf(">")!=-1){
					System.out.println("具有>的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split(">");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
						 System.out.println("具有>的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf("<")!=-1){
					System.out.println("具有<的符号");
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split("<");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
						 System.out.println("具有<的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}else if(cst[i].indexOf("=")!=-1){
					cst[i]=cst[i].replace("=", "==");
					System.out.println("具有=的符号");
					
					flag =(Boolean)engine.eval(cst[i]);
					if(flag==false){
						 div =cst[i].split("==");
						 left = div[0];
						 right = div[1];
						 subract ="Math.abs(("+left+")-"+right+")";
						 pub =(Double)engine.eval(subract);
						 allPub+=pub;
						 System.out.println("具有=的符号，并且罚值为："+pub);
						 flag=true;
					}		
				}
			}
			
		} catch (ScriptException e) {
			e.printStackTrace(); 
		}
		
		System.out.println("最终罚值为"+allPub);
		allPub*=M;
		System.out.println("最终加倍罚值为"+allPub);
	}
}

	
		
	
		

