package com.example.stepperbackend.domain;


import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;


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

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private BodyPart body_part;

    @Column(nullable = false, length = 100)
    private String video_title;

    @Column(nullable = false)
    private String video_image;

    @Column(nullable = false, length = 100)
    private String channel_name;

}

