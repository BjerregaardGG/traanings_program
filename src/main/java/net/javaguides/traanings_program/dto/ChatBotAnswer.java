package net.javaguides.traanings_program.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


// DTO for chatBot answer
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatBotAnswer {

    private String answer;

    public ChatBotAnswer(String answer){
        this.answer = answer;
    }

}
