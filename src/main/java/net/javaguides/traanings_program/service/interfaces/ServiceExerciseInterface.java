package net.javaguides.traanings_program.service.interfaces;

import net.javaguides.traanings_program.dto.ExerciseDTO;
import net.javaguides.traanings_program.model.Exercise;

import java.util.List;

public interface ServiceExerciseInterface {

    List<Exercise> fetchExercises();

    List<ExerciseDTO> getExercisesByBodyPart(String bodyPartName);
}
