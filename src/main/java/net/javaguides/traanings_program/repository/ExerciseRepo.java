package net.javaguides.traanings_program.repository;

import net.javaguides.traanings_program.model.BodyPart;
import net.javaguides.traanings_program.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ExerciseRepo extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findExerciseByName(String name);

    List<Exercise> findByBodyPart(BodyPart bodyPart);

}
