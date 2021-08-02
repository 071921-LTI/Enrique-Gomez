package com.lntinfotech.controllers;

import java.util.List;
import java.util.Scanner;

import com.lntinfotech.exceptions.ItemNotFoundException;
import com.lntinfotech.models.Item;
import com.lntinfotech.models.Offer;
import com.lntinfotech.models.User;
import com.lntinfotech.services.AuthService;
import com.lntinfotech.services.AuthServiceImpl;
import com.lntinfotech.services.ItemService;
import com.lntinfotech.services.ItemServiceImpl;
import com.lntinfotech.services.OfferService;
import com.lntinfotech.services.OfferServiceImpl;
import com.lntinfotech.services.UserService;
import com.lntinfotech.services.UserServiceImpl;

public class Shop {
    
    static Scanner sc = new Scanner(System.in);
    static AuthService as = new AuthServiceImpl();
    static UserService us = new UserServiceImpl();
    static ItemService is = new ItemServiceImpl();
    static OfferService os = new OfferServiceImpl();

    public static void enterShop() {
        print("", "Welcome to Zorky Shop!", "What would you like to do?", "1. Sign in", "2. Sign up", "3. See our products (can view but need an account to make a bid)", "4. Leave");

        String entranceChoice = sc.nextLine();

        switch(entranceChoice) {
            case "1": signIn();
                break;
            case "2": register();
                break;
            case "3": viewProducts();
                break;
            case "4": sc.close();
                break;
            default: print("done!");
        }
    }

    static void print(String... message) {
        for (String i : message) {
            System.out.println(i);
        }
    }
    
    static void register() {
    	print("Please enter a username");
    	
    	String enteredUsername = sc.nextLine();
    	
    	print("Please enter your password");
    	
    	String enteredPassword = sc.nextLine();
    	
    	User newUser = new User(enteredUsername, enteredPassword);
    	
    	boolean userAdded = us.addUser(newUser);
    	
    	if (userAdded) {
    		print("You successfully registered! Sign in to make offers! Press enter to continue");
    	} else {
    		print("This username is already taken. Press enter to continue");
    	}
        sc.nextLine();
        enterShop();
    }

