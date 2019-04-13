/*
* class that is to be used for sub-system testing. Based on code 
* developed by Esteban . This version will have a preference on 
* testing coverage 
*/


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

import my.PSM.PSM_Storage.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class SubsystemTestDriver {
	//I think jesse made these; ask later
	@Mock
	Statement statementMock;
	@Mock
	ResultSet resultMock;
	@Mock
	Connection connectMock;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private DBConnection db;
	
	final String[][] DB_CRED = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}}; //control variables used for connecting/testing connections to a local db called "mydb"
	final String[][] DB_TEST_VALS = {{"1234", "FOO", "BAR", "Spring", "01/01/1900", "07/08/1901", "am",//control variables that are used for testing entry into the class100 table in "mydb"
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};
	int cid = Integer.parseInt(DB_CRED[0][0]);	//course id
	
    //end Esteban's variables
	
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
	
    //begin Esteban's variables
    private static boolean dataRec = false;
    private static String username;
    private static String password;

    private static boolean dataRec2;
    private static boolean editSched;
    private static boolean logout;
    private static boolean initSetup;
 
	private static int selection;
	private static boolean courseSelected;
	public String [] listCourses;

    public static int defCourseID;
    public static  String defSub;
    public static  String defSemester;
    public static  String defCourseName;
    public static  String defCourseStart;
    public static  String defCourseEnd;
    public static  String defMonStart;
    public static  String defMonEnd;
    public static  String defTueStart;
    public static  String defTueEnd;
    public static  String defWedStart;
    public static  String defWedEnd;
    public static  String defThuStart;
    public static  String defThuEnd;
    public static  String defFriStart;
    public static  String defFriEnd;
    public static  String defSatStart;
    public static  String defSatEnd;
    
    public static int newCourseID;
    public static String newSub;
    public static String newSemester;
    public static String newCourseName;
    public static String newCourseStart;
    public static String newCourseEnd;
    public static String newMonStart;
    public static String newMonEnd;
    public static String newTueStart;
    public static String newTueEnd;
    public static String newWedStart;
    public static String newWedEnd;
    public static String newThuStart;
    public static String newThuEnd;
    public static String newFriStart;
    public static String newFriEnd;
    public static String newSatStart;
    public static String newSatEnd;

    public static boolean dataReceived;

    public static boolean ack;
    //end Esteban's variables

    /** <TEMPLATE> 
    * Test ID: PSM_Logic_Subsystem_000
	final String[][] DB_CRED = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}}; //control variables used for connecting/testing connections to a local db called "mydb"
	final String[][] DB_TEST_VALS = {{"1234", "FOO", "BAR", "Spring", "01/01/1900", "07/08/1901", "am",//control variables that are used for testing entry into the class100 table in "mydb"
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};

    /**
    * Test ID: PSM_Logic_Subsystem_001
    * Test purpose: Test ServicesGoingThroughClassesBeingTested> WRITE IN REGULAR ENGLISH AND MUST BE SIMILAR TO WHAT IS IN DOCUMENT
    * Setup: 1. Create necessary mocks
             2. Create instances of classes to test
             3. See what it does
    * Input: 
    * Expected output:
    * </TEMPLATE> 
    */
	
    @Test
    /**
    * Test ID: PSM_Logic_Subsystem_001
    * Test purpose: Test if you can log in to the main menu
    * Setup: 1. Create a db mock connection
             2. Pass credentials to where the login form usually does
             3. Observe results
    * Input: clarkE = DB_CRED[0][1];
			 luggagePW = DB_CRED[0][2];
    * Expected output: Peter Clarke is logged into the PSM system.
    */	
    public void subSystem_testClassEntry() throws Exception {
		connectMock = mock(Connection.class);
		
		//pass login info here
		String localHost = DB_CRED[0][0];
		String clarkE = DB_CRED[0][1];
		String luggagePW = DB_CRED[0][2];
		
		try {
		connectMock = (Connection) DriverManager.getConnection(localHost, clarkE, luggagePW);
		}
		catch (Exception e){
			e.getStackTrace();
		}
		//logged in(?)
    }
    
    @Test
    /**
    * Test ID: PSM_Logic_Subsystem_002
    * Test purpose: Test if you can add a class to the database
    * Setup: 1. Create a db mock connection
    * 		 2. Create a mock result set
             3. Log-in
             4. Mimic clicking 'add class schedule' (go straight to it, no input)
             5. Add data to the fields MANUALLY 
             6. Display result set to see if data was accepted
    * Input: defCourseid = DB_TEST_VALS[0][0]
    * 		 defSub= DB_TEST_VALS[0][1]
    * 		 defCourseName= DB_TEST_VALS[0][2]
    * 		 defSemester = DB_TEST_VALS[0][3]
    * 		 defStartDate = DB_TEST_VALS[0][4]
    * 		 defEndDate = DB_TEST_VALS[0][5]
    * 		 defMonStart = DB_TEST_VALS[0][7]
    * 		 defMonEnd = DB_TEST_VALS[0][8]
    * 		 defWedStart = DB_TEST_VALS[0][11]
    * 		 defWedEnd = DB_TEST_VALS[0][12]
    * Expected output: A new class is added to the database 
    */		
	public void subSystem_nameLater() {
    	assertTrue(true);
    }
}
