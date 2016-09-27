package com.Assignment1.carloalbino;

import java.util.Random;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public abstract class Organism {
    public String m_letter = "-";
    public int m_position;
    public boolean m_hasMoved;

    public abstract int Move(Grid grid);
    public abstract void Breed();
    public int GetRandomNumber(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}

