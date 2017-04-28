package JythonTest;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

/**
 * This class is meant for testing with Jython, a python interpreter using a
 * Java wrapper in order to call python functions. Will be used for getting
 * voice input using the python voice testing from earlier work
 * 
 * @author Max Kelly
 *
 */
public class JythonDemo {

	PythonInterpreter interpreter = null;

	public JythonDemo()  
	   {  
	      PythonInterpreter.initialize(System.getProperties(),  
	                                   System.getProperties(), new String[0]);  

	      this.interpreter = new PythonInterpreter();  
	      interpreter.exec("import sys"
	      				+ "\nsys.path.append('pathToModiles if they're not there by default')"
	      				+ "\nimport speech_recognition as sr");
	   }

	void execfile(final String fileName) {
		this.interpreter.execfile(fileName);
	}

	PyInstance createClass(final String className, final String opts) {
		return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");
	}

	public static void main(String args[]) {
		JythonDemo ie = new JythonDemo();
		
		ie.execfile("C:\\EclipseWorkspaces\\Personal Fun\\PythonSpeech\\speechTesting\\background_listening.py");

//		PyInstance hello = ie.createClass("Hello", "None");
//
//		hello.invoke("run");
	}

}
