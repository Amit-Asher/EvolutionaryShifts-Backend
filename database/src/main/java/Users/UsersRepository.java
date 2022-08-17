package Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsersRepository {

    private List<Credentials> mockUserDb = new ArrayList<>();

    private static UsersRepository instance; // singleton!

    private UsersRepository() {
    }

    public static UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();
        }
        return instance;
    }

    public boolean isValidCredentials(String username, String password) {
        return mockUserDb.stream().anyMatch(credentials ->
                Objects.equals(credentials.getUsername(), username) &&
                        Objects.equals(credentials.getPassword(), password));
    }

    public boolean changePassword(String username, String newPassword)
    {
        for (Credentials credential:mockUserDb) {
            if(credential.getUsername().equals(username))
            {
                credential.setPassword(newPassword);
                return true;
            }
        }

        return  false;
    }

    public void register(String username, String password) {
        boolean usernameAlreadyExist = mockUserDb.stream().anyMatch(credentials ->
                Objects.equals(credentials.getUsername(), username) &&
                        Objects.equals(credentials.getPassword(), password));

        if (usernameAlreadyExist) {
            throw new RuntimeException("username is already exist");
        }

        mockUserDb.add(new Credentials(username, password));
    }

    public void deleteUser(String username) {
        mockUserDb = mockUserDb.stream()
                .filter(credentials -> credentials.getUsername() == username) // do not change to intelij suggestion
                .collect(Collectors.toList());
    }
}
