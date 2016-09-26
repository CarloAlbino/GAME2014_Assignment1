package com.Assignment1.carloalbino;

import java.util.Random;

/**
 * Created by Carlo Albino on 2016-09-20.
 */
public class Ant extends Organism {
    public Ant()
    {
        m_letter = "O ";
    }

    @Override
    public void Move(Grid grid) {
        int nextDir = GetRandomNumber(0, 3);

        if(grid.IsEmptyCell(m_position, nextDir)) {
            switch (nextDir) {
                case 0:
                    //up
                    if (m_position - grid.GetWidth() < 0)
                        m_position += grid.GetWidth();
                    break;
                case 1:
                    //right
                    if (m_position % grid.GetWidth() == 0)
                        m_position++;
                    break;
                case 2:
                    //down
                    if (m_position + grid.GetWidth() > (grid.GetWidth() * grid.GetWidth()) - 1)
                        m_position += grid.GetWidth();
                    break;
                case 3:
                    //left
                    if (m_position % grid.GetWidth() == m_position)
                        m_position--;
                    break;
            }
        }
    }

    @Override
    public void Breed() {

    }

}
