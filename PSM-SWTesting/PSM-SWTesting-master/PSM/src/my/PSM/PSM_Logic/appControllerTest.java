package my.PSM.PSM_Logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import my.PSM.PSM_Storage.DBConnection;

public class appControllerTest {
	/*
	 * Reference tables
	 * 
	 */
	
	@Mock
	DBConnection dbMock;
	@Mock
	InterfaceController icMock;
	@Mock
	Authenticate authMock;
	

	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	private appController app1;
	//private appController app2;
	
	@Before
	public void setUp() throws Exception {
		dbMock = mock(DBConnection.class);
		icMock = mock(InterfaceController.class);
		authMock = mock(Authenticate.class);
		
		app1 = new appController();
		
		appController.setDependency(dbMock);
		appController.setDependency(icMock);
		appController.setDependency(authMock);
		
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
	}

	/*
	 * Test Id: AC_AppController_001
	 * Test Purpose: 
	 * Setup: see setUp()
	 * Input: returnHr()
	 * Expected Output: "0"
	 */
	@Test
	public void testAppController1() {
		assertEquals(0, app1.returnHr());
	}
	
	/*
	 * Test Id: AC_AppController_002
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testAppController2() {
		assertEquals(0, app1.returnMin());
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testMain1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testMain2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_CheckClear_001
	 * Test Purpose: 
	 * Setup: Initialize an ArrayList 'dateList' with string elements "01/05/19" and "04/25/18"
	 * Input: dateList
	 * Expected Output: True
	 */
	@Test
	public void testCheckClear1() {
		ArrayList<String> dateList = new ArrayList<String>();
		dateList.add("01/05/19");
		dateList.add("04/25/18");
		when(dbMock.getEndDates()).thenReturn(dateList);
		
		assertTrue(appController.checkClear());
		//fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testCheckClear2() {
		ArrayList<String> dateList = new ArrayList<String>();
		dateList.add("05/05/19");
		dateList.add("04/25/18");
		when(dbMock.getEndDates()).thenReturn(dateList);
		
		assertFalse(appController.checkClear());
		//fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testCheckTimes1() {
		ArrayList<Integer> courseList = new ArrayList<Integer>();
		//fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testCheckTimes2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetData1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetData2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testSleep1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testSleep2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testLogIn1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testLogIn2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetCon1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetCon2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testSetTime1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testSetTime2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetTime1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetTime2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetTimeMillis1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetTimeMillis2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testTimerParser1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testTimerParser2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testDateParser1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testDateParser2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testReturnHr1() {
		assertEquals(0, app1.returnHr());
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testReturnHr2() {
		app1.setTime(19, 01, 20, 15, 16);
		assertEquals(15, app1.returnHr());
		//fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testReturnMin1() {
		assertEquals(0, app1.returnMin());
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testReturnMin2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetEndTime1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetEndTime2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testSetSemesterClear1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testSetSemesterClear2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetSemesterClear1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetSemesterClear2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGet15BeforeEnd1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGet15BeforeEnd2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGet5BeforeEnd1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGet5BeforeEnd2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testAutoExit1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testAutoExit2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testAutoClear1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: AC_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testAutoClear2() {
		fail("Not yet implemented");
	}
	

}