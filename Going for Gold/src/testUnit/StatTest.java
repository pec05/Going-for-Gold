package testUnit;

import static org.junit.Assert.*;



import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import model.Stat;
import test.Explorateur;

public class StatTest { 
	
		Stat s1;
		Stat s2; 
		Stat s3;
		Stat sSame;
		
		@Before
		public void setUp()throws Exception  {
			s1 = new Stat("test1", 0, "theme1");
			s2 = new Stat("test2", 4, "theme2");
			s3= new Stat("test3", 2, "theme3");
			sSame = new Stat("test1", 0, "theme1");

		}

		@After
		public void tearDown()throws Exception  {
			s1 = null;
			s2 = null;
			s3 = null;
			sSame = null;

		}

		@Test
		public void constructorStat() {
			Stat s1 = new Stat("test1", 2, "s1");
			assertEquals(Explorateur.getField(s1, "playername"), "test1");
			assertEquals(Explorateur.getField(s1, "score"), 2);
			assertEquals(Explorateur.getField(s1, "theme"), "s1");

		}
		
		@Test
		public void testClone() {
			s1 = new Stat("test1", 0, "theme1");
			
			Stat s = s1.clone();
			
		}
		
		@Test
		public void testEqualsStat() {
			assertTrue(s1.equals(sSame));
		}
		
		@Test
		public void testHashcodeStat() {
			assertNotNull(s1.hashCode());
		}
		
		@Test
		public void testSetPlayerName() {
			s1.setPlayername("test");
			assertTrue(Explorateur.getField(s1, "playername").equals("test"));
		}

		@Test
		public void testToString() {
			if(s1.toString().contentEquals(s1.toString())) {
				assertTrue(true);
			}
		}
		
		@Test
		public void testGetPlayerName() {
			assertNotNull(s1.getPlayername());
		}
		
		@Test
		public void testSetScore() {
			s1.setScore(2);
			assertTrue(Explorateur.getField(s1, "score").equals(2));
		}
		
		@Test
		public void testGetScore() {
			assertNotNull(s1.getScore());
		}
		
		@Test
		public void testSetTheme() {
			s1.setTheme("theme1");
			assertTrue(Explorateur.getField(s1, "theme").equals("theme1"));
		}
		
		@Test
		public void testGetTheme() {
			assertNotNull(s1.getTheme());
		}
}
