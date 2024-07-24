package com.example.stepperbackend.web.dto.MyExercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MyExerciseResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddExerciseDTO{
        String status;
        String message;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckExerciseDTO{
        String url;
        String video_title;
        String video_image;
        String channel_name;

    }
}
