
package net.javaguides.traanings_program.dto.ChatResponse;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompletionTokensDetails {

    @JsonProperty("reasoning_tokens")
    private Integer reasoningTokens;
    @JsonProperty("audio_tokens")
    private Integer audioTokens;
    @JsonProperty("accepted_prediction_tokens")
    private Integer acceptedPredictionTokens;
    @JsonProperty("rejected_prediction_tokens")
    private Integer rejectedPredictionTokens;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}
