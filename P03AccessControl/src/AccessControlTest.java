/**
 * A class that tests the User and AccessControl classes for errors
 * 
 * @author mdgoldstein2
 * @version 1.0
 * @see User
 * @see AccessControl
 */

import java.util.Scanner;
public class AccessControlTest {
  /**
   * Driver method for tester class, checks if test methods failed
   * 
   * @param args
   */
  public static void main(String[] args) {
    int fails = 0;
    if (!testLogin1()) {
      System.out.println("testLogin1 [bad username] failed");
      fails++;
    }
    if (!testLogin2()) {
      System.out.println("testLogin2 [good login] failed");
      fails++;
    }
    if (!testLogin3()) {
      System.out.println("testLogin3 [bad username with default password] failed");
      fails++;
    }
    if (!testAddUser1()) {
      System.out.println("testAddUser1 [non-admin trying to use admin commands] failed");
      fails++;
    }
    if (!testRemove()) {
      System.out.println("testRemove [removing a known User from users ArrayList] failed");
      fails++;
    }
    if (!testChangePassword()) {
      System.out.println("testChangePassword [changing a user's password] failed");
      fails++;
    }
    if (!testChangeAdmin()) {
      System.out.println("testChangeADmin [changing a user's admin status] failed");
      fails++;
    }
    if (fails == 0) {
      System.out.println("All tests passed!");
    }
  }
  
  /**
   * Tests whether logging in with a false username and password is possible
   * 
   * @return true if the false username and password are invalid, false if they are not
   */
  public static boolean testLogin1() {
    AccessControl ac = new AccessControl();
    String user = "not in users ArrayList";
    String pw = "password";
    return !AccessControl.isValidLogin(user, pw); // isValidLogin should return false
  }
  
  /**
   * Tests whether logging in with correct username and password is possible
   * 
   * @return true if the correct username and password are valid, false if they are not
   */
  public static boolean testLogin2() {
    AccessControl ac = new AccessControl();
    String user = "is real";
    String pw = "password1";
    ac.setCurrentUser("admin");
    ac.addUser(user);
    ac.setCurrentUser("is real");
    ac.changePassword(pw);
    return AccessControl.isValidLogin(user, pw); // isValidLogin should return true
  }
  
  /**
   * Tests whether logging in with false username and default password is possible
   * 
   * @return true if the false username and default password are deemed invalid, false if the are
   * not
   */
  public static boolean testLogin3() {
    AccessControl ac = new AccessControl();
    String user = "not in users ArrayList";
    String realUser = "exists";
    ac.setCurrentUser("admin");
    ac.addUser(realUser);
    return !AccessControl.isValidLogin(user, "changeme"); // isValidLogin should return false
  }
  
  /**
   * Tests whether adding a user without being an admin is possible
   * 
   * @return true if the User is not added, false if the user is added
   */
  public static boolean testAddUser1() {
    AccessControl ac = new AccessControl();
    String user = "misha";
    boolean addUserReport = ac.addUser(user);
    if (addUserReport) {
      return false; // addUserReport should be false
    } // Make sure user wasn't added anyway
    return !AccessControl.isValidLogin(user, "changeme");  //isValidLogin should be false
  } 
  
  /**
   * Tests whether removing a user works
   * 
   * @return true if the User is removed, false if the user is never added or never removed
   */
  public static boolean testRemove() {
    AccessControl ac = new AccessControl();
    String user = "sasha";
    ac.setCurrentUser("admin");
    boolean addUserReport = ac.addUser(user);
    if (!addUserReport) {
      return false; // addUserReport should be true since admin is logged in
    } // Make sure user was added
    ac.removeUser("sasha");
    return !AccessControl.isValidLogin(user, "changeme");  //isValidLogin should be false;
  }
  
  /**
   * Tests whether changing a user's password works
   * 
   * @return true if the User is removed, false if the user is never added or never removed
   */
  public static boolean testChangePassword() {
    AccessControl ac = new AccessControl();
    String user = "yuri";
    ac.setCurrentUser("admin");
    boolean addUserReport = ac.addUser(user);
    if (!addUserReport) {
      return false; // addUserReport should be true since admin is logged in
    } // Make sure user was added
    ac.setCurrentUser("yuri");
    ac.changePassword("different password");
    return AccessControl.isValidLogin(user, "different password");  //isValidLogin should be true;
  }
  
  /**
   * Tests whether changing a user's admin status works
   * 
   * @return true if the User is removed, false if the user is never added or never removed
   */
  public static boolean testChangeAdmin() {
    AccessControl ac = new AccessControl();
    String user = "bogdan";
    ac.setCurrentUser("admin");
    boolean addUserReport = ac.addUser(user);
    if (!addUserReport) {
      return false; // addUserReport should be true since admin is logged in
    } // Make sure user was added
    ac.giveAdmin("bogdan"); //should give bogdan admin status
    ac.setCurrentUser("bogdan");
    boolean removeUserReport = ac.removeUser("yuri");
    if(!removeUserReport) {  //should be true if bogdan is admin since only admin can remove
      return false; //if false then giveAdmin() has failed.
    }
    ac.takeAdmin("bogdan");
    return !ac.removeUser("misha"); //removeUser() should return false since bogdan
    //should no longer be admin. If removeUsers() returns true, takeAdmin() has failed
  }
}
