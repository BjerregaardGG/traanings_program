package net.javaguides.traanings_program.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// The Program Request object that gets sent from the frontend in the postAndGetProgram controller method
public class ProgramRequestDTO {

    private String brugerData;
    private List<String> exercises;

}
