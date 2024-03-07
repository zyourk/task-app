import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
  private DBUpdater dbUpdater = new DBUpdater();

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
        boolean added = dbUpdater.addUserToDB(userID, username, email, password);
        return added;
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
      dbUpdater.removeUserFromDB(currentUserID);
      return true;
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

}
