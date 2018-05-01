package impl;

import api.Individual;
import api.Population;

import java.util.Arrays;
import java.util.Random;

public class Roulette {

    private final RouletteIndividual[] rouletteIndividuals;
    private final Random generator;

    public Roulette(Population population) {
        this.generator = new Random();
        this.rouletteIndividuals = new RouletteIndividual[population.getIndividuals().size()];
        for (int i = 0; i < rouletteIndividuals.length; i++) {
            rouletteIndividuals[i] = new RouletteIndividual();
            rouletteIndividuals[i].individual = population.getIndividuals().get(i);
            rouletteIndividuals[i].reverseScore = 100000 / rouletteIndividuals[i].individual.getScore();
            //todo sprawdzic wartosc

        }

        Arrays.sort(rouletteIndividuals);

        int totalReverseScore = 0;
        for (int i = 0; i < rouletteIndividuals.length; i++) {
            totalReverseScore += rouletteIndividuals[i].reverseScore;
            rouletteIndividuals[i].totalReverseScore = totalReverseScore;
        }
    }

    public Individual getNextRandom() {
        int maxVal = rouletteIndividuals[rouletteIndividuals.length - 1].totalReverseScore;
        int randomVal = generator.nextInt(maxVal);
        int prev = 0;
        for (int i = 0; i < rouletteIndividuals.length; i++) {
            int curr = rouletteIndividuals[i].totalReverseScore;
            if (randomVal <= curr && randomVal > prev) {
                return rouletteIndividuals[i].individual;
            }
            prev = curr;
        }
        throw new IllegalStateException("Invalid roulette state");
    }

    class RouletteIndividual implements Comparable<RouletteIndividual> {
        private Individual individual;
        private int totalReverseScore;
        private int reverseScore;

        @Override
        public int compareTo(RouletteIndividual o) {
            return this.reverseScore - o.reverseScore;//todo sprawdzic kolejnosc
        }
    }
}
