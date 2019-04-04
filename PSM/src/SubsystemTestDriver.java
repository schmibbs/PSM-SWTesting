/*
* class that is to be used for sub-system testing. Based on code 
* developed by Esteban . This version will have a preference on 
* testing coverage 
*/
public class SubsystemTestDriver {
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

	final String[][] DB_CRED = {{"jdbc:mysql://localhost:3306/mydb", "PeterClarke", "12345"}}; //control variables used for connecting/testing connections to a local db called "mydb"
	final String[][] DB_TEST_VALS = {{"1234", "FOO", "BAR", "Spring", "01/01/1900", "07/08/1901", "am",//control variables that are used for testing entry into the class100 table in "mydb"
							"12:00", "13:00", "12:00", "13:00","12:00", "13:00","12:00", 
							"13:00","12:00", "13:00","12:00", "13:00"}};

    /**
    * Test ID: PSM_<ClassesTested>_Subsystem_001
    * Test purpose: Test <things from class>
    * Setup: 1. Create necessary mocks
             2. Create instances of classes to test
             3. See what it does
    * Input: Methods that are called on input
    * Expected output: 
    * Actual output: 
    * Pass/fail: fail
    */
}
