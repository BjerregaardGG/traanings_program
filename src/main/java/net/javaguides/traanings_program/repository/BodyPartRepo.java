package net.javaguides.traanings_program.repository;

import net.javaguides.traanings_program.model.BodyPart;
import net.javaguides.traanings_program.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BodyPartRepo extends JpaRepository<BodyPart, Long> {

    BodyPart findBodyPartByName(String name);



}
