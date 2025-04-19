package net.javaguides.traanings_program.model;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String equipment;
    private String gifUrl;
    private String target;

    // entitet med liste af værdier (ikke behov for at identificere liste elementerne som egne entiteter)
    @ElementCollection
    private List<String> secondaryMuscles;

    @ElementCollection
    private List<String> instructions;

    @ManyToOne
    @JoinColumn(name = "bodypart_id")
    private BodyPart bodyPart;


}
