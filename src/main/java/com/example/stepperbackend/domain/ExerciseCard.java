package com.example.stepperbackend.domain;


import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.domain.enums.Week;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExerciseCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_card_id")
    private long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Week week;

    private int hour;

    private int minute;

    private int second;

    private boolean status;

    private String materials;

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "exerciseCard", cascade = CascadeType.ALL)
    private List<ExerciseStep> exerciseStepList = new ArrayList<>();

    @OneToOne(mappedBy = "exerciseCard", cascade = CascadeType.ALL)
    private RateDiary rateDiary;
}
