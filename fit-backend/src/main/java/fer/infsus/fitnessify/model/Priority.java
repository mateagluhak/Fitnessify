package fer.infsus.fitnessify.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Priority {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
}
