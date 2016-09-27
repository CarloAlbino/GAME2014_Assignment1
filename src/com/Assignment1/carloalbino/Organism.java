package com.Assignment1.carloalbino;

import java.util.Random;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public abstract class Organism {
    public String m_letter = "-";
    public int m_position;
    public boolean m_hasMoved;
    public int m_stepsTaken;
    public int m_maxSteps;

    public abstract void Move(Grid world);
    public abstract void RevertMove(int i);
    public abstract int GetPosition();
    public abstract boolean HasMoved();
    public abstract void Breed(Grid world);
    public int GetRandomNumber(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}

