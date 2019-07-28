package org.sid.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AppRole implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String roleName;

    public AppRole(String roleName) {
        this.roleName = roleName;
    }

    public AppRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
