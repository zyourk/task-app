import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwingGui
{
  // All components that need declared outside of methods
  // due to being updated by multiple methods
  JFrame logIn;
  JFrame createAccount;
  JFrame mainScreen;
  JFrame addTaskScreen;
  JFrame removeTaskScreen;
  JFrame addCategoryScreen;
  JFrame removeCategoryScreen;
  JFrame categorizeTaskScreen;
  JFrame decategorizeTaskScreen;
  JFrame deleteAccount;
  private final Application application = new Application();
  private JTextArea viewTaskArea;
  private JComboBox categoryDropdown;

  /**
   * creates frames for log in purposes
   */
  public void createLoginFrames()
  {
    createLogin();
    createCreateAccount();
  }

  /**
   * creates frame that user will use after logging in
   */
  public void createMainFrames()
  {
    createMainScreen();
    createAddTask();
    createRemoveTask();
    createAddCategory();
    createRemoveCategory();
    createCategorizeTask();
    createDecategorizeTask();
    createDeleteAccount();
  }

  /**
   * beings the app GUI
   */
  public void start()
  {
    application.begin();
    showLogin();
  }

  /**
   * creates the log in screen
   */
  public void createLogin()
  {
    logIn = new JFrame();
    logIn.setSize(400, 300);

    JLabel emailLabel = new javax.swing.JLabel();
    JTextField emailEntry = new javax.swing.JTextField();
    JLabel passwordLabel = new javax.swing.JLabel();
    JPasswordField passwordEntry = new javax.swing.JPasswordField();
    JButton logInButton = new javax.swing.JButton();
    JButton createAccountButton = new javax.swing.JButton();
    JLabel errorTextLabel = new javax.swing.JLabel();

    logIn.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    emailLabel.setText("Email: ");

    passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    passwordLabel.setText("Password: ");

    logInButton.setBackground(new java.awt.Color(145, 179, 207));
    logInButton.setText("Log In");
    logInButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        String email = emailEntry.getText();
        String password = passwordEntry.getText();
        if(application.checkLogIn(email, password))
        {
          createMainFrames();
          emailEntry.setText("");
          passwordEntry.setText("");
          showMainScreen();
          hideLogin();
        }
        else
        {
          emailEntry.setText("");
          passwordEntry.setText("");
          errorTextLabel.setText("Error logging in. Ensure you have an account before logging in.");
        }
      }
    }
    );

    createAccountButton.setBackground(new java.awt.Color(145, 179, 207));
    createAccountButton.setText("Create Account");
    createAccountButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        showCreateAccount();
      }
    });

    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(logIn.getContentPane());
    logIn.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(emailLabel)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(emailEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(passwordLabel)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(passwordEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(139, 139, 139)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(0, 23, Short.MAX_VALUE))
                                    .addComponent(errorTextLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                    .addComponent(passwordEntry))
                            .addGap(38, 38, 38)
                            .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(errorTextLabel)
                            .addContainerGap(29, Short.MAX_VALUE))
    );

    logIn.pack();
  }

  /**
   * creates the account creation screen
   */
  public void createCreateAccount()
  {
    createAccount = new JFrame();
    createAccount.setSize(400, 300);

    JLabel emailLabel = new javax.swing.JLabel();
    JLabel usernameLabel = new javax.swing.JLabel();
    JLabel passwordLabel = new javax.swing.JLabel();
    JTextField emailEntry = new javax.swing.JTextField();
    JTextField usernameEntry = new javax.swing.JTextField();
    JPasswordField passwordEntry = new javax.swing.JPasswordField();
    JButton createAccountButton = new javax.swing.JButton();
    JLabel errorLabel = new javax.swing.JLabel();

    createAccount.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    emailLabel.setText("Email:");

    usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    usernameLabel.setText("Username:");

    passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    passwordLabel.setText("Password:");

    createAccountButton.setBackground(new java.awt.Color(145, 179, 207));
    createAccountButton.setText("Create");
    createAccountButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        String username = usernameEntry.getText();
        String email = emailEntry.getText();
        String password = passwordEntry.getText();
        emailEntry.setText("");
        usernameEntry.setText("");
        passwordEntry.setText("");
        if(application.addUser(email, username, password))
        {
          hideCreateAccount();
        }
        else
        {
          errorLabel.setText("Account could not be created. Ensure formatting is correct.");
        }
      }
    });

    errorLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(createAccount.getContentPane());
    createAccount.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(emailLabel)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(emailEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(usernameLabel)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(usernameEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(passwordLabel)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(passwordEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(142, 142, 142)
                                                            .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(0, 40, Short.MAX_VALUE))
                                    .addComponent(errorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usernameEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(35, 35, 35)
                            .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(errorLabel)
                            .addContainerGap(56, Short.MAX_VALUE))
    );

    createAccount.pack();
  }

  /**
   * creates the home screen
   */
  public void createMainScreen()
  {
    mainScreen = new JFrame();
    mainScreen.setSize(710, 396);

    JLabel userIDLabel = new javax.swing.JLabel();
    JLabel viewTaskLabel = new javax.swing.JLabel();
    JLabel editCategoryLabel = new javax.swing.JLabel();
    JLabel editTaskLabel = new javax.swing.JLabel();
    JScrollPane scrollableTaskArea = new javax.swing.JScrollPane();
    viewTaskArea = new javax.swing.JTextArea();
    JLabel sortTaskLabel = new javax.swing.JLabel();
    JButton sortCategoryButton = new javax.swing.JButton();
    JButton sortDeadlineButton = new javax.swing.JButton();
    JButton addTaskButton = new javax.swing.JButton();
    JButton removeTaskButton = new javax.swing.JButton();
    JButton addCategoryButton = new javax.swing.JButton();
    JButton removeCategoryButton = new javax.swing.JButton();
    JButton categorizeButton = new javax.swing.JButton();
    JButton decategorizeButton = new javax.swing.JButton();
    JButton deleteAccountButton = new javax.swing.JButton();

    mainScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    userIDLabel.setText("userID: " + application.getCurrentUserID());

    viewTaskLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    viewTaskLabel.setText("View Tasks");

    editCategoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    editCategoryLabel.setText("Edit Categories");

    editTaskLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    editTaskLabel.setText("Edit Tasks");

    viewTaskArea.setColumns(20);
    viewTaskArea.setRows(5);
    viewTaskArea.setText("");
    scrollableTaskArea.setViewportView(viewTaskArea);
    viewTaskArea.setEditable(false);
    ArrayList<String> userTasks = application.returnUserTasks();
    for (String userTask : userTasks)
    {
      viewTaskArea.append(userTask + "\n");
    }

    sortTaskLabel.setText("Sort Tasks");

    sortCategoryButton.setBackground(new java.awt.Color(145, 179, 207));
    sortCategoryButton.setText("Category");
    sortCategoryButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        updateTaskArea(true, false);
      }
    });

    sortDeadlineButton.setBackground(new java.awt.Color(145, 179, 207));
    sortDeadlineButton.setText("Deadline");
    sortDeadlineButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        updateTaskArea(false, true);
      }
    });

    addTaskButton.setBackground(new java.awt.Color(145, 179, 207));
    addTaskButton.setText("Add Task");
    addTaskButton.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
      hideMainScreen();
      showAddTask();
    }
  });

    removeTaskButton.setBackground(new java.awt.Color(145, 179, 207));
    removeTaskButton.setText("Remove Task");
    removeTaskButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideMainScreen();
        showRemoveTask();
      }
    });

    addCategoryButton.setBackground(new java.awt.Color(145, 179, 207));
    addCategoryButton.setText("Add Category");
    addCategoryButton.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
      hideMainScreen();
      showAddCategory();
    }
  });

    removeCategoryButton.setBackground(new java.awt.Color(145, 179, 207));
    removeCategoryButton.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
    removeCategoryButton.setText("Remove Category");
    removeCategoryButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideMainScreen();
        showRemoveCategory();
      }
    });

    categorizeButton.setBackground(new java.awt.Color(145, 179, 207));
    categorizeButton.setText("Categorize Task");
    categorizeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        showCategorizeTask();
        hideMainScreen();
      }
    });

    decategorizeButton.setBackground(new java.awt.Color(145, 179, 207));
    decategorizeButton.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
    decategorizeButton.setText("Decategorize Task");
    decategorizeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        showDecategorizeTask();
        hideMainScreen();
      }
    });

    deleteAccountButton.setBackground(new java.awt.Color(245, 66, 66));
    deleteAccountButton.setText("Delete Account");
    deleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        showDeleteAccount();
        hideMainScreen();
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainScreen.getContentPane());
    mainScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(viewTaskLabel)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGap(20, 20, 20)
                                                                    .addComponent(sortTaskLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(38, 38, 38)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(sortDeadlineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(sortCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(scrollableTaskArea, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(userIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(editTaskLabel)
                                                            .addGap(68, 68, 68)
                                                            .addComponent(addTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(removeTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(categorizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(editCategoryLabel)
                                                            .addGap(74, 74, 74)
                                                            .addComponent(addCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(removeCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(65, 65, 65)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(deleteAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(decategorizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(70, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(editCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(22, 22, 22))
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(userIDLabel)
                                            .addGap(38, 38, 38)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(viewTaskLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(sortTaskLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(sortCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(sortDeadlineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(scrollableTaskArea, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                            .addComponent(editTaskLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(82, 82, 82))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(addTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(removeTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(categorizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addComponent(decategorizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(addCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(removeCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addGap(22, 22, 22))
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                            .addComponent(deleteAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addContainerGap())))))))
    );

    mainScreen.pack();
  }

  /**
   * creates the screen for adding tasks
   */
  public void createAddTask()
  {
    addTaskScreen = new JFrame();
    addTaskScreen.setSize(600, 300);

    JLabel titleLabel = new javax.swing.JLabel();
    JLabel descriptionLabel = new javax.swing.JLabel();
    JLabel priorityLabel = new javax.swing.JLabel();
    JLabel deadlineLabel = new javax.swing.JLabel();
    JTextField titleEntry = new javax.swing.JTextField();
    JTextField descriptionEntry = new javax.swing.JTextField();
    JTextField deadlineEntry = new javax.swing.JTextField();
    JTextField priorityEntry = new javax.swing.JTextField();
    JLabel titleHelpLabel = new javax.swing.JLabel();
    JLabel descriptionHelpLabel = new javax.swing.JLabel();
    JLabel priorityHelpLabel = new javax.swing.JLabel();
    JLabel deadlineHelpLabel = new javax.swing.JLabel();
    JButton submitButton = new javax.swing.JButton();
    JButton backButton = new javax.swing.JButton();
    JLabel errorTextLabel = new javax.swing.JLabel();

    addTaskScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    titleLabel.setText("Title:");

    descriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    descriptionLabel.setText("Description:");

    priorityLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    priorityLabel.setText("Priority:");

    deadlineLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    deadlineLabel.setText("Deadline:");

    titleHelpLabel.setText("Up to 20 characters");

    descriptionHelpLabel.setText("Up to 150 characters");

    priorityHelpLabel.setText("0 for low, 1 for high");

    deadlineHelpLabel.setText("Formatted MM/DD");

    submitButton.setBackground(new java.awt.Color(145, 179, 207));
    submitButton.setText("Submit");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        String taskTitle = titleEntry.getText();
        String taskDescription = descriptionEntry.getText();
        String taskDeadline = deadlineEntry.getText();
        int taskPriority = Integer.parseInt(priorityEntry.getText());
        titleEntry.setText("");
        descriptionEntry.setText("");
        deadlineEntry.setText("");
        priorityEntry.setText("");
        if(application.addTask(taskTitle, taskDescription, taskDeadline, taskPriority))
        {
          updateTaskArea(false, false);
          showMainScreen();
          hideAddTask();
        }
        else
        {
          errorTextLabel.setText("There was an error adding your task.");
        }
      }
    });

    backButton.setBackground(new java.awt.Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideAddTask();
        showMainScreen();
      }
    });

    errorTextLabel.setBackground(new java.awt.Color(255, 255, 255));
    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(addTaskScreen.getContentPane());
    addTaskScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(titleLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(titleEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(priorityLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(priorityEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(priorityHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(descriptionLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(descriptionEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addComponent(deadlineLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(deadlineEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(194, 194, 194)
                                            .addComponent(titleHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(81, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(descriptionHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(60, 60, 60))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(errorTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(62, 62, 62))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(337, Short.MAX_VALUE)
                                    .addComponent(deadlineHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(143, 143, 143)))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(titleEntry)
                                            .addGap(4, 4, 4)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(titleHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(descriptionEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(descriptionHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deadlineEntry)
                                    .addComponent(deadlineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(priorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priorityEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(priorityHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errorTextLabel)
                                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(202, Short.MAX_VALUE)
                                    .addComponent(deadlineHelpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(109, 109, 109)))
    );

    addTaskScreen.pack();
  }

  /**
   * creates the screen for removing tasks
   */
  public void createRemoveTask()
  {
    removeTaskScreen = new JFrame();
    removeTaskScreen.setSize(400, 300);

    JLabel taskIDLabel = new JLabel();
    JTextField taskIDEntry = new JTextField();
    JScrollPane unusedTextScroll = new JScrollPane();
    JTextArea taskGuideArea = new JTextArea();
    JButton submitButton = new JButton();
    JButton backButton = new JButton();
    JLabel errorTextLabel = new JLabel();

    removeTaskScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    taskIDLabel.setFont(new Font("Segoe UI", 1, 24)); // NOI18N
    taskIDLabel.setText("TaskID:");

    taskGuideArea.setEditable(false);
    taskGuideArea.setColumns(20);
    taskGuideArea.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    taskGuideArea.setRows(5);
    taskGuideArea.setText("If you are unsure of the Task ID of the task you\nwish to delete, you can find it back on the\nhome screen.");
    unusedTextScroll.setViewportView(taskGuideArea);

    submitButton.setBackground(new Color(145, 179, 207));
    submitButton.setText("Submit");
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if(application.removeTask(taskIDEntry.getText()))
        {
          errorTextLabel.setText("");
          taskIDEntry.setText("");
          hideRemoveTask();
          updateTaskArea(false, false);
          showMainScreen();
        }
        else
        {
          taskIDEntry.setText("");
          errorTextLabel.setText("There was an error removing your task.");
        }
      }
    });

    backButton.setBackground(new Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        hideRemoveTask();
        showMainScreen();
      }
    });

    errorTextLabel.setForeground(new Color(252, 28, 3));

    GroupLayout layout = new GroupLayout(removeTaskScreen.getContentPane());
    removeTaskScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(taskIDLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(taskIDEntry, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(unusedTextScroll, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(142, 142, 142)
                                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(errorTextLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(unusedTextScroll, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(taskIDLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(taskIDEntry, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
                            .addGap(23, 23, 23)
                            .addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(errorTextLabel)
                            .addContainerGap(27, Short.MAX_VALUE))
    );

    removeTaskScreen.pack();
  }

  /**
   * creates the screen for adding categories
   */
  public void createAddCategory()
  {
    addCategoryScreen = new JFrame();
    addCategoryScreen.setSize(400, 300);

    JLabel titleLabel = new javax.swing.JLabel();
    JTextField titleEntry = new javax.swing.JTextField();
    JLabel titleGuideLabel = new javax.swing.JLabel();
    JButton submitButton = new javax.swing.JButton();
    JButton backButton = new javax.swing.JButton();
    JLabel errorTextLabel = new javax.swing.JLabel();

    addCategoryScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    titleLabel.setText("Title:");

    titleGuideLabel.setText("Categories can be up to 20 characters.");

    submitButton.setBackground(new java.awt.Color(145, 179, 207));
    submitButton.setText("Submit");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        if(application.addCategory(titleEntry.getText()))
        {
          categoryDropdown.addItem(titleEntry.getText());
          errorTextLabel.setText("");
          titleEntry.setText("");
          hideAddCategory();
          showMainScreen();
        }
        else
        {
          titleEntry.setText("");
          errorTextLabel.setText("There was an error adding your category.");
        }
      }
    });

    backButton.setBackground(new java.awt.Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideAddCategory();
        showMainScreen();
      }
    });

    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(addCategoryScreen.getContentPane());
    addCategoryScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(titleLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(titleEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(90, 90, 90)
                                                            .addComponent(titleGuideLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(143, 143, 143)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(0, 77, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(errorTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(titleEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(titleGuideLabel)
                            .addGap(41, 41, 41)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(errorTextLabel)
                            .addContainerGap(26, Short.MAX_VALUE))
    );

    addCategoryScreen.pack();

    addCategoryScreen.pack();
  }

  /**
   * creates the screen for removing categories
   */
  public void createRemoveCategory()
  {
    removeCategoryScreen = new JFrame();
    removeCategoryScreen.setSize(400, 300);

    JLabel categoryIDLabel = new JLabel();
    JTextField categoryIDEntry = new JTextField();
    JScrollPane unusedTextScroll = new JScrollPane();
    JTextArea categoryGuideLabel = new JTextArea();
    JButton submitButton = new JButton();
    JButton backButton = new JButton();
    JLabel errorTextLabel = new JLabel();

    removeCategoryScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    categoryIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    categoryIDLabel.setText("CategoryID:");

    categoryGuideLabel.setEditable(false);
    categoryGuideLabel.setColumns(20);
    categoryGuideLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    categoryGuideLabel.setRows(5);
    categoryGuideLabel.setText("If you are unsure of the ID of the category you\nwish to delete, you can find it back on the\nhome screen.");
    unusedTextScroll.setViewportView(categoryGuideLabel);

    submitButton.setBackground(new java.awt.Color(145, 179, 207));
    submitButton.setText("Submit");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        String catToRemove;
        try
        {
          catToRemove = application.returnCategoryTitle(categoryIDEntry.getText());
        }
        catch(DoesNotExistException e)
        {
          errorTextLabel.setText("Category does not exist");
          return;
        }
        if(application.removeCategory(categoryIDEntry.getText()))
        {
          errorTextLabel.setText("");
          categoryIDEntry.setText("");
          categoryDropdown.removeItem(catToRemove);
          updateTaskArea(false, false);
          hideRemoveCategory();
          showMainScreen();
        }
        else
        {
          categoryIDEntry.setText("");
          errorTextLabel.setText("Category with categoryID " + categoryIDEntry.getText() + " does not exist");
        }
      }
    });

    backButton.setBackground(new java.awt.Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideRemoveCategory();
        showMainScreen();
      }
    });

    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(removeCategoryScreen.getContentPane());
    removeCategoryScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(categoryIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(categoryIDEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(unusedTextScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(142, 142, 142)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(errorTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(unusedTextScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(categoryIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(categoryIDEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(23, 23, 23)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(errorTextLabel)
                            .addContainerGap(27, Short.MAX_VALUE))
    );

    removeCategoryScreen.pack();
  }

  /**
   * creates the screen for adding tasks to a category
   */
  public void createCategorizeTask()
  {
    categorizeTaskScreen = new JFrame();
    categorizeTaskScreen.setSize(400, 300);

    JScrollPane unusedScrollPane = new javax.swing.JScrollPane();
    JTextArea taskGuideArea = new javax.swing.JTextArea();
    JLabel taskIDLabel = new javax.swing.JLabel();
    JTextField taskIDEntry = new javax.swing.JTextField();
    JLabel categoryLabel = new javax.swing.JLabel();
    categoryDropdown = new javax.swing.JComboBox<>();
    JButton submitButton = new javax.swing.JButton();
    JButton backButton = new javax.swing.JButton();
    JLabel errorTextLabel = new javax.swing.JLabel();

    categorizeTaskScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    taskGuideArea.setEditable(false);
    taskGuideArea.setColumns(20);
    taskGuideArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    taskGuideArea.setRows(5);
    taskGuideArea.setText("If you are unsure of the Task ID of the task you\nwish to delete, you can find it back on the\nhome screen.");
    unusedScrollPane.setViewportView(taskGuideArea);

    taskIDLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    taskIDLabel.setText("TaskID:");

    categoryLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    categoryLabel.setText("Category:");

    ArrayList<String> addToDropdown = application.returnUserCategories();
    for (String s : addToDropdown)
    {
      categoryDropdown.addItem(s);
    }

    submitButton.setBackground(new java.awt.Color(145, 179, 207));
    submitButton.setText("Submit");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        String taskID = taskIDEntry.getText();
        String category = categoryDropdown.getSelectedItem().toString();
        if(application.addTaskToCategory(category, taskID))
        {
          errorTextLabel.setText("");
          taskIDEntry.setText("");
          updateTaskArea(false, false);
          hideCategorizeTask();
          showMainScreen();
        }
        else
        {
          taskIDEntry.setText("");
          errorTextLabel.setText("There was an error adding task to category");
        }
      }
    });

    backButton.setBackground(new java.awt.Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideCategorizeTask();
        showMainScreen();
      }
    });

    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(categorizeTaskScreen.getContentPane());
    categorizeTaskScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(36, Short.MAX_VALUE)
                            .addComponent(unusedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(errorTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(taskIDLabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(taskIDEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(categoryLabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(categoryDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(unusedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(taskIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(taskIDEntry)
                                            .addGap(3, 3, 3)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(categoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(categoryDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(3, 3, 3)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(errorTextLabel))
                            .addContainerGap(12, Short.MAX_VALUE))
    );

    categorizeTaskScreen.pack();
  }

  /**
   * creates the screen to removing category from a task
   */
  public void createDecategorizeTask()
  {
    decategorizeTaskScreen = new JFrame();
    decategorizeTaskScreen.setSize(400, 300);

    JScrollPane unusedScrollPane = new javax.swing.JScrollPane();
    JTextArea taskGuideArea = new javax.swing.JTextArea();
    JLabel taskIDLabel = new javax.swing.JLabel();
    JTextField taskIDEntry = new javax.swing.JTextField();
    JButton submitButton = new javax.swing.JButton();
    JButton backButton = new javax.swing.JButton();
    JLabel errorTextLabel = new javax.swing.JLabel();

    decategorizeTaskScreen.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    taskGuideArea.setEditable(false);
    taskGuideArea.setColumns(20);
    taskGuideArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    taskGuideArea.setRows(5);
    taskGuideArea.setText("If you are unsure of the Task ID of the task you\nwish to change, you can find it back on the\nhome screen.");
    unusedScrollPane.setViewportView(taskGuideArea);

    taskIDLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    taskIDLabel.setText("TaskID:");

    submitButton.setBackground(new java.awt.Color(145, 179, 207));
    submitButton.setText("Submit");
    submitButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        if(application.decategorizeTask(taskIDEntry.getText()))
        {
          errorTextLabel.setText("");
          taskIDEntry.setText("");
          updateTaskArea(false, false);
          hideDecategorizeTask();
          showMainScreen();
        }
        else
        {
          errorTextLabel.setText("There was an error decategorizing. Does the task exist?");
          taskIDEntry.setText("");
        }
      }
    });

    backButton.setBackground(new java.awt.Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideDecategorizeTask();
        showMainScreen();
      }
    });

    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(decategorizeTaskScreen.getContentPane());
    decategorizeTaskScreen.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(31, 31, 31)
                                                            .addComponent(unusedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(taskIDLabel)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(taskIDEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(0, 27, Short.MAX_VALUE))
                                    .addComponent(errorTextLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(unusedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(taskIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(taskIDEntry))
                            .addGap(49, 49, 49)
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(errorTextLabel)
                            .addContainerGap())
    );

    decategorizeTaskScreen.pack();
  }

  /**
   * creates the screen on which a user can delete their account
   */
  public void createDeleteAccount()
  {
    deleteAccount = new JFrame();
    deleteAccount.setSize(400, 300);

    JButton deleteButton = new javax.swing.JButton();
    JButton backButton = new javax.swing.JButton();
    JLabel passwordLabel = new javax.swing.JLabel();
    JPasswordField passwordEntry = new javax.swing.JPasswordField();
    JLabel sureLabel = new javax.swing.JLabel();
    JLabel sureInfoLabe = new javax.swing.JLabel();
    JLabel errorTextLabel = new javax.swing.JLabel();

    deleteAccount.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    deleteButton.setBackground(new java.awt.Color(245, 66, 66));
    deleteButton.setText("Delete");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        if(application.removeUser(passwordEntry.getText()))
        {
          hideDeleteAccount();
          passwordEntry.setText("");
          showLogin();
        }
        else
        {
          passwordEntry.setText("");
          errorTextLabel.setText("Error deleting account. Ensure password is correct.");
        }
      }
    });

    backButton.setBackground(new java.awt.Color(145, 179, 207));
    backButton.setText("Back");
    backButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hideDeleteAccount();
        showMainScreen();
      }
    });

    passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    passwordLabel.setText("Password: ");

    sureLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    sureLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    sureLabel.setText("Are You Sure?");

    sureInfoLabe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    sureInfoLabe.setText("This action is irreversible. All created tasks and categories will be deleted.");

    errorTextLabel.setForeground(new java.awt.Color(252, 28, 3));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(deleteAccount.getContentPane());
    deleteAccount.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(135, 135, 135)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                                                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(passwordLabel)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(passwordEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                            .addGap(63, 63, 63)
                                                            .addComponent(sureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(sureInfoLabe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(errorTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(sureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sureInfoLabe)
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(passwordEntry))
                            .addGap(31, 31, 31)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(errorTextLabel)
                            .addContainerGap())
    );

    deleteAccount.pack();
  }

  /**
   * updates the task area on the home screen
   * @param sortCategory true if user clicks sort by category
   * @param sortDeadline true if user clicks sort by deadline
   */
  public void updateTaskArea(boolean sortCategory, boolean sortDeadline)
  {
    viewTaskArea.setText("");
    ArrayList<String> tasks = new ArrayList<>();
    if(!sortCategory && !sortDeadline)
    {
      tasks = application.returnUserTasks();
    }
    if(sortCategory && !sortDeadline)
    {
      tasks = application.returnCatSortedTasks();
    }
    if(!sortCategory && sortDeadline)
    {
      tasks = application.returnDeadlineSortedTasks();
    }
    for (String task : tasks)
    {
      viewTaskArea.append(task + "\n");
    }
  }

  /**
   * makes log in visible
   */
  public void showLogin()
  {
    logIn.setVisible(true);
  }

  /**
   * hides log in
   */
  public void hideLogin()
  {
    logIn.setVisible(false);
  }

  /**
   * makes create account screen visible
   */
  public void showCreateAccount()
  {
    createAccount.setVisible(true);
  }

  /**
   * hides create account screen
   */
  public void hideCreateAccount()
  {
    createAccount.setVisible(false);
  }

  /**
   * makes home screen visible
   */
  public void showMainScreen()
  {
    mainScreen.setVisible(true);
  }

  /**
   * hides home screen
   */
  public void hideMainScreen()
  {
    mainScreen.setVisible(false);
  }

  /**
   * makes add task screen visible
   */
  public void showAddTask()
  {
    addTaskScreen.setVisible(true);
  }

  /**
   * hides add task screen
   */
  public void hideAddTask()
  {
    addTaskScreen.setVisible(false);
  }

  /**
   * makes remove task screen visible
   */
  public void showRemoveTask()
  {
    removeTaskScreen.setVisible(true);
  }

  /**
   * hides remove task screen
   */
  public void hideRemoveTask()
  {
    removeTaskScreen.setVisible(false);
  }

  /**
   * makes add category screen
   */
  public void showAddCategory()
  {
    addCategoryScreen.setVisible(true);
  }

  /**
   * hides add category screen
   */
  public void hideAddCategory()
  {
    addCategoryScreen.setVisible(false);
  }

  /**
   * makes remove category screen visible
   */
  public void showRemoveCategory()
  {
    removeCategoryScreen.setVisible(true);
  }

  /**
   * hides remove category screen
   */
  public void hideRemoveCategory()
  {
    removeCategoryScreen.setVisible(false);
  }

  /**
   * makes categorize screen visible
   */
  public void showCategorizeTask()
  {
    categorizeTaskScreen.setVisible(true);
  }

  /**
   * hides categorize task screen
   */
  public void hideCategorizeTask()
  {
    categorizeTaskScreen.setVisible(false);
  }

  /**
   * makes decategorize task screen visible
   */
  public void showDecategorizeTask()
  {
    decategorizeTaskScreen.setVisible(true);
  }

  /**
   * hides decategorize task screen
   */
  public void hideDecategorizeTask()
  {
    decategorizeTaskScreen.setVisible(false);
  }

  /**
   * makes delete account screen visible
   */
  public void showDeleteAccount()
  {
    deleteAccount.setVisible(true);
  }

  /**
   * hides delete account screen
   */
  public void hideDeleteAccount()
  {
    deleteAccount.setVisible(false);
  }
}
