import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        if (args.length != 2) {
            System.out.println("Usage: 1st arg-path to input file, 2nd arg path to output file");
            return;
        }
        Parser parser = new Parser();
        InputData inputData = parser.parseInput(args[0]);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Individual solution = geneticAlgorithm.solveProblemInstance(inputData);
        parser.parseOutput(args[1], solution);
    }
}
