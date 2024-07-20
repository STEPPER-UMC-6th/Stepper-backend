package com.example.stepperbackend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickName;

    private String email;

    @Setter
    private String password;

    private String profileImage;

    private Long height;

    private Long weight;

    private boolean communityAlarm;

    private boolean exerciseAlarm;

    private boolean emailAgree;

    private boolean useAgree;

    private boolean perAgree;

    //private LocalDate createdAt;
    //private LocalDate updatedAt;


}