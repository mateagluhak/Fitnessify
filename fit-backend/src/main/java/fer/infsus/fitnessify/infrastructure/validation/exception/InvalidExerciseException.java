package fer.infsus.fitnessify.infrastructure.validation.exception;

import lombok.NonNull;

public class InvalidExerciseException extends CustomErrorException {

    public InvalidExerciseException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
