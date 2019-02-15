package my.PSM.PSM_Storage;

import java.util.ArrayList;


public class DataStoreFacade
{
    public DBConnection dbReal = new DBConnection();
    
    // Connect using known database address
    public int connectFacade(String db, String user, String pw)
    {
    	return dbReal.connect(db, user, pw);
    }
    
    // Connect using local host as database address
    public int connectFacade(String user,String pw)
    {
    	return dbReal.connect(user, pw);
    }
    
    public int disconnectFacace()
    {
		return dbReal.disconnect();
    }
     
    public int fetchCourseIDFacade(int courseID)
    {
    	return dbReal.fetchCourseID(courseID);
    }
    
    public ArrayList<String> getEndDatesFacade()
    {
       return dbReal.getEndDates();
    }
    
    public ArrayList<Integer> getCoursesFacade()
    {
        return dbReal.getCourses();
    }
    
    public String fetchCoursesFacade()
    {
       return dbReal.fetchCourses();
    }
    
    public String fetchCourseSubjFacade(int courseID)
    {
		return dbReal.fetchCourseSubj(courseID);
    }
    
    public String fetchCourseNameFacade(int courseID)
    {
    	return dbReal.fetchCourseName(courseID);
    }
    
    public String fetchCourseSemesterFacade(int courseID)
    {
    	return dbReal.fetchCourseSemester(courseID);
    }
    
    public String fetchCourseStartFacade(int courseID)
    {
    	return dbReal.fetchCourseStart(courseID);
    }
    
    public String fetchCourseEndFacade(int courseID)
    {
    	return dbReal.fetchCourseEnd(courseID);
    }
    
    public String fetchStartMonFacade(int courseID)
    {
    	return dbReal.fetchStartMon(courseID);
	}
    
    public String fetchEndMonFacade(int courseID)
    {
    	return dbReal.fetchEndMon(courseID);
    }
    
    public String fetchStartTueFacade(int courseID)
    {
    	return dbReal.fetchStartTue(courseID);
    }
    
    public String fetchEndTueFacade(int courseID)
    {
    	return dbReal.fetchEndTue(courseID);
    }
    
    public String fetchStartWedFacade(int courseID)
    {
    	return dbReal.fetchStartWed(courseID);
    }
    
    public String fetchEndWedFacade(int courseID)
    {
    	return dbReal.fetchEndWed(courseID);
    }
    
    
    public String fetchStartThuFacade(int courseID)
    {
        return dbReal.fetchStartThu(courseID);
    }
    
    public String fetchEndThuFacade(int courseID)
    {
        return dbReal.fetchEndThu(courseID);
    }
    
    public String fetchStartFriFacade(int courseID)
    {
    	return dbReal.fetchStartFri(courseID);
    }
    
    public String fetchEndFriFacade(int courseID)
    {
        return dbReal.fetchEndFri(courseID);
    }
    
    
    public String fetchStartSatFacade(int courseID)
    {
        return dbReal.fetchStartSat(courseID);
    }
    
    public String fetchEndSatFacade(int courseID)
    {
        return dbReal.fetchEndSat(courseID);
    }
    
    public int storeClassInfoFacade(int courseID, String courseSubj, String courseName, String semester)
    {
        return dbReal.storeClassInfo(courseID, courseSubj, courseName, semester);
    }
    
    public int storeClassSchedFacade(int courseID, String startDate, String endDate, String startMon, String endMon,
            String startTue, String endTue, String startWed, String endWed, String startThu, String endThu, 
            String startFri, String endFri, String startSat, String endSat)
    {
        return dbReal.storeClassSched(courseID, startDate, endDate, startMon, endMon, startTue, endTue, startWed, endWed, startThu, endThu, startFri, endFri, startSat, endSat);
    }
    
    public void clearDatabaseFacade()
    {
        dbReal.clearDatabase();
    }
    
    public int createClassTableFacade()
    {
    	return dbReal.createClassTable();
    }
}
