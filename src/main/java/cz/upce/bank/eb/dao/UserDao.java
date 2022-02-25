package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.ImageUploadRequest;
import cz.upce.bank.eb.entity.NewPasswordRequest;
import cz.upce.bank.eb.entity.NewUserRequest;
import cz.upce.bank.eb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

/**
 * Třída má na starosti přístup k databázi pro UserServis
 */

@Service
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private PasswordEncoder passwordEncoder;

    public UserDao(JdbcTemplate jdbcTemplate,@Lazy PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Update pro změnu obrázku
     * @param request
     */

    public void changeImage(ImageUploadRequest request){
        String query = "UPDATE UZIVATELE SET OBRAZEK = ? " +
                "WHERE ID = ?";

        jdbcTemplate.update(query, new Object[]{request.getImage(), request.getUserId()});
    }

    /**
     * Update pro změnu hesla uživatele
     * @param request
     */

    public void changePassword(NewPasswordRequest request){
        String query = "UPDATE UZIVATELE SET PASSWORD = ? " +
                "WHERE ID = ?";

        jdbcTemplate.update(query, new Object[]{request.getPassword(), request.getId()});
    }

    /**
     * Update pro reset hesla uživatele
     * @param user
     */

    public void resetPassword(User user){
        String query = "UPDATE UZIVATELE SET PASSWORD = ? " +
                "WHERE ID = ?";

        String password = passwordEncoder.encode("secret");

        jdbcTemplate.update(query, new Object[]{password, user.getUserId()});
    }

    /**
     * Update pro blokování uživatele
     * @param user
     */

    public void blockUser(User user){
        String query = "UPDATE UZIVATELE SET AKTIVNI = 0 " +
                "WHERE ID = ?";

        jdbcTemplate.update(query, new Object[]{user.getUserId()});
    }

    /**
     * Update pro odblokování uživatele
     * @param user
     */

    public void unblockUser(User user){
        String query = "UPDATE UZIVATELE SET AKTIVNI = 1 " +
                "WHERE ID = ?";

        jdbcTemplate.update(query, new Object[]{user.getUserId()});
    }

    /**
     * Select pro výbér uživatele podle loginu
     * @param login
     */

    public User getUserByLogin(String login){
        String query = "SELECT * FROM UZIVATELE WHERE LOGIN like ?";
        List<User> foundUsers  = jdbcTemplate.query(query, new Object[]{login}, User.getUserMapper());
        if (foundUsers.size() != 1){
            return null;
        }
        return foundUsers.get(0);
    }

    /**
     * Select pro zpřístupnění uživatele
     * @param user
     */

    public User getUserById(Integer id){
        String query = "SELECT * FROM UDAJE_O_UZIVATELICH WHERE ID = ?";
        List<User> foundUsers  = jdbcTemplate.query(query, new Object[]{id}, User.getUserViewMapper());
        if (foundUsers.size() != 1){
            return null;
        }
        return foundUsers.get(0);
    }

    /**
     * Insert pro vytvoření nového admina
     * @param request
     */

    public void createAdmin(NewUserRequest request){
        String query = "INSERT INTO UZIVATELE (LOGIN, PASSWORD, ROLE, REGISTERED_BY, CLIENT_ID) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, new String[] { "ID" });
            ps.setString(1, request.getLogin());
            ps.setString(2, request.getPassword());
            ps.setString(3, request.getRole().toString());
            ps.setInt(4, request.getRegisteredBy());
            if (request.getClientId() != null){
                ps.setInt(5, request.getClientId());
            }else {
                ps.setNull(5, Types.INTEGER);
            }
            return ps;
        });
    }

}
