package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalTest {
	public static void main(String[] args) {
		double result=0;
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		engine.put("X1", 0.2);
		engine.put("X2", 0.8);
		engine.put("Y1", 0);
		engine.put("Y2", 0.2);
		engine.put("Y3", 0.8);
		try {
			
			result = (double)engine.eval("8*X1+4*X2-4*Y1+40*Y2+4*Y3");
		} catch (ScriptException e) {

			e.printStackTrace();
		}
		
		System.out.println(result);
	}
}
