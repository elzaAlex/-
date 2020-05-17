package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.domain.User;
import com.example.servingwebcontent.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/profile/{user}")
public class ProfileController {
    @Autowired
    public UserRepos userRepos;

    @GetMapping("")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("password", user.getPassword());
        return "profile";
    }

    @PostMapping
    public String userSave(
            @RequestParam String password,
            @RequestParam("userId") User user
    ) {
        user.setPassword(password);

        userRepos.save(user);

        return "redirect:/profile/{user}";
    }
}
