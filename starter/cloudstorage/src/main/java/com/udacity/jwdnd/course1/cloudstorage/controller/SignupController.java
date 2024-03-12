package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//        if (userService.checkExistsUserName(users.getUserName())) {
//            model.addAttribute("message", "errorUserExist");
//            return "signup";
//        } else {
//            int insertedID = userService.createNewUser(users);
//            if (insertedID < 0) {
//                model.addAttribute("signupError", "Something went wrong");
//            }
//            model.addAttribute("signupSuccess", true);
//            return "redirect:/login";
//        }
        try {
            Integer insertedID = this.userService.createNewUser(users);
        } catch (DuplicateKeyException e) {
            model.addAttribute("message", "userExist");
            return "signup";
        }
        model.addAttribute("message", "signupSuccess");
        return "signup";
    }
}
