package com.example.peaksoft.service;

import com.example.peaksoft.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<Role> getRoleNamesToList(Set<Role> roles);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Role getById(Long id);

}
