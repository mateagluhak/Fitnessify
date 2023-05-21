package fer.infsus.fitnessify.infrastructure.validation;

import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.domain.model.Priority;
import fer.infsus.fitnessify.domain.repository.ExerciseRepository;
import fer.infsus.fitnessify.domain.repository.PriorityRepository;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.infrastructure.validation.exception.InvalidExerciseException;
import fer.infsus.fitnessify.infrastructure.validation.exception.InvalidMuscleGroupException;
import fer.infsus.fitnessify.infrastructure.validation.exception.InvalidPriorityException;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import fer.infsus.fitnessify.interfaces.dto.WorkoutExerciseDataDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Validator {
    @NonNull
    private ExerciseRepository exerciseRepository;
    @NonNull
    private PriorityRepository priorityRepository;

    private final List<String> muscleGroups = Arrays.stream(MuscleGroup.values()).map(MuscleGroup::getLiteral).toList();

    public void validateWorkoutExerciseData(List<WorkoutExerciseDataDto> workoutExerciseDataDtos) {
        if (workoutExerciseDataDtos != null) {
            List<Integer> storedExerciseIds = exerciseRepository.getExercises().stream().map(Exercise::getId).toList();
            List<Integer> storedPriorityIds = priorityRepository.getPriorities().stream().map(Priority::getId).toList();

            for (WorkoutExerciseDataDto data : workoutExerciseDataDtos) {
                if (!storedExerciseIds.contains(data.exerciseId())) {
                    throw new InvalidExerciseException(String.format("Invalid exercise: Exercise with id %d does not exist", data.exerciseId()));
                }
                if (!storedPriorityIds.contains(data.priorityId())) {
                    throw new InvalidPriorityException(String.format("Invalid priority: Priority with id %d does not exist", data.priorityId()));
                }
            }
        }
    }

    public ExerciseDto validateAndNormalizeMuscleGroup(ExerciseDto exerciseDto) {
        String normalizedMuscleGroup = exerciseDto.muscleGroup().toLowerCase().replace(" ", "_");
        if (!muscleGroups.contains(normalizedMuscleGroup)) {
            throw new InvalidMuscleGroupException(String.format("Invalid muscle group %s: Muscle groups are upper body, lower body and back", exerciseDto.muscleGroup()));
        } else {
            return new ExerciseDto(exerciseDto.name(), normalizedMuscleGroup, exerciseDto.maxWeight());
        }
    }
}
