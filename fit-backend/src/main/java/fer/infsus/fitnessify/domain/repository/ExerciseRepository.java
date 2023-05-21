package fer.infsus.fitnessify.domain.repository;

import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.math.BigDecimal;
import java.util.List;

import static fer.infsus.fitnessify.Tables.EXERCISE;

@RequiredArgsConstructor
public class ExerciseRepository {
    @NonNull
    private DSLContext dslContext;

    public Exercise getExerciseById(Integer id) {
        return dslContext.selectFrom(EXERCISE).where(EXERCISE.ID.eq(id)).fetchSingleInto(Exercise.class);
    }

    public List<Exercise> getExercises() {
        return dslContext.selectFrom(EXERCISE).fetchInto(Exercise.class);
    }

    public Exercise createExercise(ExerciseDto exerciseDto) {
        try {
            return dslContext.insertInto(EXERCISE).columns(EXERCISE.NAME, EXERCISE.MUSCLE_GROUP, EXERCISE.MAX_WEIGHT)
                    .values(exerciseDto.name(), MuscleGroup.valueOf(exerciseDto.muscleGroup()), BigDecimal.valueOf(exerciseDto.maxWeight()))
                    .returning()
                    .fetchSingleInto(Exercise.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exercise editExercise(Integer exerciseId, ExerciseDto exerciseDto) {
        try {
            return dslContext.update(EXERCISE)
                    .set(EXERCISE.NAME, exerciseDto.name())
                    .set(EXERCISE.MUSCLE_GROUP, MuscleGroup.valueOf(exerciseDto.muscleGroup()))
                    .set(EXERCISE.MAX_WEIGHT, BigDecimal.valueOf(exerciseDto.maxWeight()))
                    .where(EXERCISE.ID.eq(exerciseId))
                    .returning()
                    .fetchSingleInto(Exercise.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteExercise(Integer exerciseId) {
        return dslContext.deleteFrom(EXERCISE).where(EXERCISE.ID.eq(exerciseId)).execute() > 0;
    }
}
