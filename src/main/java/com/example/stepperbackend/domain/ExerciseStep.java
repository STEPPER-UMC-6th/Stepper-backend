package com.example.stepperbackend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExerciseStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_step")
    private Long id;

    private int step;

    private boolean stepStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_card_id")
    private ExerciseCard exerciseCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_exercise_id")
    private MyExercise myExercise;

}
