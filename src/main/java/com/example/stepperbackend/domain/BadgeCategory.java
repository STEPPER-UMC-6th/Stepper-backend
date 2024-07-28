package com.example.stepperbackend.domain;


import com.example.stepperbackend.domain.mapping.Badge;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BadgeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "badgeCategory", cascade = CascadeType.ALL)
    private List<Badge> badgeList = new ArrayList<>();
}
