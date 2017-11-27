package me.afua.thymeleafsecdemo.repositories;

import me.afua.thymeleafsecdemo.entities.Experience;
import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Long>{

    Experience findByEmployer(String Employer);
}
