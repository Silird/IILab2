import Searches.*;
import Util.State;

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


        //new SearchManhattan(target, false).Start(state);
        new SearchCountNotRight(target, false).Start(state);
    }
}
