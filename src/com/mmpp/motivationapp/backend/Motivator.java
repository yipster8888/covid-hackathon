package com.mmpp.motivationapp.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the user Motivator that is displayed to the right of the task window
 * @author Tyler Yip
 *
 */
public class Motivator {

	/**
	 * This array list stores all the messages generated from the text file
	 */
	private ArrayList<Message> MessageBox;
	
	/**
	 * This array list stores all of the tasks from yesterday, both completed or not
	 */
	private TaskList yesterdaysTasks;

	private int completedYesterday;

	/**
	 * Constructor for class Motivator
	 */
	public Motivator() {
		MessageBox = new ArrayList<Message>();
		completedYesterday = 0;
		yesterdaysTasks = null;
	}
	
	/**
	 * Creates a new message and adds it to the message box
	 * @param s the contents of the new message
	 */
	public void addMessage(String s) {
		Message newMessage = new Message(s);
		MessageBox.add(newMessage);
	}
	
	/**
	 * Picks a random message from the message box
	 * @return a randomly selected message
	 */
	public Message getRandomMessage() {
		if(MessageBox.size() == 0) {
			System.out.println("No messages to display");
			return null;
		}
		//Get a random index within the total messages
		Random r = new Random();
		int max = MessageBox.size();
		int index = r.nextInt(max);
		
		return MessageBox.get(index);
	}
	
	/**
	 * Loads preset messages from a text file to the message box
	 * @param filename the name of the text file
	 */
	public void importMessagesFromFile(String filename) {
		File file = new File(filename);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			while(in.ready()) {
				String s = in.readLine();
				addMessage(s);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find the file with the specified filename");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("I/O error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the message box ArrayList
	 * @return the message box in ArrayList
	 */
	public ArrayList<Message> getMessageBox() {
		return MessageBox;
	}

	/**
	 * Sets the message box ArrayList
	 * @param messageBox the new message box ArrayList
	 */
	public void setMessageBox(ArrayList<Message> messageBox) {
		MessageBox = messageBox;
	}
	
	/**
	 * Gets YesterdaysTasks
	 * @return YesterdaysTasks
	 */
	public TaskList getYesterdaysTasks() {
		return yesterdaysTasks;
	}

	/**
	 * Sets yesterdaysTasks 
	 * @param yesterdaysTasks the new TaskList
	 */
	public void setYesterdaysTasks(TaskList yesterdaysTasks) {
		this.yesterdaysTasks = yesterdaysTasks;
	}
	
	/**
	 * This was used to test the random message display for the motivator 
	 * @param args command line arguments, not used
	 */
	public static void main(String [] args) {
		Motivator m = new Motivator();
		m.importMessagesFromFile("MessageBank.txt");
		System.out.println(m.getRandomMessage());
	}
	
}
