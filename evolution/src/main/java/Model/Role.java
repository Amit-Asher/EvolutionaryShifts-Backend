package Model;

public class Role
{
    public String name;

    public Role(String name)
    {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Role role) {
        return this.name.equals(role.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
