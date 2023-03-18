package life;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] first_line = reader.readLine().split(" ");

        int width = Integer.parseInt(first_line[0]);
        int height = width;
        int number_of_steps = Integer.parseInt(first_line[1]);

        char[][] current_state = new char[width][height];

        for (int i = 0; i < width; i++) {
            String line = reader.readLine();
            for (int j = 0; j < height; j++) {
                current_state[i][j] = line.charAt(j);
            }
        }

        Life life = new Life(height, current_state);

        for(int i = 0; i < number_of_steps; ++i)
            life.start();

        life.print_state();
    }
    
}
