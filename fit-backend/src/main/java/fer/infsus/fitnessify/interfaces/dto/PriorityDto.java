package fer.infsus.fitnessify.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record PriorityDto(Integer id, @NotBlank(message = "Invalid name: Empty name") String name) {
    public PriorityDto(String name) {
        this(null, name);
    }
}
