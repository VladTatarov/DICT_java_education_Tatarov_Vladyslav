package RockPaperScissors;

import java.util.Scanner;

public class RockPaperScissors {
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_RESET = "\u001B[0m";
    
	public static void main(String[] args) {
		RockPaperScissors rockPaperScissors = new RockPaperScissors();
		rockPaperScissors.runMenu();
	}
	
    public void runMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("####|RockPaperScissors|####"
                        + "\n1 - [Play]"
                        + "\n2 - [EXIT]");

                if (scanner.hasNextInt()) {
                    int userChoice = scanner.nextInt();
                    processUserChoice(userChoice, scanner);
                } else {
                    System.out.println(ANSI_RED + "Invalid input!" + ANSI_RESET);
                    scanner.next();
                }
            }
        }
    }

    private void processUserChoice(int userChoice, Scanner scanner) {
        switch (userChoice) {
            case 1:
                playGame(scanner);
                break;
            case 2:
                System.out.println(ANSI_RED + "Exiting the game. Goodbye!" + ANSI_RESET);
                System.exit(0);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice. Please choose again." + ANSI_RESET);
                break;
        }
    }

    private void playGame(Scanner scanner) {
        System.out.println("Choose game level:"
                + "\n1 - Easy Level"
                + "\n2 - Hard Level"
                + "\n3 - Back to Menu");

        if (scanner.hasNextInt()) {
            int levelChoice = scanner.nextInt();
            processLevelChoice(levelChoice, scanner);
        } else {
            System.out.println(ANSI_RED + "Invalid input!" + ANSI_RESET);
            scanner.next();
        }
    }

    private void processLevelChoice(int levelChoice, Scanner scanner) {
        switch (levelChoice) {
            case 1:
                System.out.println("Starting Easy Level...");
                easyLevel easyLevel = new easyLevel();
                easyLevel.gameAction();
                break;
            case 2:
                System.out.println("Starting Hard Level...");
                hardLevel hardLevel = new hardLevel();
                hardLevel.gameActionHard();
                break;
            case 3:
            	runMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice. Please choose again." + ANSI_RESET);
                break;
        }
    }
}