package EvolutionaryShifts;

import java.util.ArrayList;

public class Employee {
    protected String m_FullName;
    protected String m_PhoneNumber;
    protected ArrayList<Role> m_FitRoles;
    //ההעדפה שהעובד הזין על עצמו
    //אפשר להוסיף העדפה של האלגוריתם, המנוע
    //it can be also Map.Entry<Integer, ArrayList<Integer>> m_PreferDay2Levels
    protected boolean m_IsTrainee;
    //maybe add email

    public Employee(String fullName, String phoneNumber,
                    ArrayList<Role> fitRoles,
                    ArrayList<EmployeePreferences> preferences,
                    boolean isTrainee)
    {
        m_FullName = fullName;
        m_PhoneNumber = phoneNumber;
        m_FitRoles = fitRoles;
        m_IsTrainee = isTrainee;
    }

    public ArrayList<Role> getFitRoles() {return m_FitRoles;}
    public String getFullName() {return m_FullName;}
    public String getPhoneNumber() {return m_PhoneNumber;}
    public boolean IsTrainee() {return m_IsTrainee;}

    public void setFitRoles(ArrayList<Role> fitRoles) {this.m_FitRoles = fitRoles;}
    public void setFullName(String fullName) {this.m_FullName = fullName;}
    public void setPhoneNumber(String phoneNumber) {this.m_PhoneNumber = phoneNumber;}
    public void setIsTrainee(boolean isTrainee) {this.m_IsTrainee = isTrainee;}
}
