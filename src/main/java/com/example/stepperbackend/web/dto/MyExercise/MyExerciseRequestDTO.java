package com.example.stepperbackend.web.dto.MyExercise;

import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


public class MyExerciseRequestDTO {
    @Getter
    public static class AddExerciseDto {

        @NotNull
        String url;
        @NotNull
        BodyPart body_part;

        @NotNull
        String video_title;

        @NotNull
        String video_image;

        @NotNull
        String channel_name;
    }

    @Getter
    public static class CheckExerciseDto{

        @NotNull
        BodyPart body_part;
    }
}
