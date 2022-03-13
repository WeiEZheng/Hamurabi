import java.util.InputMismatchException;
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
        int grain = 2800; //bushels of grain in storage
        int land = 1000;
        int valueBushelsPerAcre = 19; // land value is 19 bushels/acre
        int years = 1;
        int bought, acresPlanted, feed, plague, starved, immig, harvest, rats, sold;
        while (years <= 10) {
            printSummary();
            bought = askHowManyAcresToBuy(valueBushelsPerAcre, grain);
            if (bought > 0) {
                land += bought;
            } else {
                land -= askHowManyAcresToSell(grain);
            }
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
        printFinalSummary();
    }

    public static int askHowManyAcresToBuy(int price, int bushels) {
        //Scanner scan1 = new Scanner(System.in);
        //System.out.println("How many acres would you like to buy?");
        //Buying: Grain is paying for the purchase.
        int numberOfAcresToBuy = getNumber("How many acres would you like to buy?");
        do {
            if (numberOfAcresToBuy < 0) {
                System.out.println("You can't use a negative number.");
                numberOfAcresToBuy = getNumber("How many acres would you like to buy?");
            } else if (numberOfAcresToBuy > 0 && bushels < price * numberOfAcresToBuy) {
                System.out.println("You do not have enough to purchase.");
                numberOfAcresToBuy = getNumber("How many acres would you like to buy?");
            } else if (numberOfAcresToBuy >= 0 && bushels > price * numberOfAcresToBuy) {
                return numberOfAcresToBuy;
                //bushels = bushels - (valueBushelsPerAcre * numberOfAcresToBuy);
            }
        } while (true);
    }

    public static int askHowManyAcresToSell(int acresOwned) {
        //Ask how many to sell.
        //System.out.println("How many acres do you want to sell?");
        //Scanner sellAnswer = new Scanner(System.in);
        int numberOfAcresToSell = getNumber("How many acres do you want to sell?");
        do {
            if (numberOfAcresToSell > acresOwned) {
                System.out.println("No can do. Try Again");
                numberOfAcresToSell = getNumber("How many acres do you want to sell?");
            } else {
                return numberOfAcresToSell;
            }
        } while (true);
        //You can't sell more than you have.
    }

    public static int askHowMuchGrainToFeedPeople(int bushels) {
        int grainToFeed;
        //System.out.println("How much grain do you want to feed people?");
        //Scanner grainAmount = new Scanner(System.in);
        grainToFeed = getNumber("How much grain do you want to feed people?");
        do {
            if (grainToFeed > bushels) {
                System.out.println("No can do. Try Again.");
                grainToFeed = getNumber("How much grain do you want to feed people?");
            } else {
                return grainToFeed;
            }
        } while (true);
        //You can't sell more than you have.
    }

    public int plagueDeaths(int population) {
        return randomChance(15) ? population / 2 : 0;
    }

    public int newCostOfLand() {
        return rand.nextInt(7) + 17; //bound is exclusive
    }

    public int harvest(int acresPlantedWithSeeds) {
        return (rand.nextInt(6) + 1) * acresPlantedWithSeeds;
    }

    public int grainEatenByRats(int bushels) {
        return randomChance(40)
                ? (rand.nextInt(21) + 10) * bushels / 100 //bound is exclusive
                : 0;
    }

    public boolean randomChance(double chanceOfOccurrence) {
        return rand.nextDouble() < (chanceOfOccurrence / 100);
    }

    static int getNumber(String message) {
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