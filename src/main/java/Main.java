import api.GeneticAlgorithm;
import api.Individual;
import api.InputData;
import api.Parser;
import impl.GeneticAlgorithmImpl;
import impl.ParserImpl;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        if (args.length != 2) {
            System.out.println("Usage: 1st arg path to input file, 2nd arg path to output file");
            return;
        }
        Parser parser = new ParserImpl();
        InputData inputData = parser.parseInput(args[0]);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithmImpl();
        Individual solution = geneticAlgorithm.solveProblemInstance(inputData);
        parser.parseOutput(args[1], solution);
    }
}
