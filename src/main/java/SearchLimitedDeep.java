import java.io.IOException;
import java.util.*;

public class SearchLimitedDeep {
    private boolean step;
    private int iteration;
    private int limit;
    private List<State> used = new ArrayList<State>();
    private Stack<State> queue = new Stack<State>();
    private State target;
    private int maxSize = 0;

    public SearchLimitedDeep(State target, boolean step, int limit) {
        this.target = target;
        this.step = step;
        this.limit = limit;
    }

    public SearchLimitedDeep(State target, int limit) {
        this(target, false, limit);
    }

    public void Start(State start) {
        queue.push(start);
        iteration = 0;
        boolean cont = true;
        while (cont) {
            cont = Open();
        }
        System.out.println("Решения не найдено за " + iteration + " итераций");
        System.out.println("\nЁмкостная сложность: " + maxSize);
    }

    private boolean Open() {
        if (!queue.empty()) {
            State currentState = queue.pop();
            used.add(currentState);
            iteration++;
            if (iteration%1000 == 0) {
                System.out.println("-------------------------Итерация № " + iteration + "-------------------------------");
            }
            if (step) {
                System.out.println("Раскрывается вершина:\n");
                currentState.Print();

                if (currentState.getDeep() > limit) {
                    System.out.println("\nВновь открытые:\n");
                }
                else {
                    System.out.println("\nВершина не раскрывается, достигнут предел\n");
                }
            }

            Add(currentState.Move(State.UP));
            Add(currentState.Move(State.RIGHT));
            Add(currentState.Move(State.DOWN));
            Add(currentState.Move(State.LEFT));

            PrintQueue();

            if (step) {
                System.out.println("\nСледующая для открытия:\n");
                queue.peek().Print();

                try {
                    System.out.println("\nНажмите Enter для продолжения");
                    System.in.read();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (maxSize < (queue.size() + used.size())) {
                maxSize = queue.size() + used.size();
            }
            return true;
        }
        return false;
    }

    private void Add(State state) {
        if (state != null) {
            if (state.equals(target)) {
                End(state);
            }
            if ((state.getDeep() <= limit) && !isListContain(used, state) && !isListContain(queue, state)) {
                queue.push(state);
                if (step) System.out.println("Добавляется в очередь:");
            }
            else {
                if (step) System.out.println("Не добавляется в очередь:");
            }
            if (step) state.Print();
        }
    }

    /**
     * Проверка на содержание в данном листе данного состояния с меньшей глубиной
     * Если в данном листе есть состояние, но с большей глубиной, то оно удаляется
     * @param list - лист
     * @param state - состояние
     * @return - имеется ли данное состояние в листе
     */
    private boolean isListContain(List<State> list, State state) {
        boolean result = false;
        if (list.contains(state)) {
            int index = list.indexOf(state);
            State tmp = list.get(index);
            if (tmp.getDeep() < state.getDeep()) {
                result = true;
            }
            else {
                used.remove(index);
            }
        }

        return result;
    }

    private void PrintQueue() {
        if (step) {
            System.out.println("\nВершины, ожидающие открытия:\n");
            for (State state : queue) {
                state.Print();
                System.out.println();
            }
        }
    }

    private void End(State state) {
        Stack<State> path = new Stack<State>();
        System.out.println("Найдено решение:\n");
        state.Print();
        path.push(state);
        State tmp = state.getParent();
        while (tmp != null) {
            path.push(tmp);
            tmp = tmp.getParent();
        }
        System.out.println("\nНайдено решение в " + state.getDeep() + " шаг/а/ов");
        System.out.println("\nЁмкостная сложность: " + maxSize);
        System.out.println("За " + iteration + " итераций");
        System.out.println("Путь:\n");

        while (!path.isEmpty()) {
            tmp = path.pop();
            tmp.Print();

            System.out.println();
        }

        try {
            System.out.println("Нажмите Enter для выхода");
            System.in.read();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
