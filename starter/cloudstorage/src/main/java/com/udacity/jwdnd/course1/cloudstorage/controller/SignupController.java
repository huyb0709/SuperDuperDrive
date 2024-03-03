package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Users users, Model model) {
        if (!userService.checkExistsUserName(users.getUserName())) {
            model.addAttribute("errorMsg", "The username already exists");
            return "signup";
        }
        int insertedID = userService.createNewUser(users);
        if (insertedID < 0) {
            model.addAttribute("errorMsg", "Something went wrong");
        }
        model.addAttribute("Signup Success", true);
        return "redirect:/login";
    }
}
