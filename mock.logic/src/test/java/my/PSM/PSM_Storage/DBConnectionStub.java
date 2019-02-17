package my.PSM.PSM_Storage;

import java.util.ArrayList;


public class DBConnectionStub
{
    
	public int connect(String db, String user, String pw) {
		System.out.println("Connected (db, user, pw)!");
		return 0;
	}

	public int connect(String user, String pw) {
		System.out.println("Connected (user, pw)!");
		return 0;
	}

	public int disconnect() {
		System.out.println("Disconnected!");
		return 0;
	}

	public int fetchCourseID(int courseID) {
		System.out.println("Fetched CourseID!");
	    return 100;
	}

	public ArrayList<String> getEndDates() {
		ArrayList<String> endDates = new ArrayList<String>();
		endDates.add("08/20/15");
        return endDates;
	}

	public ArrayList<Integer> getCourses() {
        ArrayList<Integer> courseList = new ArrayList<Integer>();
		courseList.add(100);
		return courseList;
	}

	public String fetchCourses() {
		return "TestCourse";
	}

	public String fetchCourseSubj(int courseID) {
		return "TestSubject";
	}

	public String fetchCourseName(int courseID) {
		return "CourseName";
	}

	public String fetchCourseSemester(int courseID) {
		return "Spring";
	}

	public String fetchCourseStart(int courseID) {
		return "08/19/15";
	}

	public String fetchCourseEnd(int courseID) {
		return "08/20/15";
	}

	public String fetchStartMon(int courseID) {
		return "7:30";
	}

	public String fetchEndMon(int courseID) {
		return "7:31";
	}

	public String fetchStartTue(int courseID) {
		return "7:30";
	}

	public String fetchEndTue(int courseID) {
		return "7:31";
	}

	public String fetchStartWed(int courseID) {
		return "7:30";
	}

	public String fetchEndWed(int courseID) {
		return "7:31";
	}

	public String fetchStartThu(int courseID) {
		return "7:30";
	}

	public String fetchEndThu(int courseID) {
		return "7:31";
	}

	public String fetchStartFri(int courseID) {
		return "7:30";
	}

	public String fetchEndFri(int courseID) {
		return "7:31";
	}

	public String fetchStartSat(int courseID) {
		return "7:30";
	}

	public String fetchEndSat(int courseID) {
		return "7:31";
	}

	public int storeClassInfo(int courseID, String courseSubj, String courseName, String semester) {
		return 0;
	}

	public int storeClassSched(int courseID, String startDate, String endDate, String startMon, String endMon,
			String startTue, String endTue, String startWed, String endWed, String startThu, String endThu,
			String startFri, String endFri, String startSat, String endSat) {
		System.out.println("Stored info!");
		return 0;
	}

	public void clearDatabase() {
		System.out.println("Cleared Database!");
	}

	public int createClassTable() {
		System.out.println("Created Class Table!");
		return 0;
	}

}