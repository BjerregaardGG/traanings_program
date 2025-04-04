package net.javaguides.traanings_program.model;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "bodypart_id")
    private BodyPart bodyPart;


}
