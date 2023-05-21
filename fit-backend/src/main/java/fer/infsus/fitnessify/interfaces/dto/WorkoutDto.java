package fer.infsus.fitnessify.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WorkoutDto(Integer id,
                         @NotBlank(message = "Invalid Name: Empty name") String name,
                         @NotNull(message = "Invalid Workout Plan: Workout plan not selected") Integer workoutPlanId,
                         List<WorkoutExerciseDataDto> workoutExerciseData) {
    public WorkoutDto(String name, Integer workoutPlanId, List<WorkoutExerciseDataDto> workoutExerciseData) {
        this(null, name, workoutPlanId, workoutExerciseData);
    }
}
