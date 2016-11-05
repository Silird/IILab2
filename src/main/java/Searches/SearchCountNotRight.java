package Searches;

import Util.MyTreeSet;
import Util.Position;
import Util.State;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SearchCountNotRight {
    private boolean step;
    private int iteration;
    private List<State> used = new ArrayList<State>();
    private MyTreeSet queue = new MyTreeSet();
    private State target; //, next;
    private int maxSize;

    public SearchCountNotRight(State target, boolean step) {
        this.target = target;
        this.step = step;
    }

    public SearchCountNotRight(State target) {
        this(target, false);
    }

    public void Start(State start) {
        //next = start;
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
            State currentState = queue.pop();
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
            //List<State> newStates = new ArrayList<State>();
            //newStates.add(currentState.Move(State.UP));
            //newStates.add(currentState.Move(State.RIGHT));
            //newStates.add(currentState.Move(State.DOWN));
            //newStates.add(currentState.Move(State.LEFT));
            Add(currentState.Move(State.UP));
            Add(currentState.Move(State.RIGHT));
            Add(currentState.Move(State.DOWN));
            Add(currentState.Move(State.LEFT));

            //next = CalculateNextState(newStates);

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
                Integer cost = 0;
                //if (state.getParent() != null) {
                //    cost += state.getParent().getCost();
                //}
                cost += CalculateEvaluationFunction(state);
                state.setCost(cost);

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

    private Integer CalculateEvaluationFunction(State state) {
        Integer result = null;
        if (state != null) {
            result = 0;
            int[][] stateVertex = state.getVertex();
            int[][] targetVertex = target.getVertex();
            for (int i = 0; i < State.SIZE; i++) {
                for (int j = 0; j < State.SIZE; j++) {
                    if (stateVertex[i][j] != targetVertex[i][j]) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private Position findPosition(int[][] vertex, int number) {

        for (int i = 0; i < State.SIZE; i++) {
            for (int j = 0; j < State.SIZE; j++) {
                if (number == vertex[i][j]) {
                    return new Position(j, i);
                }
            }
        }

        return null;
    }
}
