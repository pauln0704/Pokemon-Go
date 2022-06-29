///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               WildPokemon.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Random;

/**
 * This class is a derived class that extends the Pokemon class
 *
 * @author Paul Nguyen
 */
public class WildPokemon extends Pokemon{
    private int patience;
    private int speed;
    private int timesEscapedFromBall;

    /**
     * creates new wild pokemon with default values
     *
     */
    public WildPokemon(){
        super();
        this.patience = 100;
        this.speed = 0;
        this.timesEscapedFromBall = 0;
    }

    /**
     * creates new wild pokemon with given values
     *
     */
    public WildPokemon(String pokemonName, String pokemonSound,
                       String pokemonType, int patienceIn, int speedIn){
        super(pokemonName, pokemonSound, pokemonType);
        this.patience = patienceIn;
        this.speed = speedIn;
        this.timesEscapedFromBall = 0;
    }


    /**
     * get patience of wild pokemon
     *
     * @return patience the patience of the wild pokemon
     */
    public int getPatience(){
        return patience;
    }

    /**
     * get the speed of wild pokemon
     *
     * @return speed the speed of the wild pokemon
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * gets the number of times the wild pokemon escaped from the ball
     *
     * @return timesEscapedFromBall the number of times the pokemon escaped ball
     */
    public int getTimesEscapedFromBall(){
        return timesEscapedFromBall;
    }

    /**
     * sets the patience of the wild pokemon
     *
     * @param newPatience the new Patience of wild pokemon
     */
    public void setPatience(int newPatience){
        this.patience = newPatience;
    }

    /**
     * sets the speed of the wild pokemon
     *
     * @param newSpeed the new speed of wild pokemon
     */
    public void setSpeed(int newSpeed){
        this.speed = newSpeed;
    }

    /**
     * increments the number of times the wild pokemon escaped from ball
     *
     */
    public void incrementTimeEscapedFromBall(){
        this.timesEscapedFromBall++;
    }

    /**
     * printst the message for a wild pokemon appearing
     *
     */
    public void appear(){
        System.out.println("You encountered a wild " + super.getName());
        super.speak();
    }

    /**
     * prints the message for the wild pokemon disappering
     *
     */
    public boolean disappear(){
        if (patience <= 0 || timesEscapedFromBall > 3){
            System.out.println(super.getName() + "disappears...");
            return true;
        }
        return false;
    }

    public boolean isCaught(Berry berry, Pokeball pokeball){
        int threshold;
        Random randGen = new Random();

        this.patience = this.patience + berry.getPatienceIncrement();
        this.speed = this.speed - berry.getSpeedDecrement();
        threshold = this.speed - this.patience + (randGen.nextInt(101) - 50);
        if (pokeball.getPerformance() >  threshold){
            return true;
        }
        else {
            this.timesEscapedFromBall++;
            return false;
        }
    }

    /**
     * returns string representation of object
     *
     * @return string representation of object
     */
    @Override
    public String toString(){
        return super.getName() + ", WildPokemon\n"
               + "type: " + super.getType() + "\n"
               + "patience: " + this.patience + "\n"
               + "speed: " + this.speed + "\n"
               + "timeEscapedFromBall: " + this.timesEscapedFromBall + "\n";
    }
}
