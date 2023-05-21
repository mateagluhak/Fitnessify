package fer.infsus.fitnessify.infrastructure.validation.exception;

import lombok.NonNull;

public class InvalidMuscleGroupException extends CustomErrorException {
    public InvalidMuscleGroupException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
