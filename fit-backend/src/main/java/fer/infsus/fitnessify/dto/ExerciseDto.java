package fer.infsus.fitnessify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseDto(@NotBlank(message = "Invalid Name: Empty name") String name,
                          @NotNull(message = "Invalid Max Weight: Empty max weight") Double maxWeight) {
}
