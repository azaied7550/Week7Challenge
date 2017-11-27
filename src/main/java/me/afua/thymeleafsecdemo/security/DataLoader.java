package me.afua.thymeleafsecdemo.security;

import me.afua.thymeleafsecdemo.entities.UserRole;
import me.afua.thymeleafsecdemo.repositories.RoleRepository;
import me.afua.thymeleafsecdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");

        roleRepository.save(new UserRole("SEEKER"));
        roleRepository.save(new UserRole("RECRUITER"));
        roleRepository.save(new UserRole("ADMIN"));
    }
}