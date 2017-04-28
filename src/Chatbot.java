import java.awt.Color;
import java.awt.FocusTraversalPolicy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This is the main chatbot that calls the other classes and makes use of the various responses.  Hopefully everything will work...eventually
 * @author Max Kelly
 *
 */
public class Chatbot extends JFrame implements KeyListener {
	
	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(20, 50);
	JTextArea input = new JTextArea(1, 50);
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	File responsesLocation = new File("responses.txt");
	File personLocation = new File("people.txt");
	File placeLocation= new File("places.txt");
	File thingLocation = new File("things.txt");
	
	public Chatbot() throws IOException{
		super("Cyndaquil");
		new ChatbotMemory(responsesLocation, personLocation, placeLocation, thingLocation);
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
		input.requestFocus();
		
		p.add(scroll);
		p.add(input);
		p.setBackground(Color.CYAN);
		
		this.add(p);
		
		this.setVisible(true);
		Voice.hear(input);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(false);
			
			//  grab input
			String in = input.getText();
			input.setText("");
			addText("Me: " + in + "\n");
			in.trim();
			while(in.charAt(in.length() - 1) == '!' ||
					in.charAt(in.length() - 1) == '?' ||
					in.charAt(in.length() - 1) == '.' ){
				in = in.substring(0, in.length() - 1);
				
			}
			
			in.trim();
			//  check for matches
			String response = getResponse(in);
			addText("Cyndaquil: " + response);
			try {
				Voice.say(response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 	
	 * @param in
	 * @return
	 */
	private String getResponse(String in) {
		// TODO Auto-generated method stub
		String response;
		if(in.startsWith("who is") || in.startsWith("do you know  who")) response = ChatbotMemory.analyzePeople(in);
		else if(in.startsWith("where is")) response = ChatbotMemory.analyzePlaces(in);
		else if(in.startsWith("what is") || in.startsWith("what's")) response = ChatbotMemory.analyzeThings(in);
		else response = ChatbotMemory.analyzeResponses(in);
		
		// if nothing was found
		if(response == null){
//			addText("I need to analyze this.");
			return "I need to analyze this";
		}
		
		return response;
	}


	public boolean inArray(String in, String[] str){
		boolean match = false;
		for(int i = 0; i < str.length; i ++){
			if(str[i].equals(in)){
				match = true;
			}
		}
		
		return match;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void addText(String text){
		dialog.setText(dialog.getText()+text +"\n");
	}

}
