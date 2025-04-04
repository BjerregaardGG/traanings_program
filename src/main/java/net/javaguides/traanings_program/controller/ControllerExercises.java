package net.javaguides.traanings_program.controller;

import net.javaguides.traanings_program.dto.ExerciseDTO;
import net.javaguides.traanings_program.model.BodyPart;
import net.javaguides.traanings_program.model.Exercise;
import net.javaguides.traanings_program.service.ServiceExercises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerExercises {

    @Autowired
    ServiceExercises serviceExercises;

    @GetMapping("/exercises")
    public List<Exercise> getExercises(){
        List<Exercise> exercises = serviceExercises.fetchExercises();
        return exercises;
    }

    @GetMapping("/exercises/bodyPart/{bodyPart}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByBodyPart(@PathVariable String bodyPart){
        List<ExerciseDTO> exercises = serviceExercises.getExercisesByBodyPart(bodyPart);
        return ResponseEntity.ok(exercises);
    }

}
