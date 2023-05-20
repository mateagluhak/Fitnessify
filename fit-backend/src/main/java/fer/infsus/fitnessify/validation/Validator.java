package fer.infsus.fitnessify.validation;

import fer.infsus.fitnessify.model.Exercise;
import fer.infsus.fitnessify.repository.ExerciseRepository;
import fer.infsus.fitnessify.validation.exception.InvalidExerciseException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Validator {
    @NonNull
    private ExerciseRepository exerciseRepository;

    public void validateExerciseList(List<Integer> exerciseIds) {
        if (exerciseIds != null) {
            List<Integer> storedExerciseIds = exerciseRepository.getExercises().stream().map(Exercise::getId).toList();
            for (Integer exerciseId : exerciseIds) {
                if (!storedExerciseIds.contains(exerciseId)) {
                    throw new InvalidExerciseException(String.format("Invalid exercise: Exercise with id %d does not exist", exerciseId));
                }
            }
        }
    }
}
