package me.afua.thymeleafsecdemo.controllers;

import me.afua.thymeleafsecdemo.UserService;
import me.afua.thymeleafsecdemo.entities.UserData;
import me.afua.thymeleafsecdemo.entities.UserEducation;
import me.afua.thymeleafsecdemo.entities.UserRole;
import me.afua.thymeleafsecdemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EducationRepository educationRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    SkillRepository skillRepository;


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
        model.addAttribute("role", new UserRole());
        model.addAttribute("user",new UserData());
        model.addAttribute("pagenumber","4");
        return "registration";
    }

//    @PostMapping("/register")
//    public String processWorkPage(@Valid UserData user, UserRole role, BindingResult bindingResult,
//                                  HttpServletRequest getRole) {
//        String roleChoice = getRole.getParameter("roleChoice");
//        role.setRole(roleChoice );
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        if (roleChoice.equals("RECRUITER")) {
//            userService.saveUser(user);
//        }
//
//        if (roleChoice.equals("SEEKER")) {
//            userService.saveUser(user);
//
//        }
//
//        return "login";
//    }
@PostMapping("/register")
public String processWorkPage(@Valid UserData user, UserRole role, BindingResult bindingResult,
                              HttpServletRequest getRole) {
    String roleResult = getRole.getParameter("roleResult");
    role.setRole(roleResult);
    if (bindingResult.hasErrors()) {
        return "registration";
    }

    if (roleResult.equals("RECRUITER")) {
        userService.saveRecruiter(user);
    }

    if (roleResult.equals("SEEKER")) {
        userService.saveSeeker(user);

    }

    return "login";
}

    @RequestMapping("/pagetwo")
    public String showPageTwo(Model model)
    {
        model.addAttribute("title","Second Page");
        model.addAttribute("pagenumber","2");
        return "pagetwo";
    }
    @GetMapping("/newResume")
    public String getEducationPage(Model model) {
        model.addAttribute(new UserEducation());
        model.addAttribute("pagenumber","3");
        return "createresume";
    }
//@RequestMapping ("/newREsume")
//public String processResume(Model model){
//        return"createresume";
//    }
@RequestMapping("/newResume")
public String newResume(Model model){
        UserEducation userEducation= new UserEducation();
        model.addAttribute("education", userEducation);
        return "createresume";
}
    @PostMapping("/newResume")
    public String processEducationPage(@Valid UserEducation education, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createresume";
        }
        educationRepository.save(education);
        model.addAttribute(new UserEducation());

        return "createresume";
    }
//    @RequestMapping("/newResume")
//    public String showResume(Model model)
//    {
//        model.addAttribute("title","Third Page");
//
//        return "createresume";
//    }


}
