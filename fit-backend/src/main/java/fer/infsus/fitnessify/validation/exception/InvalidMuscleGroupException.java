package fer.infsus.fitnessify.validation.exception;

import lombok.NonNull;

public class InvalidMuscleGroupException extends CustomErrorException {
    public InvalidMuscleGroupException(@NonNull String errorMessage) {
        super(errorMessage);
    }
}
