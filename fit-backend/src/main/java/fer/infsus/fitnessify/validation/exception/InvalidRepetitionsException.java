package fer.infsus.fitnessify.validation.exception;

import lombok.NonNull;

public class InvalidRepetitionsException extends CustomErrorException {
    public InvalidRepetitionsException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
