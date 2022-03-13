import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() {
        int population = 100;
        int bushelsOfGrain = 2800; //bushels of grain in storage
        int land = 1000;
        int valueBushelsPerAcre = 19; // land value is 19 bushels/acre
        int years = 1;
        int bought, acresPlanted,feed,plague,starved,immig,harvest,rats;
        while (years<=10) {
            printSummary();
            bought=askHowManyAcresToBuy(valueBushelsPerAcre, grain);
            if (bought>0)
                land+=bought;
            else
                land-=askHowManyAcresToSell(valueBushelsPerAcre,grain);
            feed=askHowMuchGrainToFeedPeople(grain);
            acresPlanted=askHowManyAcresToPlant(land, population, grain);
            plague=plagueDeaths(population);
            starved=starvationDeaths(population,feed);
            immig=immigrants(population,land,grain);
            harvest=harvest(acresPlanted);
            rats=grainEatenByRats(grain);
            valueBushelsPerAcre=newCostOfLand();
            population=population-plague-starved+immig;
            grain=grain-rats-feed+harvest;
            years++;
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
       int acresToPlant = 0;
        if(acresToPlant > acresOwned) {
        System.out.println(" O Hammurabi, but you only have" + acresOwned + " acres for which to plant!");
        } else if ((acresToPlant > 10*  population)) {
            System.out.println(" O Hammurabi, but you only have" + acresOwned + " acres for which to increase your grain!");
        } else if (acresToPlant < 2 * bushels)
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
}
