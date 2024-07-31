package com.example.stepperbackend.domain;

import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.StreamingHttpOutputMessage;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RateDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_card_id")
    private ExerciseCard exerciseCard;

    private LocalDate date;

    private Long conditionRate;

    private Long painRate;

    private String painMemo;

    private String painImage;

    private String bodyPart;
}