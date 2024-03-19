public class Runnable {

  //Single instance of GUI
  private static final SwingGui GUI = new SwingGui();

  /**
   * Runnable method to begin app
   * @param args main method
   */
  public static void main(String[] args)
  {
    GUI.createLoginFrames();
    GUI.start();
  }
}
