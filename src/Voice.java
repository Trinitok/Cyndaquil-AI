import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextArea;

public class Voice {
	
	public static void say(String phrase) throws IOException{
		String command = "espeak -ven-us+f5 -p70 -a50 -s180 -k20 -g0.5 \"" + phrase + "\"";    // Concat the phrase to the command
		
		System.out.println(phrase);
		Runtime.getRuntime().exec(command);
	}
	
	public static void hear(JTextArea input) throws IOException{
		Runtime.getRuntime().exec("C:\\Python27\\python.exe voice.py");
		List<String> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get("testfile.txt"))) {
			// br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());

			// parse the expected response with that of what's in the
			input.setText(list.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
