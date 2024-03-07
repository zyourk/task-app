public class Application {

  private DBUpdater dbUpdater = new DBUpdater();

  public void begin()
  {
    dbUpdater.activateJDBC();
  }

}
