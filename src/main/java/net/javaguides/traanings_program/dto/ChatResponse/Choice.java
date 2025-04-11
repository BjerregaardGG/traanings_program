
package net.javaguides.traanings_program.dto.ChatResponse;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.traanings_program.dto.ChatRequest.Message;


@Getter
@Setter
public class Choice {

    @JsonProperty("index")
    private Integer index;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("logprobs")
    private Object logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


}
