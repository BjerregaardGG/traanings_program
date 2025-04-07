package net.javaguides.traanings_program.service;

import net.javaguides.traanings_program.dto.ExerciseDTO;
import net.javaguides.traanings_program.model.BodyPart;
import net.javaguides.traanings_program.model.Exercise;
import net.javaguides.traanings_program.repository.BodyPartRepo;
import net.javaguides.traanings_program.repository.ExerciseRepo;
import net.javaguides.traanings_program.service.interfaces.ServiceExerciseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


// class to get exercises
@Service
public class ServiceExercises implements ServiceExerciseInterface {

    private final WebClient webClient;
    private final ExerciseRepo exerciseRepo;
    private final BodyPartRepo bodyPartRepo;

    @Autowired
    public ServiceExercises(WebClient.Builder webClientBuilder, ExerciseRepo exerciseRepo, BodyPartRepo bodyPartRepo) {
        this.webClient = webClientBuilder.baseUrl("https://exercisedb.p.rapidapi.com").build();
        this.exerciseRepo = exerciseRepo;
        this.bodyPartRepo = bodyPartRepo;
    }

    @Value("${api.key.exercises}")
    private String apiKeyExercises;

    // fetch exercises
    public List<Exercise> fetchExercises(){
        List<ExerciseDTO> exercisesDTOS = webClient.get()
                .uri("https://exercisedb.p.rapidapi.com/exercises?limit=200")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-RapidAPI-Key", apiKeyExercises)
                .retrieve()
                .bodyToFlux(ExerciseDTO.class)
                .collectList()
                .block();

        List<Exercise> exerciseList = new ArrayList<>();

        if(exercisesDTOS != null) {
            for (ExerciseDTO exerciseDTO : exercisesDTOS) {
                // e.setId(null);

                // se om der er en bodypart i databasen
                BodyPart bodyPart = bodyPartRepo.findBodyPartByName(exerciseDTO.getBodyPart());

                // hvis der ikke er en bodypart i databasen, så opret en ny
                if(bodyPart == null) {
                    bodyPart = new BodyPart(exerciseDTO.getBodyPart());
                    bodyPartRepo.save(bodyPart);
                }

                Exercise exercise = new Exercise();
                exercise.setName(exerciseDTO.getName());
                exercise.setBodyPart(bodyPart); // Sæt den korrekte BodyPart på Exercise
                exercise.setTarget(exerciseDTO.getTarget());
                exercise.setGifUrl(exerciseDTO.getGifUrl());
                exercise.setEquipment(exerciseDTO.getEquipment());

                exerciseRepo.save(exercise); // Gem Exercise i databasen
                exerciseList.add(exercise);
            }
        }
        return exerciseList;
    }

    public List<ExerciseDTO> getExercisesByBodyPart(String bodyPartName){

        BodyPart bodyPart = bodyPartRepo.findBodyPartByName(bodyPartName);

        return exerciseRepo.findByBodyPart(bodyPart).stream()
                .map(e -> new ExerciseDTO(
                        e.getName(),
                        e.getBodyPart().getName(),
                        e.getEquipment(),
                        e.getGifUrl(),
                        e.getTarget()))
                .collect(Collectors.toList());

    }








}
