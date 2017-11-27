package me.afua.thymeleafsecdemo;

import me.afua.thymeleafsecdemo.entities.UserData;
import me.afua.thymeleafsecdemo.repositories.RoleRepository;
import me.afua.thymeleafsecdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserData findByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }

    public UserData findByUsername (String username){
        return userRepository.findByUsername(username);
    }

    public void saveSeeker(UserData user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("SEEKER")));
        userRepository.save(user);
    }

    public void saveRecruiter(UserData user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("RECRUITER")));
        userRepository.save(user);
    }

    public void saveUser (UserData userData){
        userData.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        userData.setEnabled(true);
        userRepository.save(userData);
    }

    public void saveAdmin(UserData userData){
        userData.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        userData.setEnabled(true);
        userRepository.save(userData);
    }
}
