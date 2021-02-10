package testUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import exception.ExceptionDouble;
import exception.ExceptionEmptyField;
import model.Deck;
import model.Question;
import test.Explorateur;

public class DeckTest {

	private Question q1, q2, q3;
	private Deck deck;
	private List<Question>listQuestions;
	
	
	@Before
	public void setUp() throws Exception { 
		q1 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		q2 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		q3 = new Question("Q3", "test3", Arrays.asList("a","b"), "banane");
		
		deck = new Deck();
		listQuestions = (List<Question>) Explorateur.getField(deck, "questions");
		
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
		q3 = null;
		deck = null;
		listQuestions = null;
	}

	@Test
	public void testAddQuestion()throws ExceptionDouble {
		deck.add(q1);
		assertTrue(listQuestions.contains(q1));
	}
	
	
	@Test(expected = ExceptionDouble.class)
	public void testAddExceptionAjoutQuestionDejaPresentDeck() throws ExceptionDouble {
		listQuestions.add(q1);
		deck.add(q1);
//		assertTrue(listQuestions.contains(q1));
	}
	
	@Test
	public void testRemvoveDeck() {
		listQuestions.add(q1);
		deck.remove(q1);
		assertFalse(listQuestions.contains(q1));
	}
	
	@Test
	public void testGetQuestions() {
		assertNotNull(deck.getQuestions());
	}
	
	
	
	@Test
	public void testRemoveAllDeck() {
		List<Question> list = new ArrayList<Question>();
		list.add(q1);
		list.add(q3);
		
		
		deck.removeAll(list);
		
		assertTrue(list.contains(q1)&&list.contains(q3));
	}
	
	@Test
	public void testShuffleQuestions() throws ExceptionEmptyField, ExceptionDouble {
		q1 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		q2 = new Question("Q2", "test2", Arrays.asList("a","b"), "poire");
		
		deck.add(q1);
		deck.add(q2);
		
		List<Question> questionsCopy = new ArrayList<Question>(listQuestions);
		deck.shuffleQuestions();
		assertTrue(listQuestions.size() == questionsCopy.size() && questionsCopy.containsAll(listQuestions));
	}
	
	
	@Test
	public void testAddAllDeck() {
		List<Question> list = new ArrayList<Question>();
		list.add(q1);
		list.add(q3);
		
		
		deck.addAll(list);
		
		assertTrue(list.contains(q1)&&list.contains(q3));
	}
	
	@Test
	public void testUpdate() throws ExceptionEmptyField, ExceptionDouble  {
		deck.add(q1);
		Question a = new Question("Leandro", "test1", Arrays.asList("a","b"), "chocolat");
		Question b = new Question("Leandro", "test1", Arrays.asList("a","b"), "fraise");
		deck.update(a,b);
		assertTrue(listQuestions.contains(b));
	}
	
	
	@Test 
	public void testGetQuestionString() {
		listQuestions.addAll(Arrays.asList(q1,q2,q3));
		assertTrue(listQuestions.contains(q1) && listQuestions.contains(q2) && listQuestions.contains(q3));
		assertTrue(deck.getQuestion("test1").getTheme().equals("test1"));
		assertTrue(deck.getQuestion("p") == null);
		listQuestions.removeAll(Arrays.asList(q1,q2,q3));
	}
	
	@Test
	public void testToString() {
		if(deck.toString().contentEquals(q1.toString())) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetListQuestions() {
		assertNotNull(deck.getListQuestion());
	}
	
	@Test
	public void testSetGestionlivres() {
		listQuestions.add(q1);
		deck.setQuestions(listQuestions);
		assertTrue(listQuestions.equals(q1));
	}
	
	@Test
	public void testDrawTheme() {
		String drawnTheme;
		boolean containsQuestion;
		
		for(int i=0;i<10;i++) {
			containsQuestion = false;
			drawnTheme = Deck.getInstance().drawTheme();
			for(Question q : listQuestions) {
				if(q.getTheme().equals(drawnTheme)) {
					containsQuestion = true;
				}
			}
			if(!containsQuestion) {
				fail("Illegal question drawn");
			}
		}
	}
	
	@Test
	public void testFindQuestion() throws ExceptionDouble {
		deck.add(q1);
		deck.find(q1);
		assertTrue(listQuestions.contains(q1));
	}
	
	
	

}
