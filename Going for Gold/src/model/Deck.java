package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;
import exception.ExceptionDouble;
import util.Serialisation;

/**
 * The class Deck contains a list of questions and manages these questions
 * by adding them, shuffling them and providing them to other classes.
 * 
 * @author Peccio Leandro
 *
 */

public class Deck {
	
	
	private static Deck deck ;

	/**
	 * A list of Questions
	 */
	private List<Question> questions;

	/**
	 * Constructor of Deck, instantiating the list of questions
	 */
	public Deck() {
		questions = new ArrayList<Question>();
		
	}
	
	
	/**
	 * This method return the question is not in the list and also it make a copy of this question before it put in the list. If the question is already in the list, it is not put in the list. 
	 * An exception is create if this question is in the list.
	 * @param q is a question.
	 * @return questions
	 */
	public boolean add(Question q) throws ExceptionDouble{
		if(!questions.contains(q)) 
		{
			return questions.add(q.clone());
		}else {
			throw new ExceptionDouble();
		}
		
	}
	
	/**
	 * @param q is a question
	 * @return the position of the question thanks to the indexOf method. If the question is not in the list, it returns -1.
	 */
	public int find(Question q) {
		return questions.indexOf(q);
	}
	
	/**
	 * @param q1 the question where we put the q2.
	 * @param q2 the question which it will put in q1.
	 * @return false if q1 or q2 don't exists. True if q2 was put in q1.
	 */
	public boolean update(Question q1, Question q2)
	{
		int index = questions.indexOf(q1);
		if(index == -1)
		{
			return false;
		}
		int index2 = questions.indexOf(q2);
		if(index2 != -1)
		{
			return false;
		}
		questions.set(index, q2.clone());
		return true;
	}
	/**
	 * @param q a question.
	 * @return if the question is in the list, she is deleted and the return is true else false.
	 */
	public boolean remove(Question q) {
		
		System.out.println("test");
		return questions.remove(q);
	}
	/**
	 * @return the list of question.
	 */
	public String toString() 
	{
		return "Deck [questions=" + questions + "]";
	}
	
	/**
	 * This method copies all the question that are in the list question and put them in another list and then give back this new list.
	 * @return a new list of question.
	 */
	public List<Question> getQuestions() {
		List<Question> result = new ArrayList<Question>();
		for(Question question : questions)
		{
			result.add(question.clone());
		}
		return result;
	}
	
	/**
	 * This method removes all the questions that are in the list so in java we use the clear method and then we put the list of question that we give in parameters.
	 * @param questions a list of question.
	 */
	public void setQuestions(List<Question> questions) 
	{
		this.questions.clear();
		for (Question question : questions) 
		{
			try {
				add(question);
			} catch (ExceptionDouble e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method uses a theme, so we want to find a question by is theme. This method looks in all the list to find the question if it find the question it returns a object question else it return null.
	 * @param theme
	 * @return a copy of the good question if it find the question else null.
	 */
	public Question getQuestion(String theme)
	{
		for (Question question : questions) 
		{
			if(question.getTheme().equals(theme))
			{
				return question.clone();
			}
		}
		return null;
	}
	
	/**
	 * @return the list questions.
	 */
	public List<Question> getListQuestion()
	{
		return questions;
	}
	
	/**
	 * This method is used to save the list of question in the json format.
	 * @param sf
	 */
	public void saveJson(File sf)
	{
		Gson gson = new Gson();
		String json = gson.toJson(this, Deck.class);
		Serialisation.writeString(json, sf);		
	}
	
	/**
	 * @param s
	 * @return the list of question that is saved in a json file.
	 */
	public static Deck fromJson(String s)
	{
		Gson gson = new Gson();
		return gson.fromJson(s, Deck.class);
	}
	
	public boolean removeAll(List<Question> list)
	{
		return questions.removeAll(list);
	}
	public boolean addAll(List<Question> list)
	{
		return questions.addAll(list);
	}
	public String saveJson()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this, Deck.class);
		Serialisation.saveDeck(json);
		return gson.toJson(this);
		
	}

	/**
	 * This method shuffles the list of questions.
	 */
	public void shuffleQuestions() {
		Collections.shuffle(questions);
	}
	
	/**
	 * Returns the deck of the Deck class
	 * load the deck.json in deck
	 * @return the deck
	 */
	public static Deck getInstance() {
		if(deck == null) {
			deck = Serialisation.loadDeck();
			
			
		}
		return deck;
	}
	
	/**
	 * This method draws a random theme for the questions list.
	 * @return the random theme as a String.
	 */
	public String drawTheme() {
		Random rand = new Random();
		return questions.get(rand.nextInt(questions.size())).getTheme();
	}
	
	
	
	

}
