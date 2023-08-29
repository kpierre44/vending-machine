import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

public class VendingMachine {

	private ArrayList<Item> items; // List to store available items in the vending machine
	private double coins; // Total coins inserted in the vending machine

	// Constructor - Initializes the vending machine and reads item data from a file
	public VendingMachine(String fileName) {
		items = new ArrayList<Item>(); // Initialize the item list
		readItemsData(fileName); // Read item data from the file and add items to the list
		coins = 0; // Initialize the coins to 0
	}

	// Private method to read item data from a file and populate the item list
	private void readItemsData(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName)); // Create a BufferedReader to read the file
			String line = reader.readLine(); // Read the first line of the file
			String[] values; // Array to store the values from each line
			int id; // Item ID
			String name; // Item name
			double price; // Item price
			int quantity; // Item quantity
			Item item; // Temporary variable to store the current item being processed
			while (line != null) { // Loop through each line of the file until there is no more data
				try {
					values = line.split(","); // Split the line by comma to extract values
					id = Integer.parseInt(values[0]); // Parse the item ID
					name = values[1]; // Get the item name
					price = Double.parseDouble(values[2]); // Parse the item price
					quantity = Integer.parseInt(values[3]); // Parse the item quantity
					item = new Item(id, name, price, quantity); // Create a new item object
					items.add(item); // Add the item to the list of items
					line = reader.readLine();
				} catch (Exception e) { // If there is an error parsing the line, skip it and move to the next line
					line = reader.readLine();
				}
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayMenu() {
		
		System.out.println("Please choose from the following: "); // Prompt the user for selection
		for (int i = 0; i < items.size(); i++) { // Loop through each item in the item list
			if(items.get(i).getQuantity() > 0) { // Check if the item's quantity is greater than 0 (the item is available)
				System.out.println("\t" + items.get(i)); // Print item details
			}	
		}
	}

	// Method to buy an item from the vending machine
	public void buyItem(int itemID) {
		int index = -1; // Initialize the index variable to -1 (representing that the item ID was not found in the list)
		int i = 0; // Initialize the loop variable
		while(i < items.size()) {
			if(items.get(i).getId() == itemID) { // Check if the current item's ID matches the provided itemID
				index = i; // If found, set the index variable to the index of the matching item in the list
			}
			i++;
		}
		if(index == -1) { // If the index variable is -1, it means the item ID was not found in the list
			System.out.println("This item ID doesn't exist. Please select an available product.");
		}else { // If the item ID was found in the list
			try {
				items.get(index).decreaseQuantity(); // Decrease the quantity of the selected item by 1
				if(coins >=  items.get(index).getPrice()) { // Check if the user has enough coins to buy the item
					coins = coins - items.get(index).getPrice(); // Deduct the price of the item from the available coins
					System.out.println("Enjoy your " + items.get(index).getName()); 
				}
				else {
					System.out.println("Insufficient funds. Please enter more coins.");
				}	
			} catch (ItemQuantityException e) { // Catch the custom exception if the item is not available (quantity is 0)
				System.out.println("This item isn't available.");
			}
		}
	}
	
	// Method to add coins to the vending machine
	public void addCoins(double coins)
	{
		this.coins += coins; // Add the inputed coins to the current coins in the vending machine
	}
	// Method to get the change and reset the current coins to 0
	public double getChange() {
		double temp = coins; // Store the current coins in a temporary variable
		coins = 0; // Set current coins to 0
		return temp; // Return the stored coins as the change to be given to the user
	}
	// Method to get the total coins in the vending machine
	public double getCoins() {
		return coins;
	}

}
