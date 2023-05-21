package fer.infsus.fitnessify.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Workout {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Integer workoutPlanId;
    private List<WorkoutExerciseData> workoutExerciseData;
}
