package com.example.stepperbackend.service.MyExerciseService;


import com.example.stepperbackend.converter.MyExerciseConverter;
import com.example.stepperbackend.domain.Member;
import com.example.stepperbackend.domain.MyExercise;
import com.example.stepperbackend.repository.MemberRepository;
import com.example.stepperbackend.repository.MyExerciseRepository;
import com.example.stepperbackend.web.dto.MyExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyExerciseServiceImpl implements MyExerciseService {
    private final MyExerciseRepository myExerciseRepository;
    private final MemberRepository memberRepository;

    public MyExerciseDto.AddExerciseResponseDTO addMyExercise(MyExerciseDto.AddExerciseRequestDto request, String memberEmail) {
            Member member = memberRepository.findByEmail(memberEmail)
                    .orElseThrow(() -> new IllegalArgumentException("User not found" + memberEmail));

                MyExercise myExercise = MyExerciseConverter.toAddExerciseEntity(request, member);
                myExerciseRepository.save(myExercise);

                return MyExerciseConverter.toAddExerciseDTO(myExercise);

    }


    public List<MyExercise> checkMyExercise(MyExerciseDto.CheckExerciseRequestDto request, String memberEmail) {
        List<MyExercise> exercises = myExerciseRepository.findAll();

        return exercises.stream()
                .filter(myExercise -> myExercise.getBody_part().equals(request.getBody_part()) &&
                        myExercise.getMember().getEmail().equals(memberEmail))
                .toList();
    }
}
