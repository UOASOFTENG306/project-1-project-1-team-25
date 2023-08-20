package com.example.techswap.user;

import java.util.Objects;
import java.util.Random;

public class User {

//    private static int nextId = 1;  // Initialize the first ID as 1
    private int id;

    private String username;

    private String password;
    Random random = new Random();

    // Generate a random integer between 0 (inclusive) and 10 (exclusive)
    int randomInt = random.nextInt(100);

    public User(String username, String password) {
        this.id = randomInt;
//        nextId++;
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputPassword) {
        if (inputPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
