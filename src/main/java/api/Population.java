package api;

import java.util.Collections;
import java.util.List;


//Populacja - zbior osobnikow
public class Population {

    private final List<Individual> individuals;

    public Population(List<Individual> individuals) {
        this.individuals = Collections.unmodifiableList(individuals);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
}
