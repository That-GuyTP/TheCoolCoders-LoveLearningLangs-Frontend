/**
 * @author Alex Ervin, Thomas Peterson, Aashish Jayapuram
 */
package com.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Users {
    private static Users aUsers;
    private static ArrayList<User> users;
    public static final Scanner kb = new Scanner(System.in);
    private User user;

    /**
     * Default Constructor that is only used if there isn't a current instance of the userlist being used.
     */
    private Users(){
        users = DataLoader.getUsers();
        user = new User();
    }

    public static ArrayList<User> getUsers() {
             return users;
    }

    /**
     * Parameter Constructor that creates an instance of the userlist to be used as needed
     */
    public static Users getInstance(){
        if(aUsers == null){
            aUsers = new Users();
        }
        return aUsers;
    }

    public User login (User user) {
        boolean b = checkUsers(user);
        if (b == true) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * checkUsers method that accepts an inputed username and password, goes through the current list of users, checks the user's inputed username and password, if it doesn't match moves onto the next user, if it does match
     * returns the user's object so that the variables can be tied to the user in the user class. If the inputed data doesn't match any user it returns a value of null.
     * 
     * @param username
     * @param password
     * @return
     */
    public boolean checkUsers(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        UUID userID = user.getUUID();
        for (User check : users) {
            if (check.getUsername().equals(username) || check.getPassword().equals(password) || check.getPassword().equals(email) || check.getUUID().equals(userID)) {
                return true;
            } else if (check.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUserByObject(User user) {
        for (User input : users) {
            if (input.equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * register method that is the process of how someone creates a new account with our app. It first checks to see if either the username or password are connected to an account, and then prompts the user for the rest of the info needed for an account.
     */
    public boolean addUser(User user) {
        if(checkUsers(user) == true) {
            System.out.println("Sorry, this username or password seems to be connected to an account. Try logging in.");
        }else {
            users.add(user);
            System.out.println("Success! You have created an account.");
            DataWriter.saveUsers();
            return true;
        }
        return false;
    }

    /**
   * removeUser method that takes in a user object and removes it from the current list of users.
   * 
   * @param user
   */
    public void removeUser(User user){
        users.remove(user);
        DataWriter.saveUsers();
  }

    public User getUser(User user) {
        for (User check : users ) {
            if (check.equals(user)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Helper method for another way to sort the list of USERS if needed.
     * @param userId
     * @return
     */
    public User getUserById(UUID userId) {
        for (User user : users) {
            if (user.getUUID().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

        /**
     * updateUser method that checks the list of user's for a user with a matching ID and replaces their values if they match.
     * Otherwise it prints that a matching user UUID could not be found.
     * 
     * @param user
     */
    public boolean updateUser(User user) {
        if (user == null) {
            System.out.println("ERROR: USER IS NULL");
            return false;
        }
        UUID userId = user.getUUID();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUUID().equals(userId)) {
                users.set(i, user);
                DataWriter.saveUsers();
                return true;
            }
        }
        System.out.println("Error, user UUID could not be found.");
        return false;
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }



}
