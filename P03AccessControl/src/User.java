/**
 * A class that allows for the creation and utilization of User objects as a way to implement access
 * control
 * 
 * @see AccessControl
 * 
 * @author mdgoldstein2
 * @version 1.0
 */
public class User {
  private String username; // The user's name
  private String password; // The user's password
  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * Constructor for User class. Creates a User instance with the fields set to the parameters
   * passed through in the constructor.
   * 
   * @param username The username for the newly created instance of the User class
   * @param password The password for the newly created instance of the User class
   * @param isAdmin  The administrator status for the newly created instance of the User class
   */
  public User(String username, String password, boolean isAdmin) {
    this.username = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Checks if a password passed in as a parameter matches the password of the instance of User
   * 
   * @param password a passed in String which the method checks against the instance's password
   * @return true if the passed in password matches the instance's password, false if it does not
   */
  public boolean isValidLogin(String password) {
    if (this.password.equals(password)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the username of the instance of User
   * 
   * @return String username (the username of the instance)
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns the instance's administrator status
   * 
   * @return true if the instance has admin privileges, false if it does not
   */
  public boolean getIsAdmin() {
    return isAdmin;
  }

  /**
   * Changes the instance's password to one passed in as a parameter
   * 
   * @param password a passed in String which the instance's password is set to
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Changes the instance's administrator status to one passed in as a parameter
   * 
   * @param isAdmin a passed in boolean which the instance's admin status will be set to
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
