package com.example.stepperbackend.web.dto;

import com.example.stepperbackend.domain.enums.BodyPart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


public class MyExerciseRequestDTO {
    @Getter
    public static class AddExerciseDto{


        @NotNull
        String url;
        @NotNull
        BodyPart bodyPart;
    }
}
