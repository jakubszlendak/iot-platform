package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Role;
import java.util.List;

/**
 * Created by jakub on 18.01.16.
 */
public interface RoleDAO {
    Role getRoleById(long id);
    Role getRoleByName(String name);
    void insertRole(Role role);
    void deleteRole(long id);
    List<Role> getAllRoles();
}
