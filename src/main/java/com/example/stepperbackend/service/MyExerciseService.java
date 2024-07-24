package com.example.stepperbackend.service;

import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.repository.MyExerciseRepository;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseRequestDTO;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MyExerciseService {
    private final MyExerciseRepository myExerciseRepository;
    @PersistenceContext

    public MyExercise addMyExercise(MyExerciseRequestDTO.AddExerciseDto request) {
        MyExercise myExercise = MyExercise.builder()
                .url(request.getUrl())
                .channel_name(request.getChannel_name())
                .video_image(request.getVideo_image())
                .video_title(request.getVideo_title())
                .body_part(request.getBody_part())
                .build();

        return myExerciseRepository.save(myExercise);
    }

    public MyExercise checkMyExercise(MyExerciseRequestDTO.CheckExerciseDto request) {


    }
}
