package testUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import model.Stat;
import model.StatManagement;
import test.Explorateur;
import util.Serialisation;

public class StatManagementTest {
	
	private Stat s1, s2,s3;
	private StatManagement statManagement;
	private List<Stat>listStats;

	@Before
	public void setUp() throws Exception {
		s1 = new Stat("test1", 0, "theme1");
		s2 = new Stat("test2", 4, "theme2");
		s3= new Stat("test3", 2, "theme3");
		
		statManagement = new StatManagement();
		listStats = (List<Stat>) Explorateur.getField(statManagement, "stats");
	}

	@After
	public void tearDown() throws Exception {
		s1 = null;
		s2 = null;
		s3 = null;
		statManagement = null;
		listStats = null;
	}

	@Test
	public void testAddStat() {
		statManagement.addStat(s1);
		assertTrue(listStats.contains(s1));
	}
	
	@Test
	public void testAddAllStat() {
		List<Stat>list = new ArrayList<Stat>();
		list.add(s1);
		list.add(s2);
		
		statManagement.addAllStat(list);
		
		assertTrue(list.contains(s1)&&list.contains(s3));
	}
	
	@Test
	public void testGetListStats() {
		assertNotNull(statManagement.getStats());
	}
	
	@Test
	public void testToString() {
		if(statManagement.toString().contentEquals(s1.toString())) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(statManagement.getInstanceStatManagement());
	}

}
