package CoffeeMachine;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class CoffeeMachine {
    LinkedHashMap<String, Integer> standardEquipment = new LinkedHashMap<>();
    LinkedHashMap<String, Object> espresso = new LinkedHashMap<>();
    LinkedHashMap<String, Object> latte = new LinkedHashMap<>();
    LinkedHashMap<String, Object> cappuccino = new LinkedHashMap<>();
    
    private String currentStatus;

    private CoffeeMachine() {
        standardEquipment.put("water", 400);
        standardEquipment.put("milk", 540);
        standardEquipment.put("coffeebeans", 120);
        standardEquipment.put("disposablecups", 9);
        standardEquipment.put("money", 550);

        espresso.put("name", "espresso");
        espresso.put("water", 250);
        espresso.put("milk", 0);
        espresso.put("coffeebeans", 16);
        espresso.put("cost", 4);

        latte.put("name", "latte");
        latte.put("water", 350);
        latte.put("milk", 75);
        latte.put("coffeebeans", 20);
        latte.put("cost", 7);

        cappuccino.put("name", "cappuccino");
        cappuccino.put("water", 200);
        cappuccino.put("milk", 100);
        cappuccino.put("coffeebeans", 12);
        cappuccino.put("cost", 6);
        
        currentStatus = "Awake";
    }

    public void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println("Water - " + standardEquipment.get("water"));
        System.out.println("Milk - " + standardEquipment.get("milk"));
        System.out.println("Coffee Beans - " + standardEquipment.get("coffeebeans"));
        System.out.println("Disposable Cups - " + standardEquipment.get("disposablecups"));
        System.out.println("Money - " + standardEquipment.get("money"));
    }

    public void fill() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Write what you want to add (water, milk, coffeebeans, disposablecups) or 'back' to return to the main menu:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("back")) {
                    System.out.println("Returning to the main menu.");
                    cafeMenu();
                    break;
                } else if (standardEquipment.containsKey(input)) {
                    while (true) {
                        System.out.println("Write how many ml/grams of " + input + " do you want to add:");
                        if (scanner.hasNextInt()) {
                            int currentAmount = standardEquipment.get(input);
                            int amountToAdd = scanner.nextInt();
                            int newAmount = currentAmount + amountToAdd;
                            standardEquipment.put(input, newAmount);
                            System.out.println(standardEquipment);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void take() {
        System.out.println("I will give you - " + standardEquipment.get("money") + "$");
        standardEquipment.put("money", 0);
    }

    public void prepareCoffee(String coffeeName) {
        try (Scanner scanner = new Scanner(System.in)) {
            LinkedHashMap<String, Object> recipe = null;
            int water, milk, coffeeBeans, cost;
            switch (coffeeName) {
                case "espresso":
                    recipe = espresso;
                    break;
                case "latte":
                    recipe = latte;
                    break;
                case "cappuccino":
                    recipe = cappuccino;
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
                System.out.println("How many cups of coffee do you want?");
                if (scanner.hasNextInt()) {
                    int userCups = scanner.nextInt();
                    if (0 < userCups && userCups < 100) {
                        System.out.println("For " + userCups + " cup/s of coffee you will need:\n" + userCups * water + "ml of water");
                        System.out.println(userCups * milk + "ml of milk");
                        System.out.println(userCups * coffeeBeans + " coffee beans");
                        System.out.println(userCups * cost + "$");
                    }
                    if (standardEquipment.get("water") < userCups * water) {
                        System.out.println("Sorry, not enough water");
                    } else if (standardEquipment.get("milk") < userCups * milk) {
                        System.out.println("Sorry, not enough milk");
                    } else if (standardEquipment.get("coffeebeans") < userCups * coffeeBeans) {
                        System.out.println("Sorry, not enough coffee beans");
                    } else if (standardEquipment.get("disposablecups") < userCups) {
                        System.out.println("Sorry, not enough disposable cups");
                    } else {
                        System.out.println("Making " + coffeeName);
                        standardEquipment.put("water", standardEquipment.get("water") - userCups * water);
                        standardEquipment.put("milk", standardEquipment.get("milk") - userCups * milk);
                        standardEquipment.put("coffeebeans", standardEquipment.get("coffeebeans") - userCups * coffeeBeans);
                        standardEquipment.put("money", standardEquipment.get("money") + userCups * cost);
                        standardEquipment.put("disposablecups", standardEquipment.get("disposablecups") - userCups);
                        cafeMenu();
                    }
                } else {
                    System.out.println("error");
                    scanner.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buy() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back - to main menu");
            while (true) {
                String order = scanner.nextLine().toLowerCase();
                switch (order) {
                    case "espresso":
                    case "latte":
                    case "cappuccino":
                        prepareCoffee(order);
                        break;
                    case "back":
                        cafeMenu();
                        break;
                    default:
                        System.out.println("Error! Incorrect input.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void processUserInput(String userInput) {
        switch (userInput.toUpperCase()) {
            case "BUY":
            	currentStatus = "Buy";
            	System.out.println("Current status: " + currentStatus);
                buy();
                break;
            case "FILL":
            	currentStatus = "Fill";
            	System.out.println("Current status: " + currentStatus);
                fill();
                break;
            case "TAKE":
            	currentStatus = "Take";
            	System.out.println("Current status: " + currentStatus);
                take();
                break;
            case "REMAINING":
            	currentStatus = "Remaining";
            	System.out.println("Current status: " + currentStatus);
                remaining();
                break;
            case "EXIT":
                System.exit(0);
                break;
            default:
                System.out.println("Error! Incorrect input.");
                break;
        }
    }
    
    public void cafeMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("####|CoffeeMachine|####\n[BUY]\n[FILL]\n[TAKE]\n[REMAINING]\n[EXIT]");
                currentStatus = "Awake";
                System.out.println("Current status: " + currentStatus);
                String userChoiceString = scanner.nextLine().toUpperCase();
                processUserInput(userChoiceString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.cafeMenu();
    }
}
