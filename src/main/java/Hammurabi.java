import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);
    private int population;
    private int grain; //bushels of grain in storage
    private int land;
    int valueBushelsPerAcre = 19; // land value is 19 bushels/acre
    private int year;
    private int acresPlanted;
    private int plagueDeaths;
    private int immigrants;
    private int starved;
    private int harvest;
    private int acres;
    private int landValue;
    private int grainEatenByRats;

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }


    private  void printSummary() {
        System.out.println(" You are in year" + year + " of your ten year rule.");
        if (plagueDeaths > 0) {
            System.out.println(" A horrible plague has taken" + plagueDeaths + " lives.");
        }
        System.out.println(" In the previous year" + starved + " people starved to death.");
        System.out.println("In the previous year" + immigrants + "people have entered our kingdom.");
        System.out.println(" The total population is now" + population);
        System.out.println(" We have harvested" + harvest + " bushels of grain");
        System.out.println(" The kingdom owns" + acres + " acres of land");
        System.out.println(" Land is now priced at" + landValue + "bushels per acre.");
        if (grainEatenByRats > 0) {
            System.out.println(" O  Hammurabi, rats have destroyed" + grainEatenByRats + " bushels! We only have " + grain + " remaining in storage.");
        } else {
            System.out.println(" The remaining bushels of grain in storage is" + grain);
        }
    }
    void playGame() {
        int population = 100;
        int bushelsOfGrain = 2800; //bushels of grain in storage
        int land = 1000;
        int valueBushelsPerAcre = 19; // land value is 19 bushels/acre
        int years = 1;
        int bought, acresPlanted, feed, plague, starved, immig, harvest, rats;
        while (years <= 10) {
            printSummary();
            bought = askHowManyAcresToBuy(valueBushelsPerAcre, grain);
            if (bought > 0)
                land += bought;
            else
                land -= askHowManyAcresToSell(valueBushelsPerAcre, grain);
            feed = askHowMuchGrainToFeedPeople(grain);
            acresPlanted = askHowManyAcresToPlant(land, population, grain);
            plague = plagueDeaths(population);
            starved = starvationDeaths(population, feed);
            immig = immigrants(population, land, grain);
            harvest = harvest(acresPlanted);
            rats = grainEatenByRats(grain);
            valueBushelsPerAcre = newCostOfLand();
            population = population - plague - starved + immig;
            grain = grain - rats - feed + harvest;
            years++;
        }
    }

    public static int askHowManyAcresToBuy (int price, int bushels) {
        //Scanner scan1 = new Scanner(System.in);
        System.out.println("How many acres would you like to buy?");
        //Buying: Grain is paying for the purchase.
        int numberOfAcresToBuy = scanner.nextInt();
        do {
            if (numberOfAcresToBuy < 0) {
                System.out.println("You can't use a negative number.");
            } else if (numberOfAcresToBuy > 0 && bushels < price * numberOfAcresToBuy) {
                System.out.println("You do not have enough to purchase.");
            } else if (numberOfAcresToBuy >= 0 && bushels > price * numberOfAcresToBuy) {
                return numberOfAcresToBuy;

                //bushels = bushels - (valueBushelsPerAcre * numberOfAcresToBuy);

            }
        } while (true);
    }

    public static int askHowManyAcresToSell(int acresOwned) {
        //Ask how many to sell.
        System.out.println("How many acres do you want to sell?");
        //Scanner sellAnswer = new Scanner(System.in);
        int numberOfAcresToSell = scanner.nextInt();
        do {
            if (numberOfAcresToSell > acresOwned) {
                System.out.println("No can do.");
            } else {
                return numberOfAcresToSell;
            }
        } while (true);
        //You can't sell more than you have.
    }

    public static int askHowMuchGrainToFeedPeople(int bushels) {
        int grainToFeed;
        System.out.println("How much grain do you want to feed people?");
        //Scanner grainAmount = new Scanner(System.in);
        grainToFeed = scanner.nextInt();
        do {
            if (grainToFeed > bushels) {
                System.out.println("No can do.");
            } else {
                return grainToFeed;
            }
        } while (true);
        //You can't sell more than you have.
    }

    public static int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        String howManyAcresToPlantQuestion = "How many acres do you wish to plant?";
        int acresToPlant = 0;
        boolean validAcreage = false;
        while (!validAcreage) {

            if (acresToPlant > acresOwned) {
                System.out.println(" O Hammurabi, but you only have " + acresOwned + " acres for which to plant!");
            } else if (acresToPlant > 10 * population) {
                System.out.println(" O Hammurabi, but you only have " + acresOwned + " acres for which to increase your grain!");
            } else if (acresToPlant < 2 * bushels) {
                System.out.println(" O  Hammurabi, but you only have " + bushels + " bushels for which to plant");
                System.out.println(acresToPlant + " acres you wish to plant? We don't have the resources");
            } else validAcreage = true;

//        } acresPlanted = acresToPlant / 2;
//         grain = grain - acresToPlant;
//        System.out.println( ", we now have " + grain
//                + " bushels of grain in storage.");;
//        return acresPlanted;
        }
        return acresToPlant;
    }

    public int plagueDeaths (int population) {
        return randomChance(15) ? population/2 : 0;
    }

    public int newCostOfLand(){
        return rand.nextInt(7)+17; //bound is exclusive
    }

    public int harvest(int acresPlantedWithSeeds){
        return (rand.nextInt(6)+1)*acresPlantedWithSeeds;
    }

    public int grainEatenByRats(int bushels){
        return randomChance(40)
                ? (rand.nextInt(21)+10)*bushels / 100 //bound is exclusive
                :0;
    }

    public boolean randomChance(double chanceOfOccurrence){
        return rand.nextDouble()<(chanceOfOccurrence/100);
    }

    
    public void finalSummary(int acresOwned) {

        if (starved > (45 *  population) / 100) {
            System.out.println( " Hammurabi, \n" + "you have starved " + starved + " of your subjects in your final year./n" + " Your time is over!");
        }

        if ( population * 20 < acresOwned) {
            acresOwned = population * 20;
        }

        if (acresOwned < 700) {
            System.out.println(" Ah hammurabi!!\n" + "you have put in much effort\n" +" but have only done an OKAY job..");
        } else if (acresOwned < 900) {
            System.out.println(" Ah hammurabi!!\n" + "you have put in much effort\n" + " And have done a MAGNIFICENT JOB!!");
        }

        else {
            System.out.println( " Congratulations!! You have done well by your people\n and will be praised for many years to come!!!");
        }

    }
}



