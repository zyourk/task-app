import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Application level of the program.
 *
 * @author Zack Yourkavitch
 */
public class Application {

  // Instance variables for the application to keep track of
  // the user using the application and to have something to access the DB
  private String currentUserID;
  private final DBUpdater dbUpdater = new DBUpdater();

  /**
   * By beginning the application, the connection to the database is established
   * and the currentUserID is reset to none
   */
  public void begin()
  {
    currentUserID = "";
    dbUpdater.activateJDBC();
    dbUpdater.connect();
  }

  /**
   * Ends the connection with the db when the app is exited
   */
  public void end()
  {
    dbUpdater.close();
  }

  /**
   * ensures that a new user's information fits the formatting criteria
   * @param email user email
   * @param username user username
   * @param password user password
   * @return true if fits, false if not
   */
  public boolean verifyNewUser(String email, String username, String password)
  {
    if(email.length() == 0 || email.length() > 255)
    {
      return false;
    }
    if(username.length() == 0 || username.length() > 15)
    {
      return false;
    }
    if(password.length() == 0 || password.length() > 25)
    {
      return false;
    }
    return true;
  }

  public boolean validateTaskInfo(String taskTitle, String taskDesc, String deadline, int priority, String status)
  {
    if(taskTitle.length() == 0 || taskTitle.length() > 20)
    {
      return false;
    }
    if(taskDesc.length() > 200)
    {
      return false;
    }
    if(deadline.length() != 5 || deadline.charAt(2) != '/' || !verifyDate(deadline))
    {
      return false;
    }
    if(priority < 0 || priority > 1)
    {
      return false;
    }
    if(!(status.equalsIgnoreCase("pending")) && !(status.equalsIgnoreCase("completed")))
    {
      return false;
    }
    return true;
  }

  public boolean verifyDate(String deadline)
  {
    List<Integer> thirtyDayMonths = new ArrayList<>();
    thirtyDayMonths.add(4);
    thirtyDayMonths.add(6);
    thirtyDayMonths.add(9);
    thirtyDayMonths.add(11);
    int month = Integer.parseInt(deadline.substring(0, 2));
    int day = Integer.parseInt(deadline.substring(3));
    if(month < 1 || month > 12)
    {
      return false;
    }
    if(day < 1)
    {
      return false;
    }
    if(month == 2 && day > 29)
    {
      return false;
    }
    if(thirtyDayMonths.contains(month) && day > 30)
    {
      return false;
    }
    if(day > 31)
    {
      return false;
    }
    return true;
  }

  public String reformatDate(String date)
  {
    String[] dateParts = date.split("/");
    if(dateParts[0].length() == 1)
    {
      dateParts[0] = "0" + dateParts[0];
    }
    if(dateParts[1].length() == 1)
    {
      dateParts[1] = "0" + dateParts[1];
    }
    return dateParts[0] + "/" + dateParts[1];
  }


  /**
   * checks the credentials a user attempts to log in with
   * to ensure they both exist and match
   * @param email user email
   * @param password user password
   * @return true if log in successful, false if not
   */
  public boolean checkLogIn(String email, String password)
  {
    String storedPassword;
    try
    {
      storedPassword = dbUpdater.findPasswordFromEmail(email);
    }
    catch (DoesNotExistException | SQLException e)
    {
      System.out.println("Email does not exist. Please create account");
      return false;
    }
    if(password.equals(storedPassword))
    {
      currentUserID = dbUpdater.findUserID(email);
      System.out.println("Logged in with user ID: " + currentUserID);
      return true;
    }
    System.out.println("Login failed. Wrong password or email");
    return false;
  }

  /**
   * attempts to add a new user to the database
   * @param email user email
   * @param username user username
   * @param password user password
   * @return true if user added, false if not or if email already exists
   */
  public boolean addUser(String email, String username, String password)
  {
    if(verifyNewUser(email, username, password))
    {
      UUID uniqueID = UUID.randomUUID();
      String userID = uniqueID.toString();
      userID = userID.substring(28);
      try
      {
        return dbUpdater.addUserToDB(userID, username, email, password);
      }
      catch (SQLIntegrityConstraintViolationException e)
      {
        System.out.println("Account for this email already exists!");
        return false;
      }
    }
    System.out.println("There was an error registering your account");
    return false;
  }

  /**
   * removes a user from the DB. user can only delete
   * their account when logged in, so I used currentUserID
   * @param password user password
   * @return true if successfully removed
   */
  public boolean removeUser(String password)
  {
    if(password.equals(dbUpdater.findPasswordFromID(currentUserID)))
    {
      return dbUpdater.removeUserFromDB(currentUserID);

    }
    System.out.println("There was an error deleting your account");
    return false;
  }

