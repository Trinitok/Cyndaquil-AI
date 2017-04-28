package StanfordNLPTest;

import java.io.*;
import java.util.*;

import org.python.core.PyDictionary;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

/**
 * 	This file is for testing with Stanford Core NLP.  This will be used later in conjunction with the chatbot memory to break down a sentence and allow the chatbot to store more things in memory or load responses based on user input
 * @author Max Kelly
 *
 */
public class StanfordCoreNlpDemo {

	public static void main(String[] args) throws IOException {
		PrintWriter out;
		Scanner scanner = new Scanner(System.in);
		if (args.length > 1) {
			out = new PrintWriter(args[1]);
		} else {
			out = new PrintWriter(System.out);
		}

		
			PrintWriter xmlOut = null;
			if (args.length > 2) {
				 xmlOut = new PrintWriter(args[2]);
			}

			StanfordCoreNLP pipeline = new StanfordCoreNLP();
			Annotation annotation;
		while (!scanner.nextLine().equals("")) {
			if (args.length > 0) {
				annotation = new Annotation(IOUtils.slurpFileNoExceptions(args[0]));
			} else {
			
				System.out.println("input the next line\n");
				String line = scanner.nextLine();
				annotation = new Annotation(line);
			}

			pipeline.annotate(annotation);
			pipeline.prettyPrint(annotation, out);
			if (xmlOut != null) {
				 pipeline.xmlPrint(annotation, xmlOut);
			}
			// An Annotation is a Map and you can get and use the various
			// analyses individually.
			// For instance, this gets the parse tree of the first sentence in
			// the text.
			List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
			
			if (sentences != null && sentences.size() > 0) {
				CoreMap sentence = sentences.get(0);
				Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

//				 out.println();
//
//				 out.println("The first sentence parsed is:\n\n\n");
//				tree.pennPrint(out);
			}
		}
		scanner.close();
		out.close();
//		xmlOut.close();
	}

}