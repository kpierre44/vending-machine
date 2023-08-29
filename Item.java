public class Item {
    	private int id; // Item line/identifier
			private String name; // Item name
			private double price; // Item price
			private int quantity;
			public Item(int id, String name, double price, int quantity) throws ItemQuantityException{
				// Initializers
				this.id = id; 
				this.name = name; 
				this.price = price; 
				setQuantity(quantity);
			}
			// getters and setters
			public void setId(int id){
				this.id = id;
			}
			public int getId(){
				return id;
			}
			
			public void setName(String name){
				this.name = name;
			}
			public String getName(){
				return name;
			}

			public void setPrice(double price){
				this.price = price;
			}
			public double getPrice(){
				return price;
			}
			// Method to set quantity of item
			public void setQuantity(int quantity) throws ItemQuantityException{
				if(quantity >= 0){
					this.quantity = quantity;
				}else{
					throw new ItemQuantityException("The item's quantity cannot be a negative number");
				}
			}
			public int getQuantity(){
				return quantity;
			}
			// Method to decrease the quantity of the item when it is sold or removed from the inventory
			public void decreaseQuantity() throws ItemQuantityException{
				if(quantity == 0){ // Checks if the quantity is already zero
					throw new ItemQuantityException("This items is not available.");
				}
				else{
					quantity--;
				}
			}

			public String toString(){ // Overridden to provide a formatted string representation of the "Item" object
				return getId() + " " + getName() + " " + getPrice() + " " + getQuantity();		
			}
}
// Custom exception class that inherits from java Exception
class ItemQuantityException extends Exception{
	public ItemQuantityException(String message){
		super(message);
	}
}