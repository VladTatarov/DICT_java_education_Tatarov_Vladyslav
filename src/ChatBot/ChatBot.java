package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        // Display a greeting and ask for the user's name
        System.out.println("Hello! My name is DICT_Bot");
        System.out.println("I was created in 2023.");
        System.out.println("Please, remind me your name.");

        // Initialize a Scanner object for keyboard input
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine(); // Read the user's name

        // Greet the user by name
        System.out.println("What a great name you have, " + name + "!");

        // Ask for remainders when dividing the user's age
        System.out.println("Let me guess your age");
        System.out.println("Enter remainders of dividing your age by 3, 5, and 7");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();

        // Calculate the age based on the entered remainders
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Your age is " + age + ", that's a good time to start programming!");

        // Count up to the number entered by the user
        System.out.println("Now I will prove to you that I can count to any number you want");
        int my_number = scanner.nextInt();
        for (int i = 0; i <= my_number; i++) {
            System.out.println(i + " !");
        }

        // Ask the user a question with answer choices
        System.out.println("Choose the correct answer!");
        System.out.println("1. Wrong answer");
        System.out.println("2. Wrong answer");
        System.out.println("3. Correct answer");
        System.out.println("4. Wrong answer");

        int user_choice = 0;
        while (user_choice != 3) { // Continue the loop until the user selects the correct answer (3)
            user_choice = scanner.nextInt();
            if (user_choice > 3 || user_choice < 3)
                System.out.println("Please, try again!"); // Display a retry message for incorrect choices
            else if (user_choice == 3)
                System.out.println("Congratulations, have a nice day!"); // Display a congratulations message for the correct choice
        }

        scanner.close(); // Close the Scanner object after use
    }
}
