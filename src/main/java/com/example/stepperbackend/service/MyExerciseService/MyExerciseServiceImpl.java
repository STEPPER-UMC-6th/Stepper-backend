package com.example.stepperbackend.service.MyExerciseService;


import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.MyExerciseRepository;
import com.example.stepperbackend.web.dto.MyExercise.MyExerciseRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyExerciseServiceImpl implements MyExerciseService {
    private final MyExerciseRepository myExerciseRepository;
    private final MemberRepository memberRepository;

    public MyExercise addMyExercise(MyExerciseRequestDTO.AddExerciseDto request, String memberEmail) {
            Member member = memberRepository.findByEmail(memberEmail)
                    .orElseThrow(() -> new IllegalArgumentException("User not found" + memberEmail));

                MyExercise myExercise = MyExercise.builder()
                        .url(request.getUrl())
                        .channel_name(request.getChannel_name())
                        .video_image(request.getVideo_image())
                        .video_title(request.getVideo_title())
                        .body_part(request.getBody_part())
                        .member(member)
                        .build();

                return myExerciseRepository.save(myExercise);

    }

    public List<MyExercise> checkMyExercise(MyExerciseRequestDTO.CheckExerciseDto request, String memberEmail) {
        List<MyExercise> exercises = myExerciseRepository.findAll();

        return exercises.stream()
                .filter(myExercise -> myExercise.getBody_part().equals(request.getBody_part()) &&
                        myExercise.getMember().getEmail().equals(memberEmail))
                .toList();
    }
}
