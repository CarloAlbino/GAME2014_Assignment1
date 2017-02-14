package com.Assignment1.carloalbino;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public class Grid {
    private int gridWidth;  // The width of the grid. The grid is a square so it is also the height.
    private int numOfCells; // The number of cells in the world.

    public Organism[] grid; // The array that stores the organism of the world.

    public Grid(int n)
    {
        gridWidth = n;                      // Set the dimensions of the grid.
        numOfCells = gridWidth * gridWidth; // Set the number of cells in the world.
        grid = new Organism[numOfCells];    // Initialize the array.
    }

    // Run a time step of the simulation.
    public void Step()
    {
        // First move all the Doodlebugs.
        for(int i = 0; i < grid.length; i++)
        {
            if (grid[i] != null && !grid[i].HasMoved())      // If the grid's cell has an organism and it hasn't already moved.
            {
                if (grid[i] instanceof Doodlebug) // If the organism is a Doodlebug..
                {
                    grid[i].Move(this, grid[i].GetRandomNumber(0, 3));          // Move the Organism (changes it's m_position)
                    // If grid is not null (in case a doodle bug starves after a move.
                    if (grid[i] != null) {
                        // ...and the other cell has a Doodlebug
                        if (grid[grid[i].GetPosition()] instanceof Doodlebug) {
                            // Revert Move
                            grid[i].RevertMove(i);
                        } else {
                            if (grid[i].GetPosition() != i)            // If the new position is not the same as the current position.
                            {
                                grid[grid[i].GetPosition()] = grid[i]; // Update the grid with the Organism's new position.
                                grid[i] = null;                        // Clear the old position on the grid.
                            }
                        }
                    }
                }
            }
        }

        // Then move all the Ants
        for(int i = 0; i < grid.length; i++)
        {
            if (grid[i] != null && !grid[i].HasMoved())      // If the grid's cell has an organism and it hasn't already moved.
            {
                if (grid[i] instanceof Ant) // If the organism is an Ant..
                {
                    grid[i].Move(this, grid[i].GetRandomNumber(0, 3));          // Move the Organism (changes it's m_position)
                    if (grid[grid[i].GetPosition()] == null)        // Check the new position of the Organism in the grid. If there is no other Organism already there.
                    {
                        if (grid[i].GetPosition() != i)            // If the new position is not the same as the current position.
                        {
                            grid[grid[i].GetPosition()] = grid[i]; // Update the grid with the Organism's new position.
                            grid[i] = null;                        // Clear the old position on the grid.
                        }
                    } else {
                        // If there is an Organism in cell that you want to move in, reset the position to the current cell.
                        grid[i].RevertMove(i);
                    }
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

    // Add an Organism to the grid at a position.
    public void AddOrganism(Organism o, int position)
    {
        grid[position] = o;
    }

    // Returns the width of the grid.
    public int GetWidth()
    {
        return gridWidth;
    }

    // Is the cell of the grid empty.
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

        if(nextCell > grid.length - 1)
        {
            return false;
        }

        if(grid[nextCell] != null)
        {
            return false;
        }

        return true;
    }

    // Prints the grid to the console.
    public void PrintGrid()
    {
        String outputGrid = "";
        for(int i = 0; i < gridWidth; i++)
        {
            for(int j = 0; j < gridWidth; j++)
            {
                if(grid[i * gridWidth + j] != null)
                    outputGrid += grid[i * gridWidth + j].m_letter;
                else
                    outputGrid += "- ";
            }
            outputGrid += "\n";
        }
        System.out.println(outputGrid);
    }

    // Reset the has moved variable of Organism after the grid has been updated.
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
