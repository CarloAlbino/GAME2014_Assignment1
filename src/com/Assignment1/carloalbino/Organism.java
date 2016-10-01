package com.Assignment1.carloalbino;

import java.util.Random;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public abstract class Organism {
    public String m_letter = "- ";      // The string that is displayed when the grid is printed.
    public int m_position;              // The current position of the organism.
    public boolean m_hasMoved;          // Has the organism moved in the current time step.
    public int m_stepsTaken;            // How many steps has the organism taken since it last multiplied.
    public int m_maxSteps;              // The maximum amount of steps before the organism will breed.
    public boolean m_spawnNewOrganism;  // Can the organism spawn a new organism.
    public int m_nextPos;               // The position that the new organism will spawn.

    // Moves the organism in a random direction.
    public void Move(Grid world, int nextPos)
    {
        int nextDir = nextPos;//GetRandomNumber(0, 3);
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

    // Reverts the move that was taken.
    public void RevertMove(int i)
    {
        m_position = i;
        m_stepsTaken--;
        if(m_stepsTaken < 0)
        {
            m_stepsTaken = 0;
        }
    }

    // Returns the position of the organism.
    public int GetPosition()
    {
        return m_position;
    }

    // Returns true if the organism has made a move this time step.
    public boolean HasMoved()
    {
        return  m_hasMoved;
    }

    // Spawns an organism in a random adjacent cell if the cell is empty.
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
                // Signal to spawn a new instance of an organism.
                m_spawnNewOrganism = true;
                m_nextPos = pos;
            }
            m_stepsTaken = 0;
        }
    }

    // Returns a random number between the min and max.
    public int GetRandomNumber(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}

