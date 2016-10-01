package com.Assignment1.carloalbino;

import java.io.IOException;
import java.util.Random;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        int gridWidth = 20;
        int numberOfDoodlebugs = 10;
        int numberOfAnts = 110;

        // Instantiate grid.
        Grid grid = new Grid(gridWidth);

        // Randomly place doodlebugs on the grid.
        for(int i = 0; i < numberOfDoodlebugs; i++)
        {
            int newPos;
            do {
                Random rand = new Random();
                newPos = rand.nextInt(gridWidth * gridWidth); // Get a random position on the grid.
            }while(grid.grid[newPos] != null);  // If the position does not already have an Organism in the space.

            grid.AddOrganism(new Doodlebug(newPos), newPos);
        }

        // Randomly place ant on the grid.
        for(int i = 0; i < numberOfAnts; i++)
        {
            int newPos;
            do {
                Random rand = new Random();
                newPos = rand.nextInt(gridWidth * gridWidth); // Get a random position on the grid.
            }while(grid.grid[newPos] != null);  // If the position does not already have an Organism in the space.

            grid.AddOrganism(new Ant(newPos), newPos);
        }

        // If the console input is not the letter 'q' the simulation will run a time step.
        // Runs one time step before prompting the user for input.
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            grid.Step();
            System.out.println("Press enter to go to next time step.  Press q to quit simulation.");
            c = (char) br.read();
        }while(c != 'q');
    }
}
