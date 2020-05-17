package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.domain.User;
import com.example.servingwebcontent.repos.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepos messageRepos;
    @GetMapping
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("https://www.nbrb.by/api/exrates/rates/USD?parammode=2")
    public String GetCurrencyRate(@RequestParam String Cur_OfficialRate, Model model) {
        model.addAttribute("rate", Cur_OfficialRate);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam( required = false) String filter, Model model){

         Iterable<Message> messages;
        if (filter !=null && !filter.isEmpty()){
            messages = messageRepos.findByTag(filter);
        } else{
            messages = messageRepos.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        }else{

            model.addAttribute("message", null);
            messageRepos.save(message);
        }

        Iterable<Message> messages = messageRepos.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

}