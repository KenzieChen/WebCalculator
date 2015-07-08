package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalArray {
	public static void main(String[] args) {
		double[] Xs= new double[]{1.1, 2.2};
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		try {
			for(int i=0;i<2;i++ ){
				engine.put("X"+i, Xs[i]);
			}

			
			double all=(Double) engine.eval ("X0+X1");
			System.out.println(all);
		} catch (ScriptException e) {
			e.printStackTrace();     
		}


		
	}
	
	
}
