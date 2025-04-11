package net.javaguides.traanings_program.service.interfaces;

import net.javaguides.traanings_program.dto.ChatBotAnswer;
import net.javaguides.traanings_program.dto.PersonalizedProgram;
import net.javaguides.traanings_program.dto.ProgramRequestDTO;

import java.util.List;

public interface ServiceAiInterface {

    ChatBotAnswer askChatBot(String question, String systemMessage);

    PersonalizedProgram makeRequest(String systemMessage, String userInfo, List<String> exercises);

}
