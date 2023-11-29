package RockPaperScissors;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class hardLevel {
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_RESET = "\u001B[0m";
    public Integer scorePlayerInteger = 0;
    private Scanner scanner = new Scanner(System.in);
    private RaitingSaver raitingSaver = new RaitingSaver();
    private Random random = new Random();
    private String[] userSigns;
    private String[] userInputArray;
    String[] hardLevelSigns  = {"rock", "paper", "scissors", "lizard", "spock", "snake", "human", "tree", "wolf", "sponge", "air", "water", "dragon", "devil", "lightning", "gun", "fire", "death"};
    int[][] winningMatrix;	
    
    private String getRandomString(){
    	 int index = random.nextInt(userSigns.length);
		 return userSigns[index];
	}	
    
	private String userNameHello() {
        System.out.println("Enter your name:");
        String userNameString = scanner.nextLine();
        System.out.println(ANSI_RED + "Hello, " + userNameString + "!" + ANSI_RESET);
        return userNameString;
    }
	
	public void gameActionHard() {
	    String userNameString = userNameHello();
	    raitingSaver.loadRatingFromFile();
	    scorePlayerInteger = raitingSaver.rating.getOrDefault(userNameString, scorePlayerInteger);
	    while (true) {
	        System.out.println(userNameString + ", Enter a comma-separated list of options - rock, paper, scissors, lizard, spock,"
	                + " snake, human, tree, wolf, sponge, air, water, dragon, devil, lightning, gun, fire, death."
	                + "\nTo end the game enter '!exit'"
	                + "\nTo see the number of points enter '!rating'");

	        String userInputString = scanner.nextLine();

	        if (userInputString.equals("!exit")) {
	            raitingSaver.rating.put(userNameString, scorePlayerInteger);
	            raitingSaver.saveRatingToFile();
	            System.out.println(ANSI_RED + "Goodbye!" + ANSI_RESET);
	            System.exit(0);
	        } else if (userInputString.equals("!rating")) {
	            System.out.println(userNameString + ", you have " + scorePlayerInteger + " points!");
	        } else {
	            userInputArray = userInputString.split(",");
	            
	            if (userInputArray.length < 3) {
	                System.out.println(ANSI_RED + "Error: the number of characters must be at least 3" + ANSI_RESET);
	                break;
	            } else if (Arrays.stream(userInputArray).allMatch(sign -> Arrays.asList(hardLevelSigns ).contains(sign.trim()))) {
	                userSigns = userInputArray;
	                int n = userSigns.length;
	                winningMatrix = new int[n][n];

	                for (int i = 0; i < n; i++) {
	                    for (int j = 0; j < n; j++) {
	                        if (i == j) {
	                            winningMatrix[i][j] = 0;
	                        } else if ((j - i) % n <= n / 2) {
	                            winningMatrix[i][j] = 1;
	                        } else {
	                            winningMatrix[i][j] = -1;
	                        }
	                    }
	                }

	                mainAction(userNameString, userSigns, winningMatrix);
	            } else {
	                System.out.println(ANSI_RED + "Error" + ANSI_RESET);
	                break;
	            }
	        }
	    }
	}
	
	public void mainAction(String userNameString, String[] userSigns, int[][] winners) {
		while (true) {
			String userInput = scanner.nextLine();
			if (userInput.equals("!rating")) {
				System.out.println(userNameString + ", you have " + scorePlayerInteger + " points!");
			}
        	else if (userInput.equals("!exit")) {
        		raitingSaver.rating.put(userNameString, scorePlayerInteger);
                raitingSaver.saveRatingToFile();
                System.out.println(ANSI_RED + "Goodbye!" + ANSI_RESET);
				System.exit(0);;
			}
        	else if (Arrays.stream(userSigns).noneMatch(sign -> sign.trim().equals(userInput)))
        	{
        		System.out.println(ANSI_RED + "Error: This option is not in the list" + ANSI_RESET);
        		continue;
        	}
        	else {
        		if (userSigns != null && userSigns.length > 0) {
        	        String pcSign = getRandomString();
        	        int i = -1;
        	        int j = -1;

        	        for (int k = 0; k < userSigns.length; k++) {
        	            if (userSigns[k].trim().equals(userInput.trim())) {
        	                i = k;
        	            }
        	            if (userSigns[k].trim().equals(pcSign.trim())) {
        	                j = k;
        	            }
        	        }

        	        if (i == -1 || j == -1) {
        	            System.out.println(ANSI_RED + "Error: Unable to find index for one of the options." + ANSI_RESET);
        	            break;
        	        }
        	        int result = winners[i][j];
        	        if (result == 1) {
        	            System.out.println(ANSI_GREEN + "Win -> Well done. The computer choose " + pcSign + " and failed!" + ANSI_RESET);
        	            scorePlayerInteger += 100;
        	        } else if (result == -1) {
        	            System.out.println(ANSI_RED + "Lose -> Sorry, but the computer choose " + pcSign + ANSI_RESET);
        	        } else {
        	            System.out.println(ANSI_YELLOW + "Draw -> There is a draw, the computer and you choose " + pcSign + ANSI_RESET);
        	            scorePlayerInteger += 50;
        	        }
        	    } else {
        	        System.out.println(ANSI_RED + "Error: No options available. Please choose options first." + ANSI_RESET);
        	        break;
        	    }
			}
		}
	}
}
