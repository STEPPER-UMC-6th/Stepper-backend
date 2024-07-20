package com.example.stepperbackend.domain;


import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class MyExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_id;

    private String url;

    @Enumerated
    private BodyPart body_part;
}