package fer.infsus.fitnessify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WorkoutDto(@NotBlank(message = "Invalid Name: Empty name") String name,
                         @NotNull(message = "Invalid Workout Plan: Workout plan not selected") Integer workoutPlanId,
                         List<Integer> exerciseIds) {
}
