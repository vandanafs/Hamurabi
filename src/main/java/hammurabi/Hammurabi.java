package hammurabi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {


    Random rand=new Random();
    Scanner scanner=new Scanner(System.in);
    int rand1 = rand.nextInt(6-1+1)+1;

    public static void main ( String[] args ) {
        new Hammurabi().playGame();


    }

    private void playGame () {   //each year upto 10 years
        int population=100;
        int grain;
        int acresTotal=1000;
        int bushelsTotal=2800;
        int pricePerAcre=19;
        int harvest=0;
        int year=1;
        int harvestPerAcre=3;
        while(year<= 10) {
            System.out.println("O great Hammurabi!" + "\n" +
                    "You are in year " +year+ " of your ten year rule." + "\n" +
                    " In the previous year 0 people starved to death." + "\n" +
                    "In the previous year 5 people entered the kingdom." + "\n" +
                    "The population is now "+population+"." + "\n" +
                    " We harvested "+harvest+" bushels at "+ harvestPerAcre+" bushels per acre." + "\n" +
                    " Rats destroyed 200 bushels, leaving  "+bushelsTotal+"  bushels in storage." + "\n" +
                    " The city owns"+acresTotal+" acres of land." + "\n" +
                    " Land is currently worth 19 bushels per acre.");
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

            //  harvest(acresPlanted,acresPlanted*2);
            harvest = harvest(acresPlanted, acresPlanted * 2);
            harvestPerAcre=harvest/acresPlanted;
            bushelsTotal = bushelsTotal + harvest;
            System.out.println("harvest:" + harvest);
            //    bushelsTotal=bushelsTotal-(acresPlanted*2);
            System.out.println("Acres total :" + acresTotal);
            System.out.println("total Bushels:" + bushelsTotal);
            System.out.println("Total acres planted:" + acresPlanted);
            year++;
            System.out.println("End of the year "+year);
            System.out.println("-------------------");
        } //end of while loop

    }

    public int askHowManyAcresToBuy(int price,int bushels){  //(19, 190)
        int buy=0;
        buy=getNumber("O great Hammurabi, how many acres do you want to buy?");
        System.out.println("buy  -->"+buy);
        if(buy*price <bushels){  //10*19=190<2800
            return buy;
        }
        if(buy*price >bushels){
            System.out.println("Hey Hammurabi you don't have enough bushels");
            buy=0;
            return buy;
        }
        //return   (bushels/price);//acre

        return buy;
    }


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
    public  int askHowMuchGrainToFeedPeople(int bushels){  //2800
        int feed= getNumber("O great Hammurabi, how much grains you want feed?");
        return feed;
    }

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

    public int plagueDeaths(int population){
        return 0;
    }

    public boolean uprising(int population,int howManyPeopleStarved){
        return false;
    }
    int immigrants(int population, int acresOwned, int grainInStorage){
        return 0;
    }
    int harvest(int acres, int bushelsUsedAsSeed){
        //System.out.println("Math random :"+Math.floor(Math.random() * 8) + 1);
        int rand1 = rand.nextInt(6-1+1)+1;
        System.out.println("random 1 to 6: "+rand1);
        return (acres*rand1);
    }
    int grainEatenByRats(int bushels){
        return 0;
    }

    int newCostOfLand(){
        return 0;
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
