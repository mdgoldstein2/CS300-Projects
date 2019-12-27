/**
 * A class whose instances function as a terminal where instances of the User class can be current
 * users.
 * 
 * @author Michael Goldstein
 * @version 1.0
 * @see User
 */
import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {
  static ArrayList<User> users = new ArrayList<User>();// An ArrayList of valid users
  private User currentUser; // Who is currently logged in, if anyone?
  private static final String DEFAULT_PASSWORD = "changeme"; // Default password given to new users
  // or when there is a reset of user's password.

  /**
   * Constructor for AccessControl. Creates a new instance of AccessControl, initializes currentUser
   * to null, and adds an admin User to the users ArrayList.
   */
  public AccessControl() {
    if (!isValidLogin("admin", "root")) {
      users.add(new User ("admin", "root", true));
    }
    currentUser = null;
  }

  /**
   * Checks whether or not a given username and password are a valid login by checking the users
   * array for a User instance with the same username and password
   * 
   * @param username the username that the users array is checked for
   * @param password the password that the users array is checked for
   * @return true if an instance of User in the users array is found with the matching username and
   *         password as the parameters, false if it does not
   * @see User#isValidLogin(String)
   */
  public static boolean isValidLogin(String username, String password) {
    for (int index = 0; index < users.size(); index++) {
      if ((username.equals(users.get(index).getUsername()))
          && (users.get(index).isValidLogin(password))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Changes the currentUser's password to the parameter
   * 
   * @param newPassword the String the currentUser's password is set to
   */
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /**
   * Logs out the currentUser by setting currentUser to null
   */
  public void logout() {
    currentUser = null;
  }

  /**
   * Sets the current user to one from users ArrayList with the same username as the passed through
   * parameter
   * 
   * @param username the username the new currentUser should have
   */
  public void setCurrentUser(String username) {
    for (int index = 0; index < users.size(); index++) {
      if (username.equals(users.get(index).getUsername())) {
        currentUser = users.get(index);
        break;
      }
    }
  }

  /**
   * Adds a new User to the users ArrayList with a given username, the default password, and admin
   * status = false, as long as the currentUser is an admin and the username has not already been
   * used
   * 
   * @param username the usernamne the newly created User will have
   * @return true if the currentUser is an admin and the username has not already been used, false
   *         if either or both of the two conditions above are not met
   */
  public boolean addUser(String username) {
    // checks if currentUser is an admin or logged in
    if(currentUser==null) {
      return false; 
    }
    if (!currentUser.getIsAdmin()) {
      return false;
    } else {
      // checks if existing username already exists
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          return false;
        }
      }
      users.add(new User(username, DEFAULT_PASSWORD, false));
      return true;
    }
  }

  /**
   * Adds a new User to the users ArrayList with a given username, the default password, and given
   * admin status, as long as the currentUser is an admin and the username has not already been used
   * 
   * @param username the usernamne the newly created User will have
   * @param isAdmin  the admin status the newly created User will have
   * @return true if the currentUser is an admin and the username has not already been used, false
   *         if either or both of the two conditions above are not met
   */
  public boolean addUser(String username, boolean isAdmin) {
    // checks if currentUser is an admin or logged in
    if(currentUser==null) {
      return false; 
    }
    if (!currentUser.getIsAdmin()) {
      return false;
    } else {
      // checks if existing username already exists
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          return false;
        }
      }
      users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
      return true;
    }
  }

  /**
   * Removes a user with a given username from the Users ArrayList as long as the currentUser is an
   * admin and the username has already been used
   * 
   * @param username the username a User in users ArrayList will removed for having
   * @return true if the currentUser is an admin and the username has already been used, false if
   *         either or both of the two conditions above are not met
   */
  public boolean removeUser(String username) {
    // checks if currentUser is an admin or logged in
    if(currentUser==null) {
      return false; 
    }
    if (!currentUser.getIsAdmin()) {
      return false;
    } else {
      // checks if given username already exists, then removes the User with that username
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.remove(index);
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Gives a user with a given username admin status as long as the currentUser is an admin and the
   * username has already been used
   * 
   * @param username the username of a User in users ArrayList. That user will receive admin status
   * @return true if the currentUser is an admin and the username has already been used, false if
   *         either or both of the two conditions above are not met
   */
  public boolean giveAdmin(String username) {
    // checks if currentUser is an admin or logged in
    if(currentUser==null) {
      return false; 
    }
    if (!currentUser.getIsAdmin()) {
      return false;
    } else {
      // checks if given username already exists, then gives that User admin status
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.get(index).setIsAdmin(true);
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Takes away admin status from a user with a given username as long as the currentUser is an
   * admin and the username has already been used
   * 
   * @param username the username of a User in users ArrayList. That user will have admin status
   *                 revoked
   * @return true if the currentUser is an admin and the username has already been used, false if
   *         either or both of the two conditions above are not met
   */
  public boolean takeAdmin(String username) {
    // checks if currentUser is an admin or logged in
    if(currentUser==null) {
      return false; 
    }
    if (!currentUser.getIsAdmin()) {
      return false;
    } else {
      // checks if given username already exists, then takes away that User's admin status
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.get(index).setIsAdmin(false);
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Resets the password of a user with a given username as long as the currentUser is an admin and
   * the username has already been used
   * 
   * @param username the username of a User in users ArrayList. That user will have admin status
   *                 revoked
   * @return true if the currentUser is an admin and the username has already been used, false if
   *         either or both of the two conditions above are not met
   */
  public boolean resetPassword(String username) {
    // checks if currentUser is an admin or logged in
    if(currentUser==null) {
      return false; 
    }
    if (!currentUser.getIsAdmin()) {
      return false;
    } else {
      // checks if given username already exists, then resets that user's password
      for (int index = 0; index < users.size(); index++) {
        if (username.equals(users.get(index).getUsername())) {
          users.get(index).setPassword(DEFAULT_PASSWORD);
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Driver method which prompts user for login information. If that information is valid, calls
   * session screen driver method. If invalid, tells user their login failed.
   * 
   * @param userInputScanner Scanner object to be used for user input. Passed trough so multiple
   *                         scanners are not created
   */
  public void loginScreen(Scanner userInputScanner) {
    String username;
    String password;
    while (true) {
      System.out.println("================LOGIN SCREEN================");
      System.out.println("   ENTER USERNAME AND PASSWORD:");

      username = userInputScanner.next();
      password = userInputScanner.next();

      if (isValidLogin(username, password)) {
        sessionScreen(username, userInputScanner);
      } else {
        System.out.println("================LOGIN FAILED================");
      }
    }
  }

  /**
   * 
   * @param username
   * @param userInputScanner
   */
  public void sessionScreen(String username, Scanner userInputScanner) {
    String userInput;
    String[] splitInputs;
    setCurrentUser(username);

    while (true) {
      // Text UI
      System.out.println("===============SESSION SCREEN===============");
      System.out.println("   COMMANDS:");
      System.out.println("    logout");
      System.out.println("    newpw [newpassword]");
      System.out.println("    adduser [username]");
      System.out.println("    adduser [username] [true or false]");
      System.out.println("    rmuser [username]");
      System.out.println("    giveadmin [username]");
      System.out.println("    rmadmin [username]");
      System.out.println("    resetpw [username]");

      // Input Processing
      userInput = userInputScanner.nextLine();
      if (userInput.equals("logout")) {
        logout();
        break;
      } else {
        splitInputs = userInput.split(" ");
        if (splitInputs[0].equals("newpw")) {
          changePassword(splitInputs[1]);
        } else if (splitInputs[0].equals("adduser")) {
          if (splitInputs.length == 2) {
            addUser(splitInputs[1]);
          } else {
            boolean adminStatus = Boolean.parseBoolean(splitInputs[2]);
            addUser(splitInputs[1], adminStatus);
          }
        } else if (splitInputs[0].equals("rmuser")) {
          removeUser(splitInputs[1]);
        } else if (splitInputs[0].equals("giveadmin")) {
          giveAdmin(splitInputs[1]);
        } else if (splitInputs[0].equals("rmadmin")) {
          takeAdmin(splitInputs[1]);
        } else if (splitInputs[0].equals("resetpw")) {
          resetPassword(splitInputs[1]);
        }
      }
    }
  }
}
