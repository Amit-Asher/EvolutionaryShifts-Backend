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
    public boolean equals(Object obj) {
        Role r = (Role) obj;
        return equals(r);
    }

    @Override
    public String toString() {
        return name;
    }
}
