///////////////////////////////////////////////////////////////////////////////
// Main Class File:    PokemonGame.java
// Quarter:            CSE 8B Winter 2021
//
// Authors:             Paul Nguyen
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
* An interactive text game where users can capture Pokemon with Berries
* and Pokemon.
*
* @author Paul Nguyen
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class PokemonGame {

    /**
    * Defined variables to use in the game
    * Do not modify any of these.
    *
    */
    private static String[] pokeballNames = {"pokeball", "greatball",
    "ultraball"};
    private static int[] pokeballPerformance = {0, 10, 30};

    private static String[] berryNames = {"Razz Berry", "Nanab Berry"};
    private static final int RAZZ_BERRY_PATIENCE_INC = 10;
    private static final int RAZZ_BERRY_SPEED_DEC = 0;
    private static final int NANAB_BERRY_PATIENCE_INC = 0;
    private static final int NANAB_BERRY_SPEED_DEC = 10;

    private static String[] pokemonNames = {"Pikachu", "Bulbasaur",
    "Charmander", "Squirtle", "Mew"};
    private static String[] pokemonSounds = {"pikapika", "bulb", "char",
    "squir", "mew"};
    private static String[] pokemonTypes = {"electric", "grass", "fire",
    "water", "psychic"};
    private static int[] pokemonPatienceStats = {60, 50, 50, 50, 15};
    private static int[] pokemonSpeedStats = {25, 5, 20, 10, 50};

    //the amount of patience the WildPokemon decreases on escape from a pokeball
    private static final int PATIENCE_DEC_EACH_ESCAPE = 5;
    private static final String PROMPT_MSG_START =
    "You will encounter different pokemons.\n" +
    "You can throw different poke balls to catch them.\n" +
    "You can use Razz Berry to increase the catch rate.\n"+
    "You can use Nanab Berry to make the ball hit more easily.\n" +
    "Up to one berry per throw. Use the berry before your ball throw.\n";

    private static final String PROMPT_MSG_SECTION_BREAK =
        "//////////////////////////////////////////////////////////////////\n" +
        "//////////////////////////////////////////////////////////////////\n";
    private static final String PROMPT_MSG_RUN =
        "Would you like to run from wild pokemon (yes, no)";
    private static final String PROMPT_MSG_RUN_WRONG_INPUT =
        "Input not recognized. " +
        "Would you like to run from wild pokemon (yes, no)";
    private static final String PROMPT_MSG_BERRY =
        "Which berry do you want to use? " +
        "Type skip if you are confident. " +
        "(Razz Berry, Nanab Berry, skip)";
    private static final String PROMPT_MSG_BERRY_WRONG_INPUT =
        "Your berry input was not recognized. " +
        "Choose one from the following four options: " +
        "Razz Berry, Nanab Berry, skip";
    private static final String PROMPT_MSG_POKEBALL =
        "Which pokeball do you want to use? (pokeball, greatball, ultraball)";
    private static final String PROMPT_MSG_POKEBALL_WRONG_INPUT =
        "Your pokeball input was not recognized. " +
        "Choose one from the following four options: " +
        "pokeball, greatball, ultraball";
    private static final String PROMPT_MSG_END_JOURNEY =
        "Would you like to end your journey (yes, no)";
    private static final String PROMPT_MSG_END_JOURNEY_WRONG_INPUT =
        "Input not recognized. " +
        "Would you like to end your journey (yes, no)";
    private static final String PROMPT_MSG_CHECK_CAUGHT_POKEMONS =
        "Now let's check who're your pals now!\n";
    private static final String PROMPT_MSG_JOURNEY_SUMMARY =
        "//////////////////////////////////////////////////////////////////\n" +
        "//************************JOURNEY_SUMMARY***********************//\n" +
        "//////////////////////////////////////////////////////////////////\n";


    /**
    * These are the number of Berries and Pokeballs the user starts with
    * You can change them if you want
    *
    */
    private static final int START_RAZZ_BERRY_COUNT = 2;
    private static final int START_NANAB_BERRY_COUNT = 1;
    private static final int START_POKEBALL_COUNT = 5;
    private static final int START_GREATBALL_COUNT = 3;
    private static final int START_ULTRABALL_COUNT = 1;

    private static Backpack myBackpack;
    private static Pokedex myPokedex;

    /**
    * get a Berry from the user's backpack
    *
    * @param myScanner: reference to where user input will come from
    * @return : a Berry object that the user will use later on
    *
    * side effects: removes a berry from the backpack, if a user uses one.
    * If so, it should also decrement the appropriate berry count.
    *
    */
    public static Berry getUserBerry(Scanner myScanner){
        String input;
        Berry berryObject;

        System.out.println(PROMPT_MSG_BERRY);
        while(true){
            input = myScanner.nextLine();
            if (input.equals("skip")){
                berryObject = new Berry("", 0, 0);
                return berryObject;
            }
            else if (input.equals("Razz Berry") || input.equals("Nanab Berry")){
                berryObject = myBackpack.useBerry(input, 0);
                if (berryObject == null){
                    System.out.println("You don't have any " + input + "left!");
                    System.out.println(PROMPT_MSG_BERRY);
                    continue;
                }
                else{
                    return berryObject;
                }
            }
            else {
                System.out.println(PROMPT_MSG_BERRY_WRONG_INPUT);
            }

         }
    }

    /**
    * get a Pokeball from the user's backpack
    *
    * @param myScanner: reference to where user input will come from
    * @return : a Pokeball object that the user will use later on
    *
    * side effects: removes a Pokeball from the backpack.
    * Decrement the appropriate pokeball count.
    *
    */
    public static Pokeball getUserPokeball(Scanner myScanner){
        String input;
        Pokeball pokeBallObject;

        System.out.println(PROMPT_MSG_POKEBALL);
        while(true){
            input = myScanner.nextLine();
            if (input.equals("pokeball") || input.equals("greatball")
                     || input.equals("ultraball")){
                pokeBallObject = myBackpack.useBall(input);
                if (pokeBallObject == null){
                    System.out.println("You don't have any " + input + "left!");
                    System.out.println(PROMPT_MSG_POKEBALL);
                    continue;
                }
                else{
                    return pokeBallObject;
                }
            }
            else {
                System.out.println(PROMPT_MSG_POKEBALL_WRONG_INPUT);
            }

         }
    }

    /**
    * checks if user would like to run from pokemon
    *
    * @param myScanner: reference to where user input will come from
    * @return : boolean for yes or no
    *
    */
    public static boolean runFromPokemon(Scanner myScanner){
        String input;

        System.out.println(PROMPT_MSG_RUN);
        while(true){
            input = myScanner.nextLine();
            if (input.equals("yes")){
                return true;
            }
            else if (input.equals("no")){
                return false;
            }
            else {
                System.out.println(PROMPT_MSG_RUN_WRONG_INPUT);
            }
        }
    }

    /**
    * checks if user would like to end journey
    *
    * @param myScanner: reference to where user input will come from
    * @return : boolean for yes or no
    *
    */
    public static boolean endYourJourney(Scanner myScanner){
        String input;

        System.out.println(PROMPT_MSG_END_JOURNEY);
        while(true){
            input = myScanner.nextLine();
            if (input.equals("yes")){
                return true;
            }
            else if (input.equals("no")){
                return false;
            }
            else {
                System.out.println(PROMPT_MSG_END_JOURNEY_WRONG_INPUT);
            }
        }
    }


    public static void startYourJourney() { // TODO: end early
        ArrayList <PalPokemon> myPalPokemons = new ArrayList<PalPokemon>();

        myBackpack = new Backpack();
        myPokedex = new Pokedex();


        // the user starts out with some berries and pokeballs of each type
        // a for loop to add berries to the backpack object
        // add START_BERRY_COUNT berries of each type
        int patienceInc = -1;
        int speedDec = -1;
        for (int i = 0; i < START_RAZZ_BERRY_COUNT; i++){
            patienceInc = RAZZ_BERRY_PATIENCE_INC;
            speedDec = RAZZ_BERRY_SPEED_DEC;
            myBackpack.add(new Berry(berryNames[0], RAZZ_BERRY_PATIENCE_INC,
                                     RAZZ_BERRY_SPEED_DEC));
        }

        for (int i = 0; i < START_NANAB_BERRY_COUNT; i++){
            patienceInc = NANAB_BERRY_PATIENCE_INC;
            speedDec = NANAB_BERRY_SPEED_DEC;
            myBackpack.add(new Berry(berryNames[1], NANAB_BERRY_PATIENCE_INC,
                                    NANAB_BERRY_SPEED_DEC));
        }

        //  a for loop to add pokeballs to the backpack object
        // add START_POKEBALL_COUNT Pokeballs of each type
        for (int i = 0; i < START_POKEBALL_COUNT; i++){
            myBackpack.add(new Pokeball(pokeballNames[0],
                                        pokeballPerformance[0]));
        }
        for (int i = 0; i < START_GREATBALL_COUNT; i++){
            myBackpack.add(new Pokeball(pokeballNames[1],
                                        pokeballPerformance[1]));
        }
        for (int i = 0; i < START_ULTRABALL_COUNT; i++){
            myBackpack.add(new Pokeball(pokeballNames[2],
                                        pokeballPerformance[2]));
        }


        System.out.println("A new adventurer wakes up to start their journey!");
        System.out.println("Their backpack contains the following...");
        myBackpack.display();

        // Start the user-machine interaction below
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(PROMPT_MSG_START); // Prompt user
        while (true) {

            // gets a random wild pokemon
            System.out.println(PROMPT_MSG_SECTION_BREAK);
            Random rand = new Random();
            int randPokemon = rand.nextInt(pokemonNames.length);
            WildPokemon wildPokemon = new WildPokemon(pokemonNames[randPokemon],
                pokemonSounds[randPokemon],
                pokemonTypes[randPokemon],
                pokemonPatienceStats[randPokemon],
                pokemonSpeedStats[randPokemon]);
            wildPokemon.appear();
            myPokedex.add(wildPokemon);

            while (true) {
                Berry userBerry = null;
                Pokeball userPokeball = null;

                // check if user would like to run from pokemon
                if (runFromPokemon(myScanner)){
                    break;
                }
                // call getUserBerry and store its result in userBerry
                userBerry = getUserBerry(myScanner);
                // call getUserPokeball and store its result in userPokeball
                userPokeball = getUserPokeball(myScanner);

                if (wildPokemon.isCaught(userBerry, userPokeball) ){
                    System.out.println(wildPokemon.getName() + " is caught!\n");
                    PalPokemon caughtPokemon = new PalPokemon(
                                                   wildPokemon.getName(),
                                                   wildPokemon.getSound(),
                                                   wildPokemon.getType(),
                                                   userPokeball.getName());
                    myPalPokemons.add(caughtPokemon);
                    myPokedex.add(caughtPokemon);
                    break;
                }
                // The WildPokemon escaped.
                System.out.println(wildPokemon.getName() + " escapes!");
                System.out.println(wildPokemon.getName() +
                "'s times escaped from ball: " +
                wildPokemon.getTimesEscapedFromBall()+"\n");

                // Decrease its patience
                int patienceDec =
                wildPokemon.getTimesEscapedFromBall()*PATIENCE_DEC_EACH_ESCAPE;
                wildPokemon.setPatience(pokemonPatienceStats[randPokemon] -
                                        patienceDec);
                wildPokemon.setSpeed(pokemonSpeedStats[randPokemon]);

                // Check if the pokemon gets away.  If so, stop trying to
                // catch it.
                if (wildPokemon.disappear()){
                    break;
                }
            }

            // promt user if they want to end journey
            if (endYourJourney(myScanner)){
                break;
            }
        }

        System.out.println(PROMPT_MSG_JOURNEY_SUMMARY);
        System.out.println(PROMPT_MSG_CHECK_CAUGHT_POKEMONS);
        for (PalPokemon pal: myPalPokemons ){
            pal.comesOutFromBall();
        }

        System.out.println(PROMPT_MSG_SECTION_BREAK);
        System.out.println("Your pokedex now says:\n");
        myPokedex.display();

        System.out.println(PROMPT_MSG_SECTION_BREAK);
        System.out.println("Items left in Backpack:\n");
        myBackpack.display();
    }

    /**
    * Test your methods useBerry and isCaught here
    * make tests to ensure that your Pokemon classes work correctly
    * 2 additional tests for isCaught and useBerry each
    *
    * @param: nothing
    * @return: true if all your tests pass, false otherwise
    *
    */
    public static boolean unitTests() {

        // creates objects for testing isCaught method
        WildPokemon pikachu = new WildPokemon("Pikachu", "pikapika", "electric",
                                              60, 25);
        WildPokemon mew = new WildPokemon("Mew", "mew", "psychic", 15, 50);
        Pokeball regularBallTest = new Pokeball("Regular Ball", 0);
        Pokeball greatBallTest = new Pokeball("Great Ball", 10);
        Berry razzBerryTest = new Berry("Razz Berry", 10, 0);
        Berry nanabBerryTest = new Berry("Nanab Berry", 0, 10);

        // two unit test for isCaught method
        System.out.print("Pikachu is caught: ");
        System.out.println(pikachu.isCaught(razzBerryTest, regularBallTest));
        System.out.print("Mew is caught: ");
        System.out.println(mew.isCaught(nanabBerryTest, greatBallTest));
        System.out.println("");



        Backpack testBackpack = new Backpack();
        testBackpack.display();

        Berry razzBerry = new Berry("Razz Berry", 10, 0);
        Berry nanabBerry = new Berry("Nanab Berry", 0, 10);
        Pokeball greatball = new Pokeball("greatball", 10);
        Pokeball ultraball = new Pokeball("ultraball", 30);

        System.out.println("\nAdding 2 pokeballs -------------------------\n");
        testBackpack.add(greatball);
        testBackpack.add(ultraball);


        System.out.println("\nAdding 2 berries ----------------------------\n");
        testBackpack.add(razzBerry);
        testBackpack.add(nanabBerry);
        testBackpack.display();

        System.out.println("\nRemoving some berries -----------------------\n");
        // use some berries in the backpack
        testBackpack.useBerry("Razz Berry", 0);
        testBackpack.useBerry("Nanab Berry", 0);
        testBackpack.display();

        System.out.println("\nRemoving some pokeballs ---------------------\n");
        // use some pokeballs in the backpack
        testBackpack.useBall("greatball");
        testBackpack.useBall("ultraball");
        testBackpack.display();


        // two additional test for useBerry method
        testBackpack.display();

        Berry oranBerry = new Berry("Oran Berry", 0, 0);
        Berry pechaBerry = new Berry("Pecha Berry", 20, 20);


        System.out.println("\nAdding 2 berries ----------------------------\n");
        testBackpack.add(oranBerry);
        testBackpack.add(pechaBerry);
        testBackpack.display();

        System.out.println("\nRemoving some berries -----------------------\n");
        // use some berries in the backpack
        testBackpack.useBerry("Oran Berry", 0);
        testBackpack.useBerry("Pecha Berry", 0);
        testBackpack.display();

        return true;
    }


    public static void main(String[] args) {
        // Perform unitTests first
        /*if(unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("Failed test.\n");
            return;
        }*/

        startYourJourney();
    }
}
