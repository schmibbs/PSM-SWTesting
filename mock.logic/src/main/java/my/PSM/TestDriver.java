package my.PSM;
import java.util.Scanner;

import my.PSM.PSM_Logic.appController;

public class TestDriver {
	
    private static boolean dataRec = false;
    private static String username;
    private static String password;
	Scanner scanner = new Scanner(System.in);
	public void setScanner(Scanner sc) {
		scanner = sc;
	}
    
	public void launchForm() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Login Screen");
		System.out.println("Would you like to close here? Yes - 1, No - 0");
		int i = 0;
			i = Integer.parseInt(scanner.nextLine());
		if(i == 1) {
			System.out.println("Quit");
	        System.exit(0);
		}else if (i == 0){
			dataRec = true;
		}
		System.out.println("Username: (Try \"clarkep\")");
			username = scanner.nextLine();
		System.out.println("Password:(Try \"12345\")");
			password = scanner.nextLine();
		System.out.println("End of Login Screen");
	}
 
	public boolean dataReceived() {
		System.out.println("Data received from Login Screen? " + dataRec);
        return dataRec;
	}

	public void setDataRec(boolean condition) {
        dataRec = condition;
	}

	public String getUsername() {
        return username;
	}

	public String getPassword() {
        return password;
	}
	
	
	

    private static boolean dataRec2;
    private static boolean editSched;
    private static boolean logout;
    private static boolean initSetup;
    
	public void launchFormMenu() {
		System.out.println("In Main Menu");		
		System.out.println("What would you like to do?\n1 - Edit Schedule\n2 - New Schedule\n3 - Logout");
		int i = Integer.parseInt(scanner.nextLine());
		if(i == 3) {
			dataRec2 = true;
	        logout = true;
		} else if(i == 2) {
			dataRec2 = true;
	        initSetup = true;
		} else if(i == 1) {
			dataRec2 = true;
			editSched = true;
		}
	}

	public boolean dataRec() {
        System.out.println("Data received from Main Menu? " + dataRec2);
		return dataRec2;
	}

	public boolean editSchedSelected() {
        return editSched;
	}

	public boolean InitSetupSelected() {
        return initSetup;
	}

	public boolean logoutSelected() {
        return logout;
	}

	public void setdataRec(boolean condition) {
        dataRec2 = condition;
		
	}
	
	
	private static int selection;
	private static boolean courseSelected;
	public String [] listCourses;
	
	public void launchCourse() {
		System.out.println("Fetched courses!");        
		String ans = appController.db.fetchCourses();
        System.out.println("Result from Fetch: " + ans);
		System.out.println("Select a course:");
        listCourses = ans.split(",");
        for (int i = 0; i < listCourses.length; i++) {
            System.out.println("[" + i + "] " + listCourses[i]);
        }	
		int i = Integer.parseInt(scanner.nextLine());
		selection = i;
		courseSelected = true;
	}

	public boolean courseSelected() {
        System.out.println("Is a course selected? " + courseSelected);
        return courseSelected;
	}

	public void setCourseSelected(boolean condition) {
        courseSelected = condition;
		
	}

	public int getSelection() {
        return selection;
	}
	
	
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

    
    static boolean dataReceived;
    
    public void setDataRecPre(boolean condition)
    {
        dataReceived = condition; 
    }


	public void launchEdit(int courseID, String courseSubj, String courseName, String semester, String startDate,
			String endDate, String startMon, String endMon, String startTue, String endTue, String startWed,
			String endWed, String startThu, String endThu, String startFri, String endFri, String startSat,
			String endSat) {
		  defCourseID = courseID;
		  defSub = courseSubj;
		  defSemester = semester;
		  defCourseName = courseName;
		  defCourseStart = startDate;
		  defCourseEnd = endDate;
		  defMonStart = startMon;
		  defMonEnd = endMon;
		  defTueStart = startTue;
		  defTueEnd = endTue;
		  defWedStart = startWed;
		  defWedEnd = endWed;
		  defThuStart = startThu;
		  defThuEnd = endThu;
		  defFriStart = startFri;
		  defFriEnd = endFri;
		  defSatStart = startSat;
		  defSatEnd = endSat;
		  printEdit(courseID, courseSubj, courseName, semester,
		            startDate, endDate, startMon, endMon, startTue, endTue, 
		            startWed, endWed, startThu, endThu, startFri, endFri, startSat, endSat);
		  int i = Integer.parseInt(scanner.nextLine());
		  while(i != 0) {
			  Scanner sc = new Scanner(System.in);
				if(i == 0) {
					dataReceived = true;
			        logout = true;
				} else if(i == 1) {
					dataReceived = true;
					System.out.println("Type in Semester:");
					defSemester = sc.nextLine();
				} else if(i == 2) {
					dataReceived = true;
					System.out.println("Type in Start Date:");
					defCourseStart = sc.nextLine();
				} else if(i == 3) {
					dataReceived = true;
					System.out.println("Type in End Date:");
					defCourseEnd = sc.nextLine();
				} else if(i == 4) {
					dataReceived = true;
					System.out.println("Type in Subject:");
					defSub = sc.nextLine();
				} else if(i == 5) {
					dataReceived = true;
					System.out.println("Type in CourseID:");
					defCourseID = sc.nextInt();
				} else if(i == 6) {
					dataReceived = true;
					System.out.println("Type in Course Name:");
					defCourseName = sc.nextLine();
				} else if(i == 7) {
					dataReceived = true;
					System.out.println("Type in Mon Start:");
					defMonStart = sc.nextLine();
				} else if(i == 8) {
					dataReceived = true;
					System.out.println("Type in Tue Start:");
					defTueStart = sc.nextLine();
				} else if(i == 9) {
					dataReceived = true;
					System.out.println("Type in Wed Start:");
					defWedStart = sc.nextLine();
				} else if(i == 10) {
					dataReceived = true;
					System.out.println("Type in Thu Start:");
					defThuStart = sc.nextLine();
				} else if(i == 11) {
					dataReceived = true;
					System.out.println("Type in Fri Start:");
					defFriStart = sc.nextLine();
				} else if(i == 12) {
					dataReceived = true;
					System.out.println("Type in Sat Start:");
					defSatStart = sc.nextLine();
				} else if(i == 13) {
					dataReceived = true;
					System.out.println("Type in Mon End:");
					defMonEnd = sc.nextLine();
				} else if(i == 14) {
					dataReceived = true;
					System.out.println("Type in Tue End:");
					defTueEnd = sc.nextLine();
				} else if(i == 15) {
					dataReceived = true;
					System.out.println("Type in Wed End:");
					defWedEnd = sc.nextLine();
				} else if(i == 16) {
					dataReceived = true;
					System.out.println("Type in Thu End:");
					defThuEnd = sc.nextLine();
				} else if(i == 17) {
					dataReceived = true;
					System.out.println("Type in Fri End:");
					defFriEnd = sc.nextLine();
				} else if(i == 18) {
					dataReceived = true;
					System.out.println("Type in Sat End:");
					defSatEnd = sc.nextLine();
				}
				printEdit(defCourseID, defSub, defCourseName, defSemester,
			            defCourseStart, defCourseEnd, defMonStart, defMonEnd, defTueStart, defTueEnd, 
			            defWedStart, defWedEnd, defThuStart, defThuEnd, defFriStart, defFriEnd, defSatStart, defSatEnd);
				i = Integer.parseInt(scanner.nextLine());
		  }
		logout = false;
		initSetup = false;
		editSched = false;
		dataReceived = true;
		launchFormMenu();
		  
	}
	
	public void printEdit(int courseID, String courseSubj, String courseName, String semester, String startDate,
			String endDate, String startMon, String endMon, String startTue, String endTue, String startWed,
			String endWed, String startThu, String endThu, String startFri, String endFri, String startSat,
			String endSat) {
		  defCourseID = courseID;
		  defSub = courseSubj;
		  defSemester = semester;
		  defCourseName = courseName;
		  defCourseStart = startDate;
		  defCourseEnd = endDate;
		  defMonStart = startMon;
		  defMonEnd = endMon;
		  defTueStart = startTue;
		  defTueEnd = endTue;
		  defWedStart = startWed;
		  defWedEnd = endWed;
		  defThuStart = startThu;
		  defThuEnd = endThu;
		  defFriStart = startFri;
		  defFriEnd = endFri;
		  defSatStart = startSat;
		  defSatEnd = endSat;
		  System.out.println("Course Name In Edit Sched: " + courseName);
		  System.out.println("---Semester Information-");
		  System.out.println("[1]Semester: " + semester);
		  System.out.println("[2]Start Date: " + startDate + "\t[3]End Date: " + endDate);
		  System.out.println("---Class Information---");
		  System.out.println("[4]Subject: " + courseSubj + "\t[5]Course Number: " + courseID);
		  System.out.println("[6]Course Name: " + courseName );
		  System.out.println("---Class Times---");
		  System.out.println("\t\tMonday\t\tTuesday\t\tWednesday\tThursday\tFriday\t\tSaturday");
		  System.out.println("StartTime:\t[7]" + startMon + "\t[8]" + startTue + "\t[9]" + startWed + "\t[10]" + startThu + "\t[11]" + startFri + "\t[12]" + startSat);
		  System.out.println("EndTime: \t[13]" + endMon + "\t[14]" + endTue + "\t[15]" + endWed + "\t[16]" + endThu + "\t[17]" + endFri + "\t[18]" + endSat);
		  System.out.println("What would you like to edit? 0 to end");
	}

	Scanner sc = new Scanner(System.in);
    public void launchInitial() {
    	System.out.println("launchInitial");
    	System.out.println("New Course name:");
    	newCourseName = sc.nextLine();
    	newCourseID = 123;
		newSub = "Subject";
		newSemester = "NewSemester";
		newCourseStart = "01/01/70";
		newCourseEnd = "01/01/70";
		newMonStart = "00:00";
		newMonEnd = "00:00";
		newTueStart = "00:00";
		newTueEnd = "00:00";
		newWedStart = "00:00";
		newWedEnd = "00:00";
		newThuStart = "00:00";
		newThuEnd = "00:00";
		newFriStart = "00:00";
		newFriEnd = "00:00";
		newSatStart = "00:00";
		newSatEnd = "00:00";
    	printEdit(newCourseID, newSub, newCourseName, newSemester,
    			newCourseStart, newCourseEnd, newMonStart, newMonEnd, newTueStart, newTueEnd, 
    			newWedStart, newWedEnd, newThuStart, newThuEnd, newFriStart, newFriEnd, newSatStart, newSatEnd);
    	int i = Integer.parseInt(scanner.nextLine());
		  while(i != 0) {
				if(i == 0) {
					dataReceived = true;
			        logout = true;
				} else if(i == 1) {
					dataReceived = true;
					System.out.println("Type in Semester:");
					newSemester = sc.nextLine();
				} else if(i == 2) {
					dataReceived = true;
					System.out.println("Type in Start Date:");
					newCourseStart = sc.nextLine();
				} else if(i == 3) {
					dataReceived = true;
					System.out.println("Type in End Date:");
					newCourseEnd = sc.nextLine();
				} else if(i == 4) {
					dataReceived = true;
					System.out.println("Type in Subject:");
					newSub = sc.nextLine();
				} else if(i == 5) {
					dataReceived = true;
					System.out.println("Type in CourseID:");
					newCourseID = sc.nextInt();
				} else if(i == 6) {
					dataReceived = true;
					System.out.println("Type in Course Name:");
					newCourseName = sc.nextLine();
				} else if(i == 7) {
					dataReceived = true;
					System.out.println("Type in Mon Start:");
					newMonStart = sc.nextLine();
				} else if(i == 8) {
					dataReceived = true;
					System.out.println("Type in Tue Start:");
					newTueStart = sc.nextLine();
				} else if(i == 9) {
					dataReceived = true;
					System.out.println("Type in Wed Start:");
					newWedStart = sc.nextLine();
				} else if(i == 10) {
					dataReceived = true;
					System.out.println("Type in Thu Start:");
					newThuStart = sc.nextLine();
				} else if(i == 11) {
					dataReceived = true;
					System.out.println("Type in Fri Start:");
					newFriStart = sc.nextLine();
				} else if(i == 12) {
					dataReceived = true;
					System.out.println("Type in Sat Start:");
					newSatStart = sc.nextLine();
				} else if(i == 13) {
					dataReceived = true;
					System.out.println("Type in Mon End:");
					newMonEnd = sc.nextLine();
				} else if(i == 14) {
					dataReceived = true;
					System.out.println("Type in Tue End:");
					newTueEnd = sc.nextLine();
				} else if(i == 15) {
					dataReceived = true;
					System.out.println("Type in Wed End:");
					newWedEnd = sc.nextLine();
				} else if(i == 16) {
					dataReceived = true;
					System.out.println("Type in Thu End:");
					newThuEnd = sc.nextLine();
				} else if(i == 17) {
					dataReceived = true;
					System.out.println("Type in Fri End:");
					newFriEnd = sc.nextLine();
				} else if(i == 18) {
					dataReceived = true;
					System.out.println("Type in Sat End:");
					newSatEnd = sc.nextLine();
				}
				printEdit(newCourseID, newSub, newCourseName, newSemester,
		    			newCourseStart, newCourseEnd, newMonStart, newMonEnd, newTueStart, newTueEnd, 
		    			newWedStart, newWedEnd, newThuStart, newThuEnd, newFriStart, newFriEnd, newSatStart, newSatEnd);
				i = Integer.parseInt(scanner.nextLine());
		  }
		logout = false;
		initSetup = false;
		editSched = false;
		dataReceived = true;
		launchFormMenu();
    }
	
	
    
    public static boolean ack;

	public void lockedOut() {
		System.out.println("Too many tries. Quitting...");
		ack = true;
	}

	public void logoutConfirmation() {
		System.out.println("You have sucessfully logged out.");
        System.exit(0);
	}

	public void incorrectLogin() {
		System.out.println("Incorrect username/password");
		ack = true;
		
	}

	public void FifteenMinWarning() {
		System.out.println("15 Minutes Left");
		ack = true;
		
	}

	public void FiveMinWarning() {
		System.out.println("5 Minutes Left");
		ack = true;
	}

	public void endClassWarning() {
		System.out.println("End of Class Reached");
		ack = true;
	}
}
