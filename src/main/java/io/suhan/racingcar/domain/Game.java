package io.suhan.racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int GAME_ROUNDS_MINIMUM = 1;

    private final CarManager carManager;
    private final int rounds;

    private Game(int rounds) {
        this.carManager = CarManager.of();
        this.rounds = rounds;
    }

    public static Game of() {
        return Game.of(1);
    }

    public static Game of(int rounds) {
        if (rounds < GAME_ROUNDS_MINIMUM) {
            throw new IllegalArgumentException("시도 횟수는 " + GAME_ROUNDS_MINIMUM + " 이상이어야 합니다.");
        }

        return new Game(rounds);
    }

    public List<RoundResult> execute() {
        List<RoundResult> results = new ArrayList<>();

        for (int i = 0; i < rounds; i++) {
            carManager.moveCars();
            RoundResult result = RoundResult.of(carManager.getRegisteredCars());
            results.add(result);
        }

        return results;
    }

    public List<Car> getWinners() {
        return carManager.getCarsWithBestPosition();
    }

    public CarManager getCarManager() {
        return carManager;
    }
}
