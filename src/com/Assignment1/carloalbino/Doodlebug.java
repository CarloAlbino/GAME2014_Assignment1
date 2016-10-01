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
    public void Move(Grid world)
    {
        int currentPos = m_position;
        super.Move(world);
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

}
