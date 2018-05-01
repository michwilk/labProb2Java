package impl;

import api.Individual;
import api.InputData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossingService {


    private final ScoreService scoreService;
    private final Random generator;

    public CrossingService() {
        this.generator = new Random();
        this.scoreService = new ScoreService();
    }

    public Individual performCross(Individual parent1, Individual parent2, InputData inputData) {

        int ordersCount = inputData.getOrders().size();
        int crossIdx = generator.nextInt(ordersCount);

        List<OrderSchedule> childOrderScheduleList = new ArrayList<>();
        for (int i = 0; i < crossIdx; i++) {
            childOrderScheduleList.add(parent1.getOrderSchedules().get(i));
        }

        for (int i = crossIdx; i < ordersCount; i++) {
            childOrderScheduleList.add(parent2.getOrderSchedules().get(i));
        }
        int score = scoreService.assessOrdersSchedule(childOrderScheduleList, inputData);
        return new Individual(childOrderScheduleList, score);
    }
}
