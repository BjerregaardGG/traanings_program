package net.javaguides.traanings_program.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class BodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bodypart_id;
    private String name;

    @OneToMany(mappedBy = "bodyPart", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    // Constructor til Jackson
    @JsonCreator
    public BodyPart(String name) {
        this.name = name;
    }

    public BodyPart() {} // Standard constructor kræves af JPA

    @JsonValue
    public String getName() {
        return name;
    }

}
