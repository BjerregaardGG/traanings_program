package net.javaguides.traanings_program.service.interfaces;

import net.javaguides.traanings_program.dto.PersonalizedProgram;
import net.javaguides.traanings_program.dto.ProgramRequestDTO;

import java.util.List;

public interface ServiceAiInterface {

    PersonalizedProgram makeRequest(String systemMessage, String userInfo, List<String> exercises);

}
