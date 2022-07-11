package Model;

public class Role
{
    public String m_Name;

    public Role(String name)
    {
        m_Name = name;
    }

    public String getName() {
        return m_Name;
    }

    public boolean equals(Role role) {
        return this.m_Name.equals(role.m_Name);
    }

    @Override
    public String toString() {
        return m_Name;
    }
}
