package net.javaguides.traanings_program.repository;

import net.javaguides.traanings_program.model.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyPartRepo extends JpaRepository<BodyPart, Long> {

    BodyPart findBodyPartByName(String name);



}
