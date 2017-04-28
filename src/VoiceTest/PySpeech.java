package VoiceTest;

import edu.cmu.sphinx.jsapi.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PySpeech {
	public static void main(String[] args) throws IOException{
		Runtime.getRuntime().exec("C:\\Python27\\python.exe voice.py");
		List<String> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get("testfile.txt"))) {
			// br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());

			// parse the expected response with that of what's in the
//			System.out.println(list.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
