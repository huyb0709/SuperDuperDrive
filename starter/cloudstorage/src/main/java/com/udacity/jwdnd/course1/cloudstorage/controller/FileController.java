package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import java.io.IOException;
import org.springframework.http.ResponseEntity;


@Controller
public class FileController {

    @Autowired
    private HttpSession session;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        String username = (String) authentication.getName();
        try {
            if((fileUpload.getSize() >= 1048576)) {
                model.addAttribute("resultError", "Sorry! the file that you upload is over size. Please upload file < 1MB");
            }
            fileService.insertFile(fileUpload, username, model);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "result";
    }

    @GetMapping( value="/view", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<byte[]> viewFile(@RequestParam("fileId") Integer fileId) {
        File file = fileService.getFileById(fileId);
        String fileName = file.getFileName();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
                .body(file.getFileData());
    }

    @GetMapping("/delete/file")
    public String deleteFile(@RequestParam("fileId") Integer fileId, @ModelAttribute("file") File file, Model model) {
        fileService.deleteFile(fileId);
        model.addAttribute("success", true);
        return "result";
    }
}
