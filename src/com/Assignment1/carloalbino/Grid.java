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

    /*
    Live update the grid from the grid class.  Get random direction from Ant, update the grid here if possible,
    update position and num of steps in Ant.  Updating the grid live will prevent the disappearing Ants.
     */

    /*public void Step(){
        Grid tempGrid = new Grid(gridWidth);
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i] != null && !grid[i].m_hasMoved)
            {
                grid[i].Move(this);
                tempGrid.grid[grid[i].m_position] = grid[i];
            }
        }
        grid = tempGrid.grid;
        ResetOrganisms();
        PrintGrid();
    }*/

    public void Step()
    {
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i] != null && !grid[i].m_hasMoved)
            {
                grid[i].Move(this);
                if(grid[grid[i].m_position] == null) {
                    if (grid[i].m_position != i) {
                        grid[grid[i].m_position] = grid[i];
                        grid[i] = null;
                    }
                }else
                {
                    grid[i].m_position = i;
                }
            }
        }
        ResetOrganisms();
        PrintGrid();
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
        switch (dir)
        {
            case 0:
                //up
                if(currentCell - gridWidth < 0)
                    return false;
                else
                    currentCell -= gridWidth;
                break;
            case 1:
                //left
                if(currentCell % gridWidth == 0)
                    return false;
                else
                    currentCell--;
                break;
            case 2:
                //down
                if(currentCell + gridWidth > (gridWidth * gridWidth) - 1)
                    return false;
                else
                    currentCell += gridWidth;
                break;
            case 3:
                //right
                if(currentCell % gridWidth == currentCell)
                    return false;
                else
                    currentCell++;
                break;
        }

        if(grid[currentCell] != null)
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
