package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ConstraintTest {
	public static void main(String[]args){
		String constraint = "X-2*Y>=4;2*X-Y<=4";
		String[] cst =constraint.split(";");

		System.out.println(cst[0].indexOf("<="));
		if(cst[0].indexOf("<=")!=0){
			System.out.println("������0");
		}if(cst[0].indexOf("<=")==-1){
			System.out.println("����-1");
		}
		int length=cst.length;
	//	System.out.println("Լ��������"+length);
	/*	
		for(int i=0;i<length;i++){
			System.out.println(cst[i]);
		}
		*/
		double X=1;
		double Y=2;
		boolean flag =true;
	//	System.out.println(flag);
		
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		try {
			engine.put("X", X);
			engine.put("Y", Y);
			flag = (Boolean)engine.eval (cst[0]);
			if(flag==false){
				String[] div =cst[0].split(">=");
				int l = div.length;
				System.out.println("һ��Լ����������δ����"+l);
			/*
				for(int i=0;i<l;i++){
					System.out.println(div[i]);
					
				}
				*/

				String sbract = "Math.abs(("+div[0]+")-"+div[1]+")";
			//	System.out.println("��ֵ��"+sbract);
				double result=(double) engine.eval(sbract);
				System.out.println("��ֵP��"+result);
			}
	//		System.out.println(flag);
		} catch (ScriptException e) {
			e.printStackTrace();     //������빫ʽ��ʽ����ȷ�ᱨ�� ���繫ʽ-2*X+11*Y
		}
		
	}
		
		
}
