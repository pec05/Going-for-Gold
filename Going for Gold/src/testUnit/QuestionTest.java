package testUnit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import exception.ExceptionEmptyField;
import model.Question;
import test.Explorateur;

public class QuestionTest {
	
	Question q1;
	Question q2;
	Question q3;
	

	@Before
	public void setUp() throws Exception {
		q1 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		q2 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		q3 = new Question("Q3", "test3", Arrays.asList("a","b"), "banane");
		
	}
	
	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
		q3 = null;
	}
	
	@Before
	public void constructorQuestion() throws ExceptionEmptyField {
		Question q1 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		assertEquals(Explorateur.getField(q1, "author"), "Q1");
		assertEquals(Explorateur.getField(q1, "theme"), "test1");
		assertEquals(Explorateur.getField(q1, "clues"), Arrays.asList("a","b"));
		assertEquals(Explorateur.getField(q1, "answer"), "chocolat");
	}
	
	
	@Test(expected = ExceptionEmptyField.class)
	public void testSetExceptionEmptyField() throws ExceptionEmptyField {
		q2 = new Question("Q1", "test1", Arrays.asList("a","b"), "");
	}
	
	
	
	@Test
	public void testSetAuthor() {
		q1.setAuthor("A");
		assertTrue(Explorateur.getField(q1, "author").equals("A"));
	}
	@Test
	public void testEqualsQuestion() {
		assertTrue(q1.equals(q2));
	}
	
	@Test
	public void testHashcodeQuestion() {
		assertNotNull(q1.hashCode());
	}
	
	@Test
	public void testClone() throws ExceptionEmptyField {
		q1 = new Question("Q1", "test1", Arrays.asList("a","b"), "chocolat");
		
		Question q = q1.clone();
		
	}
	
	@Test
	public void testGetAuthor() {
		assertNotNull(q1.getAuthor());
	}
	
	@Test
	public void testGetAnswer() {
		assertNotNull(q1.getAnswer());
	}

	

}
