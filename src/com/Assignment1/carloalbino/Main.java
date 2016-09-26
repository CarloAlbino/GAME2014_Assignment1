package com.Assignment1.carloalbino;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int gridWidth = 20;
        Grid grid = new Grid(gridWidth);

        for(int i = 0; i < 100; i++)
        {
            int newPos;
            do {
                Random rand = new Random();
                newPos = rand.nextInt((gridWidth * gridWidth - 0) + 1) + 0;
            }while(grid.grid[newPos] != null);

            grid.AddOrganism(new Ant(), newPos);
        }

        grid.PrintGrid();

    }
}
