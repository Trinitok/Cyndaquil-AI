package VoiceTest;

import java.beans.PropertyVetoException;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class JSAPITest {
	SynthesizerModeDesc desc;
	Synthesizer synthesizer;
	Voice voice;

public void init(String voiceName) throws EngineException, AudioException, EngineStateError, PropertyVetoException    
{     
	 if (desc == null) {
//		 System.setProperty("freetts.voices", "Page on freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		 desc = new SynthesizerModeDesc();
		 Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral"); 
		 synthesizer = Central.createSynthesizer(desc);
		 synthesizer.allocate();
		 synthesizer.resume();
		 SynthesizerModeDesc smd = (SynthesizerModeDesc)synthesizer.getEngineModeDesc();
		 Voice[] voices = smd.getVoices();
		 Voice voice = null;
		 for(int i = 0; i < voices.length; i++) {
			 System.out.println(voices[i].getName());
			 if(voices[i].getName().equals(voiceName)) {
				 voice = voices[i];
//				 break;
			}       
		}
		 voice.setGender(Voice.GENDER_FEMALE);
		 voice.setAge(Voice.AGE_CHILD);
//		 synthesizer.getSynthesizerProperties().setVoice(voice);     
	}        
}
	 

	public void terminate() throws EngineException, EngineStateError {
		synthesizer.deallocate();
	}

	public void doSpeak(String speakText)
			throws EngineException, AudioException, IllegalArgumentException, InterruptedException {
		
		synthesizer.speakPlainText(speakText, null);
		synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
	}

	public static void main (String[]args) throws Exception{
		JSAPITest su = new JSAPITest();
		su.init("kevin16");     
		// high quality     
		su.doSpeak("Hello world from Real's How To");     
		su.terminate();
	}
}