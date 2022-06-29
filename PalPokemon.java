///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               PalPokemon.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class is a derived class that extends the Pokemon class
 *
 * @author Paul Nguyen
 */
public class PalPokemon extends Pokemon {
    private String pokeballName;

    /**
     * creates new PalPokemon with default pokemon name and undefined pokeBall
     *
     */
    public PalPokemon(){
        super();
        this.pokeballName = "undefined";
    }
    /**
     * creates new PalPokemon with given name, sound, type, and pokeball
     *
     */
    public PalPokemon (String pokemonName, String pokemonSound,
                       String pokemonType, String pokeballName){
        super(pokemonName, pokemonSound, pokemonType);
        this.pokeballName = pokeballName;
    }

    /**
     * gets the pokeball name
     *
     * @return pokeballName the pokeball name
     */
    public String getPokeballName(){
        return pokeballName;
    }

    /**
     * prints the message for pokemon comeing out of ball
     *
     */
    public void comesOutFromBall(){
        System.out.println(super.getName() + " in " + this.pokeballName + ", "
                           + super.getType() + "type pokemon.");
        super.speak();
    }

    /**
     * returns string representation of object
     *
     * @return string representation of object
     */
    @Override
    public String toString(){
        return super.getName() + ", PalPokemon\n"
               + "pokeballName: " + this.pokeballName + "\n"
               + "type: " + this.getType() + "\n";
    }
}
