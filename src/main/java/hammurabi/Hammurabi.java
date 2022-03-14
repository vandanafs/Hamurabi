package hammurabi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {


    Random rand=new Random();
    Scanner scanner=new Scanner(System.in);


    public static void main ( String[] args ) {
        new Hammurabi().playGame();


    }

    private void playGame () {   //each year upto 10 years
        int population=95;
        int grain;
        int acresTotal=1000;
        int bushelsTotal=2800;
        int pricePerAcre=19;
        int harvest=0;
        int year=1;
        int plagueDeaths=0;
        int harvestPerAcre=3;
        int starvationDeaths=0;
        int    immigrants=5;
        int grainsEatenByRats=200;

        while(year<= 10) {
            // population=population+immigrants;
            population=(population+immigrants)-(starvationDeaths+plagueDeaths);
            System.out.println("O great Hammurabi!" + "\n" +
                    "You are in year " +year+ " of your ten year rule." + "\n" +
                    " In the previous year  "+plagueDeaths+" people starved to death." +starvationDeaths+ "\n" +
                    "In the previous year "+   immigrants+" people entered the kingdom." + "\n" +
                    "The population is now "+population+"." + "\n" +
                    " We harvested "+harvest+" bushels at "+ harvestPerAcre+" bushels per acre." + "\n" +
                    " Rats destroyed "+grainsEatenByRats+" bushels, leaving  "+bushelsTotal+"  bushels in storage." + "\n" +
                    " The city owns"+acresTotal+" acres of land." + "\n" +
                    " Land is currently worth "+pricePerAcre+" bushels per acre.");
            System.out.println("---------------");
            int newAcres = askHowManyAcresToBuy(pricePerAcre, bushelsTotal);
            int buyBushel = newAcres * pricePerAcre;  //10* 19=190
            bushelsTotal = bushelsTotal - buyBushel;  //total bushel updated
            acresTotal += newAcres;   //1000+10;
            if (newAcres == 0) {  //not bought any land call sell()
                int sell = askHowManyAcresToSell(acresTotal);
                acresTotal = acresTotal - sell;
                bushelsTotal = bushelsTotal + sell * pricePerAcre;
            }
            int feed = askHowMuchGrainToFeedPeople(bushelsTotal);
            bushelsTotal = bushelsTotal - feed;
            int acresPlanted = askHowManyAcresToPlant(acresTotal, population, bushelsTotal);
            System.out.println("aceres planted:" + acresPlanted);
            //    bushelsTotal=bushelsTotal-(acresPlanted*2);  //confirm this

            //If there is a plague, and how many people die from it.
            plagueDeaths=plagueDeaths(population);

            //  //How many people starved.
            starvationDeaths= starvationDeaths(population,feed);
            if(uprising(population,starvationDeaths))
            {
                System.out.println("Hey Hammurabi you killed more than 45% of the people.You are kicked out of the game!!!");
                break;
            }

            //How many people came to the city.
            immigrants=immigrants(population, acresTotal,bushelsTotal);

            //How good the harvest is.
            harvest = harvest(acresPlanted, acresPlanted * 2);
            harvestPerAcre=harvest/acresPlanted;
            bushelsTotal = bushelsTotal + harvest;

            //If you have a problem with rats, and how much grain they eat.
            grainsEatenByRats=grainEatenByRats(bushelsTotal);

            //How much land costs (for deciding what to do next).
            pricePerAcre=newCostOfLand();

            System.out.println("harvest:" + harvest);
            System.out.println("");
            System.out.println("Acres total :" + acresTotal);
            System.out.println("total Bushels:" + bushelsTotal);
            System.out.println("Total acres planted:" + acresPlanted);
            year++;
            System.out.println("End of the year "+year);
            System.out.println("-------------------");
        } //end of while loop

    }

    /**
     * Asks the player how many acres of land to buy, and returns that number.
     * @param price     ----> price per acre to buy
     * @param bushels  ---> bushelsTotal in storage
     * @return   ---> buy value
     */
    public int askHowManyAcresToBuy(int price,int bushels){  //(19, 190)
        int buy=0;
        buy=getNumber("O great Hammurabi, how many acres do you want to buy?");
        System.out.println("buy  -->"+buy);
        if(buy*price <bushels){  //10*19=190<2800 this comes before feeding
            return buy;
        }
        if(buy*price >bushels){
            System.out.println("Hey Hammurabi you don't have enough bushels");
            buy=0;
            return buy;
        }

        return buy;
    }

    /**
     ** Asks the player how many acres of land to sell, and returns that number
     * @param acresOwned   ----> total AcreOwned
     * @return   ----> sell value
     */
    public int askHowManyAcresToSell(int acresOwned) {  ///1000, 5 acres
        int sell=0;
        sell=getNumber("O great Hammurabi, how many acres shall you sell?");
        System.out.println("sell  -->"+sell);

        if (acresOwned >= sell) {
            return sell; //acresOwned - sell;

        } else {
            System.out.println("You can't sell more than you have");
            return 0;
        }
    }

    /**
     * Ask the player how much grain to feed people, and returns that number.
     * @param bushels   ---> bushelsTotal
     * @return       ---->returns that bushelsTotal number
     */
    public  int askHowMuchGrainToFeedPeople(int bushels){  //2800
        int feed= getNumber("O great Hammurabi, how much grains you want feed?");
        return feed;
    }

    /**
     * Ask the player how many acres to plant with grain, and returns that number.
     * @param acresOwned----> totalAcre owned
     * @param population  ---->
     * @param bushels  --->
     * @return
     */
    public  int askHowManyAcresToPlant(int acresOwned,int population,int bushels){
        // 1000, 100,(2800-2000 feed people)=800 left
        int acreToPlant=getNumber("O great Hammurabi, how many acres to plant  with  grains?");
        //int leftOverBushel=610;
        // bushels> what to plant(2*acre to plant), totalAcres> to plant (1010>300) ,population> (acresToPlant/10)
        if(acresOwned>=acreToPlant){  //  check enough acresowned is true
            if(population > acreToPlant/10){
                if(bushels >=acreToPlant *2){
                    return acreToPlant;
                }else{
                    System.out.println("you don't have enough bushels to plant");
                    return  0;
                }
            }else{
                System.out.println("you don't have enough population to plant");
                return  0;
            }
        }else {
            System.out.println("You don't own enough acres to plant");
            return 0;
        }

    }

    /**
     * there is a 15% chance of a horrible plague. When this happens, half your people die.
     *
     * @param population  ---> current population
     * @return  -->Number of plague deaths (possibly zero)
     */
    public int plagueDeaths(int population){  //100
        int plagueDeath=0;
        if( rand.nextDouble() <0.15){  // upto .14 plague has hit
            plagueDeath= population/2;
        }
        return plagueDeath;
    }

    /**
     *
     * @param population
     * @param bushelsFedToPeople
     * @return
     */
   public  int starvationDeaths(int population, int bushelsFedToPeople){
        int starvationDeaths=0;
        if(population*20 <= bushelsFedToPeople) {// 100*20=2000<=2000
            starvationDeaths=0;
        } else {
            double  bal=population*20-bushelsFedToPeople;  //1000
            starvationDeaths= (int) Math.ceil(bal/20); //50
        }
        return starvationDeaths;
    }

    /**
     * Return true if more than 45% of the people starve
     * @param population
     * @param howManyPeopleStarved
     * @return  -->
     */
    public boolean uprising(int population,int howManyPeopleStarved){
        System.out.println("starved  "+howManyPeopleStarved+"ppl  "+population);
        double percentageinp1 = howManyPeopleStarved;
        double percentageinp2 = population;
       double percentage =(percentageinp1/percentageinp2)*100;
        System.out.println(percentage);
        if( percentage > 45){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param population
     * @param acresOwned
     * @param grainInStorage
     * @return  --> return immigrants
     */
    public int immigrants ( int population, int acresOwned, int grainInStorage ){
        int num= (int) Math.ceil((20 * acresOwned)+grainInStorage) / (100 *population) + 1;
      //  System.out.println(num+ "  immigants");
        return num;

    }

    /**
     *
     * @param acres
     * @param bushelsUsedAsSeed
     * @return
     */
   public  int harvest(int acres, int bushelsUsedAsSeed){
        //System.out.println("Math random :"+Math.floor(Math.random() * 8) + 1);
        int rand1 = rand.nextInt(6-1+1)+1;
        System.out.println("random 1 to 6: "+rand1);
        return (acres*rand1);
    }

    /**
     * 40% chance that you will have a rat infestation
     * @param bushels
     * @return---> amount of grain eaten by rats (possibly zero).
     */
    public int grainEatenByRats ( int bushels ){  //3000
        if(  rand.nextInt(100) < 40) { //upto 0 to 39
            int perRatsEating= 10 + rand.nextInt(21); //between 10 to 30
            double grainEatenByRats= bushels * perRatsEating/100;
            return (int) grainEatenByRats;
        }
        return 0;
    }

    /**
     * ranges from 17 to 23 bushels per acre.
     * @return --->Return the new price
     */
    public int newCostOfLand (){
        int pp=17+rand.nextInt(7);
        System.out.println("Random price:"+pp);
        return  pp;
    }

    int getNumber(String message){
        while(true){
            System.out.println(message);
            try{
                return scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("\""+scanner.next() + "\" isn't a number !");
            }
        }
    }


}