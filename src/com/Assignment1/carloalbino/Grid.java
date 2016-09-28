package com.Assignment1.carloalbino;

import java.io.IOException;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public class Grid {
    private int gridWidth;
    private int numOfCells;

    public Organism[] grid;

    public Grid(int n)
    {
        gridWidth = n;
        numOfCells = gridWidth * gridWidth;
        grid = new Organism[numOfCells];
    }

    public void Step()
    {
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i] != null && !grid[i].HasMoved())      // If the grid's cell has an organism and it hasn't already moved.
            {
                grid[i].Move(this);                            // Move the Organism (changes it's m_position)
                if(grid[grid[i].GetPosition()] == null)        // Check the new position of the Organism in the grid. If there is no other Organism already there.
                {
                    if (grid[i].GetPosition() != i)            // If the new position is not the same as the current position.
                    {
                        grid[grid[i].GetPosition()] = grid[i]; // Update the grid with the Organism's new position.
                        grid[i] = null;                        // Clear the old position on the grid.
                    }
                }
                else
                {
                    // If there is an Organism in cell that you want to move in, reset the position to the current cell.
                    grid[i].RevertMove(i);
                }
            }
        }

        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i] != null)
            {
                grid[i].Breed(this);
            }
        }

        ResetOrganisms();   // Reset all the Organism's m_hasMoved flag to false.
        PrintGrid();        // Prints out the grid with all the new changes.
    }

    public void AddOrganism(Organism o, int position)
    {
        grid[position] = o;
    }

    public int GetWidth()
    {
        return gridWidth;
    }

    public boolean IsEmptyCell(int currentCell, int dir)
    {
        if(dir > 3 || dir < 0)
            dir = 0;
        int nextCell = currentCell;
        switch (dir)
        {
            case 0:
                // Top
                if(currentCell - gridWidth < 0)
                    return false;
                else
                    nextCell -= gridWidth;
                break;
            case 1:
                // Left
                if(currentCell % gridWidth == 0 || currentCell - 1 > -1)
                    return false;
                else
                    nextCell--;
                break;
            case 2:
                // Bottom
                if(currentCell + gridWidth > (gridWidth * gridWidth) - 1)
                    return false;
                else
                    nextCell += gridWidth;
                break;
            case 3:
                // Right
                if(currentCell % gridWidth == currentCell - 1 || currentCell + 1 < gridWidth * gridWidth)
                    return false;
                else
                    nextCell++;
                break;
        }

        if(grid[nextCell] != null)
        {
            return false;
        }

        return true;
    }

    public void PrintGrid()
    {
        String outputGrid = "";
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                if(grid[i * 20 + j] != null)
                    outputGrid += grid[i * 20 + j].m_letter;
                else
                    outputGrid += "- ";
            }
            outputGrid += "\n";
        }
        System.out.println(outputGrid);
    }

    public void ResetOrganisms()
    {
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i] != null)
            {
                grid[i].m_hasMoved = false;
            }
        }
    }
}
