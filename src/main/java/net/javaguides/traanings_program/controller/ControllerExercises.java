package net.javaguides.traanings_program.controller;

import net.javaguides.traanings_program.dto.ExerciseDTO;
import net.javaguides.traanings_program.dto.PersonalizedProgram;
import net.javaguides.traanings_program.dto.ProgramRequestDTO;
import net.javaguides.traanings_program.model.BodyPart;
import net.javaguides.traanings_program.model.Exercise;
import net.javaguides.traanings_program.service.ServiceAi;
import net.javaguides.traanings_program.service.ServiceExercises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/exercises")
public class ControllerExercises {

    @Autowired
    ServiceExercises serviceExercises;
    ServiceAi serviceAi;

    final static String SYSTEM_MESSAGE = "You are a personal trainer, who is a professionel when it comes to generating personalized " +
            "training programs. Generate a very simpel program split based on the the user info and favorite exercises";

    @GetMapping("")
    public ResponseEntity<List<Exercise>> getExercises(){
        List<Exercise> exercises = serviceExercises.fetchExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/bodyPart/{bodyPart}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByBodyPart(@PathVariable String bodyPart){
        List<ExerciseDTO> exercises = serviceExercises.getExercisesByBodyPart(bodyPart);
        return ResponseEntity.ok(exercises);
    }

    @PostMapping("/program")
    public ResponseEntity<PersonalizedProgram> postAndGetProgram(@RequestBody ProgramRequestDTO program){

        ProgramRequestDTO pro = program;
        String systemMessage = SYSTEM_MESSAGE;

        PersonalizedProgram personalizedProgram = serviceAi.makeRequest(systemMessage, pro);

        return ResponseEntity.ok(personalizedProgram);


    }

}
