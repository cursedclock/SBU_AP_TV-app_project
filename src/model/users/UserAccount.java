package model.users;

public abstract class UserAccount {
    private String username;
    private int passwordHash;

    public UserAccount(String username, String password){
        this.username = username;
        this.passwordHash = password.hashCode();
    }

    public String getUsername() {
        return username;
    }

    public boolean verify(String input){
        return  passwordHash == input.hashCode();
    }

    @Override
    public String toString() {
        return username;
    }
}
