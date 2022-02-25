package cz.upce.bank.eb.service;

import cz.upce.bank.eb.controller.AccessProhibitedException;
import cz.upce.bank.eb.dao.UserDao;
import cz.upce.bank.eb.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

/**
 * Třída, která se stárá o provádění logiky spojenou s uživateli
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Změna obrázku
     * @param request
     */

    @Transactional(rollbackFor = Exception.class)
    public void changeImage(ImageUploadRequest request){
        userDao.changeImage(request);
    }

    /**
     * Změna hesla
     * @param request
     */

    public void changePassword(NewPasswordRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || (user.getRole() == UserRole.USER && user.getUserId() != request.getId())){
            throw new AccessProhibitedException("Access denied");
        }

        userDao.changePassword(request);
    }

    /**
     * Zpřístupněni uživatele podle loginu
     * @param login
     * @return
     */

    public User getUserByLogin(String login){
        return userDao.getUserByLogin(login);
    }

    public User getUserDetailsByLogin(String username){
        User user = getUserByLogin(username);

        if (user == null){
            throw new UsernameNotFoundException(format("User: %s, not found", username));
        }

        return user;
    }

    /**
     * Reset hesla
     * @param user
     */

    public void resetPassword(User user){
        if (user.getRole() != UserRole.USER){
            throw new ServiceException("It's forbidden to reset passwords for admins");
        }

        userDao.resetPassword(user);
    }

    /**
     * Blokování uživatele
     * @param user
     */

    public void blockUser(User user){
        if (user.getRole() != UserRole.USER){
            throw new ServiceException("It's forbidden to block admins");
        }

        userDao.blockUser(user);
    }

    /**
     * Odblokování uživatele
     * @param user
     */

    public void unblockUser(User user){
        if (user.getRole() != UserRole.USER){
            throw new ServiceException("It's forbidden to unblock admins");
        }

        userDao.unblockUser(user);
    }

    /**
     * Zpřístupněni uživatele podle id
     * @param id
     * @return
     */

    public User getUserDetailsById(Integer id){
        User user = userDao.getUserById(id);

        if (user == null){
            throw new UsernameNotFoundException(format("User with id: %s, not found", id));
        }

        return user;
    }

    /**
     * Vytvoření nového uživatele
     * @param request
     */

    public void createAdminUser(NewUserRequest request){
        userDao.createAdmin(request);
    }

}
