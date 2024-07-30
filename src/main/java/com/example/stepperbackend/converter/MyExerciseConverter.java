package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MyExerciseConverter {
    public static MyExerciseResponseDTO.AddExerciseDTO toAddExerciseDTO(MyExercise myExercise) {
        return MyExerciseResponseDTO.AddExerciseDTO.builder()
                .status("success")
                .message("나만의 운동 저장에 성공하였습니다.")
                .build();
    }

    public static List<MyExerciseResponseDTO.CheckExerciseDTO> toCheckExerciseListDTO(List<MyExercise> myExercise) {

        return myExercise.stream()
                .map(MyExerciseConverter::toCheckExerciseDTO)
                .collect(Collectors.toList());
    }

    public static MyExerciseResponseDTO.CheckExerciseDTO toCheckExerciseDTO(MyExercise myExercise) {

        return MyExerciseResponseDTO.CheckExerciseDTO.builder()
                .url(myExercise.getUrl())
                .video_title(myExercise.getVideo_title())
                .video_image(myExercise.getVideo_image())
                .channel_name(myExercise.getChannel_name())
                .build();
    }
}
