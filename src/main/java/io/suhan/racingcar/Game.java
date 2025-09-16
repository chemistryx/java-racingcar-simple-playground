package io.suhan.racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final CarRegistry carRegistry;
    private final int rounds;

    private Game(int rounds) {
        this.carRegistry = CarRegistry.of();
        this.rounds = rounds;
    }

    public static Game of() {
        return new Game(0);
    }

    public static Game of(int rounds) {
        return new Game(rounds);
    }

    public void execute() {
        System.out.println("\n실행 결과");
        for (int i = 0; i < this.rounds; i++) {
            carRegistry.moveCars();
            carRegistry.getRegisteredCars().forEach(this::printCurrentPosition);
            System.out.println();
        }

        List<Car> winners = getWinners();

        System.out.println(winners.stream().map(Car::getName).collect(Collectors.joining(", ")) + "가 최종 우승했습니다.");
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

    private void printCurrentPosition(Car car) {
        System.out.println(car.getName() + " : " + buildProgressBar(car.getPosition()));
    }

    private String buildProgressBar(int value) {
        return "-".repeat(Math.max(0, value));
    }
}
