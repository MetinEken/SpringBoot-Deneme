package Deneme.SpringBoot.controller;

import Deneme.SpringBoot.config.jwt.JwtUtil;
import Deneme.SpringBoot.dao.UserDAO;
import Deneme.SpringBoot.model.Role;
import Deneme.SpringBoot.model.User;
import Deneme.SpringBoot.model.UserRole;
import Deneme.SpringBoot.repository.RoleRepo;
import Deneme.SpringBoot.repository.UserRepo;
import Deneme.SpringBoot.request.LoginForm;
import Deneme.SpringBoot.request.SignUpForm;
import Deneme.SpringBoot.response.LoginResponse;
import Deneme.SpringBoot.response.Response;
import Deneme.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody SignUpForm signUpForm) {
        Response response = new Response();
        if(userRepo.existsByUsername(signUpForm.getUsername())){
            response.setMessage("Error: Username is already taken");
            response.setSuccess(false);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }

        if(userRepo.existsByEmail(signUpForm.getEmail())){
            response.setMessage("Error: Email is already taken");
            response.setSuccess(false);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }
        User user = new User(
                signUpForm.getFirstName(),
                signUpForm.getLastName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()));

        Set<UserRole> userRoles = new HashSet<>();

        Set<String> stringRoles = Collections.singleton(signUpForm.getRole());

        stringRoles.forEach(roleName -> {
            Role role = roleRepo.findByName(roleName).
                    orElseThrow(() -> new RuntimeException("User Role Not found"));
            userRoles.add(new UserRole(user, role));
        });


        user.setUserRoles(userRoles);
        userRepo.save(user);
        response.setMessage("Ragister Successfuly");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id){
        Response response = new Response();
        userRepo.deleteById(id);
        response.setMessage("Delete Successfuly");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginForm loginForm){

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(),
                        loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user =  (User) authentication.getPrincipal();

        String jwt = jwtUtil.generateToken(authentication);

        UserDAO userDAO = userService.getUserDAO(user);

        return  ResponseEntity.ok( new LoginResponse(userDAO, jwt));
    }

    @GetMapping("/user")
    public String handlLogin(){


        return "giris basarili";
    }

}
