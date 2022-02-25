package cz.upce.bank.eb.entity;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.*;
import java.sql.Clob;
import java.util.Collection;

public class User implements UserDetails, Serializable {

    private int userId;
    private String login;
    private String password;
    private UserRole role;
    private int registeredById;
    private int clientId;
    private String registeredByLogin;
    private String active;
    private String image;

    public static RowMapper<User> getUserMapper(){
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("ID"));
            user.setLogin(rs.getString("LOGIN"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setRole(UserRole.valueOf(rs.getString("ROLE")));
            user.setRegisteredById(rs.getInt("REGISTERED_BY"));
            user.setClientId(rs.getInt("CLIENT_ID"));
            user.setActive(String.valueOf(rs.getInt("AKTIVNI")));
            return user;
        };
    }

    public static RowMapper<User> getUserViewMapper(){
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("ID"));
            user.setLogin(rs.getString("LOGIN"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setRole(UserRole.valueOf(rs.getString("ROLE")));
            user.setRegisteredById(rs.getInt("REGISTERED_BY"));
            user.setClientId(rs.getInt("CLIENT_ID"));
            user.setRegisteredByLogin(rs.getString("REGISTER_BY_LOGIN"));
            user.setActive(String.valueOf(rs.getInt("AKTIVNI")));
            Clob image = rs.getClob("OBRAZEK");
            if (image != null){
                user.setImage(image.getSubString(1, (int) image.length()));
            }
            return user;
        };
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getRegisteredById() {
        return registeredById;
    }

    public void setRegisteredById(int registeredById) {
        this.registeredById = registeredById;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getRegisteredByLogin() {
        return registeredByLogin;
    }

    public void setRegisteredByLogin(String registeredByLogin) {
        this.registeredByLogin = registeredByLogin;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
