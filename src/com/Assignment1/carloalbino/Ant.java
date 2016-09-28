package com.Assignment1.carloalbino;

import java.util.Random;

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
    }

}
