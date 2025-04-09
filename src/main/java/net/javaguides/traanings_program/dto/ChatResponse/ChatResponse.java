
package net.javaguides.traanings_program.dto.ChatResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
