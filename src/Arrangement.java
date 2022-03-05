package EvolutionaryShifts;

import java.util.ArrayList;

public class Arrangement
{
    protected ArrayList<Shift> m_Shifts;

    public ArrayList<Shift> getShifts() {
        return m_Shifts;
    }

    /* public Arrangement(ArrayList<Map.Entry<Integer, ArrayList<Integer>>> daysOfWork2Levels, ArrayList<ArrayList<Shift>> arrangement)
    {
        m_DaysOfWork2Levels = daysOfWork2Levels;
        m_Arrangement = arrangement;
    }

    public Arrangement(ArrayList<Map.Entry<Integer, ArrayList<Integer>>> daysOfWork2Levels)
    {
        m_DaysOfWork2Levels = daysOfWork2Levels;
        for (int i = 0;i < m_DaysOfWork2Levels.size();i++)
        {
            m_Arrangement.add(new ArrayList<>());
        }
    }

    public ArrayList<Map.Entry<Integer, ArrayList<Integer>>> getDaysOfWork2Levels() {return m_DaysOfWork2Levels;}
    public ArrayList<Shift> getArrangement(int day)
    {
        int index = 0;
        while(true)
        {
          if(m_DaysOfWork2Levels.get(index).getKey() == day)
              break;
          else
              index++;
        }

        return m_Arrangement.get(index);
    }
    public ArrayList<ArrayList<Shift>> getArrangement() {return m_Arrangement;}
    public void setArrangement(int day, ArrayList<Shift> arrayShifts)
    {
        int index = 0;
        while(true)
        {
            if(m_DaysOfWork2Levels.get(index).getKey() == day)
                break;
            else
                index++;
        }

        m_Arrangement.set(index, arrayShifts);
    }
    public void setArrangement(ArrayList<ArrayList<Shift>> arrangement) {this.m_Arrangement = arrangement;}
    public void setDaysOfWork(ArrayList<Map.Entry<Integer, ArrayList<Integer>>> daysOfWork2Levels) {this.m_DaysOfWork2Levels = daysOfWork2Levels;}

    @Override
    public String toString() {
        return "ArrangeShifts{" +
                "m_DaysOfWork=" + m_DaysOfWork2Levels +
                ", m_Arrangement=" + m_Arrangement +
                '}';
    }*/
}
