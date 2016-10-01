package com.Assignment1.carloalbino;

/**
 * Created by Carlo Albino on 2016-09-26.
 */
public class Doodlebug extends Organism {

    private int m_starveCount;  // The number of steps taken since the Doodlebug has last eaten.
    private int m_stepsToStarve;// The number of steps the Doodlebug can take before it starves.

    public Doodlebug(int pos)
    {
        m_letter = "X ";
        m_position = pos;
        m_hasMoved = false;
        m_stepsTaken = 0;
        m_maxSteps = 5;         // Changed steps to breed so that the simulation is a little more balanced.
        m_spawnNewOrganism = false;
        m_starveCount = 0;
        m_stepsToStarve = 4;    // Changed steps to starve so that the simulation is a little more balanced.
    }

    @Override
    public void Move(Grid world, int nextPos)
    {
        int currentPos = m_position;

        super.Move(world, CheckSurroundingCellsForAnts(world, nextPos)); // Check surroundings looks for the nearest Ant
        m_starveCount++;
        if(world.grid[m_position] instanceof Ant)
        {
            // Eat the Ant.
            m_starveCount = 0;
        }

        Starve(world, currentPos);
    }

    @Override
    public void Breed(Grid world)
    {
        super.Breed(world);

        if(m_spawnNewOrganism)
        {
            // Spawn a Doodlebug.
            world.AddOrganism(new Doodlebug(m_nextPos), m_nextPos);
            m_spawnNewOrganism = false;
        }
    }

    // Removes the Doodlebug from the grid if it has not eaten for a number of steps.
    private void Starve(Grid world, int currentPos)
    {
        if(m_starveCount > m_stepsToStarve)
        {
            world.grid[currentPos] = null;
        }
    }

    // Returns a next cell with an Ant in it if it exists, else it passes through the random number.
    private int CheckSurroundingCellsForAnts(Grid world, int randPos)
    {
        int adjacentCell = m_position;
        if(world.IsEmptyCell(adjacentCell, 3) && world.grid[adjacentCell + 1] instanceof Ant)
        {
            return  adjacentCell++;
        }else if(world.IsEmptyCell(adjacentCell, 1) && world.grid[adjacentCell - 1] instanceof Ant)
        {
            return adjacentCell--;
        }else if(world.IsEmptyCell(adjacentCell, 2) && world.grid[adjacentCell + world.GetWidth()] instanceof Ant)
        {
            return adjacentCell + world.GetWidth();
        }
        else if(world.IsEmptyCell(adjacentCell, 0) && world.grid[adjacentCell - world.GetWidth()] instanceof Ant)
        {
            return adjacentCell - world.GetWidth();
        }else
        {
            return randPos;
        }
    }
}
