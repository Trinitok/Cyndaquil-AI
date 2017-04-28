import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 	The main program runner.  Should just need to run via the command line or by compiling via your IDE.  If you choose to compile without downloading the JAR, be sure to include all dependencies.
 * @author Max Kelly
 *
 */
public class Main {
	
	/**
	 * 	The main method of the program.  Will create the chatbot and load all responses.
	 * @param args - the list of arguments for the program
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		
		//  execute python vtt and tts
		
		
		new Chatbot();
		
		
	}

}
