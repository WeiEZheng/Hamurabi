import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
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
        int grain = 2800; //bushels of grain in storage
        int land = 1000;
        int valueBushelsPerAcre = 19; // land value is 19 bushels/acre
        int years = 10;
    }

//        public static int askHowManyAcresToBuy (int price, int bushels) {
//            Scanner scan1 = new Scanner (System.in);
//            System.out.println("How many acres would you like to buy?");
//            //Buying =
//            int numberOfAcresToBuy = scan1.nextInt();
//
//        }


    //for (int i = 0; i < years ; i ++) {


//            System.out.println("O great Hammurabi!\n" +
//                    "You are in year 1 of your ten year rule.\n" +
//                    "In the previous year 0 people starved to death.\n" +
//                    "In the previous year 5 people entered the kingdom.\n" +
//                    "The population is now 100.\n" +
//                    "We harvested 3000 bushels at 3 bushels per acre.\n" +
//                    "Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n" +
//                    "The city owns 1000 acres of land.\n" +
//                    "Land is currently worth 19 bushels per acre.");


    //Ask the questions in this order. No backing up. No changing answers.
//        int askHowManyAcresToBuy(int price, int bushels);
//        int askHowManyAcresToSell(int acresOwned);
//        int askHowMuchGrainToFeedPeople(int bushels);

    //Asks the user for the initial quantity, returns the number entered. If response is not an integer question repeats

    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out
                        .println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
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

//    public void finalSummary() {
//        if (starved > (45 *  population) / 100) {
//        System.out.println( " Hammurabi, \n" + "you have starved " + starved + " of your subjects in your final year./n" + " Your time is over!");
//        return;
//        }
//        if ( population * 20 < acresOwned {
//            acresOwned = population * 20;
//            }
//        if acresOwned < 700) {
//                System.out.println(" Ah hammurabi!!\n" + "you have put in much effort\n" +" but have only done OKAY..");
//            }
//            else if acresOwned < 700) {
//                System.out.println(" Ah hammurabi!!\n" + "you have put in much effort\n" + " And have done a MAGNIFICENT JOB!!");
//        }
//
//
//        }
}












//    def ask_to_cultivate(acres, population, bushels):
//            ''' Ask user how much land you want to plant seed in'''
//    acres_cultivate=input( 'how much land you want to plant seed in')
//    while acres_cultivate * 2> bushels:
//    print " you only have", bushels, "bushels  stupid!!!! you still need to give me", eval('acres_cultivate * 2 - bushels'), " bushels"
//    acres_cultivate=input(' how much land you want to seed in')
//    while acres_cultivate > population * 10:
//    print " the population is only", population
//            acres_cultivate=input(' how much land you want to plant seed in')
//    while acres_cultivate > acres :
//    print " you only have" , acres, " acres "
//    acres_cultivate=input(' how much land you want to plant seed in')
//    return acres_cultivate
