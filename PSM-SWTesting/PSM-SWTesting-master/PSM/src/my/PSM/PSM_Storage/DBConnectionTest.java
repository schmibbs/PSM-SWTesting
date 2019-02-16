package my.PSM.PSM_Storage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class DBConnectionTest {
	
	@Mock
	Statement statementMock;
	@Mock
	ResultSet resultMock;
	@Mock
	Connection connectMock;
	
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private DBConnection db;
	
	
	
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

//	@Test
//	public void testDBConnection() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testConnectStringStringConnection() {
		assertEquals(0, db.connect("user", "pw", connectMock));
		//fail("Not yet implemented");
	}
//	@Test
//	public void testConnectStringStringString() {
//		//assertEquals(0, db.connect("db", "user", "pw"));
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testConnectStringString() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testDisconnect() {
		assertEquals(0, db.disconnect());
		//fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseID() {
		//assertEquals(0, db.fetchCourseID(000));
		fail("Not yet implemented");
	}

	@Test
	public void testGetEndDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseSubj() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseSemester() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseEnd() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartMon1() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_mon")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartMon(0));
		//fail("Not yet implemented");
	}

	@Test
	public void testFetchStartMon2() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_mon")).thenReturn("11:00");
		String temp = db.fetchStartMon(0);
		assertFalse("12:00"==temp);
	}
	
	
	@Test
	public void testFetchEndMon1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_mon")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndMon(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchEndMon2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_mon")).thenReturn("11:00");
		String temp = db.fetchEndMon(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchStartTue1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_tue")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartTue(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchStartTue2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_tue")).thenReturn("11:00");
		String temp = db.fetchStartTue(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchEndTue1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_tue")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndTue(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchEndTue2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_tue")).thenReturn("11:00");
		String temp = db.fetchEndTue(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchStartWed1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_wed")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartWed(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchStartWed2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_wed")).thenReturn("11:00");
		String temp = db.fetchStartWed(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchEndWed1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_wed")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndWed(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchEndWed2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_wed")).thenReturn("11:00");
		String temp = db.fetchEndWed(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchStartThu1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_thu")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartThu(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchStartThu2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_thu")).thenReturn("11:00");
		String temp = db.fetchStartThu(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchEndThu1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_thu")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndThu(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchEndThu2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_thu")).thenReturn("11:00");
		String temp = db.fetchStartThu(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchStartFri1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_fri")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartFri(0));
		//fail("Not yet implemented");
	}

	@Test
	public void testFetchStartFri2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("start_fri")).thenReturn("11:00");
		String temp = db.fetchStartFri(0);
		assertFalse("12:00"==temp);
	}
	
	@Test
	public void testFetchEndFri1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_fri")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndFri(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchEndFri2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.getResultSet()).thenReturn(resultMock);
		when(resultMock.getString("end_fri")).thenReturn("11:00");
		String temp = db.fetchEndFri(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchStartSat1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT start_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("start_sat")).thenReturn("11:00");
		assertEquals("11:00", db.fetchStartSat(0));
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFetchStartSat2() throws SQLException {
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT start_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("start_sat")).thenReturn("11:00");
		String temp = db.fetchStartSat(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testFetchEndSat1() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT end_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("end_sat")).thenReturn("12:00");
		assertEquals("12:00", db.fetchEndSat(0));
		//fail("Not yet implemented");
	}
	
	public void testFetchEndSat2() throws SQLException{
		when(connectMock.createStatement()).thenReturn(statementMock);
		when(statementMock.executeQuery("SELECT end_sat FROM Class100 WHERE course_id = " +0 +";")).thenReturn(resultMock);
		when(resultMock.getString("end_sat")).thenReturn("12:00");
		String temp = db.fetchEndSat(0);
		assertFalse("12:00"==temp);
	}

	@Test
	public void testStoreClassInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testStoreClassSched() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearDatabase() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClassTable() {
		fail("Not yet implemented");
	}

}
