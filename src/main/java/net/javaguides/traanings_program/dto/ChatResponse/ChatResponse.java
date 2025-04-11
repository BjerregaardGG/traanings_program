
package net.javaguides.traanings_program.dto.ChatResponse;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse {

    private String id;
    private String object;
    private Integer created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String serviceTier;
    private Object systemFingerprint;

}
