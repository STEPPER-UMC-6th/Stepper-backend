package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.domain.enums.BodyPart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MyExerciseDto {
    @Getter
    public static class AddExerciseRequestDto {

        private String url;
        private Integer body_part;
        private String video_title;
        private String video_image;
        private String channel_name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddExerciseResponseDTO {
        private Long exerciseId;
    }


    @Getter
    public static class CheckExerciseRequestDto {
        private BodyPart body_part;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckExerciseResponseDTO {
        private String url;
        private String video_title;
        private String video_image;
        private String channel_name;

    }
}
