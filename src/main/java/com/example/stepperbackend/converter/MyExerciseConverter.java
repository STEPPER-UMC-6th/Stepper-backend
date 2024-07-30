package com.example.stepperbackend.converter;

import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.domain.enums.BodyPart;
import com.example.stepperbackend.web.dto.MyExerciseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MyExerciseConverter {
    public static MyExercise toAddExerciseEntity(MyExerciseDto.AddExerciseRequestDto request, Member member) {

        BodyPart bodyPart = null;

        switch(request.getBody_part()) {
            case "머리":
                bodyPart = BodyPart.머리;
                break;
            case "가슴":
                bodyPart = BodyPart.가슴;
                break;
            case "복부":
                bodyPart = BodyPart.복부;
                break;
            case "골반":
                bodyPart = BodyPart.골반;
                break;
            case "어깨팔":
                bodyPart = BodyPart.어깨팔;
                break;
            case "무릎다리":
                bodyPart = BodyPart.무릎다리;
                break;
            case "등":
                bodyPart = BodyPart.등;
                break;
            case "허리":
                bodyPart = BodyPart.허리;
                break;
            case "발":
                bodyPart = BodyPart.발;
                break;

        }

        return MyExercise.builder()
                .url(request.getUrl())
                .channel_name(request.getChannel_name())
                .video_image(request.getVideo_image())
                .video_title(request.getVideo_title())
                .body_part(bodyPart)
                .member(member)
                .build();

    }

    public static MyExerciseDto.AddExerciseResponseDTO toAddExerciseDTO(MyExercise myExercise) {
        return MyExerciseDto.AddExerciseResponseDTO.builder()
                .exerciseId(myExercise.getExercise_id())
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
