package dinnerparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class dinnerparty {

    public static void main(String[] args) {
        displayMenu();
    }

    public static void mainProcess() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the number of friends joining (including you):");

            int numFriends = scanner.nextInt();
            scanner.nextLine();

            if (numFriends <= 0) {
                System.out.println("No one is joining for the party!");
                displayMenu();
            }

            HashMap<String, Double> friendsNamesAndCosts = new HashMap<>();

            System.out.println("Enter the name of every friend (including you), each on a new line:");
            for (int i = 0; i < numFriends; i++) {
                String friendName = scanner.nextLine();
                friendsNamesAndCosts.put(friendName, 0.0);
            }

            System.out.println(friendsNamesAndCosts);
            System.out.println("Enter the total amount:");
            double totalAmount = scanner.nextDouble();
            double splitAmount = totalAmount / numFriends;

            for (String friendName : friendsNamesAndCosts.keySet()) {
                friendsNamesAndCosts.put(friendName, splitAmount);
            }

            System.out.println(friendsNamesAndCosts);
            System.out.println("Do you want to use the 'Who is lucky?' feature? Write Yes/No:");
            scanner.nextLine();
            String luckyChoice = scanner.nextLine().toLowerCase();

            if (luckyChoice.equals("yes")) {
                luckyMethod(friendsNamesAndCosts, totalAmount, numFriends);
            } else if (luckyChoice.equals("no")) {
                System.out.println("Bye");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please enter Yes or No.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public static void luckyMethod(HashMap<String, Double> friendsNamesAndCosts, double totalAmount, int numFriends) {
        ArrayList<String> friendNamesList = new ArrayList<>(friendsNamesAndCosts.keySet());
        Random random = new Random();
        double happyAmount = totalAmount / (numFriends - 1);
        int randomIndex = random.nextInt(friendNamesList.size());
        String randomFriendName = friendNamesList.get(randomIndex);
        for (String friendName : friendsNamesAndCosts.keySet()) {
            friendsNamesAndCosts.put(friendName, happyAmount);
        }
        System.out.println("Lucky Friend => " + randomFriendName);
        friendsNamesAndCosts.put(randomFriendName, 0.0);
        System.out.println(friendsNamesAndCosts);
    }

    public static void displayMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int menuChoice = 0;
            while (menuChoice != 1 && menuChoice != 2) {
                System.out.println("MENU - SHARING OF COMMON COSTS");
                System.out.println("[1] ENTER PARTICIPANTS!");
                System.out.println("[2] EXIT");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    menuChoice = scanner.nextInt();

                    switch (menuChoice) {
                        case 1:
                            System.out.println("Welcome to the game!");
                            mainProcess();
                            break;
                        case 2:
                            System.out.println("Bye!");
                            System.exit(0);
                        default:
                            System.out.println("Try again!");
                            break;
                    }
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    scanner.next();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }
}
