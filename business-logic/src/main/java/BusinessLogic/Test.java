package BusinessLogic;

import Model.Company;
import Model.Role;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        Company company = new Company("test"); // for testing
        /////////////////

        BusinessLogic businessLogic = BusinessLogic.getInstance();
        businessLogic.addEmployee("test", "e1", "1", new HashSet<Role>(){{
            add(new Role("test"));
        }});
    }
}
