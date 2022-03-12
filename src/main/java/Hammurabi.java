package hammurabi.src.main.java;

import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public int plagueDeath (int population) {
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
        Random rand = new Random();
        return rand.nextDouble()>(chanceOfOccurrence/100);
    }
}
