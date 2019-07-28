package org.sid.services;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Override
    public AppUser save(AppUser appUser) {
        String hash= bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(hash);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole save(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String roleName, String username) {
        AppRole role = appRoleRepository.findByRoleName(roleName);
        AppUser user = appUserRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
