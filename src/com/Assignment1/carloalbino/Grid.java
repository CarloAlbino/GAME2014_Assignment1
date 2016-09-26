package com.Assignment1.carloalbino;

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
                break;
            case 1:
                //right
                if(currentCell % gridWidth == 0)
                    return false;
                break;
            case 2:
                //down
                if(currentCell + gridWidth > (gridWidth * gridWidth) - 1)
                    return false;
                break;
            case 3:
                //left
                if(currentCell % gridWidth == currentCell)
                    return false;
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
}
