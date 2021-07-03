package com.example.peaksoft.repository;

import com.example.peaksoft.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select role from Role")
    Set<Role> getRoleNamesToList();
}
