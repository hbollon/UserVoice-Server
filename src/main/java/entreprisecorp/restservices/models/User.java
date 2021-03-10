package entreprisecorp.restservices.models;

import java.util.Objects;

public class User {
    private int dbId;
    private String username;
    private String password;
    private String email;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(int dbId, String username, String email) {
        this.dbId = dbId;
        this.username = username;
        this.email = email;
    }

    public User(int dbId, String username, String password, String email) {
        this.dbId = dbId;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }



    public int getDbId() {
        return this.dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return dbId == user.dbId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbId, username, password, email);
    }

    @Override
    public String toString() {
        return "{" +
            " dbId='" + dbId + "'" +
            ", username='" + username + "'" +
            ", password='" + password + "'" +
            ", email='" + email + "'" +
            "}";
    }

}
