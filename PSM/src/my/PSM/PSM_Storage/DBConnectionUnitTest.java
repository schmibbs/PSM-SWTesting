package my.PSM.PSM_Storage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
public class DBConnectionUnitTest {

	DBConnection db = mock(DBConnection.class);
	String[][] dbCred = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}};
	String[][] dbTable = {{"1234", "FOO", "BAR", "Spring", "1/1/1900", "7/8/1901", "am",
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		
	}

	/*
	 * Test Id: DBC_001
	 * Test Purpose: Use DBConnection to create a DBConnection object
	 * Setup: 
	 * Input: none
	 * Expected Output: new DBConnection object
	 */
	@Test
	public void testDBConnection() {
		DBConnection db = mock(DBConnection.class);
		assertEquals("DBConnection Obj established", db, db);
	}

	/*
	 * Test Id: DBC_002
	 * Test Purpose: Test connection using a known database
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testConnectDbUserPw1() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("DBConnection success", db.connect(dbCred[0][0], dbCred[0][1], dbCred[0][2]), 0);
	}
	
	/*
	 * Test Id: DBC_003
	 * Test Purpose: Test connection using a known database with incorrect credentials
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testConnectDbUserPw2() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("DBConnection success", db.connect(dbCred[0][0], "notPeterClarke", dbCred[0][2]), -1);
	}

	/*
	 * Test Id: DBC_004
	 * Test Purpose: Test connection using localhost
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testConnectUserPw1() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("Local DBConnection success", db.connect(dbCred[0][1], dbCred[0][2]), 0);
	}
	
	/*
	 * Test Id: DBC_005
	 * Test Purpose: Test connection using localhost with incorrect credentials
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testConnectUserPw2() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("Local DBConnection failure", db.connect("notPeterClarke", dbCred[0][2]), -1);
	}

	/*
	 * Test Id: DBC_006
	 * Test Purpose: Test if users can dc properly
	 * Setup: 
	 * Input: none
	 * Expected Output: user is disconnected from database
	 */
	@Test
	public void testDisconnect1() {
		db.connect(dbCred[0][1], dbCred[0][1]);
		assertEquals("DC Successful", db.disconnect(), 0);
	}

	/*
	 * Test Id: DBC_006
	 * Test Purpose: Test if users cannot dc 
	 * Setup: 
	 * Input: none
	 * Expected Output: user is connected from database
	 */
	@Test
	public void testDisconnect2() {
		assertEquals("DC Successful", db.disconnect(), -1);	
	}
	
	/*
	 * Test Id: DBC_007
	 * Test Purpose: retrieve the course associated with the four digit int supplied
	 * Setup: 
	 * Input: course_id = 1234
	 * Expected Output: the course associated with 1234
	 */
	@Test
	public void testFetchCourseID1() {
		db.connect(dbCred[0][1], dbCred[0][2]);
		int testIDInt = db.fetchCourseID(Integer.parseInt(dbTable[0][0]));
		assertEquals("Fetch Course ID Test success", testIDInt, 1234);
	}
	
	/*
	 * Test Id: DBC_008
	 * Test Purpose: attempt to retrieve a course with a 1 digit int
	 * Setup: 
	 * Input: course_id = 1
	 * Expected Output: error message
	 */
	@Test
	public void testFetchCourseID2() {
		db.connect(dbCred[0][1], dbCred[0][2]);
		int testIDInt = db.fetchCourseID(5);
		assertEquals("Fetch Course ID Test success", testIDInt, -1);
	}

	/*
	 * Test Id: DBC_009
	 * Test Purpose: Test if users can get the end date of a semester
	 * Setup: 
	 * Input: none
	 * Expected Output: date when semester ends
	 */
	@Test
	public void testGetEndDates1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_010
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testGetEndDates2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_011
	 * Test Purpose: Test if Peter Clarke can get courses for a summer semester
	 * Setup: 
	 * Input: none
	 * Expected Output: courses for a summer semester
	 */
	@Test
	public void testGetCourses1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_012
	 * Test Purpose: Test if Peter Clarke cannot get courses for a summer semester
	 * Setup: 
	 * Input: none
	 * Expected Output: error
	 */
	@Test
	public void testGetCourses2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_013
	 * Test Purpose: test if Peter Clarke can 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourses1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_014
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourses2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_015
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseSubj1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_016
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseSubj2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_017
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseName1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_018
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseName2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_019
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseSemester1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_020
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseSemester2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_021
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseStart1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_022
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseStart2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_023
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseEnd1() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_024
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourseEnd2() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_025
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartMon1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_026
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartMon2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_027
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndMon1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_028
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndMon2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_029
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartTue1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_030
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartTue2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_031
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndTue1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_032
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndTue2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_033
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartWed1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_034
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartWed2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_035
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndWed1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_036
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndWed2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_037
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartThu1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_038
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartThu2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_039
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndThu1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_040
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndThu2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_041
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartFri1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_042
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartFri2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_043
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndFri1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_044
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndFri2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_045
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartSat1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_046
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartSat2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_047
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndSat1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_048
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndSat2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_049
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testStoreClassInfo1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_050
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testStoreClassInfo2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_051
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testStoreClassSched1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_052
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testStoreClassSched2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_053
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testClearDatabase1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_054
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testClearDatabase2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_055
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testCreateClassTable1() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test Id: DBC_056
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testCreateClassTable2() {
		fail("Not yet implemented");
	}

}
