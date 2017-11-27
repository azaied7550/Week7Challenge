package me.afua.thymeleafsecdemo.repositories;

import me.afua.thymeleafsecdemo.entities.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long>{
    Skill findBySkillName(String skillName);
}
