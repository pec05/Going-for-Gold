package model;

import java.util.List;
import exception.ExceptionEmptyField;

/**
 This class holds information about a question and manages its values.
 * 
 * @author Peccio Leandro
 *
 */
public class Question {
	/**
	 * author: The author of the question
	 * theme: The theme of the question
	 * The list of clues helping users to answer the question
	 * answer: The answer of the question
	 */
	private String author;
	private String theme;
	private List<String> clues;
	private String answer;
	
	/**
	 * Constructor of Question. Creates a question using all of its parameters.
	 * @param author The author of the question.
	 * @param theme The theme of the question.
	 * @param clues The list of clues.
	 * @param answer The answer of the question.
	 * An exception is create if this parameters is empty.
	 */
	public Question(String author, String theme, List<String> clues, String answer)throws ExceptionEmptyField {
		if(author.equals("")||theme.equals("")||clues == null || answer.equals("")) {
			throw new ExceptionEmptyField();
		}else {
			this.author = author;
			this.theme = theme;
			this.clues = clues;
			this.answer = answer;
		}
		
	}

	/**
	 * This method returns the author of the question.
	 * @return The author of the question.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * This method changes the author of the question.
	 * @param author The author of the question.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * This method returns the theme of the question.
	 * @return The theme of the question.
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * This method changes the theme of the question.
	 * @param theme The theme of the question.
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * This method return the list of clues of the question
	 * @return The list of clues of the question
	 */
	public List<String> getClues() {
		return clues;
	}

	/**
	 * This method changes the list of clues of the question.
	 * @param The list of clues of the question.
	 */
	public void setClues(List<String> clues) {
		this.clues = clues;
	}

	/**
	 * This method returns the answer of the question.
	 * @return The answer of the question.
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * This method changes the answer of the question.
	 * @param answer The answer of the question.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * This method changes the first clue of the question.
	 * @param clue The first clue of the question.
	 */
	public void setClue1(String clue) {
		this.getClues().set(0, clue);
	}
	
	/**
	 * This method changes the second clue of the question.
	 * @param clue The second clue of the question.
	 */
	public void setClue2(String clue) {
		this.getClues().set(1, clue);
	}
	
	/**
	 * This method changes the third clue of the question.
	 * @param clue The third clue of the question.
	 */
	public void setClue3(String clue) {
		this.getClues().set(2, clue);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((clues == null) ? 0 : clues.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		return result;
	}

	/**
	 * This method compares two Questions to check if they are the same based on their theme and answer
	 * @return Whether the Questions are equals (true) or not (false).
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Question) {
			Question q =(Question)o;
			return this.theme.equals(q.theme) && this.answer.equalsIgnoreCase(q.answer);
		}
		return false;
	}

	/**
	 * This method returns the Question's fields as text.
	 * @return The question's  fields as text
	 */
	@Override
	public String toString() {
		return "Question [author=" + author + ", theme=" + theme + ", clues=" + clues + ", answer=" + answer + "]";
	}
	
	/**
	 * @return a copy of a question
	 */
	public Question clone() {
		try {
			return new Question(author, theme, clues, answer);
		} catch (ExceptionEmptyField e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
