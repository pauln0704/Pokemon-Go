///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               Item.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is a super class. Berry.java and Pokeball.java will be
 * inherited from this Item class. To inherit from a class,
 * use the extends keyword.
 *
 *
 * @author Paul Nguyen
 */

public class Item{
	protected String name;

	/**
	 * Creates a new Item with default name "item"
	 *
	 */
	public Item () {
		this.name = "item";
	}

	/**
	 * Creates a new Item with the given itemName
	 *
	 * @param itemName the item name
	 */
	public Item(String itemName) {
		this.name = itemName;
	}

	/**
	 * Gets the name of the item.
	 *
	 * @return name of the item
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns a string representation of this object.
	 *
	 * @return string representation of this object.
	 */
	@Override
	public String toString() {
		String outputString;

		outputString = this.name + "\n";
		return outputString;
	}
}
