package fer.infsus.fitnessify.validation;

import fer.infsus.fitnessify.dto.ExerciseDto;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.model.Exercise;
import fer.infsus.fitnessify.model.Priority;
import fer.infsus.fitnessify.model.WorkoutExerciseData;
import fer.infsus.fitnessify.repository.ExerciseRepository;
import fer.infsus.fitnessify.repository.PriorityRepository;
import fer.infsus.fitnessify.validation.exception.InvalidExerciseException;
import fer.infsus.fitnessify.validation.exception.InvalidMuscleGroupException;
import fer.infsus.fitnessify.validation.exception.InvalidPriorityException;
import fer.infsus.fitnessify.validation.exception.InvalidRepetitionsException;
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

    public void validateWorkoutExerciseData(List<WorkoutExerciseData> workoutExerciseData) {
        if (workoutExerciseData != null) {
            List<Integer> storedExerciseIds = exerciseRepository.getExercises().stream().map(Exercise::getId).toList();
            List<Integer> storedPriorityIds = priorityRepository.getPriorities().stream().map(Priority::getId).toList();

            for (WorkoutExerciseData data : workoutExerciseData) {
                if (!storedExerciseIds.contains(data.getExerciseId())) {
                    throw new InvalidExerciseException(String.format("Invalid exercise: Exercise with id %d does not exist", data.getExerciseId()));
                }
                if (!storedPriorityIds.contains(data.getPriorityId())) {
                    throw new InvalidPriorityException(String.format("Invalid priority: Priority with id %d does not exist", data.getPriorityId()));
                }
                if (data.getRepetitions() < 0) {
                    throw new InvalidRepetitionsException("Invalid repetitions: Repetitions can not be less than 0");
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
