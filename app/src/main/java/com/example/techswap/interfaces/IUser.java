package com.example.techswap.interfaces;

/**
 * The `IUser` interface defines methods for accessing basic user information, such as username and password.
 * Implementations of this interface are intended to provide a way to retrieve user credentials.
 */
public interface IUser {

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    String getUsername();

    /**
     * Retrieves the password associated with the user.
     *
     * @return The password of the user.
     */
    String getPassword();
}