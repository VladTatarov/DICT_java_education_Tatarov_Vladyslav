package RockPaperScissors;

import java.util.Scanner;

public class easyLevel {
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_RESET = "\u001B[0m";
    public Integer scorePlayerInteger = 0;
    private Scanner scanner = new Scanner(System.in);
    private RaitingSaver raitingSaver = new RaitingSaver();
    
    private String getRandomString(){
        int r = (int) (Math.random()*3);
        String pcSign = new String [] {"rock","paper","scissors"}[r];
        return pcSign;
    }
    private String userNameHello() {
        System.out.println("Enter your name:");
        String userNameString = scanner.nextLine();
        System.out.println(ANSI_RED + "Hello, " + userNameString + "!" + ANSI_RESET);
        return userNameString;
    }
    
    public void gameAction() {
    	raitingSaver.loadRatingFromFile();
        String userNameString = userNameHello();
        scorePlayerInteger = raitingSaver.rating.getOrDefault(userNameString, scorePlayerInteger);
        System.out.println(userNameString + ", you must choose one of the signs - rock, paper, scissors."
                + "\nTo end the game enter '!exit'"
                + "\nTo see the number of points enter '!rating'");
        while (true) {
        	String pcSign = getRandomString();
        	String userSign = scanner.nextLine();
        	if (userSign.equals(pcSign)) {
				System.out.println(ANSI_YELLOW + "Draw -> There is a draw, pc and you choose " + pcSign + ANSI_RESET);
				scorePlayerInteger += 50;
			}
        	else if (userSign.equals("rock")) {
        		if (pcSign.equals("scissors")) {
					System.out.println(ANSI_GREEN + "Win -> Well done. The computer chose " + pcSign + " and failed!" + ANSI_RESET);
					scorePlayerInteger += 100;
				}
        		else {
					System.out.println(ANSI_RED + "Lose -> Sorry, but the computer choose " + pcSign + ANSI_RESET);
				}
				
			}
        	else if (userSign.equals("paper")) {
        		if (pcSign.equals("rock")) {
					System.out.println(ANSI_GREEN + "Win -> Well done. The computer chose " + pcSign + " and failed!" + ANSI_RESET);
					scorePlayerInteger += 100;
				}
        		else {
					System.out.println(ANSI_RED + "Lose -> Sorry, but the computer choose " + pcSign + ANSI_RESET);
				}
				
			}
        	else if (userSign.equals("scissors")) {
        		if (pcSign.equals("paper")) {
					System.out.println(ANSI_GREEN + "Win -> Well done. The computer chose " + pcSign + " and failed!" + ANSI_RESET);
					scorePlayerInteger += 100;
				}
        		else {
					System.out.println(ANSI_RED + "Lose -> Sorry, but the computer choose " + pcSign + ANSI_RESET);
				}
				
			}
        	else if (userSign.equals("!rating")) {
				System.out.println(userNameString + ", you have " + scorePlayerInteger + " points!");
			}
        	else if (userSign.equals("!exit")) {
        		raitingSaver.rating.put(userNameString, scorePlayerInteger);
                raitingSaver.saveRatingToFile();
                System.out.println(ANSI_RED + "Goodbye!" + ANSI_RESET);
				System.exit(0);;
			}
        	else {
				System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET);
			}
		}
    }
}
