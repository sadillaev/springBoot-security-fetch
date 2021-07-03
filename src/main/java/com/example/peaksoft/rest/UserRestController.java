package com.example.peaksoft.rest;
import com.example.peaksoft.entity.Role;
import com.example.peaksoft.entity.User;
import com.example.peaksoft.service.RoleService;
import com.example.peaksoft.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/user")
    public ResponseEntity<User> getById(Principal principal) {
        try {
            return new ResponseEntity<>(userService.getByName(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            Set<Role> rolesFromBD = new HashSet<>();
            rolesFromBD.add(roleService.getRoleByName("ROLE_USER"));
            User userObj = new User();
            userObj.setName(user.getName());
            userObj.setLastName(user.getLastName());
            userObj.setEmail(user.getEmail());
            userObj.setPassword(user.getPassword());
            userObj.setAddress(user.getAddress());
            userObj.setAge(user.getAge());
            userObj.setRoles(rolesFromBD);
            return new ResponseEntity<>(userService.save(userObj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        try{
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