package com.example.peaksoft.service.impl;

import com.example.peaksoft.entity.Role;
import com.example.peaksoft.repository.RoleRepository;
import com.example.peaksoft.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository userRepository) {
        this.roleRepository = userRepository;
    }


    @Override
    @Transactional
    public Set<Role> getRoleNamesToList(Set<Role> roles) {
        return roleRepository.getRoleNamesToList();
    }



    @Override
    @Transactional
    public Role getRoleByName(String name) {
        List<Role>roles=getAllRoles();
        return roles.stream().filter(x->x.getRole().equals(name)).findAny().orElse(null);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }

}
