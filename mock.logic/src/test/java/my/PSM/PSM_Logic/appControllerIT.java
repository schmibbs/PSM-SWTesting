package my.PSM.PSM_Logic;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import my.PSM.PSM_Storage.DBConnection;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class appControllerIT {

	  /*
		PSM_002-Logout
		PSM_004-Schedule Setup
		PSM_012-Schedule Edit
		PSM_017-Password Conflict
	  */
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	@Rule
	public final TextFromStandardInputStream systemInMock
	  = emptyStandardInputStream();
	@Rule
	public final TextFromStandardInputStream systemInMock2
	  = emptyStandardInputStream();
	@Mock
	DBConnection db;
	
	/*
	 * Test Id: PSM_004-Schedule Setup_001_Sunny
	 */
	@Test
	public void scheduleSetupIT_usingAStub() {
		InterfaceController ic = new InterfaceController();
		DBConnection db = new DBConnection();

		systemInMock.provideLines("Software Testing", "0", "3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        
        assertEquals(0, db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,ic.sched.newSemester));
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());

	}
	/*
	 * Test Id: PSM_004-Schedule Setup_002_Sunny
	 */
	@Test
	public void scheduleSetupIT_usingAMock() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,ic.sched.newSemester)).thenReturn(0);
		when(db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd)).thenReturn(0);
		systemInMock.provideLines("English", "5", "400", "0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        
        assertEquals(0, db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,ic.sched.newSemester));
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());

	}
	
	/*
	 * Test Id: PSM_004-Schedule Setup_003_Sunny
	 */
	@Test
	public void scheduleSetupIT_usingAMock2() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassInfo(0, "CEN", "Software Testing", "Spring")).thenReturn(0);
		when(db.storeClassSched(0, "08/19/15", "08/20/15", 
				"07:30", "07:31", "07:30", "07:31", 
				"07:30", "07:31", "07:30", "07:31", 
				"07:30", "07:31", "07:30", "07:31")).thenReturn(0);
		systemInMock.provideLines("Software Testing", "4", "CEN", "5", "2020", "0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        
        assertEquals(0, db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,ic.sched.newSemester));
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());
        assertEquals("CEN", ic.sched.newSub);
        assertEquals(2020, ic.sched.newCourseID);

	}
	/*
	 * Test Id: PSM_004-Schedule_Setup_004_Rainy
	 */
	@Test
	public void scheduleSetupIT_usingAMockwFail() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("Software Testing", "2", "32/01/19997","0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        assertEquals("32/01/19997", ic.sched.newCourseStart);
	}
	/*
	 * Test Id: PSM_004-Schedule_Setup_005_Rainy
	 */
	@Test
	public void scheduleSetupIT_Fail2() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("Software Testing", "3", "32/100/1997","0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        assertEquals("32/100/1997", ic.sched.newCourseEnd);
	}
	/*
	 * Test Id: PSM_004-Schedule_Setup_006_Rainy
	 */
	@Test
	public void scheduleSetupIT_Fail3() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("Software Testing", "7", "32/100/1997","0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        assertEquals("32/100/97", ic.sched.newMonStart);
	}
	/*
	 * Test Id: PSM_004-Schedule Edit -01_Sunny
	 */
	@Test
	public void editScheduleIT_usingAMock() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd)).thenReturn(0);
		boolean dataReceived = false;
		systemInMock.provideLines("0", "3","03/23/16","0", "3");
		String defSub = "";
		String defSemester = "";
		String defCourseName = "";
		String defCourseStart = "";
		String defCourseEnd = "";
		String defMonStart = "";
		String defMonEnd = "";
		String defTueStart = "";
		String defTueEnd = "";
		String defWedStart = "";
		String defWedEnd = "";
		String defThuStart = "";
		String defThuEnd = "";
		String defFriStart = "";
		String defFriEnd = "";
		String defSatStart = "";
		String defSatEnd = "";
        int course = 0;
        defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);     
        when(db.fetchCourseSubj(course)).thenReturn("Software");
        when(db.fetchCourseName(course)).thenReturn("CEN");
        when(db.fetchCourseStart(course)).thenReturn("10/23/15");
        when(db.fetchCourseEnd(course)).thenReturn("03/23/16");
        //Setup End
        
        
        ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(dataReceived);

        ic.cs.setCourseSelected(false);
        dataReceived = false;

        int courseSel = ic.cs.getSelection();
        course = courseSel;     
        
        defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);     
        
        ic.Pre_Filled_Form(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        
        while(!dataReceived)
        {
            dataReceived = ic.edSched.dataRec(); 
        }
        
        dataReceived = false;
        ic.edSched.setDataRec(false);
        
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());
        assertEquals("03/23/16", db.fetchCourseEnd(course));

	}
	
	/*
	 * Test Id: PSM_004-Schedule Edit_002_Sunny
	 * Test Purpose: Enter, edit, and leave
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void editScheduleIT_usingAMock2() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd)).thenReturn(0);
		boolean dataReceived = false;
		systemInMock.provideLines("0", "5","123","0", "3");
		String defSub = "";
		String defSemester = "";
		String defCourseName = "";
		String defCourseStart = "";
		String defCourseEnd = "";
		String defMonStart = "";
		String defMonEnd = "";
		String defTueStart = "";
		String defTueEnd = "";
		String defWedStart = "";
		String defWedEnd = "";
		String defThuStart = "";
		String defThuEnd = "";
		String defFriStart = "";
		String defFriEnd = "";
		String defSatStart = "";
		String defSatEnd = "";
        int course = 0;
        defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);     
        //Setup End
        
        
        ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(dataReceived);

        ic.cs.setCourseSelected(false);
        dataReceived = false;

        int courseSel = ic.cs.getSelection();
        course = courseSel;     
        
        defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);     
        
        ic.Pre_Filled_Form(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        
        while(!dataReceived)
        {
            dataReceived = ic.edSched.dataRec(); 
        }
        
        dataReceived = false;
        ic.edSched.setDataRec(false);
        
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());
        assertEquals(123, ic.sched.defCourseID);

	}
	
	/*
	 * Test Id: PSM_004-Schedule Edit_003_Sunny
	 * Test Purpose: Enter, edit, and leave
	 */
	@Test
	public void editScheduleIT_usingAMock3() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd)).thenReturn(0);
		boolean dataReceived = false;
		systemInMock.provideLines("0", "4","English","0", "3");
		String defSub = "";
		String defSemester = "";
		String defCourseName = "";
		String defCourseStart = "";
		String defCourseEnd = "";
		String defMonStart = "";
		String defMonEnd = "";
		String defTueStart = "";
		String defTueEnd = "";
		String defWedStart = "";
		String defWedEnd = "";
		String defThuStart = "";
		String defThuEnd = "";
		String defFriStart = "";
		String defFriEnd = "";
		String defSatStart = "";
		String defSatEnd = "";
        int course = 0;
        defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);     
        //Setup End
        
        
        ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(dataReceived);

        ic.cs.setCourseSelected(false);
        dataReceived = false;

        int courseSel = ic.cs.getSelection();
        course = courseSel;     
        
        defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);     
        
        ic.Pre_Filled_Form(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        
        while(!dataReceived)
        {
            dataReceived = ic.edSched.dataRec(); 
        }
        
        dataReceived = false;
        ic.edSched.setDataRec(false);
        
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());
        assertEquals("English", ic.sched.defSub);

	}
	
	/*
	 * Test Id: PSM_004-Schedule Edit_04
	 * Test Purpose: Testing login
	 */
	@Test
	public void editScheduleIT_usingAMockwFail() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("0", "7", "Winter","0","3");
		db = mock(DBConnection.class);
		boolean dataReceived = false; 
        //Setup End
        
        ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(dataReceived);

        ic.cs.setCourseSelected(false);
        dataReceived = false;

        int courseSel = ic.cs.getSelection();
        String defSub = db.fetchCourseSubj(courseSel);
        String defSemester = db.fetchCourseSemester(courseSel);
        String defCourseName = db.fetchCourseName(courseSel);
        String defCourseStart = db.fetchCourseStart(courseSel);
        String defCourseEnd = db.fetchCourseEnd(courseSel);
        String defMonStart = db.fetchStartMon(courseSel);
        String defMonEnd = db.fetchEndMon(courseSel);
        String defTueStart = db.fetchStartTue(courseSel);
        String defTueEnd = db.fetchEndTue(courseSel);
        String defWedStart = db.fetchStartWed(courseSel);
        String defWedEnd = db.fetchEndWed(courseSel);
        String defThuStart = db.fetchStartThu(courseSel);
        String defThuEnd = db.fetchEndThu(courseSel);
        String defFriStart = db.fetchStartFri(courseSel);
        String defFriEnd = db.fetchEndFri(courseSel);
        String defSatStart = db.fetchStartSat(courseSel);
        String defSatEnd = db.fetchEndSat(courseSel);   
		ic.sched.launchEdit(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        assertEquals("Winter", ic.sched.defMonStart);
	}
	
	/*
	 * Test Id: PSM_004-Schedule Edit_05_Rainy
	 * Test Purpose: Testing login
	 */
	@Test
	public void editScheduleIT_usingAMockwFail2() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("0", "9", "-2:00","0","3");
		db = mock(DBConnection.class);
		boolean dataReceived = false; 
        //Setup End
        
        
        ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(dataReceived);

        ic.cs.setCourseSelected(false);
        dataReceived = false;

        int courseSel = ic.cs.getSelection();
        String defSub = db.fetchCourseSubj(courseSel);
        String defSemester = db.fetchCourseSemester(courseSel);
        String defCourseName = db.fetchCourseName(courseSel);
        String defCourseStart = db.fetchCourseStart(courseSel);
        String defCourseEnd = db.fetchCourseEnd(courseSel);
        String defMonStart = db.fetchStartMon(courseSel);
        String defMonEnd = db.fetchEndMon(courseSel);
        String defTueStart = db.fetchStartTue(courseSel);
        String defTueEnd = db.fetchEndTue(courseSel);
        String defWedStart = db.fetchStartWed(courseSel);
        String defWedEnd = db.fetchEndWed(courseSel);
        String defThuStart = db.fetchStartThu(courseSel);
        String defThuEnd = db.fetchEndThu(courseSel);
        String defFriStart = db.fetchStartFri(courseSel);
        String defFriEnd = db.fetchEndFri(courseSel);
        String defSatStart = db.fetchStartSat(courseSel);
        String defSatEnd = db.fetchEndSat(courseSel);   
		ic.sched.launchEdit(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        assertEquals("-2:00", ic.sched.defWedStart);
	}
	/*
	 * Test Id: PSM_004-Schedule Edit_06_Rainy
	 * Test Purpose: Testing login
	 */
	@Test(expected = InputMismatchException.class)
	public void editScheduleIT_usingAMockwFail3() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("0", "5", "abc");
		db = mock(DBConnection.class);
		boolean dataReceived = false; 
        //Setup End
        
        
        ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(dataReceived);

        ic.cs.setCourseSelected(false);
        dataReceived = false;

        int courseSel = ic.cs.getSelection();
        String defSub = db.fetchCourseSubj(courseSel);
        String defSemester = db.fetchCourseSemester(courseSel);
        String defCourseName = db.fetchCourseName(courseSel);
        String defCourseStart = db.fetchCourseStart(courseSel);
        String defCourseEnd = db.fetchCourseEnd(courseSel);
        String defMonStart = db.fetchStartMon(courseSel);
        String defMonEnd = db.fetchEndMon(courseSel);
        String defTueStart = db.fetchStartTue(courseSel);
        String defTueEnd = db.fetchEndTue(courseSel);
        String defWedStart = db.fetchStartWed(courseSel);
        String defWedEnd = db.fetchEndWed(courseSel);
        String defThuStart = db.fetchStartThu(courseSel);
        String defThuEnd = db.fetchEndThu(courseSel);
        String defFriStart = db.fetchStartFri(courseSel);
        String defFriEnd = db.fetchEndFri(courseSel);
        String defSatStart = db.fetchStartSat(courseSel);
        String defSatEnd = db.fetchEndSat(courseSel);   
		ic.sched.launchEdit(courseSel,defSub,defCourseName,defSemester,defCourseStart,
                defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
                defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        assertEquals("abc", ic.sched.newCourseID);
	}
	/*
	 * Test Id: SS_DataReceived_001
	 */
	@Test
	public void dataReceivedSetUpSchedIT_usingAStub() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("Software Testing", "0", "3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
	}
	/*
	 * Test Id: SS_DataReceived_002
	 */
	@Test
	public void dataReceivedEditSchedIT_usingAStub() {
		InterfaceController ic = new InterfaceController();
		systemInMock.provideLines("0", "3");
		boolean dataReceived = false;
		
		ic.Course_Select_Form();

        while(!dataReceived)
        {
            dataReceived = ic.cs.courseSelected();
        }
        assertTrue(ic.cs.courseSelected());
	}	
	
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	/*
	 * Test Id: PSM-002-Logout-001
	 * Test Purpose: Testing login
	 */
	@Test
	public void LogoutIT() {
		String username = "clarkep";
		String password = "12345";
		Authenticate logAuth = new Authenticate(username, password);
		logAuth.validate_Login();
		assertTrue(logAuth.logout());
	}
	/*
	 * Test Id: PSM-002-Logout-002
	 * Test Purpose: Testing login
	 */
	@Test
	public void LogoutIT2() {
		exit.expectSystemExitWithStatus(0);
		systemInMock.provideLines("0", "clarkep","12345","3");
		appController main = new appController();
		main.main(new String[] {});
	}
	/*
	 * Test Id: PSM-002-Logout-003
	 * Test Purpose: Testing Fail login
	 */
	@Test
	public void LogoutIT3() {
		db = mock(DBConnection.class);
		String username = "root";
		String password = "12345";
		when(db.connect(username, password)).thenReturn(0);
		db.connect(username, password);
		db.disconnect();
		assertEquals(0, db.disconnect());
	}
	/*
	 * Test Id: PSM-002-Logout-004 -Rainy
	 * Test Purpose: Fail the login call
	 */
	@Test
	public void LogoutIT4() {
		String username = "clarkep";
		String password = "12345";
		Authenticate logAuth = new Authenticate(username, password);
		//logAuth.validate_Login();
		assertFalse(logAuth.logout());
	}
	/*
	 * Test Id: PSM-002-Logout-005- Rainy
	 * Test Purpose: Fail the login call
	 */
	@Test
	public void LogoutIT5() {
		appController main = mock(appController.class);
		DBConnection db2;
		db2 = main.getCon();
		db2.disconnect();
		assertEquals(-1, db2.disconnect());
	}
	/*
	 * Test Id: PSM-002-Logout-006- Rainy
	 * Test Purpose: Fail the login call
	 */
	@Test
	public void LogoutIT6() {
		appController main = mock(appController.class);
		DBConnection db2;
		db2 = main.getCon();
		db2.disconnect();
		db2.disconnect();
		assertEquals(-1, db2.disconnect());
	}
	
	/*
	 * Test Id: PSM_017_PasswordConflict-001
	 * Test Purpose: Test multiple password failures leading to quit
	 */
	@Test
	public void passwordConflictIT() {
		exit.expectSystemExitWithStatus(0);
		systemInMock.provideLines("0", "clarkep","1235","0","clarkep","1234","0","clarkep","1234");
		appController main = new appController();
		main.main(new String[] {});
	}
	/*
	 * Test Id: PSM_017_PasswordConflict-002
	 * Test Purpose: Test multiple password failures leading to quit
	 */
	@Test
	public void passwordConflictIT2() {
		exit.expectSystemExitWithStatus(0);
		systemInMock.provideLines("0", "12345","12345","0","12345","12345","0","12345","12345");
		appController main = new appController();
		main.main(new String[] {});
	}
	/*
	 * Test Id: PSM_017_PasswordConflict-003
	 * Test Purpose: Test multiple password failures leading to quit
	 */
	@Test
	public void passwordConflictIT3() {
		exit.expectSystemExitWithStatus(0);
		systemInMock.provideLines("0", "clarkep","clarkep","0","clarkep","clarkep","0","clarkep","clarkep");
		appController main = new appController();
		main.main(new String[] {});
	}



}
