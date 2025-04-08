package net.javaguides.traanings_program.service;

import net.javaguides.traanings_program.dto.ChatRequest.ChatRequest;
import net.javaguides.traanings_program.dto.ChatRequest.Message;
import net.javaguides.traanings_program.dto.ChatResponse.ChatResponse;
import net.javaguides.traanings_program.dto.PersonalizedProgram;
import net.javaguides.traanings_program.dto.ProgramRequestDTO;
import net.javaguides.traanings_program.service.interfaces.ServiceAiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAi implements ServiceAiInterface {

    private final WebClient webClient;

    @Autowired
    public ServiceAi(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }

    @Value("${api.key.openai}")
    private String openAiKey;


    public PersonalizedProgram makeRequest(String systemMessage, ProgramRequestDTO program){

        // her laver vi request objektet
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("gpt-4");
        chatRequest.setTemperature(1.0);
        chatRequest.setMaxTokens(600);

        List<Message> lstMessages = new ArrayList<>(); //en liste af messages med roller
        lstMessages.add(new Message("system", systemMessage));
        lstMessages.add(new Message("user", program.getBrugerData()));
        chatRequest.setMessages(lstMessages);

        // her sender vi en request og gemmer det i response
        ChatResponse response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON) // JSON
                .headers(h -> h.setBearerAuth(openAiKey)) // set Bearer til api nøglen i application.prop
                .bodyValue(chatRequest) // vores request objekt
                .retrieve()
                .bodyToMono(ChatResponse.class) // konverterer HTTP response til et Java objekt
                .block(); // hvis ikke block, så ville det være et mono objekt

        String responseMessage = response.getChoices().get(0).getMessage().getContent();

        return new PersonalizedProgram(responseMessage);




    }


}
