package com.Assignment1.carloalbino;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public class Ant extends Organism {

    public Ant(int pos)
    {
        m_letter = "O ";
        m_position = pos;
        m_hasMoved = false;
        m_stepsTaken = 0;
        m_maxSteps = 3;
        m_spawnNewOrganism = false;
    }

    @Override
    public void Breed(Grid world)
    {
        super.Breed(world);

        if(m_spawnNewOrganism)
        {
            // Spawn a new Ant.
            world.AddOrganism(new Ant(m_nextPos), m_nextPos);
            m_spawnNewOrganism = false;
        }
    }

}