    static void signIn() {
        print("Please enter your username");

        String enteredUsername = sc.nextLine();

        print("Please enter your password");

        String enteredPassword = sc.nextLine();

        User credentials = new User(enteredUsername, enteredPassword);
        User loggedInUser = as.login(credentials);
        if (loggedInUser != null) {
            loggedIn(loggedInUser);
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
        print("You're logged in!");
        if (user.getUserType().equals("customer")) {

            customerOptions(user);
        } else {
            employeeOptions(user);
        }
    }

    static void customerOptions(User user) {

        print("What would you like to do?", "1. View our products to make an offer", "2. View products you've purchased", "3. Logout");
        
        String customerChoice = sc.nextLine();

        while (!customerChoice.equals("1") && !customerChoice.equals("2") && !customerChoice.equals("3")) {
            print("Please enter 1 2, or 3", "1. View our products to make an offer", "2. View products you've purchased", "3. Logout");

            customerChoice = sc.nextLine();
        }

        if (customerChoice.equals("3")) {
            sc.close();
        } else if (customerChoice.equals("1")) {
            customerProducts(user);
        } else if (customerChoice.equals("2")) {
        	viewOwnedItems(user);
        }
        
    }

    static void viewProducts() {
        List<Item> items = is.getAllItems();

        print("Here are our products! You must create an account to make an offer. Press enter to go back to main menu");

        for(Item item : items) {
            if (item.getIsPurchased() == false) {
                print(item.getId() + ". " + item.getItemName() + " - minimum offer: $" + item.getMinimumOffer());
            }
        }

        sc.nextLine();

        enterShop();
    }

    static void customerProducts(User user) {
        List<Item> items = is.getAllItems();
        
        if (user.getUserType().equals("customer")) {
            print("Here are our products! Enter the number of the item you would like to bid on.", "You can also press 0 to go back");
        } else {
            print("Here are our products! Enter the number of the item you would like to remove", "You can also enter 0 to go back to main menu");
        }

        for(Item item : items) {
            if (item.getIsPurchased() == false) {
                print(item.getId() + ". " + item.getItemName() + " - minimum offer: $" + item.getMinimumOffer());
            }
        }

        String itemChoice = sc.nextLine();
        
        if(user.getUserType().equals("employee")) {
            if (itemChoice.equals("0")) {
                employeeOptions(user);
            } else {
                String itemName = is.removeItem(Integer.parseInt(itemChoice));
                print("You removed " + itemName + " from our inventory. Press enter to continue");

                sc.nextLine();

                employeeOptions(user);
            }
        } else {
            if (itemChoice.equals("0")) {
                customerOptions(user);
            } else {
                try {
                    Item chosenItem = is.getItem(Integer.parseInt(itemChoice));
        
                    itemBid(user, chosenItem);
                } catch (ItemNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    static void employeeOptions(User user) {
        print("What would you like to do?", "1. Remove an item", "2. Add an item", "3. See offers", "4. Logout");
        
        String employeeChoice = sc.nextLine();

        while (!employeeChoice.equals("1") && !employeeChoice.equals("2") && !employeeChoice.equals("3") && !employeeChoice.equals("4")) {
            print("Please enter 1, 2, 3, or 4", "1. Remove an item", "2. Add an item", "3. See offers", "4. Logout");

            employeeChoice = sc.nextLine();
        }

        if (employeeChoice.equals("4")) {
            sc.close();
        } else if (employeeChoice.equals("1")) {
            customerProducts(user);
        } else if (employeeChoice.equals("2")) {
            addAnItem(user);
        } else if (employeeChoice.equals("3")) {
            viewOffers(user);
        }
    }

    static void itemBid(User user, Item item) {
        print("The " + item.getItemName() + " requires a minimum offer of $" + item.getMinimumOffer() + ".", "How much would you like to offer for it? (please enter a number with no more than two decimal places)");

        double offerAmount = sc.nextDouble();

        while(offerAmount < item.getMinimumOffer()) {
            print("Please enter an offer more than the minimum");
            offerAmount = sc.nextDouble();
        }

        Offer offer = new Offer(user.getId(), item.getId(), offerAmount);

        boolean offerMade = os.makeOffer(offer);

        if (offerMade == false) {
            print("Something went wrong. Press enter to return to main menu.");

            sc.nextLine();

            customerOptions(user);
        } else {
            print("You made an offer of $" + offer.getOfferAmount() + " for the " + item.getItemName() + "!", "Wait for to see if your offer is accepted!", "Enter 'more' to view more products or press enter to go to main menu.");

            String input = sc.nextLine();
            
            if(input.equals("more")) {
                customerProducts(user);
            } else {
                customerOptions(user);
            }
        }
    }

    static void addAnItem(User user) {
        print("What is the new item called?");

        String itemName = sc.nextLine();

        print("What is the minimum amount that must be offered for this item?");

        double minimumOffer = sc.nextDouble();

        Item newItem = new Item(itemName, minimumOffer);

        int newItemId = is.addItem(newItem);

        if (newItemId != -1) {
            print("You successfully added the item. Press enter to return to main menu");
        } else {
            print("Something went wrong. Press enter to return to main menu");
        }
        sc.nextLine();
        employeeOptions(user);
    }

    static void viewOffers(User user) {
        print("Here are all the offers. Choose the one you want to accept or reject or press 0 to return to main menu");

        List<Offer> offers = os.getAllOffers();

        for (Offer offer : offers) {
            if (offer.getIsAccepted() == false) {
                print(offer.getId() + ". " + offer.getUser().getUsername() + " offered $" + offer.getOfferAmount() + " for " + offer.getItem().getItemName());
            }
        }
        
        String offerChoice = sc.nextLine();

        boolean next = false;

        while (next != true) {
            try {
                Integer.parseInt(offerChoice);
                
                next = true;
            } catch (Exception e) {
                print("Please enter a the number of the offer you want to accept or reject or press 0 to go to main menu");
                offerChoice = sc.nextLine();
            }
        }

        if (offerChoice.equals("0")) {
            employeeOptions(user);
        } else {
            print("What would you like to with this offer? Enter 'accept' or 'reject'");

            String acceptOrReject = sc.nextLine();

            boolean moveOn = false;

            if (acceptOrReject.equals("accept") || acceptOrReject.equals("reject")) {
                moveOn = true;
            }

            while (moveOn != true) {
                print("please enter 'accept' or 'reject'");
                acceptOrReject = sc.nextLine();
                if (acceptOrReject.equals("accept") || acceptOrReject.equals("reject")) {
                    moveOn = true;
                }
            }

            if (acceptOrReject.equals("accept")) {
                Offer offerBeingAccepted = os.getOffer(Integer.parseInt(offerChoice));
                boolean offerAccepted = os.acceptOffer(Integer.parseInt(offerChoice), offerBeingAccepted);

                if (offerAccepted) {
                    print("You just earned " + offerBeingAccepted.getOfferAmount() + ". Press enter to return to main menu.");
                    
                } else {
                    print("Something went wrong. Press enter to return to main menu");
                }
            } else {
                Offer offerBeingRejected = os.getOffer(Integer.parseInt(offerChoice));
                boolean offerRejected = os.rejectOffer(Integer.parseInt(offerChoice));

                if (offerRejected) {
                    print("You just rejected " + offerBeingRejected.getOfferAmount() + ". Press enter to return to main menu.");
                    
                } else {
                    print("Something went wrong. Press enter to return to main menu");
                }
            }
            sc.nextLine();
            employeeOptions(user);
        }
    }
    
    static void viewOwnedItems(User user) {
    	print("Here are all the items you own. Press enter to go back to main menu");
    	
    	List<Offer> items = is.getItemsByOwner(user.getId());
    	for (Offer item : items) {
    		print(item.getItem().getId() + ". " + item.getItem().getItemName() + " for $" + item.getOfferAmount());
    		
    	}
    	
    	sc.nextLine();
    	
    	customerOptions(user);
    }

}
