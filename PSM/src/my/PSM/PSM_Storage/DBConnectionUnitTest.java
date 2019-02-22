package my.PSM.PSM_Storage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.plugins.MockMaker.*;

import static java.lang.IllegalStateException.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import org.mockito.*;
import org.mockito.junit.*;

import org.powermock.*;
import org.*;

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
		
		MockitoAnnotations.initMocks(this);  //src: https://stackoverflow.com/questions/49284647/how-to-obtain-service-object-in-junit-test
	}
	
	@After
	public void tearDown() throws Exception {
		db = null;
	}
	
	/*
	 * Test Id: DBC_DBConnection_022
	 * Test Purpose: Use DBConnection to create a DBConnection object
	 * Setup: DBConnection mock
	 * Input: DBConnection.class
	 * Expected Output: new DBConnection object
	 * Actual Output: new DBConnection object
	 * Pass/Fail: pass
	 */
	@Test
	public void testDBConnection() {
		DBConnection db = mock(DBConnection.class);
		assertEquals("DBConnection Obj established", db, db);
	}

	/*
	 * Test Id: DBC_Connect_023
	 * Test Purpose: Test connection using a known database
	 * Setup: DBConnection mock
	 * Input: db.connect
	 * Expected Output: none
	 * Actual Output: new connection to a known database
	 * Pass/Fail: pass 
	 */
	@Test
	public void testConnectDbUserPw1() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("DBConnection success", 0, db.connect(dbCred[0][0], dbCred[0][1], dbCred[0][2]));
	}
	
	/*
	 * Test Id: DBC_Connect_024
	 * Test Purpose: Test connection using a known database with incorrect credentials
	 * Setup: DBConnection mock
	 * Input: db.connect
	 * Expected Output: none
	 * Actual Output: 0
	 * Pass/Fail: fail
	 */
	@Test
	public void testConnectDbUserPw2() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("DBConnection success", -1, db.connect(dbCred[0][0], "notPeterClarke", dbCred[0][2]));
	}

	/*
	 * Test Id: DBC_ConnectLocal_025
	 * Test Purpose: Test connection using localhost
	 * Setup: DBConnection mock
	 * Input: db.connect
	 * Expected Output: new connection to a known database
	 * Actual Output: 0
	 * Pass/Fail: pass
	 */
	@Test
	public void testConnectUserPw1() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("Local DBConnection success", 0, db.connect(dbCred[0][1], dbCred[0][2]));
	}
	
	/*
	 * Test Id: DBC_DontConnectLocal_026
	 * Test Purpose: Test connection using localhost with incorrect credentials
	 * Setup: DBConnection mock
	 * Input: db.connect
	 * Expected Output: -1; exception
	 * Actual Output: 0
	 * Pass/Fail: fail
	 */
	@Test
	public void testConnectUserPw2() {
		DBConnection db = mock(DBConnection.class);
		
		assertEquals("Local DBConnection failure", -1, db.connect("notPeterClarke", dbCred[0][2]));
	}

	
	/*
	 * Test Id: DBC_Disconnect_027
	 * Test Purpose: Test if users can dc properly
	 * Setup: 
	 * Input: db.connect, db.disconnect
	 * Expected Output: user is disconnected from database
	 * Actual Output: 0
	 * Pass/Fail: pass
	 */
	@Test
	public void testDisconnect1() {
		db.connect(dbCred[0][1], dbCred[0][2]);
		assertEquals("DC Successful", 0, db.disconnect());
	}

	/*
	 * Test Id: DBC_Disconnect_028
	 * Test Purpose: Test if users cannot dc 
	 * Setup: DBConnection mock
	 * Input: db.connect, db.disconnect
	 * Expected Output: user is not connected from database
	 * Actual Output: 0
	 * Pass/Fail: fail
	 */
	@Test
	public void testDisconnect2() {
		db.connect(dbCred[0][1], dbCred[0][2], connectMock);
		db.forceNullCon();
		assertEquals("DC Successful", -1, db.disconnect());	
	}
	
	/*
	 * Test Id: DBC_FetchCourseID_029
	 * Test Purpose: retrieve the course associated with the four digit int supplied
	 * Setup: DBConnection mock
	 * Input: course_id = 1234, db.connect, db.fetchCourseID
	 * Expected Output: the course associated with 1234
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseID1() {
		db.connect(dbCred[0][1], dbCred[0][2], connectMock);
		int testIDInt = db.fetchCourseID(Integer.parseInt(dbTable[0][0]));
		assertEquals("Fetch Course ID Test success", 1234, testIDInt);
	}
	
	/*
	 * Test Id: DBC_FetchCourseID_030
	 * Test Purpose: attempt to retrieve a course with a 1 digit int
	 * Setup: 
	 * Input: course_id = 1
	 * Expected Output: error message
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseID2() {
		db.connect(dbCred[0][1], dbCred[0][2]);
		int testIDInt = db.fetchCourseID(5);
		assertEquals("Fetch Course ID Test success", -1, testIDInt);
	}

	/*
	 * Test Id: DBC_GetEndDates_031
	 * Test Purpose: Test if users can get the end date of a semester
	 * Setup: Run an SQL server
	 * 		  Add the "Class100" table
	 * 		  Add the columns
	 * 			course_id, course_subject, course_name, semester,
	 * 			start_date, end_date, am_or_pm, start_mon, end_mon,
	 * 			start_tue, end_mon, start_wed, end_mon, start_thu, end_thu, 
	 * 			start_fri, end_fri, start_sat, end_sat 
	 * 		  Populate with data from dbTable
	 * 		  Ensure there is a user: "PeterClarke" with password: "12345"  
	 * Input: none
	 * Expected Output: date when semester ends
	 * Actual Output: date when semester ends
	 * Pass/Fail: pass
	 */
	@Test
	public void testGetEndDates1() {
		db.connect(dbCred[0][1], dbCred[0][2]);
		ArrayList<String> results = new ArrayList<>();
		ArrayList<String> dateToCompare = new ArrayList<>();
		
		dateToCompare.add("07/08/1901");
		results = db.getEndDates();
		assertEquals("getEndDatesTest success", dateToCompare, results) ;
	}
	@Test
	/*
	 * Test Id: DBC_GetEndDates_032
	 * Test Purpose: Test if "PeterClarke" cannot get the end date of a semester
	 * Setup: Run an SQL server
	 * 		  Add the "Class100" table
	 * 		  Add the columns
	 * 			course_id, course_subject, course_name, semester,
	 * 			start_date, end_date, am_or_pm, start_mon, end_mon,
	 * 			start_tue, end_mon, start_wed, end_mon, start_thu, end_thu, 
	 * 			start_fri, end_fri, start_sat, end_sat 
	 * 		  Populate with data from dbTable
	 * 		  Ensure there is a user: "PeterClarke" with password: "12345"  
	 * Input: none
	 * Expected Output:null
	 * Actual Output: date when semester ends
	 * Pass/Fail: pass
	 */
	public void testGetEndDates2() {
		//db.connect(dbCred[0][1], dbCred[0][2]);
		ArrayList<String> results = new ArrayList<>();
		
		results = db.getEndDates();
		assertEquals("getEndDatesTest success", null, results);
	}

	/*
	 * Test Id: DBC_GetCourses_033
	 * Test Purpose: Test if Peter Clarke can get courses for a summer semester
	 * Setup: 
	 * Input: none
	 * Expected Output: courses for a Spring semester
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testGetCourses1() {
		ArrayList<Integer> results = db.getCourses();
		int inputCourse = Integer.parseInt(dbTable[0][2]);
		int firstCourse = results.get(0);
		
		assertEquals("getCourses success", inputCourse, firstCourse);
	}
	
	/*
	 * Test Id: DBC_GetCourses_034
	 * Test Purpose: Test if Peter Clarke cannot get courses for a summer semester
	 * Setup: 
	 * Input: none
	 * Expected Output: error
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testGetCourses2() {
		fail("Not yet implemented");
	}

	/*
	 * Test Id: DBC_FetchCourses_035
	 * Test Purpose: test if Peter Clarke can retrieve a course's name
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourses1() {
		db.connect(dbCred[0][1], dbCred[0][2]);
		String courses = db.fetchCourses();
		String testInput = dbTable[0][0] + ",";
		
		assertEquals("Fetch Course Success", testInput, courses);
	}         
	
	/*
	 * Test Id: DBC_FetchCourses036
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourses2() {
		fail("cannot implement");
	}

	/*
	 * Test Id: DBC_FetchCourseSubj_037
	 * Test Purpose: test if Peter Clarke can retrieve a course's subject with a specific int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][1], dbTable[0][0]
	 * Expected Output: FOO
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseSubj1() {
		int cid = Integer.parseInt(dbTable[0][0]);
		String subj = db.fetchCourseSubj(cid);
		
		assertEquals("Couse subject fetch successful", "FOO", subj);
	}
	
	/*
	 * Test Id: DBC_FetchCouseSubj_038
	 * Test Purpose: test if Peter Clarke can fail to retrieve a course with a specific int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][1], dbTable[0][0]
	 * Expected Output: BAR
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseSubj2() {
		int cid = 8;
		String subj = db.fetchCourseSubj(cid);
		
		assertEquals("Couse subject failure to fetch, successful", null, subj);
	}

	/*
	 * Test Id: DBC_FetchCourseName_039
	 * Test Purpose: test if Peter Clarke can retrieve a course's name with a specific int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][0]
	 * Expected Output: FOO
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseName1() {
		int cid = Integer.parseInt(dbTable[0][0]);
		String name = db.fetchCourseSubj(cid);
		
		assertEquals("Couse name fetch successful", "BAR", name);
	}
	
	/*
	 * Test Id: DBC__FetchCourseName_040
	 * Test Purpose: test if Peter Clarke cannot retrieve a course's name with an incorrect int course ID
	 * Setup: dbTable
	 * Input: 4
	 * Expected Output: null
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseName2() {
		cid = 4;
		String name = db.fetchCourseSubj(cid);
		
		assertEquals("Couse name fetch unsuccessful", null, name);
	}

	/*
	 * Test Id: DBC_FetchCourseSemester_041
	 * Test Purpose: test if Peter Clarke can retrieve the semester a course takes place in with an int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][0]
	 * Expected Output: Spring
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseSemester1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String sem = db.fetchCourseSemester(cid);
		
		assertEquals("Couse semester fetch successful", "Spring", sem);
	}
	
	/*
	 * Test Id: DBC_FetchCourseSemester_042
	 * Test Purpose: test if Peter Clarke cannot retrieve the semester a course takes place in with an incorrect int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][3], dbTable[0][0]
	 * Expected Output: null
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseSemester2() {
		cid = 8;
		String sem = db.fetchCourseSemester(cid);
		
		assertEquals("Couse semester fetch unsuccessful", null, sem);
	}

	/*
	 * Test Id: DBC_FetchCourseStart_043
	 * Test Purpose: test if Peter Clarke can retrieve the starting data a semester takes place in with an int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][4], dbTable[0][0]
	 * Expected Output: 01/01/1900
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseStart1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String sDate = db.fetchCourseStart(cid);
		
		assertEquals("Couse start date fetch successful", "01/01/1900", sDate);
	}
	
	/*
	 * Test Id: DBC_FetchCourseStart_044
	 * Test Purpose: test if Peter Clarke cannot retrieve the starting date a semester takes place in with an incorrect int course ID
	 * Setup: dbTable
	 * Input: 15
	 * Expected Output: null
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseStart2() {
		cid = 15;
		String sDate = db.fetchCourseStart(cid);
		
		assertEquals("Couse start date fetch unsuccessful", null, sDate);
	}

	/*
	 * Test Id: DBC_FetchCourseEnd_045
	 * Test Purpose: test if Peter Clarke can retrieve the ending date of a semester with a correct int course ID
	 * Setup: dbTable
	 * Input: dbTable[0][0]
	 * Expected Output: 07/08/1901
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseEnd1() {
		cid = Integer.parseInt(dbTable[0][0]);
		String eDate = db.fetchCourseEnd(cid);
		
		assertEquals("Couse end date fetch successful", "07/08/1901", eDate);
	}

	/*
	 * Test Id: DBC_FetchCourseEnd_046
	 * Test Purpose: test if Peter Clarke cannot retrieve the ending date of a semester with an incorrect int course ID
	 * Setup: dbTable
	 * Input: 16
	 * Expected Output: null
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchCourseEnd2() {
		cid = 16;
		String eDate = db.fetchCourseEnd(cid);
		
		assertEquals("Couse end date fetch successful", null, eDate);
	}
	
	/*
	 * Test Id: DBC_FetchStartMon_047
	 * Test Purpose: Test if Peter Clarke can retrieve the start times of classes on Monday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchStartMon1() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_mon")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartMon(0));
	}
	
	/*
	 * Test Id: DBC_FetchStartMon_048
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Monday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchEndMon_049
	 * Test Purpose: Test if Peter Clarke can retrieve the end times of classes on Monday
	 * Setup: dbTable
	 * Input: db.FetchEndThu
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchEndMon1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_mon")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndMon(0));
	}
	
	/*
	 * Test Id: DBC_FetchEndMon_050
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Monday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchStartTue_051
	 * Test Purpose: Test if Peter Clarke can retrieve the start times of classes on Tuesday 
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00  
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchStartTue1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_tue")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartTue(0));
	}
	
	/*
	 * Test Id: DBC_FetchStartTue_052
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Tuesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchEndTue_053
	 * Test Purpose: Test if Peter Clarke can retrieve the end times of classes on Tuesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchEndTue1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_tue")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndTue(0));
	}
	
	/*
	 * Test Id: DBC_FetchEndTue_054
	 * Test Purpose: Test if Peter Clarke cannot retrieve the end times of classes on Tuesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchStartWed_055
	 * Test Purpose: Test if Peter Clarke can retrieve the start times of classes on Wednesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchStartWed1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_wed")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartWed(0));
	}
	
	/*
	 * Test Id: DBC_FetchStartWed_056
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Wednesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchEndWed_057
	 * Test Purpose: Test if Peter Clarke can retrieve the end times of classes on Wednesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchEndWed1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_wed")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndWed(0));
	}
	
	/*
	 * Test Id: DBC_FetchEndWed_058
	 * Test Purpose: Test if Peter Clarke cannot retrieve the end times of classes on Wednesday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchStartThu_059
	 * Test Purpose: Test if Peter Clarke can retrieve the start times of classes on Thursday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchStartThu1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_thu")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartThu(0));
	}
	
	/*
	 * Test Id: DBC_FetchStartThu_060
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Thursday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchEndThu_061
	 * Test Purpose: Test if Peter Clarke can retrieve the end times of classes on Thursday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchEndThu1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_thu")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndThu(0));
	}
	
	/*
	 * Test Id: DBC_FetchEndThu_062
	 * Test Purpose: Test if Peter Clarke cannot retrieve the end times of classes on Thursday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchStartFri_063
	 * Test Purpose: Test if Peter Clarke can retrieve the start times of classes on Friday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchStartFri1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_fri")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartFri(0));
	}
	
	/*
	 * Test Id: DBC_FetchStartFri_064
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Friday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchEndFri_065
	 * Test Purpose: Test if Peter Clarke can retrieve the end times of classes on Friday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchEndFri1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_fri")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndFri(0));
	}
	
	/*
	 * Test Id: DBC_FetchEndFri_066
	 * Test Purpose: Test if Peter Clarke cannot retrieve the end times of classes on Friday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchStartSat_067
	 * Test Purpose: Test if Peter Clarke can retrieve the start times of classes on Saturday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchStartSat1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT start_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("start_sat")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartSat(0));
	}
	
	/*
	 * Test Id: DBC_FetchStartSat_068
	 * Test Purpose: Test if Peter Clarke cannot retrieve the start times of classes on Saturday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_FetchEndSat_069
	 * Test Purpose: Test if Peter Clarke can retrieve the end times of classes on Saturday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: 11:00 
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testFetchEndSat1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT end_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("end_sat")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndSat(0));
	}
	
	/*
	 * Test Id: DBC_FetchEndSat_070
	 * Test Purpose: Test if Peter Clarke cannot retrieve the end times of classes on Saturday
	 * Setup: dbTable
	 * Input:
	 * Expected Output: false
	 * Actual Output: 
	 * Pass/Fail: 
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
	 * Test Id: DBC_StoreClassInfo_071
	 * Test Purpose: Test if Peter Clarke can save a course's info into a db with a legal course id, subject, name and semester
	 * Setup: dbTable
	 * Input: dbTable[0][0], dbTable[0][1], dbTable[0][2], dbTable[0][3]
	 * Expected Output: 0
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testStoreClassInfo1() throws SQLException {
		cid = Integer.parseInt(dbTable[0][0]);
		String subject = dbTable[0][1]; 
		String name = dbTable[0][2];
		String sem = dbTable[0][3];
		
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("INSERT INTO Class100 (course_id, course_subject, course_name, semester) VALUES ("
				+ cid + "," + subject + "," + name + "," + sem +");")).thenReturn(resultMock);
		
		
		int result = db.storeClassInfo(cid, subject, name, sem);
		
		assertEquals("Successful class storage", result, 0);
	}
	
	
	/*
	 * Test Id: DBC_StoreClassInfo_072
	 * Test Purpose: Test if Peter Clarke cannot save a course's info into a db with an illegal parameter                                     
	 * Setup: dbTable
	 * Input: 1, null, null, null,
	 * Expected Output: -1
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testStoreClassInfo2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("INSERT INTO Class100 (course_id, course_subject, course_name, semester) VALUES ("
				+ cid + ");")).thenReturn(resultMock);
		
		
		int result = db.storeClassInfo(cid, null, null, null);
		
		assertFalse(result == -1);
	}

	/*
	 * Test Id: DBC_StoreClassSched_073
	 * Test Purpose: Test if Peter Clarke can store a class's schedule given proper inputs
	 * Setup: dbTable
	 * Input: dbTable[0][0..18], null
	 * Expected Output: 0
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testStoreClassSched1() throws SQLException {
		String startDate = dbTable[0][4];
		String endDate = dbTable[0][5];
		String startMon = dbTable[0][4];
		String endMon = dbTable[0][5];
		String startWed = dbTable[0][4];
		String endWed = dbTable[0][5];
		String testQuery = "UPDATE Class100 SET start_date = '" +startDate +"', end_date = '" +endDate +"', start_mon =  '" 
        +startMon +"', end_mon = '" +endMon + "', start_tue = '" + null +"', end_tue = '" + null 
        +"', start_wed = '" +startWed +"', end_wed = '" +endWed +"', start_thu =  '" + null 
        + "', end_thu = '" + null +"', start_fri = '" + null +"', end_fri = '" + null 
        +"', start_sat =  '" + null +"', end_sat = '" + null
        +"' WHERE course_id = '" + null + "';";
		
		
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery(testQuery)).thenReturn(resultMock);
		
		assertEquals("Successful schedule storage", resultMock, 0);
	}
	
	/*
	 * Test Id: DBC_StoreClassSched_074
	 * Test Purpose: Test if Peter Clarke cannot store a class's schedule providing null inputs
	 * Setup:
	 * Input: null
	 * Expected Output: -1
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testStoreClassSched2() throws SQLException {
		String testQuery = "UPDATE Class100 SET start_date = '" + null +"', end_date = '" + null +"', start_mon =  '" 
		        + null +"', end_mon = '" + null + "', start_tue = '" +  null +"', end_tue = '" + null 
		        +"', start_wed = '" + null +"', end_wed = '" + null +"', start_thu =  '" + null 
		        + "', end_thu = '" + null +"', start_fri = '" + null +"', end_fri = '" + null 
		        +"', start_sat =  '" + null +"', end_sat = '" + null
		        +"' WHERE course_id = '" + null + "';";
		
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery(testQuery)).thenReturn(resultMock);
		
		assertEquals("Successful schedule storage", resultMock, -1);
	}

	/*
	 * Test Id: DBC_ClearDatabase_075
	 * Test Purpose: Test if Peter Clarke can clear the database of entries
	 * Setup: 
	 * Input:
	 * Expected Output: empty database
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testClearDatabase1() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("DELTE FROM CLASS100;")).thenReturn(resultMock);
		
		assertEquals("Database purge test success", resultMock, null);
	}
	
	/*
	 * Test Id: DBC_ClearDatabase_076
	 * Test Purpose: Test if Peter Clarke cannot clear the database of entries
	 * Setup: 
	 * Input:
	 * Expected Output: !success, exception
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testClearDatabase2() throws SQLException {
		boolean success;
		try {
			when(statementMock.executeQuery("DELTE FROM CLASS100;")).thenReturn(resultMock);
			success = true;
		}
		catch (Exception e) {
			e.getStackTrace();
			success = false;
		}
		
		assertFalse(!success);
	}

	/*
	 * Test Id: DBC_CreateClassTable_077
	 * Test Purpose: Test if Peter Clarke can create the the database tables
	 * Setup: 
	 * Input:
	 * Expected Output: class100 table
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testCreateClassTable1() throws SQLException {
		String createQuery = "CREATE TABLE Class100(course_id INT UNSIGNED NOT NULL,"
                + "course_subject VARCHAR (20), course_name VARCHAR (20),"
                + "semester VARCHAR (20), start_date VARCHAR (20),"
                + "end_date VARCHAR (20),"
//              + "am_or_pm VARCHAR (5),"
                + "start_mon VARCHAR (20), end_mon VARCHAR (20),"
                + "start_tue VARCHAR (20), end_tue VARCHAR (20),"
                + "start_wed VARCHAR (20), end_wed VARCHAR (20),"
                + "start_thu VARCHAR (20), end_thu VARCHAR (20),"
                + "start_fri VARCHAR (20), end_fri VARCHAR (20),"
                + "start_sat VARCHAR (20), end_sat VARCHAR (20),"
                + "PRIMARY KEY (course_id))";
		
		
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery(createQuery)).thenReturn(resultMock);
	
		assertEquals("Database table creation test success", db.createClassTable(), 0);
	}
	
	/*
	 * Test Id: DBC_CreateClassTable_078
	 * Test Purpose: Test if Peter Clarke cannot create the database tables
	 * Setup: 
	 * Input:
	 * Expected Output: exception
	 * Actual Output: 
	 * Pass/Fail: 
	 */
	@Test
	public void testCreateClassTable2() throws SQLException {
		String createQuery = "CREATE TABLE Class100(course_id INT UNSIGNED NOT NULL,"
                + "course_subject VARCHAR (20), course_name VARCHAR (20),"
                + "semester VARCHAR (20), start_date VARCHAR (20),"
                + "end_date VARCHAR (20),"
//              + "am_or_pm VARCHAR (5),"
                + "start_mon VARCHAR (20), end_mon VARCHAR (20),"
                + "start_tue VARCHAR (20), end_tue VARCHAR (20),"
                + "start_wed VARCHAR (20), end_wed VARCHAR (20),"
                + "start_thu VARCHAR (20), end_thu VARCHAR (20),"
                + "start_fri VARCHAR (20), end_fri VARCHAR (20),"
                + "start_sat VARCHAR (20), end_sat VARCHAR (20),"
                + "PRIMARY KEY (course_id))";
		
		when(statementMock.executeQuery(createQuery)).thenReturn(resultMock);
		
		assertFalse(db.createClassTable() == -1);
	}
}
