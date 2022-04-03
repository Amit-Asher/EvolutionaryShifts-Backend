package EvolutionaryShifts;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Employee {
    protected String m_FullName;
    protected String m_PhoneNumber;
    protected Set<Role> m_FitRoles;
    protected String m_ID;

    //ההעדפה שהעובד הזין על עצמו
    //אפשר להוסיף העדפה של האלגוריתם, המנוע
    //it can be also Map.Entry<Integer, ArrayList<Integer>> m_PreferDay2Levels
//    protected boolean m_IsTrainee;
    // todo: list of positions in the company

    //maybe add email

    public Employee(String fullName, String phoneNumber, Set<Role> fitRoles)
    {
        m_FullName = fullName;
        m_PhoneNumber = phoneNumber;
        m_FitRoles = fitRoles;
//        m_IsTrainee = isTrainee;
        m_ID =  UUID.randomUUID().toString();
    }

    public Set<Role> getFitRoles() {return m_FitRoles;}
    public String getFullName() {return m_FullName;}
    public String getPhoneNumber() {return m_PhoneNumber;}
//    public boolean IsTrainee() {return m_IsTrainee;}
    public String getID() {return m_ID;}

    public void setFitRoles(Set<Role> fitRoles) {this.m_FitRoles = fitRoles;}
    public void setFullName(String fullName) {this.m_FullName = fullName;}
    public void setPhoneNumber(String phoneNumber) {this.m_PhoneNumber = phoneNumber;}
//    public void setIsTrainee(boolean isTrainee) {this.m_IsTrainee = isTrainee;}
}
