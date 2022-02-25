package cz.upce.bank.eb.entity;

public class NewUserRequest {

    private String login;
    private String password;
    private UserRole role;
    private Integer registeredBy;
    private Integer clientId;

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Integer registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
