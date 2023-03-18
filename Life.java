package life;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Life {

    private final int width;
    private final int height;
    private char[][] current_state;
    private char[][] next_state;

    public Life(int height, char[][] current_state){
        this.width = height;
        this.height = height;
        this.current_state = current_state;
        this.next_state = new char[width][height];
        
    }

    private int living_neighbours(int i, int j) {
        int alive = 0;
    
        // Check the left, right, top and bottom neighbors
        alive += (current_state[(i - 1 + width) % width][j] == 'X') ? 1 : 0;
        alive += (current_state[(i + 1) % width][j] == 'X') ? 1 : 0;
        alive += (current_state[i][(j - 1 + height) % height] == 'X') ? 1 : 0;
        alive += (current_state[i][(j + 1) % height] == 'X') ? 1 : 0;
    
        // Check the diagonal neighbors
        alive += (current_state[(i - 1 + width) % width][(j - 1 + height) % height] == 'X') ? 1 : 0;
        alive += (current_state[(i + 1) % width][(j - 1 + height) % height] == 'X') ? 1 : 0;
        alive += (current_state[(i - 1 + width) % width][(j + 1) % height] == 'X') ? 1 : 0;
        alive += (current_state[(i + 1) % width][(j + 1) % height] == 'X') ? 1 : 0;

        return alive;
    }

    public void print_state() {
        for (int i = 0; i < current_state.length; i++) {
            for (int j = 0; j < current_state[0].length; j++)
                System.out.print(current_state[i][j]);
            System.out.println();
        }
    }

    
    public void start(){

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int step = 0; step < height; step++)
            tasks.add(new Life.LifeTask(step));
    
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) { }

        char[][] temp = current_state;
        current_state = next_state;
        next_state = temp;
    
        executor.shutdown();
    }

    public class LifeTask implements Callable<Void> {

        private int i;

        public LifeTask(int i) {
            this.i = i;
        }

        private synchronized void compute_next_step() {
            for (int j = 0; j < current_state[0].length; j++) {
                int alive = living_neighbours(i, j);
        
                if (current_state[i][j] == 'X') {
                    if (alive < 2 || alive > 3) 
                        next_state[i][j] = '_';
                    else
                        next_state[i][j] = 'X';
                } else {
                    if (alive == 3)
                        next_state[i][j] = 'X';
                    else
                        next_state[i][j] = '_';
                }
            }
        }

        @Override   
        public Void call() throws Exception {
            compute_next_step();
            return null;
        }
    }
}

