package fer.infsus.fitnessify.repository;

import fer.infsus.fitnessify.model.Workout;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;

import static fer.infsus.fitnessify.Tables.WORKOUT;
import static fer.infsus.fitnessify.Tables.WORKOUTEXERCISE;

@RequiredArgsConstructor
public class WorkoutRepository {
    @NonNull
    private DSLContext dslContext;

    public Workout getWorkoutById(Integer id) {
        return dslContext.selectFrom(WORKOUT).where(WORKOUT.ID.eq(id)).fetchSingle().into(Workout.class);
    }

    public List<Workout> getWorkouts() {
        List<Workout> workouts = dslContext.selectFrom(WORKOUT).fetch().into(Workout.class);
        for (Workout workout : workouts) {
            workout.setExerciseIds(dslContext.select(WORKOUTEXERCISE.EXERCISE_ID)
                    .from(WORKOUTEXERCISE)
                    .where(WORKOUTEXERCISE.WORKOUT_ID.eq(workout.getId()))
                    .fetch()
                    .into(Integer.class));
        }
        return workouts;
    }
}
