package net.javaguides.traanings_program.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private String name;
    private String bodyPart;
    private String equipment;
    private String gifUrl;
    private String target;
    private List<String> secondaryMuscles;
    private List<String> instructions;

}
