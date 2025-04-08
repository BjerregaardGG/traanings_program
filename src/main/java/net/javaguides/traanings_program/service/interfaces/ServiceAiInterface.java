package net.javaguides.traanings_program.service.interfaces;

import net.javaguides.traanings_program.dto.PersonalizedProgram;
import net.javaguides.traanings_program.dto.ProgramRequestDTO;

public interface ServiceAiInterface {

    PersonalizedProgram makeRequest(String systemMessage, ProgramRequestDTO program);

}
