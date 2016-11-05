import java.io.IOException;
import java.util.*;

public class SearchWidth {
    private boolean step;
    private int iteration;
    private List<State> used = new ArrayList<State>();
    private Queue<State> queue = new LinkedList<State>();
    private State target;
    private int maxSize;

    public SearchWidth(State target, boolean step) {
        this.target = target;
        this.step = step;
    }

    public SearchWidth(State target) {
        this(target, false);
    }

    public void Start(State start) {
        queue.add(start);
        iteration = 0;
        boolean cont = true;
        while (cont) {
            cont = Open();
        }
        System.out.println("Решения не найдено");
        System.out.println("\nЁмкостная сложность: " + maxSize);
    }

    private boolean Open() {
        if (!queue.isEmpty()) {
            State currentState = queue.poll();
            used.add(currentState);
            iteration++;
            if (iteration%1000 == 0) {
                System.out.println("-------------------------Итерация № " + iteration + "-------------------------------");
            }
            if (step) {
                System.out.println("Раскрывается вершина:\n");
                currentState.Print();

                System.out.println("\nВновь открытые:\n");
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
            if (!used.contains(state) && !queue.contains(state)) {
                queue.add(state);
                if (step) System.out.println("Добавляется в очередь:");
            }
            else {
                if (step) System.out.println("Не добавляется в очередь:");
            }
            if (step) state.Print();
        }
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
        path.add(state);
        State tmp = state.getParent();
        while (tmp != null) {
            path.add(tmp);
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
