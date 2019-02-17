package my.PSM.PSM_Logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import my.PSM.PSM_Storage.DBConnection;
//Using Stub
public class AuthenticateTest {

    private String username;
    private String password;
    
	@Before
	public void setUp() throws Exception {
        username = "clarkep";
        password = "12345";
	}
	
	@After
	public void tearDown() throws Exception {
        username = null;
        password = null;
	}
	
	/*
	 * Test Id: PSM_001-Login_002
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testValidate_Login_usingAStub() {
		Authenticate logAuth = new Authenticate(username, password);
		assertEquals(true, logAuth.validate_Login());
	}
	
	/*
	 * Test Id: PSM_001-Login_002
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testValidate_Login_usingAStub2() {
		Authenticate logAuth = new Authenticate("clarkeep", "1234");
		assertEquals(false, logAuth.validate_Login());
	}	
	
	/*
	 * Test Id: PSM_002-Logout_001
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testlogout_usingAStub() {
		Authenticate logAuth = new Authenticate(username, password);
		assertEquals(true, logAuth.validate_Login());
		assertEquals(true, logAuth.logout());
	}
	
	/*
	 * Test Id: PSM_002-Logout_002
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testlogout_usingAStub2() {
		Authenticate logAuth = new Authenticate("clarkeep", "1234");
		assertEquals(false, logAuth.validate_Login());
		assertEquals(false, logAuth.logout());
	}
	/*
	 * Test Id: PSM_002-Logout_002
	 * Test Purpose: 
	 * Setup: 
	 * Input:
	 * Expected Output: 
	 */
	@Test
	public void testlogout_usingAStub3() {
		Authenticate logAuth = new Authenticate("clarkep", "12345");
		assertEquals(true, logAuth.validate_Login());
		assertEquals(true, logAuth.logout());
		assertEquals(false, logAuth.logout());
	}
}
