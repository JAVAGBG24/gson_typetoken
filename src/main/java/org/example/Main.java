package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserManager userManager = new UserManager();

        userManager.readFile();

        User user = new User();
        user.setUsername("NisseJanne");
        user.setId(5);

        userManager.saveToFile(user);
    }
}