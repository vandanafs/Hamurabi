package hammurabi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Hammurabi().playGame();

    }

    void playGame() {   //each year upto 10 years
        int population = 100;
        int grainBushels = 2800;
        int acresLand = 1000;
        int acresToBuy = 0;
        int acresToSell = 0;
        int feedThePeople = 0;
        int acresToPlant = 0;
        int plagueDeaths = 0;
        int starvationDeaths = 0;
        boolean uprising;
        int immigrants = 0;
        int harvest = 0;
        int harvestPercent = 0;
        int ratGrain = 0;
        int landValue = 19;

        boolean gameGoesOn = true;
        int yearNumber = 0;
        System.out.println(welcomeMessage());
        while (gameGoesOn) {
            yearNumber++;


            // Getting user inputs and adjusting inventories of land and grain
            acresToBuy = askHowManyAcresToBuy(landValue, grainBushels);
            acresLand += acresToBuy;
            grainBushels -= acresToBuy * landValue;

            // If not buying land, ask if selling and make adjustments to land and grain
            if (acresToBuy == 0) {
                acresToSell = askHowManyAcresToSell(acresLand);
                acresLand -= acresToSell;
                grainBushels += landValue * acresToSell;
            }

          /*  // Determine people food allocation and amount of land to plant
            feedThePeople = askHowMuchGrainToFeedPeople(grainBushels);
            acresToPlant = askHowManyAcresToPlant(population, acresLand, grainBushels);  */

            // Calculating the harvest and harvestPercent
            harvest = harvest(acresToPlant);
            if (acresToPlant > 0) {
                harvestPercent = harvest / acresToPlant;
            }

            // Check on rats, plagues, and starvation
            ratGrain = grainEatenByRats(grainBushels);
            plagueDeaths = plagueDeaths(population);
            starvationDeaths = starvationDeaths(population, feedThePeople);

            // Check for uprising
            uprising = uprising(population, starvationDeaths);

            // Calculate immigration
            immigrants = immigrants(population, acresLand, grainBushels);

            // Making adustments to population and grain levels for harvest, feeding, rats, plague and starvation
            grainBushels = grainBushels + harvest - feedThePeople - ratGrain;
            population = population - plagueDeaths - starvationDeaths + immigrants;

            // Recalculate land value at end of year
            landValue = newCostOfLand();

            // print out end of year report
            System.out.println(printSummary(yearNumber, starvationDeaths, immigrants, population, harvest, harvestPercent, ratGrain, grainBushels, acresLand, landValue));



            // End of 10 year term
            if (yearNumber == 10) {
                gameGoesOn = false;
                System.out.println(printFinalSummary(acresLand, grainBushels, population));
                // Or uprising
            } else if (uprising) {
                gameGoesOn = false;
                System.out.println("\n\nThere has been an uprising by the people!");
                System.out.println("More than 45% of the people starved!");
                System.out.println("GAME OVER!");
            }
        }
    }


    /**
     * welcomeMessage()
     */
    String welcomeMessage() {
        String welcomeMessage = "Congratulations, you are the newest ruler of ancient Samaria,\n"
                + "elected for a 10 year term of office. Your duties are to\n"
                + "dispense food, direct farming, and buy and sell land as needed\n"
                + "to support your people. Watch out for rat infestations and the\n"
                + "plague! Grain is the general currency, measured in bushels. \n"
                + "The following will help you in your decisions:\n\n"
                + "   * Each person needs at least 20 bushels of grain per year to survive\n"
                + "   * Each person can farm at most 10 acres of land\n"
                + "   * It takes 2 bushels of grain to farm an acre of land\n"
                + "   * The market price for land fluctuates yearly\n\n"
                + "Rule wisely and you will be showered with appreciation at the\n"
                + "end of your term. Rule poorly and you will be kicked out of office!\n"
                + "\n\n"
                + "To begin, your resources are aso follows:\n"
                + "\tPopulation = 100 people\n"
                + "\tGrain on hand = 2800 bushels\n"
                + "\tLand = 1000 acres\n"
                + "\tLand value = 19 bushels of grain per acre\n\n";
        return welcomeMessage;
    }


    /**
     * Prints the summary
     *
     * @return a  summary status
     */
    String printSummary(int yearNumber, int starvationDeaths, int immigrants, int population, int harvest,
                        int harvestPercent, int ratGrain, int grainBushels, int acresLand, int landValue) {
        String annualMessage = "O great Hammurabi!\n\n"
                + "You are in year " + yearNumber + " of your ten rule\n"
                + "In the previous year " + starvationDeaths + " people starved to death.\n"
                + "In the previous year " + immigrants + " people entered the kingdom.\n"
                + "The population is now " + population + ".\n"
                + "We harvested " + harvest + " bushels at " + harvestPercent + " bushels per acre.\n"
                + "Rats destroyed " + ratGrain + " bushels, leaving " + grainBushels + " bushels in storage.\n"
                + "The city owns " + acresLand + " acres of land.\n"
                + "Land is currently worth " + landValue + " bushels per acre.\n";

        return annualMessage;

    }

    /**
     * Prints the final Summary after game is run 10 years
     *
     * @return game results
     */
    String printFinalSummary(int acresLand, int grainBushels, int population) {
        String finalSummary = "O great Hammurabi!  Your game is over!\n\n"
                + "Here is the final report of your reign: \n\n"
                + "You ended up with a population of " + population + " people, a change of " + (population - 100) + " people.\n\n"
                + "You ended up with " + grainBushels + " bushels of grain, a change of " + (grainBushels - 2800) + " bushels.\n\n"
                + "You ended up with " + acresLand + " acres of land, a change of " + (acresLand - 1000) + " acres.\n\n"
                + "Thanks for playing!\n\n";
        return finalSummary;

    }


    public int askHowManyAcresToBuy(int price, int bushels) {return 0;}


    public int askHowManyAcresToSell(int acresOwned) {return 0;}


    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels){return 0;}


    public int askHowMuchGrainToFeedPeople ( int bushels){return 0;}


    public int askHowManyAcresToPlant ( int acresOwned, int population, int bushels){return 0;}


    public int plagueDeaths ( int population){
        int plagueDeaths = 0;
        int plagueIndex = rand.nextInt(101);
           if (plagueIndex > 85) {
                plagueDeaths = (int) (0.5 * population);
            }
            return plagueDeaths;
        }


    public boolean uprising ( int population, int howManyPeopleStarved) {
           if( howManyPeopleStarved > (population * .45)){
            System.out.println("You failed, and were outed from office...");
            return true;
        }
           return false;
    }


    int starvationDeaths ( int population, int bushelsFedToPeople){
        int starvationDeaths = 0;
        int peopleFed = bushelsFedToPeople / 20;
            if (population > bushelsFedToPeople / 20) {
                starvationDeaths = population - bushelsFedToPeople / 20;
            }
            return starvationDeaths;
        }


    public int immigrants(int population, int acresOwned, int grainInStorage){
            int immigrants = (20 * acresOwned + grainInStorage) / (100 * population) + 1;
            return immigrants;
        }



    public int harvest(int acres){
        int harvest = 0;
        int harvestPercent = rand.nextInt(7) + 1;
        int bushelsUsedAsSeed = 0;
        harvest = acres * harvestPercent - bushelsUsedAsSeed;
        return harvest;
        }


    public int grainEatenByRats(int bushels){
            int grainEaten = 0;
            int ratIndex = rand.nextInt(101);
            if (ratIndex > 60) {
                grainEaten = (rand.nextInt(10, 30) * bushels) / 100;
            }

            return grainEaten;
        }


    public int newCostOfLand(){
            return rand.nextInt(17, 24);}



        /**
         * Prints the given message (which should ask the user for some integral
         * quantity), and returns the number entered by the user. If the user's
         * response isn't an integer, the question is repeated until the user
         * does give a integer response.
         *
         * @param message The request to present to the user.
         * @return The user's numeric response.
         */
        int getNumber(String message){
            while (true) {
                System.out.print(message);
                try {
                    return scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\"" + scanner.next() + "\" isn't a number!");
                }
            }
        }
    }
}