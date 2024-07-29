package com.example.stepperbackend.domain;

import jakarta.persistence.*;
import lombok.*;

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

   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "card_id")
   // private ExerciesCard exerciesCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate date;

    private Long conditionRate;

    private Long pain_rate;

    private String painMemo;

    private String painImage;



}