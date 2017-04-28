import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class controls the chatbot memory. There are several files associated
 * with it that will be loaded into the program via hashmaps. The hashmaps will
 * 
 * @author Max Kelly
 *
 */
public class ChatbotMemory {

	private static HashMap<String, List<String>> responseMap;
	private static HashMap<String, List<String>> personMap;
	private static HashMap<String, List<String>> placeMap;
	private static HashMap<String, List<String>> thingMap;

	/**
	 * the chatbot currently has knowledge of all previous responses and nouns.
	 * It expects the fil locations to be sent to it
	 * 
	 * @param responsesLocation
	 *            - the file location of user input and the expected responses
	 * @param personLocation
	 *            - the people nouns stored in a file for memory and easy
	 *            modification
	 * @param placeLocation
	 *            - the file of place nouns stored in a file for memory and easy
	 *            modification
	 * @param thingLocation
	 *            - the file of things that will have both people, places and
	 *            extraneous nouns
	 * @throws FileNotFoundException
	 *             - if the location of the files does not exist or is misplaced
	 *             it will throw an error. As if you lost memory!
	 */
	public ChatbotMemory(File responsesLocation, File personLocation, File placeLocation, File thingLocation)
			throws FileNotFoundException {

		responseMap = instantiateHMap(responsesLocation);
		personMap = instantiateHMap(personLocation);
		placeMap = instantiateHMap(placeLocation);
		thingMap = instantiateHMap(thingLocation);

	}

	/**
	 * Will instantiate the hashmaps used for the various responses
	 * 
	 * @param fileLocation
	 *            - the file that will be used for the hashmap
	 * @return - the hashmap of the file contents
	 */
	private HashMap<String, List<String>> instantiateHMap(File fileLocation) {
		List<String> list = new ArrayList<>();
		HashMap<String, List<String>> tempMap = new HashMap<String, List<String>>();

		// fill the hashmap of the expected responses
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileLocation.getPath()))) {
			// br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());

			// parse the expected response with that of what's in the
			for (int i = 0; i < list.size(); i += 2) {
				tempMap.put(list.get(i), Arrays.asList(list.get(i).split(";")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempMap;
	}

	/**
	 * Takes in the user input and compares it to the responses in the file for
	 * responses
	 * 
	 * @param input
	 *            - the user input
	 * @return the chatbot response
	 */
	public static String analyzeResponses(String input) {
		if(responseMap.get(input.toLowerCase())== null){
			return null;
		}
		int responselen = responseMap.get(input.toLowerCase()).size();

		return responseMap.get(input.toLowerCase()).get((int) (Math.random() * responselen - 1));
	}

	/**
	 * Takes in the user input and compares it to the responses in the file for
	 * people
	 * 
	 * @param input
	 *            - the user input
	 * @return the chatbot response
	 */
	public static String analyzePeople(String input) {

		if(personMap.get(input.toLowerCase()) == null) return analyzeThings(input);
		int responselen = personMap.get(input.toLowerCase()).size();

		if (personMap.get(input.toLowerCase()).get((int) (Math.random() * responselen - 1)) != null)
			return personMap.get(input.toLowerCase()).get((int) (Math.random() * responselen - 1));
		return analyzeThings(input);
	}

	/**
	 * Takes in the user input and compares it to the responses in the file for
	 * places
	 * 
	 * @param input
	 *            - the user input
	 * @return the chatbot response
	 */
	public static String analyzePlaces(String input) {

		if(placeMap.get(input.toLowerCase()) == null) return analyzeThings(input);
		
		int responselen = placeMap.get(input.toLowerCase()).size();

		if (placeMap.get(input.toLowerCase()).get((int) (Math.random() * responselen - 1)) != null)
			return placeMap.get(input.toLowerCase()).get((int) (Math.random() * responselen - 1));
		return analyzeThings(input);
	}

	/**
	 * Takes in the user input and compares it to the responses in the file for
	 * things
	 * 
	 * @param input
	 *            - the user input
	 * @return the chatbot response
	 */
	public static String analyzeThings(String input) {

		if(thingMap.get(input.toLowerCase())== null){
			return null;
		}
		
		int responselen = thingMap.get(input.toLowerCase()).size();

		return thingMap.get(input.toLowerCase()).get((int) (Math.random() * responselen - 1));

	}

}
