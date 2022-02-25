package cz.upce.bank.eb.controller;

import cz.upce.bank.config.JwtTokenUtil;
import cz.upce.bank.eb.entity.*;
import cz.upce.bank.eb.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler pro ověřování a vytváření nových uživatelů
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Převod údaju na formát, který bude odeslan na frontend
     * @param user
     * @return
     */

    private static UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getUserId());
        userResponse.setClientId(user.getClientId());
        userResponse.setLogin(user.getLogin());
        userResponse.setRole(user.getRole());
        userResponse.setRegisteredByLogin(user.getRegisteredByLogin());
        userResponse.setActive(user.getActive());
        userResponse.setImage(user.getImage());
        return userResponse;
    }

    /**
     * Změna hesla
     * @param request údaje o novém hesle
     * @throws Exception
     */

    @PostMapping("changePassword")
    public void changePassword(@RequestBody NewPasswordRequest request) throws Exception {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.changePassword(request);
    }

    /**
     * Změna obrazku uživatele
     * @param request zapouzdřuje id uživatele a nový obrazek jako řetezec ve formátu BASE64
     * @throws Exception
     */

    @PostMapping("changeImage")
    public void uploadImage(@RequestBody ImageUploadRequest request) throws Exception {
        userService.changeImage(request);
    }

    /**
     * Vytvoření nového uživatele
     * @param request
     * @throws Exception
     */

    @PostMapping("new")
    public void createNewAdmin(@RequestBody NewUserRequest request) throws Exception {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || user.getRole() == UserRole.USER){
            throw new AccessProhibitedException("Access denied");
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        userService.createAdminUser(request);

    }

    /**
     * Reset hesla
     * @param userId
     * @throws Exception
     */

    @GetMapping("/reset/{userId}")
    public void resetPassword(@PathVariable Integer userId) throws Exception{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || user.getRole() == UserRole.USER) {
            throw new AccessProhibitedException("Access denied");
        }

        User foundUser = userService.getUserDetailsById(userId);
        if (foundUser == null){
            throw new RuntimeException();
        }
        else{
            userService.resetPassword(foundUser);
        }

    }

    /**
     * Blokovaní uživatele
     * @param userId
     * @throws Exception
     */

    @GetMapping("/blockUser/{userId}")
    public void blockUser(@PathVariable Integer userId) throws Exception{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || user.getRole() == UserRole.USER) {
            throw new AccessProhibitedException("Access denied");
        }

        User foundUser = userService.getUserDetailsById(userId);
        if (foundUser == null){
            throw new RuntimeException();
        }
        else{
            userService.blockUser(foundUser);
        }

    }

    /**
     * Odblokovaní uživatele
     * @param userId
     * @throws Exception
     */

    @GetMapping("/unblockUser/{userId}")
    public void unblockUser(@PathVariable Integer userId) throws Exception{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || user.getRole() == UserRole.USER) {
            throw new AccessProhibitedException("Access denied");
        }

        User foundUser = userService.getUserDetailsById(userId);
        if (foundUser == null){
            return;
        }
        else{
            userService.unblockUser(foundUser);
        }

    }

    /**
     * Ziskaní údaju o uživateli
     * @param userId
     * @return POJO s údaji
     * @throws Exception
     */

    @GetMapping("getUser/{userId}")
    public ResponseEntity<UserResponse> getUserDataById(@PathVariable Integer userId) throws Exception{
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null || (user.getRole() == UserRole.USER && user.getUserId() != userId)){
            throw new AccessProhibitedException("Access denied");
        }

        User foundUser = userService.getUserDetailsById(userId);
        UserResponse response = userToUserResponse(foundUser);

        return ResponseEntity.ok()
                .body(response);
    }

    /**
     * Přihlášení
     * @param request
     * @return
     */

    @PostMapping("login")
    public ResponseEntity<UserResponse> login(@RequestBody AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            UserResponse userResponse = userToUserResponse(user);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(user)
                    )
                    .body(userResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
