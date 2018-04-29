package impl;

import api.GeneticAlgorithm;
import api.Individual;
import api.InputData;
import api.Population;

public class GeneticAlgorithmImpl implements GeneticAlgorithm {
    public Individual solveProblemInstance(InputData data) {

        int generation = 0;
        Population population = generatePopulation(data);
        while (generation < data.getMaxGeneration()) {
            generation++;
            System.out.println("Generation " + generation);
            Population children = generateChildren(population);
            population = children;
        }
        return findBestIndividual(population);
    }

    private Individual findBestIndividual(Population population) {
        int bestScore = Integer.MAX_VALUE;
        Individual best = null;
        for (int i = 0; i < population.getIndividuals().length; i++) {
            int currScore = population.getScores()[i];
            if (currScore < bestScore) {
                bestScore = currScore;
                best = population.getIndividuals()[i];
            }
        }
        return best;
    }


    private Population generatePopulation(InputData data) {
        Individual[] individuals = new Individual[data.getPopulationSize()];
        for (int i = 0; i < data.getPopulationSize(); i++) {
            individuals[i] = generateIndividual(data);
        }
        int[] scores = assessIndividuals(individuals, data);
        return new Population(individuals, scores);
    }

    private int[] assessIndividuals(Individual[] individuals, InputData data) {
        int[] scores = new int[individuals.length];
        for (int i = 0; i < individuals.length; i++) {
            scores[i] = assessIndividual(individuals[i], data);
        }
        return scores;
    }

    private Individual generateIndividual(InputData data) {
        int tryCount = 0;
        Individual individual;

        do {
            tryCount++;
            individual = randomIndividual(data);
            if (tryCount > 100) throw new IllegalArgumentException("Cant create proper individual");
        } while (!validateIndividual(individual));

        return individual;
    }

    private boolean validateIndividual(Individual individual) {
        return validateDeadlines(individual) && validateSupplies(individual);
    }

    /**
     * Ocena osobnika(potencjalnego rozwiazania) to ilosc  godzin od rozpoczcia prodockcji
     * do zakocznenia ostatniego z jego taskow.
     * Czy mniejsza ocena tym lepiej.
     * Do obliczenia tej wartosci potrzeba daty rozpoczecia produkcji, daty rozpoczecia ostatniego zamowienia
     * oraz czasu trwania tego zamowienia (z InputData.durations())
     *
     * @param individual
     * @param data
     * @return
     */
    private int assessIndividual(Individual individual, InputData data) {
        //todo
        return 0;
    }

    private Individual randomIndividual(InputData data) {
        //todo przed walidacja
        return null;
    }

    private boolean validateSupplies(Individual individual) {
        return false;//todo
    }

    private boolean validateDeadlines(Individual individual) {
        return false;//todo
    }

    private Population generateChildren(Population parents) {
        //todo
        return null;
    }
}
