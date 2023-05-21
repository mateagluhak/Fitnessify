package fer.infsus.fitnessify.domain.repository;

import fer.infsus.fitnessify.domain.model.Workout;
import fer.infsus.fitnessify.domain.model.WorkoutExerciseData;
import fer.infsus.fitnessify.interfaces.dto.WorkoutDto;
import fer.infsus.fitnessify.interfaces.dto.WorkoutExerciseDataDto;
import fer.infsus.fitnessify.interfaces.mapper.WorkoutExerciseDataMapper;
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
        Workout workout = dslContext.selectFrom(WORKOUT).where(WORKOUT.ID.eq(id)).fetchSingle().into(Workout.class);
        workout.setWorkoutExerciseData(dslContext.select(WORKOUTEXERCISE.EXERCISE_ID, WORKOUTEXERCISE.PRIORITY_ID, WORKOUTEXERCISE.REPETITIONS)
                .from(WORKOUTEXERCISE)
                .where(WORKOUTEXERCISE.WORKOUT_ID.eq(workout.getId()))
                .fetch()
                .into(WorkoutExerciseData.class));
        return workout;
    }

    public List<Workout> getWorkouts() {
        List<Workout> workouts = dslContext.selectFrom(WORKOUT).fetch().into(Workout.class);
        for (Workout workout : workouts) {
            workout.setWorkoutExerciseData(dslContext.select(WORKOUTEXERCISE.EXERCISE_ID, WORKOUTEXERCISE.PRIORITY_ID, WORKOUTEXERCISE.REPETITIONS)
                    .from(WORKOUTEXERCISE)
                    .where(WORKOUTEXERCISE.WORKOUT_ID.eq(workout.getId()))
                    .fetch()
                    .into(WorkoutExerciseData.class));
        }
        return workouts;
    }

    public Workout createWorkout(WorkoutDto workoutDto) {
        try {
            Workout workout = dslContext.insertInto(WORKOUT)
                    .columns(WORKOUT.NAME, WORKOUT.WORKOUT_PLAN_ID)
                    .values(workoutDto.name(), workoutDto.workoutPlanId())
                    .returning()
                    .fetchSingleInto(Workout.class);
            for (WorkoutExerciseDataDto data : workoutDto.workoutExerciseData()) {
                dslContext.insertInto(WORKOUTEXERCISE)
                        .columns(WORKOUTEXERCISE.WORKOUT_ID, WORKOUTEXERCISE.EXERCISE_ID, WORKOUTEXERCISE.PRIORITY_ID, WORKOUTEXERCISE.REPETITIONS)
                        .values(workout.getId(), data.exerciseId(), data.priorityId(), data.repetitions())
                        .execute();
            }
            workout.setWorkoutExerciseData(workoutDto.workoutExerciseData().stream().map(WorkoutExerciseDataMapper::toModel).toList());
            return workout;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Workout editWorkout(Integer workoutId, WorkoutDto workoutDto) {
        try {
            Workout workout = dslContext.update(WORKOUT)
                    .set(WORKOUT.NAME, workoutDto.name())
                    .set(WORKOUT.WORKOUT_PLAN_ID, workoutDto.workoutPlanId())
                    .where(WORKOUT.ID.eq(workoutId))
                    .returning()
                    .fetchSingleInto(Workout.class);
            dslContext.deleteFrom(WORKOUTEXERCISE).where(WORKOUTEXERCISE.WORKOUT_ID.eq(workoutId)).execute();
            for (WorkoutExerciseDataDto data : workoutDto.workoutExerciseData()) {
                dslContext.insertInto(WORKOUTEXERCISE)
                        .columns(WORKOUTEXERCISE.WORKOUT_ID, WORKOUTEXERCISE.EXERCISE_ID, WORKOUTEXERCISE.PRIORITY_ID, WORKOUTEXERCISE.REPETITIONS)
                        .values(workoutId, data.exerciseId(), data.priorityId(), data.repetitions())
                        .execute();
            }
            workout.setWorkoutExerciseData(workoutDto.workoutExerciseData().stream().map(WorkoutExerciseDataMapper::toModel).toList());
            return workout;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteWorkout(Integer workoutId) {
        return dslContext.deleteFrom(WORKOUT).where(WORKOUT.ID.eq(workoutId)).execute() > 0;
    }
}
