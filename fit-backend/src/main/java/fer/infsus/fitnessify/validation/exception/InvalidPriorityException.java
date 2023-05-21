package fer.infsus.fitnessify.validation.exception;

import lombok.NonNull;

public class InvalidPriorityException extends CustomErrorException {
    public InvalidPriorityException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
