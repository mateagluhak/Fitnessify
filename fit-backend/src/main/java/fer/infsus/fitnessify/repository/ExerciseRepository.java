package fer.infsus.fitnessify.repository;

import fer.infsus.fitnessify.model.Exercise;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;

import static fer.infsus.fitnessify.Tables.EXERCISE;

@RequiredArgsConstructor
public class ExerciseRepository {
    @NonNull
    private DSLContext dslContext;

    public Exercise getExerciseById(Integer id) {
        return dslContext.selectFrom(EXERCISE).where(EXERCISE.ID.eq(id)).fetchSingle().into(Exercise.class);
    }

    public List<Exercise> getExercises() {
        return dslContext.selectFrom(EXERCISE).fetch().into(Exercise.class);
    }
}
