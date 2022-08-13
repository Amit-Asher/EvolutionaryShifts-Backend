package Model.Employee;

import Model.Role;

import java.util.*;

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
        m_ID = UUID.randomUUID().toString();
    }

    public Employee(Employee employee) {
        this.m_FullName = employee.m_FullName;
        this.m_PhoneNumber = employee.m_PhoneNumber;
        this.m_FitRoles = new HashSet<>();
        this.m_FitRoles.addAll(employee.m_FitRoles);
        this.m_ID = employee.m_ID;
    }

    public Set<Role> getFitRoles() {return m_FitRoles;}
    public String getFullName() {return m_FullName;}
    public String getPhoneNumber() {return m_PhoneNumber;}
//    public boolean IsTrainee() {return m_IsTrainee;}
    public String getID() { return m_ID; }

    public void setFitRoles(Set<Role> fitRoles) {this.m_FitRoles = fitRoles;}
    public void setFullName(String fullName) {this.m_FullName = fullName;}
    public void setPhoneNumber(String phoneNumber) {this.m_PhoneNumber = phoneNumber;}
//    public void setIsTrainee(boolean isTrainee) {this.m_IsTrainee = isTrainee;}


    public String getFirstName(){
        return m_FullName.split(" ", 2)[0];
    }

    public String getLastName(){
        return m_FullName.split(" ", 2)[1];
    }
}
