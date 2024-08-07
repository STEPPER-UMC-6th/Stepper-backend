package com.example.stepperbackend.domain;


import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private BodyPart body_part;

    @NotNull
    private String video_title;

    @NotNull
    private String video_image;

    @NotNull
    private String channel_name;

    @OneToMany(mappedBy = "myExercise", cascade = CascadeType.ALL)
    private List<ExerciseStep> exerciseStepList = new ArrayList<>();

}

