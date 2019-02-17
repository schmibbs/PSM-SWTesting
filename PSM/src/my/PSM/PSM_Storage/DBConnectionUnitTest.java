package my.PSM.PSM_Storage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import org.mockito.*;
import org.mockito.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.jdbc.*;
import java.sql.SQLException;

public class DBConnectionUnitTest {

	
	@Mock
	Statement statementMock;
	@Mock
	ResultSet resultMock;
	@Mock
	Connection connectMock;
	
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private DBConnection db;
	
	//DBConnection db = mock(DBConnection.class);
	String[][] dbCred = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}};
	String[][] dbTable = {{"1234", "FOO", "BAR", "Spring", "01/01/1900", "07/08/1901", "am",
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};
	int cid = Integer.parseInt(dbTable[0][0]);	//course id
	
	@Before
	public void setUp() throws Exception {
		statementMock = mock(Statement.class);
		resultMock = mock(ResultSet.class);
		connectMock = mock(Connection.class);
		
		db = new DBConnection();		
		
		db.setDependency(connectMock);
	}
	
	@After
	public void tearDown() throws Exception {
		db = null;
	}

	/*
	 * Test Id: DBC_DBConnection_001
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
	 * Test Id: DBC_Connect_002
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
	 * Test Id: DBC_Connect_003
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
	 * Test Id: DBC_ConnectLocal_004
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
	 * Test Id: DBC_DontConnectLocal_005
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
	 * Test Id: DBC_Disconnect_006
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
	 * Test Id: DBC_Disconnect_006
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
	 * Test Id: DBC_FetchCourseID_007
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
	 * Test Id: DBC_FetchCourseID_008
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
	 * Test Id: DBC_GetEndDates_009
	 * Test Purpose: Test if users can get the end date of a semester
	 * Setup: 
	 * Input: none
	 * Expected Output: date when semester ends
	 */
	@Test
	public void testGetEndDates1() {
		ArrayList<String> results = new ArrayList<>();
		
		results = db.getEndDates();
		assertEquals("getEndDatesTest success", results, ArrayList.class);
	}
	@Test
	/*
	 * Test Id: DBC_010
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	
	public void testGetEndDates2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_GetCourses_011
	 * Test Purpose: Test if Peter Clarke can get courses for a summer semester
	 * Setup: 
	 * Input: none
	 * Expected Output: courses for a summer semester
	 */
	@Test
	public void testGetCourses1() {
		ArrayList<Integer> results = db.getCourses();
		int inputCourse = Integer.parseInt(dbTable[0][2]);
		int firstCourse = results.get(0);
		
		assertEquals("getCourses success", firstCourse, inputCourse);
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
	 * Test Id: DBC_FetchCourses_013
	 * Test Purpose: test if Peter Clarke can retrieve a course's name
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchCourses1() {
		String courses = db.fetchCourses();
		String testInput = dbTable[0][1] + ",";
		
		assertEquals("Fetch Course Success", courses, testInput);
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
		fail("cannot implement");
	}

	/*
	 * Test Id: DBC_FetchCourseSubj_015
	 * Test Purpose: test if Peter Clarke can retrieve a course's subject with a specific int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][1], dbTable[0][0]
	 * Expected Output: FOO
	 */
	@Test
	public void testFetchCourseSubj1() {
		int cid = Integer.parseInt(dbTable[0][0]);
		String subj = db.fetchCourseSubj(cid);
		
		assertEquals("Couse subject fetch successful", subj, "FOO");
	}
	
	/*
	 * Test Id: DBC_FetchCouseFubj_016
	 * Test Purpose: test if Peter Clarke can fail to retrieve a course with a specific int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][1], dbTable[0][0]
	 * Expected Output: BAR
	 */
	@Test
	public void testFetchCourseSubj2() {
		int cid = 8;
		String subj = db.fetchCourseSubj(cid);
		
		assertEquals("Couse subject failure to fetch, successful", subj, null);
	}

	/*
	 * Test Id: DBC_FetchCourseName_017
	 * Test Purpose: test if Peter Clarke can retrieve a course's name with a specific int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][0]
	 * Expected Output: FOO
	 */
	@Test
	public void testFetchCourseName1() {
		int cid = Integer.parseInt(dbTable[0][0]);
		String name = db.fetchCourseSubj(cid);
		
		assertEquals("Couse name fetch successful", name, "BAR");
	}
	
	/*
	 * Test Id: DBC__FetchCourseName_018
	 * Test Purpose: test if Peter Clarke cannot retrieve a course's name with an incorrect int course ID
	 * Setup: dbTable
	 * Input: 4
	 * Expected Output: null
	 */
	@Test
	public void testFetchCourseName2() {
		cid = 4;
		String name = db.fetchCourseSubj(cid);
		
		assertEquals("Couse name fetch unsuccessful", name, null);
	}

	/*
	 * Test Id: DBC_FetchCourseSemester_019
	 * Test Purpose: test if Peter Clarke can retrieve the semester a course takes place in with an int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][0]
	 * Expected Output: Spring
	 */
	@Test
	public void testFetchCourseSemester1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String sem = db.fetchCourseSemester(cid);
		
		assertEquals("Couse semester fetch successful", sem, "Spring");
	}
	
	/*
	 * Test Id: DBC_FetchCourseSemester_020
	 * Test Purpose: test if Peter Clarke cannot retrieve the semester a course takes place in with an incorrect int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][3], dbTable[0][0]
	 * Expected Output: null
	 */
	@Test
	public void testFetchCourseSemester2() {
		cid = 8;
		String sem = db.fetchCourseSemester(cid);
		
		assertEquals("Couse semester fetch successful", sem, null);
	}

	/*
	 * Test Id: DBC_FetchCourseStart_021
	 * Test Purpose: test if Peter Clarke can retrieve the starting data a semester takes place in with an int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][4], dbTable[0][0]
	 * Expected Output: 01/01/1900
	 */
	@Test
	public void testFetchCourseStart1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String sDate = db.fetchCourseStart(cid);
		
		assertEquals("Couse start date fetch successful", sDate, "01/01/1900");
	}
	
	/*
	 * Test Id: DBC_FetchCourseStart_022
	 * Test Purpose: test if Peter Clarke cannot retrieve the starting date a semester takes place in with an incorrect int course ID
	 * Setup: dbTable
	 * Input: 15
	 * Expected Output: null
	 */
	@Test
	public void testFetchCourseStart2() {
		cid = 15;
		String sDate = db.fetchCourseStart(cid);
		
		assertEquals("Couse start date fetch unsuccessful", sDate, null);
	}

	/*
	 * Test Id: DBC_FetchCourseEnd_023
	 * Test Purpose: test if Peter Clarke can retrieve the ending date of a semester with a correct int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][0]
	 * Expected Output: 07/08/1901
	 */
	@Test
	public void testFetchCourseEnd1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String eDate = db.fetchCourseEnd(cid);
		
		assertEquals("Couse end date fetch successful", eDate, "07/08/1901");
	}

	/*
	 * Test Id: DBC_FetchCourseEnd_024
	 * Test Purpose: test if Peter Clarke cannot retrieve the ending date of a semester with an incorrect int course ID
	 * Setup: dbTable
	 * Input: 16
	 * Expected Output: null
	 */
	@Test
	public void testFetchCourseEnd2() {
		cid = 16;
		String eDate = db.fetchCourseEnd(cid);
		
		assertEquals("Couse end date fetch successful", eDate, null);
	}
	
	/*
	 * Test Id: DBC_025
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartMon1() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_mon")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartMon(0));
	}
	
	/*
	 * Test Id: DBC_026
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartMon2() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_mon")).thenReturn("11:00");
		String temp = db.fetchStartMon(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_027
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndMon1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_mon")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndMon(0));
	}
	
	/*
	 * Test Id: DBC_028
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndMon2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_mon")).thenReturn("11:00");
		String temp = db.fetchEndMon(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_029
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartTue1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_tue")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartTue(0));
	}
	
	/*
	 * Test Id: DBC_030
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartTue2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_tue")).thenReturn("11:00");
		String temp = db.fetchStartTue(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_031
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndTue1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_tue")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndTue(0));
	}
	
	/*
	 * Test Id: DBC_032
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndTue2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_tue")).thenReturn("11:00");
		String temp = db.fetchEndTue(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_033
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartWed1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_wed")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartWed(0));
	}
	
	/*
	 * Test Id: DBC_034
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartWed2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_wed")).thenReturn("11:00");
		String temp = db.fetchStartWed(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_035
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndWed1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_wed")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndWed(0));
	}
	
	/*
	 * Test Id: DBC_036
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndWed2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_wed")).thenReturn("11:00");
		String temp = db.fetchEndWed(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_037
	 * Test Purpose: Test if Peter Clarke can 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartThu1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_thu")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartThu(0));
	}
	
	/*
	 * Test Id: DBC_038
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartThu2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_thu")).thenReturn("11:00");
		String temp = db.fetchStartThu(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_039
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndThu1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_thu")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndThu(0));
	}
	
	/*
	 * Test Id: DBC_040
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndThu2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_thu")).thenReturn("11:00");
		String temp = db.fetchStartThu(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_041
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartFri1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_fri")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartFri(0));
	}
	
	/*
	 * Test Id: DBC_042
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartFri2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_fri")).thenReturn("11:00");
		String temp = db.fetchStartFri(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_043
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndFri1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_fri")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndFri(0));
	}
	
	/*
	 * Test Id: DBC_044
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndFri2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_fri")).thenReturn("11:00");
		String temp = db.fetchEndFri(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_045
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartSat1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT start_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("start_sat")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartSat(0));
	}
	
	/*
	 * Test Id: DBC_046
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchStartSat2() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT start_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("start_sat")).thenReturn("11:00");
		String temp = db.fetchStartSat(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_047
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndSat1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT end_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("end_sat")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndSat(0));
	}
	
	/*
	 * Test Id: DBC_048
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testFetchEndSat2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT end_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("end_sat")).thenReturn("12:00");
		String temp = db.fetchEndSat(0);
		assertFalse("12:00"==temp);
	}

	/*
	 * Test Id: DBC_StoreClassInfo_049
	 * Test Purpose: Test if Peter Clarke can save a course's info into a db with a legal course id, subject, name and semester
	 * Setup: dbTable
	 * Input: dbTable[0][0], dbTable[0][1], dbTable[0][2], dbTable[0][3]
	 * Expected Output: 0
	 */
	@Test
	public void testStoreClassInfo1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String subject = dbTable[0][1]; 
		String name = dbTable[0][2];
		String sem = dbTable[0][3];
		
		int result = db.storeClassInfo(cid, subject, name, sem);
		
		assertEquals("Successful class storage", result, 0);
	}
	
	
	/*
	 * Test Id: DBC_StoreClassInfo_050
	 * Test Purpose: Test if Peter Clarke cannot save a course's info into a db with an illegal parameter                                     
	 * Setup: dbTable
	 * Input: 1, "a", "s", "d"
	 * Expected Output: 0
	 */
	@Test
	public void testStoreClassInfo2() {
		int result = db.storeClassInfo(1, "a", "s", "d");
		
		assertEquals("Successful class storage", result, -1);
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
