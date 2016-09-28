package com.Assignment1.carloalbino;

/**
 * Created by Carlo Albino on 2016-09-26.
 */
public class Doodlebug extends Organism {

    private boolean m_hasEaten;

    public Doodlebug(int pos)
    {
        m_letter = "X ";
        m_position = pos;
        m_hasMoved = false;
        m_stepsTaken = 0;
        m_maxSteps = 8;
        m_hasEaten = false;
    }

    @Override
    public void Move(Grid world)
    {
        super.Move(world);

        // Add stuff about eating ants
    }

    public void Starve()
    {

    }

}
