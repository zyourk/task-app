import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests implemented to ensure functionality
 * prior to implementing GUI
 *
 * @author Zack Yourkavitch
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Tests {
  private final Application testApp = new Application();

  /**
   * tests adding a user to the DB
   */
  @Test
  @Order(1)
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
  @Order(2)
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
  @Order(3)
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
  @Order(4)
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
  @Order(5)
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
  @Order(6)
  public void testLogInWrongPass()
  {
    testApp.begin();
    assertFalse(testApp.checkLogIn("test@example.com", "incorrect"));
    testApp.end();
  }

  /**
   * tests the ability to add categories to the database
   */
  @Test
  @Order(7)
  public void testAddCategory()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.addCategory("Urgent"));
    testApp.end();
  }

  /**
   * tests the ability to add a task to the database
   */
  @Test
  @Order(8)
  public void testAddTask()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.addTask("ExampleTask", "Brief Description", "03/31", 1));
    testApp.end();
  }

  /**
   * tests adding a task to the database when its deadline is not formatted
   * tests the re-format ability of the application level
   */
  @Test
  @Order(9)
  public void testAddTaskShortDate()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.addTask("title", "desc", "3/31", 0));
    assertTrue(testApp.addTask("othertitle", "desc", "11/1", 1));
    assertTrue(testApp.addTask("finaltitle", "desc", "1/1", 0));
    testApp.end();
  }

  /**
   * tests adding tasks when the date is invalid
   */
  @Test
  @Order(10)
  public void testAddBadDate()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertFalse(testApp.addTask("badexample", "baddesc", "0/15", 1));
    assertFalse(testApp.addTask("badexample2", "baddesc", "6/0", 0));
    assertFalse(testApp.addTask("badexample3", "baddesc", "146/1", 1));
    assertFalse(testApp.addTask("badexample4", "baddesc", "1/56", 0));
    assertFalse(testApp.addTask("badexample5", "baddesc", "4/589", 1));
    testApp.end();
  }

  /**
   * tests adding a task when certain columns in the task are invalid
   */
  @Test
  @Order(11)
  public void testAddInvalidTask()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertFalse(testApp.addTask("ThisTaskTitleIsTooLong", "This Is Fine", "02/29", 0));
    assertFalse(testApp.addTask("TitleGood", "This Is Fine", "2/30", 0));
    assertFalse(testApp.addTask("TitleGood", "This Is Fine", "02/29", 2));
    testApp.end();
  }

  /**
   * tests adding a task to a category
   */
  @Test
  @Order(12)
  public void testCategorizeTask()
  {
   testApp.begin();
   assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
   assertTrue(testApp.addTaskToCategory("Urgent", "ExampleTask"));
   testApp.end();
  }

  /**
   * tests adding a task to a nonexistent (NE) category
   */
  @Test
  @Order(13)
  public void testAddTaskToNECategory()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertFalse(testApp.addTaskToCategory("FakeCategory", "ExampleTask"));
    testApp.end();
  }

  /**
   * tests removing a task from a category
   */
  @Test
  @Order(14)
  public void testDecategorizeTask()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.decategorizeTask("ExampleTask"));
    testApp.end();
  }

  /**
   * tests that a task's category is null if its current category is deleted
   */
  @Test
  @Order(15)
  public void testTasksDecatWhenCatDelete()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.removeCategory("Urgent"));
    //Will check task part of mysql
    testApp.end();
  }

  /**
   * tests displaying of all tasks in a certain category
   */
  @Test
  @Order(16)
  public void testDisplayCategoryTasks()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.addCategory("Display"));
    assertTrue(testApp.addTask("Task1", "I do things", "2/17", 1));
    assertTrue(testApp.addTask("Task2", "I do things", "2/17", 1));
    assertTrue(testApp.addTask("Task3", "I do things", "2/17", 1));
    assertTrue(testApp.addTaskToCategory("Display", "Task1"));
    assertTrue(testApp.addTaskToCategory("Display", "Task2"));
    assertTrue(testApp.addTaskToCategory("Display", "Task3"));
    assertTrue(testApp.displayTasksInCat("Display"));
    testApp.end();
  }

  /**
   * tests removal of a task
   */
  @Test
  @Order(17)
  public void testRemoveTask()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.removeTask("exampletask"));
    testApp.end();
  }


  /**
   * tests removing a user from the DB by first logging in
   * and then deleting the account
   */
  @Test
  @Order(18)
  public void testRemoveUserFromDB()
  {
    testApp.begin();
    assertTrue(testApp.checkLogIn("test@example.com", "examplepass"));
    assertTrue(testApp.removeUser("examplepass"));
    testApp.end();
  }

}
