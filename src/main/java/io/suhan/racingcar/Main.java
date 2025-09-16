package io.suhan.racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String nameInput = scanner.next();


        System.out.println("시도할 회수는 몇회인가요?");
        int count = scanner.nextInt();

        List<String> names = extractCarNames(nameInput);
        Game game = Game.of(count);

        for (String name : names) {
            Car car = Car.of(name);
            game.getCarRegistry().register(car);
        }

        game.execute();
    }

    private static List<String> extractCarNames(String input) {
        return Arrays.stream(input.split(",")).toList();
    }
}
