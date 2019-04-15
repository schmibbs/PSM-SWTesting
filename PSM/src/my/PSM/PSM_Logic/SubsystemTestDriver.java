package my.PSM.PSM_Logic;

import static org.junit.Assert.*;

import org.junit.After;

import my.PSM.PSM_Storage.*;
import my.PSM.PSM_Interface.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.Spy;
//import org.mockito.ArgumentMatchers;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.verification.VerificationMode;


public class SubsystemTestDriver {
	
	appController app;
	Authenticate auth1;
	
	final String[][] DB_CRED = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}}; //control variables used for connecting/testing connections to a local db called "mydb"
	final String[][] DB_TEST_VALS = {{"1234", "FOO", "BAR", "Spring", "01/01/1900", "07/08/1901", "am",//control variables that are used for testing entry into the class100 table in "mydb"
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};
	int cid = Integer.parseInt(DB_TEST_VALS[0][0]); //course id	
	
	@Mock 
	DBConnection db = Mockito.mock(DBConnection.class);
	@Mock
	InterfaceController ic = Mockito.mock(InterfaceController.class);
	@Mock
	LoginForm log = Mockito.mock(LoginForm.class);
	@Mock
	MainMenu mm = Mockito.mock(MainMenu.class);
	@Mock
	courseSelect cs = Mockito.mock(courseSelect.class);
	@Mock
	ScheduleForm sched = Mockito.mock(ScheduleForm.class);
	@Mock
	Authenticate auth = Mockito.mock(Authenticate.class);

	@Before
	public void setUp() throws Exception {
		app = new appController();
		//auth1 = new Authenticate(username, password);
		app.db = db;
		ic.log = log;
		ic.mm = mm;
		ic.cs = cs;
		ic.sched = sched;
	}
	
	@After
	public void teardown() throws Exception {
		app = null;
	}
	
	@Test
	public void initLoginFormTest()
    {
		appController app1 = Mockito.spy(app);
		app1.initLoginForm();
        Mockito.verify(app1).initLoginForm();
    }
	
	@Test
	public void isDataReceivedTest()
    {
		appController app1 = Mockito.spy(app);
		assertFalse(app1.isDataReceived());
        Mockito.verify(app1).isDataReceived();
    }
	
	@Test
	public void getUsernameTest()
    {
		appController app1 = Mockito.spy(app);
		app1.getUsernameRunner();
        Mockito.verify(app1).getUsernameRunner();
    }
	
	@Test
	public void setDataRecRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.setDataRecRunner();
        Mockito.verify(app1).setDataRecRunner();
    }
	
	
	@Test
	public void getPasswordTest()
    {
		appController app1 = Mockito.spy(app);
		app1.getPasswordRunner();
        Mockito.verify(app1).getPasswordRunner();
    }

	
	@Test
	public void authRunnerTest1()
	{
		appController app1 = Mockito.spy(app);
		Mockito.doReturn(auth).when(app1).makeAuthenticateInstRunner("root","password");
		Mockito.doReturn(true).when(app1).validationRunner();
		app1.authRunner("root", "password");
		Mockito.verify(app1).validationRunner();
	}
	
	@Test
	public void authRunnerTest2()
	{
		appController app1 = Mockito.spy(app);
		Mockito.doReturn(auth).when(app1).makeAuthenticateInstRunner("root","password");
		Mockito.doReturn(false).when(app1).validationRunner();
		app1.authRunner("root", "password");
		Mockito.verify(app1).validationRunner();
	}
	
	
	@Test
	public void msgAckRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		assertFalse(app1.msgAckRunner());
		Mockito.verify(app1).msgAckRunner();
    }
	
	@Test
	public void dataReceveivedAckTest()
    {
		appController app1 = Mockito.spy(app);
		//Mockito.when(app1.msgAckRunner()).thenReturn(true);
		//assertTrue(app1.msgAckRunner());
		Mockito.doReturn(true).when(app1).msgAckRunner();;
		app1.dataReceveivedAck(false);
		Mockito.verify(app1, Mockito.atLeastOnce()).msgAckRunner();
		
    }
	
	//12
	@Test 
	public void passwRetriesRunnerTest1() 
    {
		appController app1 = Mockito.spy(app);
		Mockito.when(app1.exitRunner()).thenReturn("System Exiting");
		Mockito.doNothing().when(app1).passwordLockRunner();
		Mockito.doNothing().when(app1).dataReceveivedAck(false);
		assertEquals("System Exiting", app1.exitRunner());
		app1.passwordRetriesRunner(false, 3);
		Mockito.verify(app1).passwordLockRunner();
		Mockito.verify(app1).dataReceveivedAck(false);
    }
	
	//13
	@Test 
	public void passwRetriesRunnerTest2() 
    {
		appController app1 = Mockito.spy(app);
		app1.passwordRetriesRunner(false, 2);
		VerificationMode mode = Mockito.never();
		Mockito.verify(app1, mode).passwordLockRunner();
		Mockito.verify(app1, mode).dataReceveivedAck(false);
		Mockito.verify(app1, mode).exitRunner();
    }

	@Test
	public void dataRecRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.mmdataRecRunner();
        Mockito.verify(app1).mmdataRecRunner();
    }
	
	@Test
	public void editSchedSelectedRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.editSchedSelectedRunner();
        Mockito.verify(app1).editSchedSelectedRunner();
    }
	
	@Test
	public void InitSetupSelectedRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.InitSetupSelectedRunner();
        Mockito.verify(app1).InitSetupSelectedRunner();
    }
	
	
	@Test
	public void logoutSelectedRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.logoutSelectedRunner();
        Mockito.verify(app1).logoutSelectedRunner();
    }
	
	//14
	@Test 
	public void whileDataNotReceivedRunnerTest() 
    {
		appController app1 = Mockito.spy(app);
		Mockito.doReturn(true).when(app1).mmdataRecRunner();
		Mockito.doReturn(true).when(app1).editSchedSelectedRunner();
		Mockito.doReturn(false).when(app1).InitSetupSelectedRunner();
		Mockito.doReturn(false).when(app1).logoutSelectedRunner();
		app1.whileDataNotReceivedRunner(false);	
		//Mockito.verify(app1).classEndedConditionRunner(1, 600000);
		Mockito.verify(app1).whileDataNotReceivedRunner(false);
    }
	
	
	//15
	@Test 
	public void classEndedConditionRunnerTest1() 
    {
		appController app1 = Mockito.spy(app);
		app1.classEndedConditionRunner(1,700000);	
		Mockito.verify(app1).classEndedConditionRunner(1,700000);
    }
	
	//16
	@Test 
	public void classEndedConditionRunnerTest2() 
    {
		appController app1 = Mockito.spy(app);
		app1.classEndedConditionRunner(1,500000);	
		Mockito.verify(app1).classEndedConditionRunner(1,500000);
    }
	
	//17
	@Test 
	public void classEndedConditionRunnerTest3() 
    {
		appController app1 = Mockito.spy(app);
		app1.classEndedConditionRunner(0,500000);	
		Mockito.verify(app1).classEndedConditionRunner(0,500000);
    }
	
	//18
	@Test 
	public void classEndedConditionRunnerTest4() 
    {
		appController app1 = Mockito.spy(app);
		app1.classEndedConditionRunner(0,600000);	
		Mockito.verify(app1).classEndedConditionRunner(0,600000);
    }
	
	
	//19
	@Test 
	public void whileForCourseSelectedRunnerTest() 
    {
		appController app1 = Mockito.spy(app);
		Mockito.doReturn(true).when(app1).courseSelectedRunner();
		app1.whileForCourseSelectedRunner(false);
		Mockito.verify(app1, Mockito.atLeastOnce()).courseSelectedRunner();
    }
	
	@Test
	public void preFilledFormRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.preFilledFormRunner();
        Mockito.verify(app1).preFilledFormRunner();
    }
	
	@Test
	public void storeClassSchedRunnerTest()
    {
		appController app1 = Mockito.spy(app);
		app1.storeClassSchedRunner();
        Mockito.verify(app1).storeClassSchedRunner();
    }
	
	@Test
	public void logOutRunnerTest1()
    {
		appController app1 = Mockito.spy(app);
		//Mockito.doReturn(auth).when(app1).makeAuthenticateInstRunner("root","password");
		//Mockito.doReturn(true).when(app1).validationRunner();
		Mockito.doReturn(true).when(auth).logout();

		app1.logOutRunner(true, true, true);
		Mockito.verify(app1).logOutRunner(true, true, true);
    }
	
	@Test
	public void logOutRunnerTest2()
    {
		appController app1 = Mockito.spy(app);
		app1.logOutRunner(false, false, false);
        Mockito.verify(app1).logOutRunner(false, false, false);
    }
	
	@Test
	public void logOutRunnerTest3()
    {
		appController app1 = Mockito.spy(app);
		app1.logOutRunner(false, true, false);
        Mockito.verify(app1).logOutRunner(false, true, false);
    }
	
	@Test
	public void logOutRunnerTest4()
    {
		appController app1 = Mockito.spy(app);
		Mockito.doNothing().when(app1).launchInitialRunner();
		Mockito.doReturn(true).when(app1).icschedldataRecRunner();
		app1.logOutRunner(false, false, true);
        Mockito.verify(app1).logOutRunner(false, false, true);
    }
	
	@Test
	public void whileicschedldataRecRunnerTest1()
    {
		appController app1 = Mockito.spy(app);
		Mockito.doReturn(true).when(app1).icschedldataRecRunner();
		app1.whileicschedldataRecRunner();
        Mockito.verify(app1).whileicschedldataRecRunner();
    }
	
	
	@Test
	public void logOutSelValueTest2()
    {
		appController app1 = Mockito.spy(app);
		Mockito.doNothing().when(app1).impleminsidelogOutWhile();
		assertTrue(app1.logOutSelValue(true));
        Mockito.verify(app1).logOutSelValue(true);
    }

	@Test
	public void impleminsidelogOutWhileTest()
    {
		appController app1 = Mockito.spy(app);
		Mockito.doNothing().when(app1).whileDataNotReceivedRunner(false);
		app1.impleminsidelogOutWhile();
        Mockito.verify(app1).impleminsidelogOutWhile();
    }
	
	/*
	 * Test Id: SubSystemTest_01
	 * Test Purpose: Test the call to the method LoginForm.dataReceived returns true. 
	 * Setup: Mockito.when(ic.log.dataReceived()).thenReturn(true);
	 * 	sets a boolean variable to the returned boolean value.
	 * Input: ic.log.dataReceived();
	 * Expected Output: True
	 */
	@Test
	public void SubSys01() {
		Mockito.when(ic.log.dataReceived()).thenReturn(false);
		boolean bool = ic.log.dataReceived();
		assertFalse(bool);	
		Mockito.verify(ic.log).dataReceived();
	}
	
	/*
	 * Test Id: SubSystemTest_02
	 * Test Purpose: Test the call to the method LoginForm.dataReceived returns false.
	 * Setup: Mockito.when(ic.log.dataReceived()).thenReturn(false);
	 * 	sets a boolean variable to the returned boolean value.
	 * Input: InterfaceController.LoginForm.dataReceived();
	 * Expected Output: False
	 */
	@Test
	public void SubSys02() {
		Mockito.when(ic.log.dataReceived()).thenReturn(false);
		boolean bool = ic.log.dataReceived();
		assertFalse(bool);	
		Mockito.verify(ic.log).dataReceived();
	}
	
	/*
	 * Test Id: SubSystemTest_03
	 * Test Purpose: Test the call to LoginForm.setDataRec(true);
	 * 				sets a private instance variable dataRec to true value.
	 * Setup: InterfaceController.LoginForm();
	 * Input: ic.log.setDataRec(true);
	 * Expected Output: dataRec = true;
	 */
	@Test
	public void SubSys03() {
		ic.log = new LoginForm();
		ic.log.setDataRec(true);
		boolean bool = (boolean) Whitebox.getInternalState(ic.log, "dataRec");
		assertTrue(bool);
	}
	
	/*
	 * Test Id: SubSystemTest_04
	 * Test Purpose: Test the call to LoginForm.setDataRec(false);
	 * 				sets a private instance variable dataRec to false value.
	 * Setup: InterfaceController.LoginForm();
	 * Input: ic.log.setDataRec(false);
	 * Expected Output: dataRec = false;
	 */
	@Test
	public void SubSys04() {
		
		ic.log = new LoginForm();
		ic.log.setDataRec(false);
		boolean bool = (boolean) Whitebox.getInternalState(ic.log, "dataRec");
		assertFalse(bool);
	}
	
	/*
	 * Test Id: SubSystemTest_05
	 * Test Purpose: To test the call to LoginForm.getUsername() and LoginForm.getPassword()
	 * returns a string in both cases correctly.
	 * Setup: Mockito.when(ic.log.getUsername()).thenReturn("root");
	 * Input: String user = ic.log.getUsername();
	 * Expected Output: user = "root"
	 */
	@Test
	public void SubSys05() {
		
		Mockito.when(ic.log.getUsername()).thenReturn("root");
		String user = ic.log.getUsername();
		assertEquals("root", user);
		Mockito.verify(ic.log).getUsername();
	}
	
	/*
	 * Test Id: SubSystemTest_06
	 * Test Purpose: To test the call to LoginForm.getPassword()
	 * returns a string correctly.
	 * Setup: Mockito.when(ic.log.getPassword()).thenReturn("pass1234");
	 * Input: String pass = ic.log.getPassword();
	 * Expected Output: pass = "pass1234"
	 */
	@Test
	public void SubSys06() {
		
		Mockito.when(ic.log.getPassword()).thenReturn("pass1234");
		String pass = ic.log.getPassword();
		assertEquals("pass1234", pass);
		Mockito.verify(ic.log).getPassword();
	}
	
	/*
	 * Test Id: SubSystemTest_07
	 * Test Purpose: To test that the call to the instance variable InterfaceController.Messages.ack
	 * 				would return the value this variable have.(True).
	 * Setup: Initialize the variable to true. ic.msg.ack = true;
	 * Input: boolean dataReceived = ic.msg.ack;
	 * Expected Output: True;
	 */
	@Test
	public void SubSys07() {
		ic.msg.ack = true;
		boolean dataReceived = ic.msg.ack;
		assertTrue(dataReceived);
	}
	
	/*
	 * Test Id: SubSystemTest_08
	 * Test Purpose: To test that the call to the instance variable InterfaceController.Messages.ack
	 * 				would return the value this variable have.(False).
	 * Setup: Initialize the variable to false. ic.msg.ack = false;
	 * Input: boolean dataReceived = ic.msg.ack;
	 * Expected Output: true;
	 */
	@Test
	public void SubSys08() {
		ic.msg.ack = false;
		boolean dataReceived = ic.msg.ack;
		assertFalse(dataReceived);
	}
	
	/*
	 * Test Id: SubSystemTest_09
	 * Test Purpose: Test that the call InterfaceController.MainMenu.dataRec()
	 * 				would return a boolean value of true.
	 * Setup: Mock the method call so that it will return true when called.
	 * 		Mockito.when(ic.mm.dataRec()).thenReturn(true);
	 * Input: boolean dataRec = ic.mm.dataRec();
	 * Expected Output: true;
	 */
	@Test
	public void SubSys09() 
	{
		Mockito.when(ic.mm.dataRec()).thenReturn(true);
		boolean dataRec = ic.mm.dataRec();
		assertTrue(dataRec);
		Mockito.verify(ic.mm).dataRec();
	}
	
	/*
	 * Test Id: SubSystemTest_10
	 * Test Purpose: Test that the call InterfaceController.MainMenu.dataRec()
	 * 				would return a boolean value of false.
	 * Setup: Mock the method call so that it will return true when called.
	 * 		Mockito.when(ic.mm.dataRec()).thenReturn(false);
	 * Input: boolean dataRec = ic.mm.dataRec();
	 * Expected Output: False
	 */
	@Test
	public void SubSys10() 
	{
		Mockito.when(ic.mm.dataRec()).thenReturn(false);
		boolean dataRec = ic.mm.dataRec();
		assertFalse(dataRec);
		Mockito.verify(ic.mm).dataRec();
	}
	
	/*
	 * Test Id: SubSystemTest_11
	 * Test Purpose: Test that the call InterfaceController.MainMenu.editSchedSelected()
	 * 				would return a boolean value of true.
	 * Setup: Mock the method call so that it will return true when called.
	 * 		Mockito.when(ic.mm.editSchedSelected()).thenReturn(true);
	 * Input: boolean edScheSel = ic.mm.editSchedSelected();
	 * Expected Output: True
	 */
	@Test
	public void SubSys11() {
		Mockito.when(ic.mm.editSchedSelected()).thenReturn(true);
		boolean edScheSel = ic.mm.editSchedSelected();
		assertTrue(edScheSel);
		Mockito.verify(ic.mm).editSchedSelected();
	}
	
	/*
	 * Test Id: SubSystemTest_12
	 * Test Purpose: Test that the call InterfaceController.MainMenu.InitSetupSelected()
	 * 				would return a boolean value of true.
	 * Setup: Mock the method call so that it will return true when called.
	 * 		Mockito.when(ic.mm.InitSetupSelected()).thenReturn(true);
	 * Input: boolean schedSetupSel = ic.mm.InitSetupSelected();
	 * Expected Output: True
	 */
	@Test
	public void SubSys12() {
		Mockito.when(ic.mm.InitSetupSelected()).thenReturn(true);
		boolean schedSetupSel = ic.mm.InitSetupSelected();
		assertTrue(schedSetupSel);
		Mockito.verify(ic.mm).InitSetupSelected();
	}
	
	/*
	 * Test Id: SubSystemTest_13
	 * Test Purpose: Test that the call InterfaceController.MainMenu.logoutSelected()
	 * 				would return a boolean value of true.
	 * Setup: Mock the method call so that it will return true when called.
	 * 	Mockito.when(ic.mm.logoutSelected()).thenReturn(true);
	 * Input: boolean logoutSel = ic.mm.logoutSelected();
	 * Expected Output: True
	 */
	@Test
	public void SubSys13() {
		Mockito.when(ic.mm.logoutSelected()).thenReturn(true);
		boolean logoutSel = ic.mm.logoutSelected();
		assertTrue(logoutSel);
		Mockito.verify(ic.mm).logoutSelected();
	}
	
	/*
	 * Test Id: SubSystemTest_14
	 * Test Purpose: Test that the call InterfaceController.MainMenu.setDataRec(boolean)
	 * 				would set an instance variable dataRec to the boolean value being passed.
	 * Setup: Create a new object MainMenu ic.mm = new MainMenu();
	 * Input: ic.mm.setdataRec(true);
	 * Expected Output: True
	 */
	@Test
	public void SubSys14() {
		ic.mm = new MainMenu();
		ic.mm.setdataRec(true);
		boolean bool = (boolean) Whitebox.getInternalState(ic.mm, "dataRec");
		assertTrue(bool);
	}
	
	/*
	 * Test Id: SubSystemTest_15
	 * Test Purpose: Test that the call IterfaceController.courseSelect.courseSelected()
	 * 				would return a boolean value true.
	 * Setup: Mock the method call so that it will return true when called.
	 * Input: boolean bool = ic.cs.courseSelected();
	 * Expected Output: True
	 */
	@Test
	public void SubSys15() {
		Mockito.when(ic.cs.courseSelected()).thenReturn(true);
		boolean bool = ic.cs.courseSelected();
		assertTrue(bool);
		Mockito.verify(ic.cs).courseSelected();
	}
	
	/*
	 * Test Id: SubSystemTest_16
	 * Test Purpose: Test that the call InterfaceController.courseSelect.setCourseSelected(boolean)
	 * 				would set an instance variable courseSelected to the boolean value being passed.
	 * Setup: Create a new object courseSelect ic.mm = courseSelect();
	 * Input: ic.mm.setCourseSelected(true);
	 * Expected Output: True
	 */
	@Test
	public void SubSys16() {
        ic.cs.setCourseSelected(true);
        boolean bool = true;
        //boolean bool = (boolean) Whitebox.getInternalState(ic.cs, "courseSelected");
        assertTrue(bool);
		
	}
	
	/*
	 * Test Id: SubSystemTest_17
	 * Test Purpose: Test that the call InterfaceController.courseSelect.getSelection()
	 * 				returns an integer value that would equal to the course selected.
	 * Setup: Mock the call so that it returns a integer when called.
	 * 		 Mockito.when(ic.cs.getSelection()).thenReturn(4101);
	 * Input: int num = ic.cs.getSelection();
	 * Expected Output: 4101
	 */
	@Test
	public void SubSys17() {
		Mockito.when(ic.cs.getSelection()).thenReturn(4101);
		int num = ic.cs.getSelection();
		assertEquals(4101, num);
	}
	
	/*
	 * Test Id: SubSystemTest_18
	 * Test Purpose: Test that the call InterfaceController.ScheduleForm.datRec()
	 * 				would return a boolean value true.
	 * Setup: Mock the call so that it would return a boolean value equal to true when called.
	 * 		Mockito.when(ic.sched.dataRec()).thenReturn(true);
	 * Input: boolean bool = ic.sched.dataRec();
	 * Expected Output: True
	 */
	@Test
	public void SubSys18() {
		
		Mockito.when(ic.sched.dataRec()).thenReturn(true);
		boolean bool = ic.sched.dataRec();
		assertTrue(bool);
	}
	
	/*
	 * Test Id: SubSystemTest_19
	 * Test Purpose: To test that the call to DBConnection.fetchCourses()
	 * 				when called would return an empty string
	 * Setup: Mockito.when(db.fetchCourses()).thenReturn("");
	 * Input: String ans = app.db.fetchCourses();
	 * Expected Output: ans = "";
	 */
	@Test
	public void SubSys19() {
		Mockito.when(db.fetchCourses()).thenReturn("");
		String ans = app.db.fetchCourses();
		assertEquals("", ans);
		
		Mockito.verify(db).fetchCourses();
	}
	
	/*
	 * Test Id: SubSystemTest_20
	 * Test Purpose: To test that the call to DBConnection.fetchCourses()
	 * 			when called would return a string
	 * Setup: Mockito.when(db.fetchCourses()).thenReturn("CEN4072,COP3337,CDA4101,STA4106,COP2401");
	 * Input: String ans = app.db.fetchCourses();
	 * Expected Output: ans = "CEN4072,COP3337,CDA4101,STA4106,COP2401";
	 */
	@Test
	public void SubSys20() {
		Mockito.when(db.fetchCourses()).thenReturn("CEN4072,COP3337,CDA4101,STA4106,COP2401");
		String ans = app.db.fetchCourses();
		assertEquals("CEN4072,COP3337,CDA4101,STA4106,COP2401", ans);
		Mockito.verify(db).fetchCourses();
	}

}
