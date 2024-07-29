package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.web.dto.MyExerciseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MyExerciseConverter {
    public static MyExerciseDto.AddExerciseResponseDTO toAddExerciseDTO(MyExercise myExercise) {
        return MyExerciseDto.AddExerciseResponseDTO.builder()
                .status("success")
                .message("나만의 운동 저장에 성공하였습니다.")
                .build();
    }

    public static List<MyExerciseDto.CheckExerciseResponseDTO> toCheckExerciseDTO(List<MyExercise> myExercise) {

        return myExercise.stream()
                .map(myExercise1 -> MyExerciseDto.CheckExerciseResponseDTO.builder()
                                .url(myExercise1.getUrl())
                                .video_title(myExercise1.getVideo_title())
                                .video_image(myExercise1.getVideo_image())
                                .channel_name(myExercise1.getChannel_name())
                        .build())
                .collect(Collectors.toList());



    }
}
