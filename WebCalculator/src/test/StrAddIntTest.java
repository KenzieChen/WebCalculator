package test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class StrAddIntTest {
	public static void main(String[] args) {
		int i=0;
		double X=3;
		double Y=3;

		
		ScriptEngineManager manager = new ScriptEngineManager ();
		ScriptEngine engine = manager.getEngineByName ("js");
		try {
			engine.put("X"+(i+1), X);
			engine.put("Y1", Y);
			double l=(Double) engine.eval ("X1-Y1");
			System.out.println(l);
		} catch (ScriptException e) {
			e.printStackTrace();     
		}
	}
}
