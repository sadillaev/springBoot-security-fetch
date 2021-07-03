package com.example.peaksoft.rest;
import com.example.peaksoft.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class SimpleController {

    @GetMapping("/admin")
    public String getTable() {
         return "index";
    }

    @GetMapping("/user")
    public String userPage() {
    return "user";
    }
}
