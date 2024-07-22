package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class MyExerciseRequestDTO {
    @Getter
    public static class AddExerciseDto{

        @NotNull
        String url;
        @NotNull
        BodyPart bodyPart;

        @NotNull
        String video_title;

        @NotNull
        String video_image;

        @NotNull
        String channel_name;
    }
}
