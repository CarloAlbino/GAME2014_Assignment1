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

    public void Move(Grid world)
    {
        int nextDir = GetRandomNumber(0, 3);
        m_hasMoved = true;
        switch (nextDir) {
            case 0:
                // Move up
                if (m_position - world.GetWidth() > 0) {
                    m_position -= world.GetWidth();
                }
                break;
            case 1:
                // Move right
                if (m_position % world.GetWidth() != 0 && m_position + 1 < (world.GetWidth() * world.GetWidth()) - 1) {
                    m_position++;
                }
                break;
            case 2:
                // Move down
                if (m_position + world.GetWidth() < (world.GetWidth() * world.GetWidth()) - 1) {
                    m_position += world.GetWidth();
                }
                break;
            case 3:
                // Move left
                if (m_position % world.GetWidth() != m_position && m_position - 1 > 0) {
                    m_position--;
                }
                break;
        }
        m_stepsTaken++;
        if(m_stepsTaken > m_maxSteps)
        {
            m_stepsTaken = m_maxSteps;
        }
    }

    public void RevertMove(int i)
    {
        m_position = i;
        m_stepsTaken--;
        if(m_stepsTaken < 0)
        {
            m_stepsTaken = 0;
        }
    }

    public int GetPosition()
    {
        return m_position;
    }

    public boolean HasMoved()
    {
        return  m_hasMoved;
    }

    public void Breed(Grid world)
    {
        if(m_stepsTaken >= m_maxSteps)
        {
            int breedDir = GetRandomNumber(0, 3);
            if(world.IsEmptyCell(m_position, breedDir))
            {
                int pos = m_position;
                switch (breedDir) {
                    case 0:
                        // Move up
                        if (pos - world.GetWidth() > -1) {
                            pos -= world.GetWidth();
                        }
                        break;
                    case 1:
                        // Move right
                        if (pos % world.GetWidth() != pos - 1 && pos + 1 < world.GetWidth() * world.GetWidth()) {
                            pos++;
                        }
                        break;
                    case 2:
                        // Move down
                        if (pos + world.GetWidth() < world.GetWidth() * world.GetWidth()) {
                            pos += world.GetWidth();
                        }
                        break;
                    case 3:
                        // Move left
                        if (pos % world.GetWidth() != 0 && pos - 1 > -1) {
                            pos--;
                        }
                        break;
                }
                world.AddOrganism(new Ant(pos), pos);
            }
            m_stepsTaken = 0;
        }
    }

    public int GetRandomNumber(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}

