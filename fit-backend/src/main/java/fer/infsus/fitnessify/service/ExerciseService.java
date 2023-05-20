package fer.infsus.fitnessify.service;

import fer.infsus.fitnessify.dto.ExerciseDto;
import fer.infsus.fitnessify.model.Exercise;
import fer.infsus.fitnessify.repository.ExerciseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    @NonNull
    private ExerciseRepository exerciseRepository;

    public Exercise getExercise(Integer exerciseId) {
        return exerciseRepository.getExerciseById(exerciseId);
    }

    public List<Exercise> getExercises() {
        return exerciseRepository.getExercises();
    }

    public Exercise createExercise(ExerciseDto exerciseDto) {
        return exerciseRepository.createExercise(exerciseDto);
    }
}
