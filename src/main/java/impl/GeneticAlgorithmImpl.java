package impl;

import api.*;

import java.util.*;

import static impl.Util.MILLI_TO_HOUR;
import static impl.Util.generateDateBetween;
import static impl.Util.hoursDifference;

public class GeneticAlgorithmImpl implements GeneticAlgorithm {

    /**
     * Glowna petla algorytmu
     *
     * @param data
     * @return
     */
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


    /**
     * Zwraca najlpeszego osobnika -  z minimalna funkcja oceny
     *
     * @param population
     * @return
     */
    private Individual findBestIndividual(Population population) {
        return population.getIndividuals().stream().min(Comparator.comparingInt(Individual::getScore)).get();
    }

    /**
     * Generuje populacje n osobnikow
     *
     * @param data
     * @return
     */
    private Population generatePopulation(InputData data) {
        List<Individual> individuals = new ArrayList<>();
        for (int i = 0; i < data.getPopulationSize(); i++) {
            individuals.add(generateIndividual(data));
        }
        return new Population(individuals);
    }

    /**
     * Probuje wygyenerowac osobnika ktory spelnia wymagania dostaw i deadlinow co nazwyzej 100 razy.
     * Jesli sie nie uda w tym czasie rzuca wyjatek.
     *
     * @param data
     * @return
     */
    private Individual generateIndividual(InputData data) {
        int tryCount = 0;
        List<OrderSchedule> schedule;
        int score;
        Individual result;
        do {
            tryCount++;
            if (tryCount > 100) throw new IllegalArgumentException("Cant create proper individual");
            schedule = randomOrdersSchedule(data);
            score = assessOrdersSchedule(schedule, data);
            result = new Individual(schedule, score);
        } while (!validateIndvidual(result, data));

        return result;
    }

    /**
     * walidacja na dostawy i nie-zachodzenia na siebie realizacji zamowien
     * (bo mamy tylko 1 linie produkcyjna)
     *
     * @param individual
     * @param data
     * @return
     */
    private boolean validateIndvidual(Individual individual, InputData data) {
        SuppliesValidator suppliesValidator = new SuppliesValidator();
        OverLappingValidator overLappingValidator = new OverLappingValidator();
        return suppliesValidator.validateSupplies(individual, data) &&
                overLappingValidator.validateOverLappping(individual, data);
    }

    /**
     * Ocena harmonogramu(potencjalnego rozwiazania) to ilosc  godzin od rozpoczcia prodockcji
     * do zakocznenia ostatniego z jego zamownien(Order).
     * Czy mniejsza ocena tym lepiej.
     * Do obliczenia tej wartosci potrzeba daty rozpoczecia produkcji,
     * oraz daty zakonczenia ostatniego taska
     *
     * @param orderSchedule
     * @param data
     * @return
     */
    private int assessOrdersSchedule(List<OrderSchedule> orderSchedule, InputData data) {
        Date endOfLastOrder = orderSchedule.stream().map(OrderSchedule::getEndDate).max(Date::compareTo).get();
        //todo sprawdzic to
        return hoursDifference(endOfLastOrder, data.getProductionStartDate());
    }

    /**
     * Generuje losowy harmonogram zamowien. Wylosowane okresy startu zamowien sa przed deadlineami
     *
     * @param data
     * @return
     */
    private List<OrderSchedule> randomOrdersSchedule(InputData data) {
        List<OrderSchedule> result = new ArrayList<>();
        for (Order order : data.getOrders()) {
            Date randomStartDateBeforeDeadline =
                    generateDateBetween(data.getProductionStartDate(),
                            new Date(order.getDueDate().getTime() - order.getDuration() * MILLI_TO_HOUR));
            result.add(new OrderSchedule(order.getId(), order.getDuration(), randomStartDateBeforeDeadline));
        }
        return Collections.unmodifiableList(result);
    }

    private Population generateChildren(Population parents) {
        //todo
        //one point crossover z 2 rodzicow 2 potomkow
        return null;
    }
}
