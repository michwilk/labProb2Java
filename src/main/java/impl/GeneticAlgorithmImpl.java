package impl;

import api.*;

import java.util.Date;
import java.util.List;

import static impl.Util.MILLI_TO_HOUR;
import static impl.Util.generateDateBetween;
import static impl.Util.hoursDifference;

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
        Date latestTasBeginningDate = individual.getBeginningDates()[0];
        int durationOfLastTask = data.getOrders().get(0).getDuration();

        for (int i = 1; i < individual.getBeginningDates().length; i++) {
            Date date = individual.getBeginningDates()[i];
            if (date.after(latestTasBeginningDate)) {
                latestTasBeginningDate = date;
                durationOfLastTask = data.getOrders().get(i).getDuration();
            }
        }
        return hoursDifference(latestTasBeginningDate, data.getProductionStartDate()) + durationOfLastTask;
    }

    private Individual randomIndividual(InputData data) {
        Date[] beginningDates = new Date[data.getOrders().size()];
        Date nextPossibleTaskDate = data.getProductionStartDate();
        Date endOfFreePeriod = new Date(data.getProductionEndDate().getTime() - getTotalTimeOfAllOrdersInHours(data) * MILLI_TO_HOUR);
        List<Order> orders = data.getOrders();
        for (int i = 0; i < beginningDates.length; i++) {
            beginningDates[i] = generateDateBetween(nextPossibleTaskDate, endOfFreePeriod);

            long currOrderDurationInMillis = orders.get(i).getDuration() * MILLI_TO_HOUR;
            //przesun poczatek przedzialu losowania o czas trwania taska do przodu
            nextPossibleTaskDate = new Date(beginningDates[i].getTime() + currOrderDurationInMillis);
            //przesun koniec przedzialu losowania o czas trwania taska do przodu
            endOfFreePeriod = new Date(endOfFreePeriod.getTime() + currOrderDurationInMillis);
        }
        return new Individual(beginningDates);
    }

    private int getTotalTimeOfAllOrdersInHours(InputData data) {
        int totalHours = 0;
        for (Order order : data.getOrders()) {
            totalHours += order.getDuration();
        }
        return totalHours;
    }

    private boolean validateDeadlines(Individual individual) {
        return false;//todo
    }

    private boolean validateSupplies(Individual individual) {
        return false;//todo
    }


    private Population generateChildren(Population parents) {
        //todo
        return null;
    }
}
