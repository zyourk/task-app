import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUpdater
{

  Hidden info = new Hidden();
  private String user = info.getUser();
  private String pass = info.getPass();
  private String url = info.getUrl();
  protected Connection dbConnection;


  public boolean activateJDBC()
  {
    try
    {
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return true;
  }

  public void connect()
  {
    try
    {
      dbConnection = DriverManager.getConnection(url, user, pass);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  public void close()
  {
    try
    {
      dbConnection.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  public void addUserToDB(String userID, String username, String email, String password)
  {
    String addUser = "insert into user (userid, username, email, password) values (?, ?, ?, ?);";
    try
    {
      PreparedStatement preparedAddUser = dbConnection.prepareStatement(addUser);
      preparedAddUser.setString(1, userID);
      preparedAddUser.setString(2, username);
      preparedAddUser.setString(3, email);
      preparedAddUser.setString(4, password);
      int rowsAdded = preparedAddUser.executeUpdate();
      if (rowsAdded == 1)
      {
        System.out.println("Added");
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  public void removeUserFromDB(String email)
  {
    String removeUser = "delete from user where (email = ?);";
    try
    {
      PreparedStatement preparedRemoveUser = dbConnection.prepareStatement(removeUser);
      preparedRemoveUser.setString(1, email);
      int rowsRemoved = preparedRemoveUser.executeUpdate();
      if (rowsRemoved == 1)
      {
        System.out.println("Removed");
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

}
