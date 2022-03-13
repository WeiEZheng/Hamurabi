package hammurabi.src.main.java;

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
        int year = 0;
        int numberOfAcresToBuy = 0;
        int numberOfAcresToSell = 0;
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

        }


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



//        //Ask the questions in this order. No backing up. No changing answers.
//        int askHowManyAcresToBuy(int price, int bushels)
//        int askHowManyAcresToSell(int acresOwned)
//        int askHowMuchGrainToFeedPeople(int bushels)
//        int askHowManyAcresToPlant(int acresOwned, int population, int bushels)
//
//
//
//
//
//    }
//}
