package fer.infsus.fitnessify.infrastructure.validation.exception;

import lombok.NonNull;

public class InvalidPriorityException extends CustomErrorException {
    public InvalidPriorityException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
