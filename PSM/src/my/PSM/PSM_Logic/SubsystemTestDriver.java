package my.PSM.PSM_Logic;
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

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.*;

import my.PSM.LoginForm;
import my.PSM.PSM_Storage.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class SubsystemTestDriver {
	private static final long TENMIN = 600000;
	//jesse's mocks
	@Mock
	Statement statementMock;
	@Mock
	ResultSet resultMock;
	@Mock
	Connection connectMock;
	
	//my mocks
	@Mock
	static
	InterfaceController ic;
	@Mock
	static
	Authenticate auth;
	@Mock
	static
	LoginForm mockLogin;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private static DBConnection db;
	
	final String[][] DB_CRED = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}}; //control variables used for connecting/testing connections to a local db called "mydb"
	final String[][] DB_TEST_VALS = {{"1234", "FOO", "BAR", "Spring", "01/01/1900", "07/08/1901", "am",//control variables that are used for testing entry into the class100 table in "mydb"
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};
	int cid = Integer.parseInt(DB_TEST_VALS[0][0]);	//course id
	
    //end Esteban's variables
	
	@Before
	public void setUp() throws Exception {
		statementMock = mock(Statement.class);
		resultMock = mock(ResultSet.class);
		connectMock = mock(Connection.class);
		
		ic = mock(InterfaceController.class);
		auth = mock(Authenticate.class);
		mockLogin = mock(LoginForm.class);
		
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
    private static int clearDate, clearMonth, clearYear;
    private static int hr = 0;
    private static int min = 0;
    private static boolean dbCleared = false;

    /** <TEMPLATE> 
    * Test ID: PSM_Logic_Subsystem_000
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
    public void subSystem_testLogin() throws Exception {
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
		assertTrue(getLoggedIn());
		
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
	public void subSystem_addNewClass() {
    	assertTrue(true);
    }
  
  
    @Test
    public void goofus() throws Exception {
    	String[] args = {""};
    	appRunner(args);
    }
    //====================================================================
    /**
    ADVICE FROM DAVID
    Re-create the main method
        if it calls DBConnection or ANYthing in other packages, stub the classes from the package that calls it
        create tests for main to see if it is doing what yuo expect it to do
    fetchCouses(Interfaces try to pull from here) can be left alone but may need to stub 
        happens to be somehting the interfaces call, comes from the DBConnection, create a method in appController to call method instead.
        anytime a call is made to DBConn, replace that call with the version that was in appController
        it wont call but it is in the db ... logic->db so you need to create something in the db ot test that for you
     */

static boolean loggin;
boolean dataReceived2;	//non static version of data received. 
static boolean loggedin;
static boolean edSchedSel;
static boolean logoutSel;
static boolean schedSetupSel;
static int counter = 0;
static int courseSel;
static long classEnded = 0;

    //Tester-Created Get(s)
    public boolean getDataReceived(){
        return dataReceived;
    }
    public boolean getLoggedIn(){
        return loggedin;
    }
    public boolean getLogOutSel(){
        return logoutSel;
    }
    public boolean getEdSchedSel(){
        return edSchedSel;
    }
    public boolean getSchedSetupSel(){
        return schedSetupSel;
    }
    public int getCounter(){
        return counter;
    }
    public int getCourseSel(){
        return courseSel;
    }
    public long getClassEnded(){
        return classEnded;
    }

    
    
    //From original code
    public static void LogIn()
    {      
        if(db.connect(username,password) == 0)
            loggedin = true;
        else
            loggedin = false;        
    }

    //From original code
    public static void sleep(int milli)
    {
          try { 
           Thread.sleep(milli);
        } catch (InterruptedException ex) {
           Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void appRunner(String args[]) throws Exception {
    	boolean dataRcv1 = false;
        //TEST value: loggedin [X]
    	assertFalse(getLoggedIn());
    	
       while(!loggedin)
       {     
           //Mock the IC, but don't call the UI [X]     
           ic.Initiate_Login_Form();
           
            //Check if log-in has been entered. [X]
           //subSystem_testLogin();
            do
            {
                //TEST values: dataReceived [X]
                //Mock dataRecieved, do not call ic.log.dataReceived
            	mockLogin = mock(LoginForm.class);
            	mockLogin.dataReceived();
                
                assertTrue(dataRcv1);
               
                
            }while(!dataReceived);
//            //Mock a IC for setDataRec
//            //UI info, needs a Mock for ic.log.*
            branchLogic_1();
            
            //TEST values: auth method check user,pw
            auth = new Authenticate(username,password);
            assertEquals("Proper username", DB_CRED[0][1], auth.username);
            assertEquals("Proper password", DB_CRED[0][2], auth.username);
            if(auth.validate_Login()){
            	//branchLogic_2
                loggedin = true;
                //Check state change 
                auth.logout();
                //Mock this
                //db.connect(username,password);
            }
            
              //Change state change: loggedin
            if(!loggedin){
            	////branchLogic_3
                //ic.Initiate_IncorrectLogin();
                counter++;
                //Check state change
                while(!dataReceived)
                {
                //TEST values: dataReceived
                //Mock dataRecieved, do not call ic.msg.ack
                    dataReceived = ic.msg.ack;
                }
                dataReceived = false;
                //Mock IC for false.
                //ic.msg.ack = false;
                
            }
            //Check state change: counter
            if(counter >= 3){
            ////branchLogic_4
                //Do not call ic.passwordLock()
                //ic.passwordLock();
                //Check state change: dataReceived
                while(!dataReceived)
                {
                    //Mock ic.msg.ack
                    //dataReceived = ic.msg.ack;
                    
                }
                //Expect System Exit
                System.exit(0);
             }
       }
	   //Do not call ic.Initiate_MainMenu
       //ic.Initiate_MainMenu();
       //Check state change in checkClear() method
       if(checkClear())
       {
           //Stub DB Connection, rewrite below for stub VVVV
           //db.clearDatabase();
       }
       //Check state change in checkTimes()
       checkTimes();
       //Check state change in logoutSel
       while(!logoutSel)
       {
    	////branchLogic_5
           
           long newCurrentTime;
           //Check state dataReceived
           while(!dataReceived)
           {
        	////branchLogic_6 LOOK UP MIGHT BE SWAPPED WITH 5
               //MUST MOCK dataReceived, edSchedSel, schedSetupSel, logoutSel
               //Don't call anything from ic.mm.*
               dataReceived = ic.mm.dataRec();
               edSchedSel = ic.mm.editSchedSelected();
               schedSetupSel = ic.mm.InitSetupSelected();
               logoutSel = ic.mm.logoutSelected();

               //System.out.println("Class end time: " +classEnded);
               //System.out.println("Current time: " +System.currentTimeMillis());
                       
               //Get expected status for classEnded if you wnt this test this portion.
               if(classEnded != 0 && System.currentTimeMillis() - classEnded >= TENMIN)
               {
                 //System.out.println("EXIT");
                 //Expect system exit
                 System.exit(0);   
               }

               //Not needed
               //sleep(500);
           }
           newCurrentTime = 0;

           //ic.mm.setdataRec(false);
           //Check state dataReceived
           dataReceived = false;

            //Get status for logoutSet
           if(logoutSel)
           {
        	////branchLogic_7
               //Get status of auth
               auth.logout();

               //Mock of IC
               ic.Initiate_Logout();

           }
           //Get edSchedSel status
           else if(edSchedSel)
           {
        	////branchLogic_8
               //Edit Schedule 
               //ic.Course_Select_Form();

                //Get status of dataReceived
               while(!dataReceived)
               {
                   //Mock dataReceived VVVVV
                   //dataReceived = ic.cs.courseSelected();
                  //Unnneeded
                  // sleep(300);
                //   System.out.println("test");
               }

               //ic.cs.setCourseSelected(false);
               //get status change, you know the idea
               dataReceived = false;
                
                //Mock ic.cs.getSelection
               courseSel = ic.cs.getSelection();
               getData(courseSel);
               
               //Mock this VV
               //ic.Pre_Filled_Form(courseSel,defSub,defCourseName,defSemester,defCourseStart,
               //        defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
               //        defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
               
               //Get status
               while(!dataReceived)
               {
                   //Mock dataReceived 
                   dataReceived = ic.edSched.dataRec(); 
                   //sleep(300);
               }
               
               
               dataReceived = false;
               //Mock of IC
               ic.edSched.setDataRec(false);

               //System.out.println("Save has been pressed" +ic.edSched.newMonStart);

                //***Stub this DB
               db.storeClassSched(ic.edSched.defCourseID, ic.edSched.newCourseStart, ic.edSched.newCourseEnd, 
                       ic.edSched.newMonStart, ic.edSched.newMonEnd, ic.edSched.newTueStart, ic.edSched.newTueEnd, 
                       ic.edSched.newWedStart, ic.edSched.newWedEnd, ic.edSched.newThuStart, ic.edSched.newThuEnd, 
                       ic.edSched.newFriStart, ic.edSched.newFriEnd, ic.edSched.newSatStart, ic.edSched.newSatEnd);


           }
           //Expected status of schedSetupSel
           else if(schedSetupSel)
           {
        	////branchLogic_9
                //Mock this VVV, you know the drill
               ic.sched.launchInitial();

               //Initial Schedule Setup
               //VVV This uses the same mock as above.
               while(!ic.sched.dataRec())
               {////branchLogic_10
                   //Mock again
                   dataReceived = ic.sched.dataRec();
                   //sleep(300);
               }
               dataReceived = false;

                //Mock again
               ic.sched.setDataRec(false);
               
               //Stub these DB(s)
               db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,ic.sched.newSemester);
               db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                       ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                       ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                       ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd);

           }

           dataReceived = false;
           
          // db.disconnect();
       }

    }

    //
    //Used Methods Below outside of main
    //

    //TODO: Make these stubs do something
    private static void getData(int courseSel2) {
		// TODO Auto-generated method stub
		
	}

    private static void dateParser(String string) {
		// TODO Auto-generated method stub
		
	}
    
	private static Date getEndTime(int hr, int min) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Date get5BeforeEnd(int hr, int min) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Date get15BeforeEnd(int hr, int min) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	private static void timerParser(String defMonEnd2) {
		// TODO Auto-generated method stub
		
	}
    
    private static TimerTask dbClear = new TimerTask()
    {//TODO 
        public void run()
        {
           db.clearDatabase();
        }
    };
    
    private static TimerTask popup15min = new TimerTask()
    {//TODO 
        public void run()
        {
            //ic.msg.FifteenMinWarning();
        }
    };
	
    private static TimerTask popup5min = new TimerTask()
    {//TODO 
        public void run()
        {
            //ic.msg.FiveMinWarning();
    	}
    };
    
    
    private static TimerTask endofclass = new TimerTask()
    {//TODO 
        public void run()
        {
            //ic.msg.endClassWarning();
            classEnded = System.currentTimeMillis();
        }
    };
    
    
    private static TimerTask systemExit = new TimerTask()
    {//TODO 
        public void run()
        {
            System.exit(0);
        }
    };
	
	public static boolean checkClear()
    {
        ArrayList<String> endDates = db.getEndDates();
        Calendar endCal = new GregorianCalendar();
        Calendar now = Calendar.getInstance();
        
        for(int i = 0; i < endDates.size(); i++)
        {
            dateParser(endDates.get(i));
            
            //System.out.println("Day : " +clearDate);
            //System.out.println("Month : " +clearMonth);
            //System.out.println("Year : " +clearYear);

			endCal.set(clearYear + 2000, clearMonth-1, clearDate);
            if(now.compareTo(endCal) <= 0)
               return false;
                
        }
        return true;
                
    }

	public static void checkTimes()
    {
        ArrayList<Integer> courseList = db.getCourses();
        
        Calendar tempC = new GregorianCalendar();
        int currentDay = tempC.get(tempC.DAY_OF_WEEK);
        tempC.setTimeInMillis(System.currentTimeMillis());
        //System.out.println("Curr Day: " +currentDay);
        Date fifteenMin;
        Date fiveMin;
        Date endClass;
        
        for(int i = 0; i < courseList.size(); i++)
        {
            boolean isNull = true;
            getData(courseList.get(i).intValue());
            Timer newTimer = new FutureTimer();
            
            if(currentDay == 2 && defMonEnd.compareTo("") != 0)
            {
                timerParser(defMonEnd);
                isNull = false;
            }
            else if(currentDay == 3 && defTueEnd.compareTo("") != 0)
            {
                timerParser(defTueEnd);
                isNull = false;
            }
            else if(currentDay == 4 && defWedEnd.compareTo("") != 0)
            {
                timerParser(defWedEnd);
                isNull = false;
            }
            else if(currentDay == 5 && defThuEnd.compareTo("") != 0)
            {
                timerParser(defThuEnd);
                isNull = false;
            }
            else if(currentDay == 6 && defFriEnd.compareTo("") != 0)
            {
                timerParser(defFriEnd);
                isNull = false;
            }
            
            else if(currentDay == 7 && defSatEnd.compareTo("") != 0)
            {
                
                timerParser(defSatEnd);
                isNull = false;
            }
            
//            int hr = 1;
//            int min = 0;
            if(!isNull){
//            	TimerTask popup5min = null;
//            	TimerTask popup15min = null;
//            	TimerTask endofclass = null;
            	
                fiveMin = get5BeforeEnd(hr, min);
				newTimer.schedule(popup5min, fiveMin);
                fifteenMin = get15BeforeEnd(hr, min);              
                newTimer.schedule(popup15min, fifteenMin);
                endClass = getEndTime(hr, min);
                newTimer.schedule(endofclass, endClass);
            }
        }   
       
}

	/**
	 * method that is used to control logic after a branch is entered in the usual main
	 * Done since you have control when the branch is entered vs the regular main doing it whenver
	 * 
	 * This is after while(!datarecieved) on line 99
	 * */
	public static void branchLogic_1() {
		ic = mock(InterfaceController.class);
		boolean dataRecieved;
		ic.log.setDataRec(false);
		dataRecieved = false;
		
		username = ic.log.getUsername();
		password = ic.log.getPassword();
		
		auth = new Authenticate(username, password);
	}
	
	/**
	 * auth.validate_Login() == true on line 108
	 */
	public static void branchLogic_2() {
        loggedin = true;
        auth.logout();
        db.connect(username,password);
	}
	
	/**
	 * !loggedIn == true on line 114
	 */
	public static void branchLogic_3() {
        ic.Initiate_IncorrectLogin();
        counter++;
        
        while(!dataReceived)
        {
            dataReceived = ic.msg.ack;
        }
        dataReceived = false;
        ic.msg.ack = false;
	}
	
	/**
	 * counter >= 3 line 125
	 */
	public static void branchLogic_4() {
        ic.passwordLock();
        while(!dataReceived)
        {
            dataReceived = ic.msg.ack;
            
        }
        System.exit(0);
	}	
	
	/**
	 * !dataRcv== true on line 149
	 */
	public static void branchLogic_5() {
        dataReceived = ic.mm.dataRec();
        edSchedSel = ic.mm.editSchedSelected();
        schedSetupSel = ic.mm.InitSetupSelected();
        logoutSel = ic.mm.logoutSelected();
	}	

	/**
	 * !logoutSelected == true on line 144 
	 */
	public static void branchLogic_6() {
        long newCurrentTime = 0;
        ic.mm.setdataRec(false);
        dataReceived = false;
	}	
	
	/**
	 * logoutSel on line 171
	 */
	public static void branchLogic_7() {
        auth.logout();
        ic.Initiate_Logout();
	}	
	
	/**
	 * edit sched on line 178
	 */
	public static void branchLogic_8() {
		ic.Course_Select_Form();
		
        ic.cs.setCourseSelected(false);
        dataReceived = false;

        courseSel = ic.cs.getSelection();
        getData(courseSel);
        
        ic.Pre_Filled_Form(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        
        dataReceived = false;
        ic.edSched.setDataRec(false);

        //System.out.println("Save has been pressed" +ic.edSched.newMonStart);

        db.storeClassSched(ic.edSched.defCourseID, ic.edSched.newCourseStart, ic.edSched.newCourseEnd, 
                ic.edSched.newMonStart, ic.edSched.newMonEnd, ic.edSched.newTueStart, ic.edSched.newTueEnd, 
                ic.edSched.newWedStart, ic.edSched.newWedEnd, ic.edSched.newThuStart, ic.edSched.newThuEnd, 
                ic.edSched.newFriStart, ic.edSched.newFriEnd, ic.edSched.newSatStart, ic.edSched.newSatEnd);        
	}	
	
	/**
	 * auth.validate_Login() == true on line 220
	 */
	public static void branchLogic_9() {
        ic.sched.launchInitial();
        //Initial Schedule Setup

        dataReceived = false;
        ic.sched.setDataRec(false);
        
        db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,ic.sched.newSemester);
        db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd);
	}	
	
	/**
	 * auth.validate_Login() == true on line 224
	 */
	public static void branchLogic_10() {
        dataReceived = ic.sched.dataRec();
        sleep(300);
	}		
}
