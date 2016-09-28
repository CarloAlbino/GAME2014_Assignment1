package com.Assignment1.carloalbino;

import java.io.IOException;
import java.util.Random;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int gridWidth = 20;
        // Instantiate grid.
        Grid grid = new Grid(gridWidth);

        // Randomly place 5 doodlebugs on the grid.
        for(int i = 0; i < 5; i++)
        {
            int newPos;
            do {
                Random rand = new Random();
                newPos = rand.nextInt(gridWidth * gridWidth); // Get a random position on the grid.
            }while(grid.grid[newPos] != null);  // If the position does not already have an Organism in the space.

            grid.AddOrganism(new Doodlebug(newPos), newPos);
        }

        // Randomly place 100 ant on the grid.
        for(int i = 0; i < 100; i++)
        {
            int newPos;
            do {
                Random rand = new Random();
                newPos = rand.nextInt(gridWidth * gridWidth); // Get a random position on the grid.
            }while(grid.grid[newPos] != null);  // If the position does not already have an Organism in the space.

            grid.AddOrganism(new Ant(newPos), newPos);
        }

        grid.PrintGrid();
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            c = (char) br.read();
            grid.Step();
        }while(c != 'q');
    }
}
