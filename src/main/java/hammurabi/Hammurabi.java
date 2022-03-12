package hammurabi;

import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand;
    Scanner scanner=new Scanner(System.in);

    public static void main ( String[] args ) {
        new Hammurabi().playGame();

    }

    private void playGame () {   //each year upto 10 years
        int population;
        int grain;
        int acres;
        //int value;

    }
    /**
     * A method to ask how many acres to purchase
     *
     * @param price the current value of land per acre
     * @param bushels the bushels available for purchasing the land
     *
     * @return a verified purchase number for acres to be purchased
     */

    public int askHowManyAcresToBuy(int price,int bushels){return 0 ;}

    /**
     * A method to ask how many acres to sell
     *
     * @param acresOwned the acres available for sale
     *
     * @return a verified purchase number for acres to be purchased
     */

    public int askHowManyAcresToSell(int acresOwned){
        return 0;
    }

    public  int askHowManyAcresToPlant(int acresOwned,int population,int bushels){

        return 0;
    }
    /**
     * A method for determining how much grain to feed the people
     *
     * @param bushels the bushels of grain available at the present time
     *
     * @return the number of verified bushels for the people to eat
     */

    public int askHowMuchGrainToFeedPeople(int bushels) {return 0;}

    /**
     * A method for determining the number of acres to be planted
     *
     * @param acresOwned the acres of land owned
     * @param population the number of people in the kingdom
     * @param bushels the bushels of grain on hand
     *
     * @return bushels to feed the people, verified available.
     */

    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {return 0;}

    /**
     * Method determines annual deaths due to plague
     *
     * @param population the number of people in the kingdom
     * @return the number of people dead from plague
     */

    public int plagueDeaths(int population){
        return 0;
    }
    /**
     * Determines whether or not there will be an uprising
     *
     * @param population number of people in the kingdom
     * @param howManyPeopleStarved number of people starved
     * @return true if more than 45% starved, false otherwise
     */

    public boolean uprising(int population,int howManyPeopleStarved){
        return false;
    }

    /**
     * Determines the number of people to immigrate into the kingdom
     *
     * @param population the starting number of people in the kingdom
     * @param acresOwned land owned by the kingdom
     * @param grainInStorage grain in bushels
     * @return the number of immigrants for the year
     */

    public int immigrants(int population, int acresOwned, int grainInStorage){
        return 0;
    }

    /**
     * Determines crop harvest
     *
     * @param acres indicating amount of land to be planted
     * @return the bushels of grain to be harvested
     */
    public int harvest(int acres, int bushelsUsedAsSeed){
        return 0;
    }

    /**
     * Determines amount of grain eaten by rats
     *
     * @param bushels amount of grain on hand
     * @return the bushels of grain eaten by rats
     */

    public  int grainEatenByRats(int bushels){
        return 0;
    }
    /**
     * Determines the cost of an acre of land in bushels of grain per acre
     *
     * @return the cost of land in bushels per acre
     */

    public  int newCostOfLand(){
        return 0;
    }


}
