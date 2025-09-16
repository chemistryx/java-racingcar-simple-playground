package io.suhan.racingcar.domain;

import java.util.List;

public class RoundResult {
    private final List<Car> cars;

    private RoundResult(List<Car> cars) {
        this.cars = cars;
    }

    public static RoundResult of(List<Car> cars) {
        return new RoundResult(cars);
    }

    public List<Car> getCars() {
        return cars;
    }
}
