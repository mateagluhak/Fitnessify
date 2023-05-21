package fer.infsus.fitnessify.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WorkoutPlanDto(Integer id, @NotBlank(message = "Invalid name: Empty name") String name,
                             @NotNull(message = "Invalid profile: Empty profile") Integer profileId) {
    public WorkoutPlanDto(String name, Integer profileId) {
        this(null, name, profileId);
    }
}
