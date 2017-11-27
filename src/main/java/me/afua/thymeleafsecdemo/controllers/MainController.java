package me.afua.thymeleafsecdemo.controllers;

import me.afua.thymeleafsecdemo.UserService;
import me.afua.thymeleafsecdemo.entities.*;
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

    @GetMapping("/addE")
    public String petForm (Model model){
        model.addAttribute("education", new UserEducation());
        return "education";
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

    @RequestMapping("/search")
    public String showSearch(Model model)
    {
//        model.addAttribute("title","Second Page");
//        model.addAttribute("pagenumber","2");
        return "search";
    }
    @GetMapping("/newResume")
    public String getEducationPage(Model model) {
        model.addAttribute(new UserEducation());
        model.addAttribute("pagenumber","3");
        return "education";
    }

    @RequestMapping("/newResume")
    public String newResume(Model model){
        UserEducation userEducation= new UserEducation();
        model.addAttribute("education", userEducation);
        return "education";
    }
    @PostMapping("/newResume")
    public String processEducationPage(@Valid UserEducation education, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "education";
        }
        educationRepository.save(education);
        model.addAttribute(new UserEducation());

        return "education";
    }
//    @RequestMapping("/newResume")
//    public String showResume(Model model)
//    {
//        model.addAttribute("title","Third Page");
//
//        return "education";
//    }

    @GetMapping("/newExperience")
    public String getExperience(Model model) {
        model.addAttribute(new Experience());
        //model.addAttribute("pagenumber","3");
        return "experience";
    }

    @RequestMapping("/newExperience")
    public String newExperience(Model model){
        Experience experience= new Experience();
        model.addAttribute("experience", experience);
        return "experience";
    }
    @PostMapping("/newExperience")
    public String processExperience(@Valid Experience experience, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "experience";
        }
        experienceRepository.save(experience);
        model.addAttribute(new Experience());

        return "experience";
    }
    @GetMapping("/addExperience")
    public String experienceForm (Model model){
        model.addAttribute("experience", new Experience());
        return "experience";
    }

    //// Skills
    @GetMapping("/newSkill")
    public String getSkill(Model model) {
        model.addAttribute(new Skill());
        //model.addAttribute("pagenumber","3");
        return "skill";
    }

    @RequestMapping("/newSkill")
    public String newSkill(Model model){
        Skill skill= new Skill();
        model.addAttribute("skill", skill);
        return "skill";
    }
    @PostMapping("/newSkill")
    public String processSkill(@Valid Skill skill, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "skill";
        }
        skillRepository.save(skill);
        model.addAttribute(new Skill());

        return "skill";
    }
    @GetMapping("/addSkill")
    public String skillForm (Model model){
        model.addAttribute("skill", new Skill());
        return "skill";
    }

    @RequestMapping("/detail/{id}")
    public String displayResume(@PathVariable ("id") long id, Model model){
        model.addAttribute("user", userRepository.findOne(id));
     model.addAttribute("userEducation", userRepository.findOne(id));
//        model.addAttribute("userExperience", experienceRepository.findOne(id));
//        model.addAttribute("userSkill", skillRepository.findOne(id));
        return "display";
    }

    @RequestMapping("/displayList")
    public String listResumes (Model model){
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("education", educationRepository.findAll());
        model.addAttribute("experience", experienceRepository.findAll());
        model.addAttribute("skill", skillRepository.findAll());

        return "displaylist";
    }

    @RequestMapping("/result")
    public String historyPage(Model model) {
        model.addAttribute("eduList", educationRepository.findAll());
        model.addAttribute("experienceList", experienceRepository.findAll());
        model.addAttribute("skillList", skillRepository.findAll());
        model.addAttribute("userList", userRepository.findAll());
        return "results";
    }

}
