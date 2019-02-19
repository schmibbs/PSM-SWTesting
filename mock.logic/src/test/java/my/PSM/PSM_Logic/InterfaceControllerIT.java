package my.PSM.PSM_Logic;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class InterfaceControllerIT {
	@Rule
	public final TextFromStandardInputStream systemInMock
	  = emptyStandardInputStream();
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
/*
	@Test
	public void testLogout() {
	  systemInMock.provideLines("0", "clarkep","12345","3");
	  exit.expectSystemExitWithStatus(0);
	  appController.main(new String[] {});
	  assertTrue(true);
	}*/
}
