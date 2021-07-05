package com.example.peaksoft.rest;
import com.example.peaksoft.entity.Role;
import com.example.peaksoft.entity.User;
import com.example.peaksoft.service.RoleService;
import com.example.peaksoft.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/user")
    public ResponseEntity<User> getUser(Authentication authentication) {
        try {
            System.out.println(authentication.getName());
            return new ResponseEntity<>(userService.findByEmail(authentication.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            Set<Role> rolesFromBD = new HashSet<>();
            rolesFromBD.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(rolesFromBD);
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        try{
            Set<Role> rolesFromBD = new HashSet<>();
            rolesFromBD.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(rolesFromBD);
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity update(@PathVariable("userId") Long employeeId) {
        try{
            User user = userService.getById(employeeId);
            userService.deleteUser(user);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
