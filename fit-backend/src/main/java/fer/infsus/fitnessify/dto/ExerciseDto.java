package fer.infsus.fitnessify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseDto(@NotBlank(message = "Invalid Name: Empty name") String name,
                          @NotBlank(message = "Invalid Muscle group: Empty muscle group") String muscleGroup,
                          @NotNull(message = "Invalid Max Weight: Empty max weight") Double maxWeight) {
}
