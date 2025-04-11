package net.javaguides.traanings_program.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

// DTO for Personalized Program
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalizedProgram {

    private String answer;

    public PersonalizedProgram(String answer){
        this.answer = answer;
    }

}
