public class GeneticAlgorithm {
    public Individual solveProblemInstance(InputData data) {

        int generation = 0;
        Population population = generatePopulation(data);
        while (generation < data.getMaxGeneration()) {
            generation++;
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
            individual = randonIndividual(data);
            if (tryCount > 100) throw new IllegalArgumentException();
        } while (!validateIndividual(individual));

        return individual;
    }

    private Individual randonIndividual(InputData data) {
        //todo przed walidacja
    }

    private boolean validateIndividual(Individual individual) {
        //todo
    }

    private int assessIndividual(Individual individual, InputData data) {
        //todo
        return 0;
    }


    private Population generateChildren(Population parents) {
        //todo
    }
}
