package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalString {
	public static void main(String[] args) {
		double X =1;
		double Y= -2;
		double l = 0;
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		try {
			engine.put("X1", X);
			engine.put("Y1", Y);
			l=(Double) engine.eval ("X1-Y1");
		} catch (ScriptException e) {
			e.printStackTrace();     
		}
		
		System.out.println(l);
	}
}
