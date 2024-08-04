package com.example.stepperbackend.domain;

import com.example.stepperbackend.domain.common.BaseEntity;
import com.example.stepperbackend.domain.enums.SubCategory;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String title;
    private String body;

    private Long categoryId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_mission_id")
    private WeeklyMission weeklyMission;
}
