package fer.infsus.fitnessify.domain.model;

import fer.infsus.fitnessify.enums.MuscleGroup;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Exercise {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private MuscleGroup muscleGroup;
    @NonNull
    private Double maxWeight;
}
