package fer.infsus.fitnessify.validation.exception;

import lombok.NonNull;

public class InvalidExerciseException extends CustomErrorException {

    public InvalidExerciseException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
