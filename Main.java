import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	// Method to display the menu options and prompt user
	public static void displayMenu() {
		System.out.println("Please select an option:");
		System.out.println("1. Purchase item from the vending machine");
		System.out.println("2. Add coins");
		System.out.println("3. Get coins & leave");
	}

	// Main method where the Vending Machine program starts
	public static void main(String[] args) throws InputMismatchException {
		Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input
		VendingMachine machine = new VendingMachine("file.txt"); // Create a VendingMachine object and initialize it with data from a file
		System.out.println("--------------------------------");
		System.out.println("Welcome to our Vending Machine!");
		System.out.println("--------------------------------");

		int choice = 1; // Initialize the user's choice to 1 (a default value to enter the loop)
		double coins; // Variable to store the user's input coins
		int itemID; // Variable to store the user's selected item ID
		while (choice != 3) {  // Continue looping until the user selects option 3 to exit the program
	
			System.out.printf("Number of coins: $%.2f\n", machine.getCoins());
			System.out.println("--------------------------------");
			displayMenu(); // Call the method to display the menu options

			try {
				choice = scanner.nextInt(); // Read user input for menu choice

				switch (choice) { // Process the user's choice
				case 1: // option 1 (Purchase item from the vending machine)
					machine.displayMenu(); // Show the available items in the vending machine
					itemID = scanner.nextInt(); // Read the user's selected item ID
					machine.buyItem(itemID); // Attempt to buy the selected item
					break;

				case 2: // option 2 (Add coins)
					System.out.print("Please enter a number of coins in decimal form: ");
					coins = scanner.nextDouble(); // Read the user's input coins
					machine.addCoins(coins); // Add the user's coins to the vending machine
					break;
				case 3: // option 3 (Exits program)
					System.out.println("Exiting the Vending Machine application. Goodbye!");
					System.out.println("Here's your change: $" + machine.getChange()); // Returns the user's change
					break;
				default:
					// if no other case matches statements
					System.out.println("Invalid choice. Please select a valid option.");
					break;
				}
			} catch (InputMismatchException e) {
				scanner.next(); // Consume the invalid input
				System.out.println("Invalid input! You must enter an integer.");

			}
			
		}
    	// Close the scanner before exiting the program
		scanner.close();

	}

}