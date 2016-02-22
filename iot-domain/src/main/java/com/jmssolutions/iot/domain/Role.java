package com.jmssolutions.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jakub on 17.01.16.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long ID;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonIgnore
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (ID != role1.ID) return false;
        return !(role != null ? !role.equals(role1.role) : role1.role != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
