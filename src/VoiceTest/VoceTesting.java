package VoiceTest;

import voce.SpeechRecognizer;

public class VoceTesting {

	public static void main(String[] args) {
		voce.SpeechInterface.init("D:\\Voice Recognition Programs\\voce\\voce-0.9.1\\lib", false, true,
				"D:\\Voice Recognition Programs\\voce\\voce-0.9.1\\samples\\recognitionTest\\java\\grammar", "digits");
		// voce.SpeechInterface.synthesize("Hello World");

		// SpeechRecognizer recog = new SpeechRecognizer("D:\\Voice Recognition
		// Programs\\voce\\voce-0.9.1\\lib", "", "");
		boolean quit = false;
		while (!quit) {
			// Normally, applications would do application-specific things
			// here. For this sample, we'll just sleep for a little bit.
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}

			while (voce.SpeechInterface.getRecognizerQueueSize() > 0) {
				String s = voce.SpeechInterface.popRecognizedString();

				// Check if the string contains 'quit'.
				if (-1 != s.indexOf("quit")) {
					quit = true;
				}

				System.out.println("You said: " + s);
				// voce.SpeechInterface.synthesize(s);
			}
		}

		voce.SpeechInterface.destroy();
		System.exit(0);
	}

}
