package org.sid.web;

import org.sid.entities.AppUser;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm registerForm) {
        if(registerForm.getPassword() != registerForm.getRepassword())
            throw  new RuntimeException("you must confirm your password");
        if(accountService.findUserByUsername(registerForm.getUsername()) == null)
            throw new RuntimeException("This username already exists");
        AppUser appUser = new AppUser();
        appUser.setUsername(registerForm.getUsername());
        appUser.setPassword(registerForm.getPassword());
        appUser.setRoles(new ArrayList<>());
        accountService.save(appUser);
        accountService.addRoleToUser("USER", registerForm.getUsername());
        return appUser;
    }
}
