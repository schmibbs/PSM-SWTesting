package my.PSM.PSM_Logic;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import my.PSM.PSM_Storage.DBConnection;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
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
	    PSM_001-Login
		PSM_002-Logout
		PSM_004-Schedule Setup
		PSM_012-Schedule Edit
		PSM_008-Message Popup
		PSM_017-Password Conflict
		PSM_003-Security-IdleLogout
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
	 * Test Id: PSM_004-Schedule Setup_001
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
	 * Test Id: PSM_004-Schedule Setup_002
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
	 * Test Id: PSM_004-Schedule Setup_003
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
	 * Test Id: PSM_004-Schedule Setup_002
	 */
	@Test
	public void scheduleSetupIT_usingAMockwFail() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,"Winter")).thenReturn(-1);
		when(db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd)).thenReturn(0);
		systemInMock.provideLines("Software Testing", "0", "1", "Winter","0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        
        assertEquals(-1, db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,"Winter"));
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());

	}
	/*
	 * Test Id: PSM_004-Schedule Edit -01
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
	 * Test Id: PSM_004-Schedule Edit_002
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
	 * Test Id: PSM_004-Schedule Edit_003
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
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void editScheduleIT_usingAMockwFail() {
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		when(db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,"Winter")).thenReturn(-1);
		when(db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd)).thenReturn(0);
		systemInMock.provideLines("Software Testing", "0", "1", "Winter","0","3");
		ic.sched.launchInitial();
        while(!ic.sched.dataRec())
        {
        	//Do nothing
        }
        assertTrue(ic.sched.dataRec());
        ic.sched.setDataRec(false);
        
        assertEquals(-1, db.storeClassInfo(ic.sched.newCourseID, ic.sched.newSub, ic.sched.newCourseName,"Winter"));
        assertEquals(0, db.storeClassSched(ic.sched.newCourseID, ic.sched.newCourseStart, ic.sched.newCourseEnd, 
                ic.sched.newMonStart, ic.sched.newMonEnd, ic.sched.newTueStart, ic.sched.newTueEnd, 
                ic.sched.newWedStart, ic.sched.newWedEnd, ic.sched.newThuStart, ic.sched.newThuEnd, 
                ic.sched.newFriStart, ic.sched.newFriEnd, ic.sched.newSatStart, ic.sched.newSatEnd));
        assertTrue(ic.sched.dataRec());

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
		InterfaceController ic = new InterfaceController();
		db = mock(DBConnection.class);
		exit.expectSystemExitWithStatus(0);
		String username = "clarkep";
		String password = "12345";
		when(db.connect(username, password)).thenReturn(0);
		Authenticate logAuth = new Authenticate(username, password);
		
		logAuth.logout();
		ic.Initiate_Logout();
		assertEquals(-1, logAuth.logout());
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
	 * Test Id: PSM_017_PasswordConflict-001
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
	 * Test Id: PSM_017_PasswordConflict-001
	 * Test Purpose: Test multiple password failures leading to quit
	 */
	@Test
	public void passwordConflictIT3() {
		exit.expectSystemExitWithStatus(0);
		systemInMock.provideLines("0", "clarkep","clarkep","0","clarkep","clarkep","0","clarkep","clarkep");
		appController main = new appController();
		main.main(new String[] {});
	}
	/*
	 * Test Id: PSM_017_PasswordConflict-004
	 * Test Purpose: Test multiple password failures,but last password is correct
	 */
	@Test(expected = NoSuchElementException.class)
	public void passwordConflictITwPass() {
		systemInMock2.provideLines("0", "clarkep","1235","0","clarkep","1234","0","clarkep","12345");
		appController main = new appController();
		main.main(new String[] {});
	}

}
