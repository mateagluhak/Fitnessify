package fer.infsus.fitnessify.infrastructure.service;

import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.domain.repository.ExerciseRepository;
import fer.infsus.fitnessify.infrastructure.validation.Validator;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    @NonNull
    private Validator validator;
    @NonNull
    private ExerciseRepository exerciseRepository;

    public Exercise getExercise(Integer exerciseId) {
        return exerciseRepository.getExerciseById(exerciseId);
    }

    public List<Exercise> getExercises() {
        return exerciseRepository.getExercises();
    }

    public Exercise createExercise(ExerciseDto exerciseDto) {
        return exerciseRepository.createExercise(validator.validateAndNormalizeMuscleGroup(exerciseDto));
    }

    public Exercise editExercise(Integer exerciseId, ExerciseDto exerciseDto) {
        return exerciseRepository.editExercise(exerciseId, validator.validateAndNormalizeMuscleGroup(exerciseDto));
    }

    public boolean deleteExercise(Integer exerciseId) {
        return exerciseRepository.deleteExercise(exerciseId);
    }
}
