package me.afua.thymeleafsecdemo.controllers;

import me.afua.thymeleafsecdemo.UserService;
import me.afua.thymeleafsecdemo.entities.UserData;
import me.afua.thymeleafsecdemo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

//    @RequestMapping(value="/register", method= RequestMethod.GET)
//    public String showRegistrationPage(Model model){
//        model.addAttribute("userData", new UserData());
//        return "registration";
//    }

    @RequestMapping("/")
    public String showMainPage(Principal p) {

        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping("/pageone")
    public String showPageOne(Model model)
    {
        model.addAttribute("title","First Page");
        model.addAttribute("pagenumber","1");
        return "pageone";
    }


    @GetMapping("/register")
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user",new UserData());
        model.addAttribute("pagenumber","4");
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") UserData user,
                                          BindingResult bindingresult, Model model)
    {

        if(bindingresult.hasErrors()){
            return "registration";
        }else{
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index";
    }
    @RequestMapping("/pagetwo")
    public String showPageTwo(Model model)
    {
        model.addAttribute("title","Second Page");
        model.addAttribute("pagenumber","2");
        return "pagetwo";
    }

    @RequestMapping("/pagethree")
    public String showPageThree(Model model)
    {
        model.addAttribute("title","Third Page");
        model.addAttribute("pagenumber","3");
        return "pagethree";
    }


}
