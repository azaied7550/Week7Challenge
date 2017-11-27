package me.afua.thymeleafsecdemo.repositories;

import me.afua.thymeleafsecdemo.entities.UserEducation;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<UserEducation, Long> {

    UserEducation findByInstitution(String institution);
    UserEducation findByMajor(String Major);


}
