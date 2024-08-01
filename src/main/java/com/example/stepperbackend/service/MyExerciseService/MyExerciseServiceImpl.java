package com.example.stepperbackend.service.MyExerciseService;


import com.example.stepperbackend.apiPayload.code.status.ErrorStatus;
import com.example.stepperbackend.apiPayload.exception.handler.ExerciseHandler;
import com.example.stepperbackend.apiPayload.exception.handler.MemberHandler;
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
                    .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

                MyExercise myExercise = MyExerciseConverter.toAddExerciseEntity(request, member);
                myExerciseRepository.save(myExercise);

                return MyExerciseConverter.toAddExerciseDTO(myExercise);

    }


    public List<MyExercise> checkMyExercise(MyExerciseDto.CheckExerciseRequestDto request, String memberEmail) {
        List<MyExercise> exercises = myExerciseRepository.findAll();

                List<MyExercise> filteredList = exercises.stream()
                        .filter(myExercise -> myExercise.getBody_part().equals(request.getBody_part())&&
                                myExercise.getMember().getEmail().equals(memberEmail)).toList();

                if(filteredList.isEmpty()){
                    throw new ExerciseHandler(ErrorStatus.MY_EXERCISE_NOT_FOUND);
                }

        return filteredList;

    }
}
