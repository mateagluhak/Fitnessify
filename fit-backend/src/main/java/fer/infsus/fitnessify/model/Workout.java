package fer.infsus.fitnessify.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Workout {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Integer workoutPlanId;
    private List<Integer> exerciseIds;
}
