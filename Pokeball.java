///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               Pokeball.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * this class is a derived class that extends the items class
 *
 * @author Paul Nguyen
 */
public class Pokeball extends Item {
    private int performance;


    /**
     * creates new pokeball with default values
     *
     */
    public Pokeball(){
        this.performance = 0;
    }

    /**
     * creates new pokeball with inputed values
     *
     */
    public Pokeball(String pokeballName, int performanceIn){
        super(pokeballName);
        this.performance = performanceIn;
    }

    /**
     * gets the performance of pokeball
     *
     * @return the performance of pokeball
     */
    public int getPerformance(){
        return performance;
    }

    /**
     * returns string representation of object
     *
     * @return string representation of object
     */
    @Override
    public String toString(){
        return super.getName() + "\nperformance: " + this.performance + "\n";
    }
}
