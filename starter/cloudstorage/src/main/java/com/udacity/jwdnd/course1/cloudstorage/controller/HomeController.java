package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
public class HomeController {

    @Autowired FileService fileService;
    @Autowired NoteService noteService;
    @Autowired CredentialService credentialService;
    @Autowired EncryptionService encryptionService;

    @RequestMapping("/home")
    public String homePage(Model model, Authentication authentication){

        String username = (String) authentication.getName();
        List<Notes> listAllNotes = noteService.listAllNotes(username);
        List<Credentials> listAllCredentials = credentialService.listAllCredentials(username);
        //Get list files by userId
        model.addAttribute("files", fileService.getFiles(username));
        //Get list notes by userId
        model.addAttribute("notes", listAllNotes);
        //Get list credentials by userId
        model.addAttribute("credentials", listAllCredentials);
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

}
