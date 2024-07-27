package com.example.stepperbackend.web.converter;

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

    public static List<MyExerciseResponseDTO.CheckExerciseDTO> toCheckExerciseDTO(List<MyExercise> myExercise) {

        return myExercise.stream()
                .map(myExercise1 -> MyExerciseResponseDTO.CheckExerciseDTO.builder()
                                .url(myExercise1.getUrl())
                                .video_title(myExercise1.getVideo_title())
                                .video_image(myExercise1.getVideo_image())
                                .channel_name(myExercise1.getChannel_name())
                        .build())
                .collect(Collectors.toList());



    }
}
