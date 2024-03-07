import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests implemented to ensure functionality
 * prior to implementing GUI
 *
 * @author Zack Yourkavitch
 */
public class Tests {
  private DBUpdater testDB = new DBUpdater();
  private Application testApp = new Application();

  /**
   * tests adding a user to the DB
   */
  @Test
  public void testAddUserToDB()
  {
    testApp.begin();
    assertTrue(testApp.addUser("test@example.com", "examplename", "examplepass"));
    testApp.end();
  }

  /**
   * tests adding user to DB when information does not fit formatting
   */
  @Test
  public void testBadInformation()
  {
    testApp.begin();
    assertFalse(testApp.addUser("test@example.com", "thisistoomanycharacters", "1234"));
    assertFalse(testApp.addUser("test@example.com", "thisisfine", "thispasswordisgoingtobetoolong"));
    testApp.end();
  }

  /**
   * tests adding user to DB when email already exists for an account
   */
  @Test
  public void testRepeatEmail()
  {
    testApp.begin();
    testApp.addUser("test@example.com", "example2", "256");
    testApp.end();
  }

  /**
   * tests logging in with credentials that do not have an account
   */
  @Test
  public void testLogInAccountNonExistent()
  {
    testApp.begin();
    assertFalse(testApp.checkLogIn("badtest@example.com", "bad"));
    testApp.end();
  }

  /**
   * tests logging in with valid credentials
   */
  @Test
  public void testLogInAccountExists()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    testApp.end();
  }

  /**
   * tests logging in when the email is valid but password is wrong
   */
  @Test
  public void testLogInWrongPass()
  {
    testApp.begin();
    assertFalse(testApp.checkLogIn("test@example.com", "incorrect"));
    testApp.end();
  }

  /**
   * tests removing a user from the DB by first logging in
   * and then deleting the account
   */
  @Test
  public void testRemoveUserFromDB()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.removeUser("examplepass"));
    testApp.end();
  }

}
