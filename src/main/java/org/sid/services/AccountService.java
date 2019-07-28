package org.sid.services;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {
    public AppUser save(AppUser appUser);
    public AppRole save(AppRole appRole);
    public void addRoleToUser(String roleName, String username);
    public AppUser findUserByUsername(String username);
}
