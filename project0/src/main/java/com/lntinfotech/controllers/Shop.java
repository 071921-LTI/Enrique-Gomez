package com.lntinfotech.controllers;

import java.util.Scanner;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;
import com.lntinfotech.services.AuthService;
import com.lntinfotech.services.AuthServiceImpl;
import com.lntinfotech.services.ItemService;
import com.lntinfotech.services.ItemServiceImpl;
import com.lntinfotech.services.UserService;
import com.lntinfotech.services.UserServiceImpl;

public class Shop {
    
    static Scanner sc = new Scanner(System.in);
    static AuthService as = new AuthServiceImpl();
    static UserService us = new UserServiceImpl();
    static ItemService is = new ItemServiceImpl();

    public static void enterShop() {
        print("", "Welcome to Zorky Shop!", "What would you like to do?", "1. Sign in", "2. Sign up", "3. See our products (can view but need an account to purchase)", "4. Leave");

        String entranceChoice = sc.nextLine();

        switch(entranceChoice) {
            case "1": signIn();
                break;
            case "2":
            case "3":
            case "4":
            default: print("done!");
        }
    }

    static void print(String... message) {
        for (String i : message) {
            System.out.println(i);
        }
    }

    static void signIn() {
        print("Please enter your username");

        String enteredUsername = sc.nextLine();

        print("Please enter your password");

        String enteredPassword = sc.nextLine();

        User credentials = new User(enteredUsername, enteredPassword, "");

        if (as.login(credentials)) {
            loggedIn(credentials);
        } else {
            print("Username and password do not match", "Press enter to try again or type 'exit' or 'quit' to quit");

            String input = sc.nextLine();

            while (!input.equals("") && !input.equals("exit") && !input.equals("quit")) {
                print("Please press enter to try again or type 'exit' or 'quit' to quit");
                input = sc.nextLine();
            }

            if (input.equals("exit") || input.equals("quit")) {
                sc.close();
            } else {
                signIn();
            }
            
        }
    }

    static void loggedIn(User user) {
        try {
            User loggedInUser = us.getUser(user.getUsername());

            if (loggedInUser.getUserType().equals("customer")) {
                customerOptions(loggedInUser);
            } else {
                print("he is an employee");
            }
        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void customerOptions(User user) {
        print("", "You're logged in!", "What would you like to do?", "1. View our products for purchase", "2. Logout");
        
        String customerChoice = sc.nextLine();

        while (!customerChoice.equals("1") && !customerChoice.equals("2")) {
            print("Please enter 1 or 2", "1. View our products for purchase", "2. Logout");

            customerChoice = sc.nextLine();
        }

        if (customerChoice.equals("2")) {
            sc.close();
        } else if (customerChoice.equals("1")) {
            print("here are our products!");
        }
        
    }

    static void employeeOptions(User user) {

    }
}