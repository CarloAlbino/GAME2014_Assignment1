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
    }

    @Override
    public void Move(Grid world) {
        int nextDir = GetRandomNumber(0, 3);
        if(world.IsEmptyCell(m_position, nextDir)) {
            m_hasMoved = true;
            switch (nextDir) {
                case 0:
                    //up
                    if (m_position - world.GetWidth() > 0) {
                        m_position -= world.GetWidth();
                    }
                    break;
                case 1:
                    //right
                    if (m_position % world.GetWidth() != 0) {
                        m_position++;
                    }
                    break;
                case 2:
                    //down
                    if (m_position + world.GetWidth() < (world.GetWidth() * world.GetWidth()) - 1) {
                        m_position += world.GetWidth();
                    }
                    break;
                case 3:
                    //left
                    if (m_position % world.GetWidth() != m_position) {
                        m_position--;
                    }
                    break;
            }
        }
    }

    @Override
    public void Breed() {

    }

}
