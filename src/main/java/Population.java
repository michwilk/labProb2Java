

public class Population {

    private final Individual[] individuals;
    private final int[] scores;
    public Population(Individual[] individuals, int[] scores) {
        this.individuals = individuals;
        this.scores = scores;
    }

    public int[] getScores() {
        return scores;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }
}
