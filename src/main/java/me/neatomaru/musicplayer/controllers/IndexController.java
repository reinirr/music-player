package me.neatomaru.musicplayer.controllers;

import me.neatomaru.musicplayer.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexController {

    private final StorageService storageService;


    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("songFileNames", storageService.getSongFileNames());
        return "index";
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file")MultipartFile file) {
        storageService.uploadSong(file);
        return "redirect:/";
    }
}