  /**
   * adds a category to the DB
   * @param categoryTitle title of category
   * @return true if successfully added
   */
  public boolean addCategory(String categoryTitle)
  {
    if (categoryTitle.length() > 0 && categoryTitle.length() <= 20)
    {
      UUID uniqueCatID = UUID.randomUUID();
      String categoryID = uniqueCatID.toString();
      categoryID = categoryID.substring(28);
      if(dbUpdater.addCategoryToDB(categoryID, currentUserID, categoryTitle))
      {
        System.out.println("Application: Success");
        return true;
      }
      else
      {
        System.out.println("Error adding category to DB");
        return false;
      }
    }
    else
    {
      System.out.println("Incorrect formatting");
      return false;
    }
  }

  /**
   * removes a category from DB
   * @param categoryTitle name of category
   * @return true if successful
   */
  public boolean removeCategory(String categoryTitle)
  {
    String categoryID;
    try
    {
      categoryID = dbUpdater.findCategoryID(categoryTitle, currentUserID);
    }
    catch (DoesNotExistException e)
    {
      System.out.println("Category nonexistent");
      return false;
    }
    if(dbUpdater.removeCategoryFromDB(categoryID))
    {
      System.out.println("Category deleted successfully");
      return true;
    }
    return false;
  }

  /**
   * adds task to DB
   * @param taskTitle name of task
   * @param taskDesc description
   * @param deadline deadline of task
   * @param priority 0 or 1 (unimportant or important)
   * @param status pending or completed
   * @return true if successful
   */
  public boolean addTask(String taskTitle, String taskDesc, String deadline, int priority, String status)
  {
    deadline = reformatDate(deadline);
    if(validateTaskInfo(taskTitle, taskDesc, deadline, priority, status))
    {
      UUID uniqueTaskID = UUID.randomUUID();
      String taskID = uniqueTaskID.toString();
      taskID = taskID.substring(28);
      if(dbUpdater.addTaskToDB(taskID, taskTitle, taskDesc, deadline, priority, status, currentUserID))
      {
        System.out.println("Task added successfully");
        return true;
      }
      System.out.println("Could not add task to database");
      return false;
    }
    System.out.println("Task info invalid");
    return false;
  }

  /**
   * removes a task from DB
   * @param taskTitle given task
   * @return true if successful
   */
  public boolean removeTask(String taskTitle)
  {
    String taskID;
    try
    {
      taskID = dbUpdater.findTaskID(taskTitle, currentUserID);
    }
    catch (DoesNotExistException e)
    {
      System.out.println("Task nonexistent");
      return false;
    }
    if(dbUpdater.removeTaskFromDB(taskID))
    {
      System.out.println("Task deleted successfully");
      return true;
    }
    return false;
  }

  /**
   * adds a task to a category
   * @param categoryTitle given category
   * @param taskTitle given task
   * @return true if successful
   */
  public boolean addTaskToCategory(String categoryTitle, String taskTitle)
  {
    String taskID;
    String categoryID;
    try
    {
      taskID = dbUpdater.findTaskID(taskTitle, currentUserID);
      categoryID = dbUpdater.findCategoryID(categoryTitle, currentUserID);
    }
    catch(DoesNotExistException e)
    {
      System.out.println("Task or category does not exist");
      return false;
    }
    if(dbUpdater.addTaskToCategory(taskID, categoryID))
    {
      System.out.println("Task added to category");
      return true;
    }
    else
    {
      System.out.println("Error adding task to category");
      return false;
    }
  }

  /**
   * removes a task from its category
   * @param taskTitle given task
   * @return true if process successful
   */
  public boolean decategorizeTask(String taskTitle)
  {
    String taskID;
    try
    {
      taskID = dbUpdater.findTaskID(taskTitle, currentUserID);
    }
    catch(DoesNotExistException e)
    {
      System.out.println("Task does not exist");
      return false;
    }
    if(dbUpdater.removeTaskCategory(taskID))
    {
      System.out.println("Task decategorized successfully");
      return true;
    }
    else
    {
      System.out.println("Error decategorizing task");
      return false;
    }
  }

  /**
   * displays all tasks in a given category
   * @param categoryTitle the given category
   * @return true if process successful
   */
  public boolean displayTasksInCat(String categoryTitle)
  {
    String categoryID;
    System.out.println("Tasks in category " + categoryTitle + ":");
    try
    {
      categoryID = dbUpdater.findCategoryID(categoryTitle, currentUserID);
    }
    catch(DoesNotExistException e)
    {
      System.out.println("Category does not exist");
      return false;
    }
    String[] taskTitles = dbUpdater.findTasksInCategory(categoryID);
    for (String taskTitle : taskTitles)
    {
      System.out.println(taskTitle);
    }
    return true;
  }
}
