package com.example.techswap.user;

import androidx.annotation.NonNull;

import com.example.techswap.interfaces.IUser;

import java.util.Objects;

/**
 * The `User` class represents a user account in a system, with a unique username and associated password.
 * It implements the {@link IUser} interface to provide access to user credentials.
 */
public class User implements IUser {

    private static IUser currentUser;
    private final String username;
    private final String password;

    /**
     * Constructs a new `User` object with the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password associated with the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user.
     */
    public static IUser getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param currentUser The user to set as the current user.
     */
    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    /**
     * Logs in a user by creating a new User instance with the provided username and password
     * and sets the current user as the logged-in user.
     *
     * @param username The username of the user to log in.
     * @param password The password associated with the user's account.
     */
    public static void userLogin(String username, String password) {
        User user = new User(username, password);
        User.setCurrentUser(user);
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password associated with the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if this user is equal to another object.
     *
     * @param o The object to compare against.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    /**
     * Generates a hash code for the user.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    /**
     * Generates a string representation of the user.
     *
     * @return The string representation of the user.
     */
    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "username='" + username +
                ", password='" + password + '\'' +
                '}';
    }
}
