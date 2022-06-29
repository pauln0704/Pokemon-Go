///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               Backpack.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;

/**
 * This is Backpack class that you can add items which are berries and
 * pokeballs. You can display your backpack with display() method
 *
 * @author Paul Nguyen
 */

public class Backpack {

    // ArrayList for items (berreis, pokeballs)
    private ArrayList<Item> myItems;

    /**
     * Creates a resizable-array ArrayLists of Items
     *
     */
    public Backpack() {
        this.myItems = new ArrayList<Item>();
    }

    /**
     * Adds the item to myItems
     *
     */
    public void add(Item item) {
        this.myItems.add(item);
    }

    /**
     * Displays the elements in the myItems ArrayList
     *
     */
    public void display() {
        int i;

        System.out.println("Items in the backpack\n");
        if (myItems.isEmpty()){
            System.out.println("none\n");
        }
        else{
            for (i = 0; i < myItems.size(); i++){
                System.out.println(myItems.get(i).toString());
            }
        }
    }

    public Pokeball useBall(String ballName) {
        for (int i = 0; i < myItems.size(); i++) {
            if (myItems.get(i).getName().equals(ballName)){
                Pokeball usedPokeball = (Pokeball)myItems.get(i);
                myItems.remove(i);
                return usedPokeball;
            }
        }
        System.out.print("Pokeball " + ballName);
        System.out.println(" does not exist in the backpack!");
        return null;
    }

    public Berry useBerry(String berryName, int index){
        if (index >= myItems.size()){
            return null;
        }
        else if (myItems.get(index).getName().equals(berryName)){
            Berry usedBerry = (Berry)myItems.get(index);
            myItems.remove(index);
            return usedBerry;
        }
        else {
            return useBerry(berryName, index + 1);
        }
    }
}
