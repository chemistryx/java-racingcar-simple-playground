package io.suhan.racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int GAME_ROUNDS_MINIMUM = 1;

    private final CarRegistry carRegistry;
    private final int rounds;

    private Game(int rounds) {
        this.carRegistry = CarRegistry.of();
        this.rounds = rounds;
    }

    public static Game of() {
        return Game.of(1);
    }

    public static Game of(int rounds) {
        if (rounds < GAME_ROUNDS_MINIMUM) {
            throw new IllegalArgumentException("시도 회수는 " + GAME_ROUNDS_MINIMUM + " 이상이어야 합니다.");
        }

        return new Game(rounds);
    }

    public List<RoundResult> execute() {
        List<RoundResult> results = new ArrayList<>();

        for (int i = 0; i < rounds; i++) {
            carRegistry.moveCars();
            results.add(RoundResult.of(carRegistry.getRegisteredCars()));
        }

        return results;
    }

    public List<Car> getWinners() {
        int bestPosition = carRegistry.getBestPosition();

        return carRegistry.getRegisteredCars()
                .stream()
                .filter((car) -> car.getPosition() == bestPosition)
                .toList();
    }

    public CarRegistry getCarRegistry() {
        return carRegistry;
    }

    public void registerCars(List<String> names) {
        for (String name : names) {
            Car car = Car.of(name);
            carRegistry.register(car);
        }
    }
}
