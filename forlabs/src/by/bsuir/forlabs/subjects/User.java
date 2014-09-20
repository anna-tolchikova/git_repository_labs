package by.bsuir.forlabs.subjects;

public class User extends Entity{

    int id;
    String login;
    String password;
    int idRole;

    public User () {

    }

    public User(int id, String login, String password, int idRole) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.idRole = idRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }


}
