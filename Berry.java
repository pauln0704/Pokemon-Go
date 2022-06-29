///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// File:               Berry.java
//
// Author:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * this class is a derived class that extends the items class
 *
 * @author Paul Nguyen
 */
public class Berry extends Item {
    private int patienceIncrement;
    private int speedDecrement;

    /**
     * creates a new berry item with default values
     *
     */
    public Berry(){
        super();
        patienceIncrement = 0;
        speedDecrement = 0;
    }

    /**
     * creates new berry with inputed values
     *
     */
    public Berry(String berryName, int patienceInc, int speedDec){
        super(berryName);
        this.patienceIncrement = patienceInc;
        this.speedDecrement = speedDec;
    }


    /**
     * gets the patience increment for berry
     *
     * @return the patience increment for berry
     */
    public int getPatienceIncrement(){
        return this.patienceIncrement;
    }

    /**
     * gets the speed decrement for berry
     *
     * @return the speed decrement for berry
     */
    public int getSpeedDecrement(){
        return this.speedDecrement;
    }

    /**
     * returns string representation of object
     *
     * @return string representation of object
     */
    @Override
    public String toString(){
        return super.getName()
               + "\npatienceIncrement: " + this.patienceIncrement
               + "\nspeedDecrement: " + this.speedDecrement + "\n";
    }
}
