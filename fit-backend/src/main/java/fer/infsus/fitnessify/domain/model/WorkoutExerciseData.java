package fer.infsus.fitnessify.domain.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WorkoutExerciseData {
    @NonNull
    private Integer exerciseId;
    @NonNull
    private Integer priorityId;
    @NonNull
    private Integer repetitions;
}
