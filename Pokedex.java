///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               Pokedex.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;

/**
 * This is Pokedex class that you can add pokemons which can be wild or pal
 * pokemons. You can display your pokedex with display() method
 *
 * @author Paul Nguyen
 */

public class Pokedex {

    // ArrayList for pokemons (WildPokemons, PalPokemons)
    ArrayList<Pokemon> myPokedex;

    /**
     * Creates a resizable-array ArrayLists of Pokemons
     *
     */
    public Pokedex() {
        this.myPokedex = new ArrayList<Pokemon>();
    }

    /**
     * Adds the pokemon to myPokedex
     *
     */
    public void add(Pokemon pokemon) {
        this.myPokedex.add(pokemon);
    }

    /**
     * Displays the elements in the myPokedex ArrayList
     *
     */
    public void display() {
        int i;

        System.out.println("Pokemons in the pokedex\n");
        if (myPokedex.isEmpty()){
            System.out.println("none\n");
        }
        else{
            for (i = 0; i < myPokedex.size(); i++){
                System.out.println(myPokedex.get(i).toString());
            }
        }
    }
}
