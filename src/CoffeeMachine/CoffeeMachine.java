package CoffeeMachine;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class CoffeeMachine {
    LinkedHashMap<String, Integer> standardEquipment = new LinkedHashMap<>(); // This LinkedHashMap stores the standard equipment and its quantities.
    LinkedHashMap<String, Object> espresso = new LinkedHashMap<>(); // This LinkedHashMap represents the properties of an espresso.
    LinkedHashMap<String, Object> latte = new LinkedHashMap<>(); // This LinkedHashMap represents the properties of a latte.
    LinkedHashMap<String, Object> cappuccino = new LinkedHashMap<>(); // This LinkedHashMap represents the properties of a cappuccino.

    // Constructor for CoffeeMachine class
    private CoffeeMachine() {
        // Initialize the standard equipment with default quantities.
        standardEquipment.put("water", 400);
        standardEquipment.put("milk", 540);
        standardEquipment.put("coffeebeans", 120);
        standardEquipment.put("disposablecups", 9);
        standardEquipment.put("money", 550);

        // Initialize properties for the espresso.
        espresso.put("name", "espresso");
        espresso.put("water", 250);
        espresso.put("milk", 0);
        espresso.put("coffeebeans", 16);
        espresso.put("cost", 4);

        // Initialize properties for the latte.
        latte.put("name", "latte");
        latte.put("water", 350);
        latte.put("milk", 75);
        latte.put("coffeebeans", 20);
        latte.put("cost", 7);

        // Initialize properties for the cappuccino.
        cappuccino.put("name", "cappuccino");
        cappuccino.put("water", 200);
        cappuccino.put("milk", 100);
        cappuccino.put("coffeebeans", 12);
        cappuccino.put("cost", 6);
    }
    // Main
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        cafeMenu(coffeeMachine);
    }
    // Remaining function
    public static void remaining(CoffeeMachine coffeeMachine) {
        System.out.println("The coffee machine has:");
        System.out.println("Water - " + coffeeMachine.standardEquipment.get("water"));
        System.out.println("Milk - " + coffeeMachine.standardEquipment.get("milk"));
        System.out.println("Coffee Beans - " + coffeeMachine.standardEquipment.get("coffeebeans"));
        System.out.println("Disposable Cups - " + coffeeMachine.standardEquipment.get("disposablecups"));
        System.out.println("Money - " + coffeeMachine.standardEquipment.get("money"));
    }
    // Fill function
	public static void fill(CoffeeMachine coffeeMachine) {
	    try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    System.out.println("Write what you want to add (water, milk, coffeebeans, disposablecups) or 'back' to return to the main menu:");
			    String input = scanner.nextLine();

			    if (input.equalsIgnoreCase("back")) {
			        System.out.println("Returning to the main menu.");
			        break;
			    } else if (coffeeMachine.standardEquipment.containsKey(input)) {
			        while (true) {
			            System.out.println("Write how many ml/grams of " + input + " do you want to add:");
			            if (scanner.hasNextInt()) {
			                int currentAmount = coffeeMachine.standardEquipment.get(input);
			                int amountToAdd = scanner.nextInt();
			                int newAmount = currentAmount + amountToAdd;
			                coffeeMachine.standardEquipment.put(input, newAmount);
			                System.out.println(coffeeMachine.standardEquipment);
			                scanner.nextLine();	
			                break;
			            } else {
			                System.out.println("Error! Please enter a valid number.");
			                scanner.nextLine();
			            }
			        }
			    } else {
			        System.out.println("Invalid input. Please enter a valid ingredient or 'back'.");
			    }
			}
		}
	    catch (Exception e) {
	    	e.printStackTrace();
		}
	}
	// Take function
	public static void take(CoffeeMachine coffeeMachine) {
		System.out.println("I will give you - " + coffeeMachine.standardEquipment.get("money") + "$");
		coffeeMachine.standardEquipment.put("money", 0);
	}
	// Coffee making function
	public static void prepareCoffee(CoffeeMachine coffeeMachine, String coffeeName) {
		try (Scanner scanner = new Scanner(System.in)) {
			LinkedHashMap<String, Object> recipe = null;
			int water, milk, coffeeBeans, cost;
			switch (coffeeName) {
			    case "espresso":
			        recipe = coffeeMachine.espresso;
			        break;
			    case "latte":
			        recipe = coffeeMachine.latte;
			        break;
			    case "cappuccino":
			        recipe = coffeeMachine.cappuccino;
			        break;
			    default:
			        System.out.println("Invalid coffee choice");
			        return;
			}
			water = (int) recipe.get("water");
			milk = (int) recipe.get("milk");
			coffeeBeans = (int) recipe.get("coffeebeans");
			cost = (int) recipe.get("cost");
			while (true) {
			System.out.println("How many cops of coffee do you want?");
			if (scanner.hasNextInt()) {
				int userCups = scanner.nextInt();
				if ( 0 < userCups && userCups < 100) {
					System.out.println("For " + userCups + " cup/s of coffee you will need:\n" + userCups * water + "ml of water");
					System.out.println(userCups * milk + "ml of milk");
					System.out.println(userCups * coffeeBeans + " coffee beans");
					System.out.println(userCups * cost + "$");
				}
			    if (coffeeMachine.standardEquipment.get("water") < userCups * water) {
			        System.out.println("Sorry, not enough water");
			    } else if (coffeeMachine.standardEquipment.get("milk") < userCups * milk) {
			        System.out.println("Sorry, not enough milk");
			    } else if (coffeeMachine.standardEquipment.get("coffeebeans") < userCups * coffeeBeans) {
			        System.out.println("Sorry, not enough coffee beans");
			    } else if (coffeeMachine.standardEquipment.get("disposablecups") < userCups) {
			        System.out.println("Sorry, not enough disposable cups");
			    } else {
			        System.out.println("Making " + coffeeName);
			        coffeeMachine.standardEquipment.put("water", coffeeMachine.standardEquipment.get("water") - userCups * water);
			        coffeeMachine.standardEquipment.put("milk", coffeeMachine.standardEquipment.get("milk") - userCups * milk);
			        coffeeMachine.standardEquipment.put("coffeebeans", coffeeMachine.standardEquipment.get("coffeebeans") - userCups * coffeeBeans);
			        coffeeMachine.standardEquipment.put("money", coffeeMachine.standardEquipment.get("money") + userCups * cost);
			        coffeeMachine.standardEquipment.put("disposablecups", coffeeMachine.standardEquipment.get("disposablecups") - userCups);
			        cafeMenu(coffeeMachine);
			    }
			}
			 else {
					System.out.println("error");
					scanner.next();
				}
			}
		}
		catch (Exception e) {
	    	e.printStackTrace();
		}
	}
	// Buy function
	public static void buy(CoffeeMachine coffeeMachine) {
	    try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back - to main menu");
			while (true) {
			String order = scanner.nextLine().toLowerCase();
			switch (order) {
			    case "espresso":
			    case "latte":
			    case "cappuccino":
			        prepareCoffee(coffeeMachine, order);
			        break;
			    case "back":
			        cafeMenu(coffeeMachine);
			        break;
			    default:
			        System.out.println("Error! Incorrect input.");
			        break;
			}
			}
		}
	    catch (Exception e) {
	    	e.printStackTrace();
		}
	}
	// Main menu function
	public static void cafeMenu(CoffeeMachine coffeeMachine) {
	    try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			    System.out.println("####|CoffeeMachine|####\n[BUY]\n[FILL]\n[TAKE]\n[REMAINING]\n[EXIT]");
			    String userChoiceString = scanner.nextLine().toUpperCase();
			    switch (userChoiceString) {
			        case "BUY":
			            buy(coffeeMachine);
			            break;
			        case "FILL":
			            fill(coffeeMachine);
			            break;
			        case "TAKE":
			            take(coffeeMachine);
			            break;
			        case "REMAINING":
			            remaining(coffeeMachine);
			            break;
			        case "EXIT":
			            System.exit(0);
			            break;
			        default:
			            System.out.println("Error! Incorrect input.");
			            break;
			    }
			}
		}
	    catch (Exception e) {
	    	e.printStackTrace();
		}
	}

}
