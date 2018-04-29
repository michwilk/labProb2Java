package api;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface Parser {
    void parseOutput(String arg, Individual solution);

    InputData parseInput(String path) throws IOException,
            ParseException, java.text.ParseException;
}





