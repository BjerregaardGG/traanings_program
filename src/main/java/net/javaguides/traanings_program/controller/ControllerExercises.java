package net.javaguides.traanings_program.controller;

import net.javaguides.traanings_program.dto.ChatBotAnswer;
import net.javaguides.traanings_program.dto.ExerciseDTO;
import net.javaguides.traanings_program.dto.PersonalizedProgram;
import net.javaguides.traanings_program.dto.ProgramRequestDTO;
import net.javaguides.traanings_program.model.Exercise;
import net.javaguides.traanings_program.service.ServiceAi;
import net.javaguides.traanings_program.service.ServiceExercises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/exercises")
public class ControllerExercises {

    @Autowired
    ServiceExercises serviceExercises;

    @Autowired
    ServiceAi serviceAi;

    final static String SYSTEM_MESSAGE = "You are a personal trainer, who is a professionel when it comes to generating personalized " +
            "training programs. Generate a very simple split based program on the the user info and favorite exercises" +
            ".You should still add more exercises even if the user has only chosen a few. Keep in mind the user info, such as:" +
            "'mål, træningsniveau, træningslængde, skader, træningsniveau' when you make the program. " +
            "You should not do an intro or ending but only present the training program for each day of the week and not repeat " +
            "the same split." + "You should present the program directly to the user in professional english and start each day with " +
            "Day 1 (and muscle group), Day 2 (and muscle group) etc. You should name all 7 week Days even though it is a rest day";

    final static String SYSTEM_MESSAGE_CHATBOT = "You have to answer training related questions in a short format. " +
            "If asked about stuff that is not related to any training specific topic, please inform the user that you only answer" +
            "training related questions";

    // fetches all exercises from an extern API and maps them to the database
    @GetMapping("")
    public ResponseEntity<List<Exercise>> getExercises(){
        try {
            List<Exercise> exercises = serviceExercises.fetchExercises();
            return ResponseEntity.ok(exercises);

        }catch(Exception e){
            e.printStackTrace(); // Eller brug en logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    // fetches exercises based on bodyPart
    @GetMapping("/bodyPart/{bodyPart}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesByBodyPart(@PathVariable String bodyPart){
        try {
            List<ExerciseDTO> exercises = serviceExercises.getExercisesByBodyPart(bodyPart);
            return ResponseEntity.ok(exercises);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // sends user info & favorite exercises and returns a personal training program
    @PostMapping("/program")
    public ResponseEntity<?> postAndGetProgram(@RequestBody ProgramRequestDTO program){

        try {
            String promptUserinfo = program.getBrugerData();
            List<String> exerciseList = program.getExercises();
            String systemMessage = SYSTEM_MESSAGE;

            System.out.println(promptUserinfo);
            System.out.println(exerciseList);

            PersonalizedProgram personalizedProgram = serviceAi.makeRequest(systemMessage, promptUserinfo, exerciseList);

            return ResponseEntity.ok(personalizedProgram);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body("Forkert eller manglende data: " + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // sends a training related question and returns an answer
    @PostMapping("/chatbot")
    public ResponseEntity<?> postAndGetChatBotAnswer(@RequestBody String question) {

        try {
            ChatBotAnswer answer = serviceAi.askChatBot(question, SYSTEM_MESSAGE_CHATBOT);

            return ResponseEntity.ok(answer);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Forkert eller manglende data: " + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
