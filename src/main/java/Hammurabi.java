import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);
    private int population=100;
    private int grain=2800; //bushels of grain in storage
    private int land=1000;
    private int valueBushelsPerAcre = 19; // land value is 19 bushels/acre
    private int year=1;
    private int acresPlanted;
    private int plagueDeaths;
    private int immigrants;
    private int starved;
    private int harvest;
    private int grainEatenByRats;

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() {
        int feed, bought, sell;
        while (year <= 10) {
            printSummary();
            bought = askHowManyAcresToBuy(valueBushelsPerAcre, grain);
            if (bought > 0) {
                land += bought;
                grain -=bought*valueBushelsPerAcre;
            } else {
                sell = askHowManyAcresToSell(grain);
                land -=sell;
                grain =grain +(sell*valueBushelsPerAcre);
            }
            feed = askHowMuchGrainToFeedPeople(grain);
            grain -= feed;
            acresPlanted = askHowManyAcresToPlant(land, population, grain);
            grain -=acresPlanted/2;
            plagueDeaths = plagueDeaths(population);
            population -= plagueDeaths;
            starved = starvationDeaths(population, feed);
            if (starved==0) {immigrants = immigrants(population, land, grain);}
            if (uprising(population,starved))
                break;
            harvest = harvest(acresPlanted);
            grain += harvest;
            grainEatenByRats = grainEatenByRats(grain);
            valueBushelsPerAcre = newCostOfLand();
            population = population - starved + immigrants;
            grain = grain - grainEatenByRats;
            year++;
        }
        finalSummary();
    }

    public int askHowManyAcresToBuy(int price, int bushels) {
        //Scanner scan1 = new Scanner(System.in);
        //System.out.println("How many acres would you like to buy?");
        //Buying: Grain is paying for the purchase.
        int numberOfAcresToBuy = getNumber("\nHow many acres would you like to buy?\n");
        do {
            if (numberOfAcresToBuy < 0) {
                System.out.println("O Hammurabi, but that is not a positive number!\n");
                numberOfAcresToBuy = getNumber("\nHow many acres would you like to buy?");
            } else if (numberOfAcresToBuy > 0 && bushels < price * numberOfAcresToBuy) {
                System.out.println("\nO Hammurabi, but you only have " + bushels + " bushels of grain!\nIt costs " +  valueBushelsPerAcre + " bushels per acre");
                numberOfAcresToBuy = getNumber("\nHow many acres would you like to buy?\n");
            } else if (bushels > price * numberOfAcresToBuy) {
                return numberOfAcresToBuy;
                //bushels = bushels - (valueBushelsPerAcre * numberOfAcresToBuy);
            } else if (bushels==0) return 0;
        } while (true);
    }

    public int askHowManyAcresToSell(int acresOwned) {
        //Ask how many to sell.
        //System.out.println("How many acres do you want to sell?");
        //Scanner sellAnswer = new Scanner(System.in);
        int numberOfAcresToSell = getNumber("How many acres do you want to sell?\n");
        do {
            if (numberOfAcresToSell > acresOwned) {
                System.out.println("O Hammurabi, but you only have " + land + " acres of land!\n");
                numberOfAcresToSell = getNumber("How many acres do you want to sell?\n");
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
        do {
            grainToFeed = getNumber("\nHow many bushels of grain do you want to feed your people?\n");
            if (grainToFeed > bushels) {
                System.out.println("O Hammurabi, but you only have " + bushels + " bushels of grain!\n");
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

    private  void printSummary() {
        System.out.println("\nYou are in year " + year + " of your ten year rule.");
        if (plagueDeaths > 0) {
            System.out.println("A horrible plague has taken " + plagueDeaths + " lives.");
        }
        System.out.println("In the previous year " + starved + " people starved to death.");
        System.out.println("In the previous year " + immigrants + " people have entered our kingdom.");
        System.out.println("The total population is now " + population);
        System.out.println("We have harvested " + harvest + " bushels of grain");
        System.out.println("The kingdom owns " + land + " acres of land");
        System.out.println("Land is now priced at " + valueBushelsPerAcre + " bushels per acre.");
        if (grainEatenByRats > 0) {
            System.out.println("O  Hammurabi, rats have destroyed " + grainEatenByRats + " bushels! We only have " + grain + " remaining in storage.\n");
        } else {
            System.out.println("The remaining bushels of grain in storage is " + grain + "\n") ;
        }
    }

    public static int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        int acresToPlant=0;
        boolean validAcreage = false;
        while (!validAcreage) {
            acresToPlant=getNumber("\nHow many acres do you wish to plant?\n");
            if (acresToPlant > acresOwned) {
                System.out.println("\nO Hammurabi, but you only have " + acresOwned + " acres for which to plant!\n");
            } else if (acresToPlant > 10 * population) {
                System.out.println("\nO Hammurabi, but you only have " + population + " people to work your fields!\n");
            } else if (acresToPlant > 2 * bushels) {
                System.out.println("\n O  Hammurabi, but you only have " + bushels + " bushels for which to plant!\n");
                System.out.println("We don't have the resources to plant " + acresToPlant + "acres");
            } else validAcreage = true;
        }
        return acresToPlant;
    }

    public void finalSummary() {
        if (starved >= (45 *  population) / 100) {
            System.out.println( "Hammurabi, you have starved " + starved + " of your subjects in your final year.\n" + "Your time is over!");
            return;
        }
        if (population * 20 < land) {
            land = population * 20;
        }
        if (land < 700) {
            System.out.println("\nAh hammurabi!!\n" + "You have put in much effort but have only done an okay job..");
        } else if (land > 700 && land < 900) {
            System.out.println("\nAh hammurabi!!\n" + "You have put in much effort and have done a magnificent job!!");
        }
        else {
            System.out.println( "\nCongratulations!! You have done well by your people, " + population + " have survived under your rule.\n" + "You shall be praised for many years to come!!!");
        }
    }

    public static int immigrants(int population,int acresOwned,int grainInStorage){
        return (20 * acresOwned + grainInStorage)/(100 * population) +1;

    }

    public static int starvationDeaths(int population, int bushelsFedtoPeople){
        if(population *20 <= bushelsFedtoPeople) return 0;
        else return population - (bushelsFedtoPeople/20);
    }

    public static boolean uprising(int population, int howManyPeopleSarved){
        return (((double)howManyPeopleSarved/population) >= .45);
    }
}


