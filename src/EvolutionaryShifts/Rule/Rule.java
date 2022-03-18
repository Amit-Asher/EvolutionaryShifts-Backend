package EvolutionaryShifts.Rule;

import EvolutionaryShifts.Arrangement.Arrangement;
import EvolutionaryShifts.Preference;

public abstract class Rule
{
    //protected String m_Config;
    protected String m_Name;

    //public Rule(Rule rule) {
     //   m_Config = rule.m_Config;
    //}

    public abstract void setM_Config(Preference preference);

    //public String getConfig(){return m_Config;}

    public abstract double Execute(Arrangement arrangeShifts);
}
