import Searches.*;
import Util.State;

import java.text.ParseException;

public class lab2 {
    // TODO Эвристический поиск с использованием: число фишек не на своих местах и манхеттенское расстояние
    public static void main(String[] args) {
        // Состояния для изи тестов
        //State state = new State(1, 2, 3, 4, 5, 6, 7, 8, State.EMPTY);
        //State target = state.Move(State.UP).Move(State.LEFT).Move(State.LEFT).Move(State.UP);

        // Целевые состояния для серьёзных тестов

        State state = new State(5, 8, 3, 4, State.EMPTY, 2, 7, 6, 1);
        //State target = new State(4, 3, State.EMPTY, 6, 5, 2, 7, 1, 8);   // Лайт оптимальное за 14


        State target = new State(1, 2, 3, 4, 5, 6, 7, 8, State.EMPTY);  // Хард оптимальное за 22

        if (args.length < 2) {
            System.out.println("Недостаточно аргументов");
            System.out.println("Первый аргумент boolean (пошагово или нет)\n" +
                    "Второй аргумент boolean (true - число фишек не на своих местах, false - манхеттенские расстояния)");
        }
        else {
            boolean step = Boolean.parseBoolean(args[0]);
            boolean countNotRight = Boolean.parseBoolean(args[1]);
            if (countNotRight) {
                new SearchCountNotRight(target, step).Start(state);
            }
            else {
                new SearchManhattan(target, step).Start(state);
            }
        }
    }
}
