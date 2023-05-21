package fer.infsus.fitnessify.interfaces.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WorkoutExerciseDataDto(@NotNull(message = "Invalid exercise: Empty exercise") Integer exerciseId,
                                     @NotNull(message = "Invalid priority: Empty priority") Integer priorityId,
                                     @NotNull(message = "Invalid repetitions: Empty repetitions")
                                     @Min(value = 0, message = "Invalid repetitions: Can not be less than 0") Integer repetitions) {
}
