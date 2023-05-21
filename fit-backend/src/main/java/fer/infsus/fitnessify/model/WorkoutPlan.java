package fer.infsus.fitnessify.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WorkoutPlan {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Integer profile_id;
}
