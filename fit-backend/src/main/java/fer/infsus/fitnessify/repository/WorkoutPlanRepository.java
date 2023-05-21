package fer.infsus.fitnessify.repository;

import fer.infsus.fitnessify.model.WorkoutPlan;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;

import static fer.infsus.fitnessify.tables.Workoutplan.WORKOUTPLAN;

@RequiredArgsConstructor
public class WorkoutPlanRepository {
    @NonNull
    private DSLContext dslContext;

    public WorkoutPlan getWorkoutPlan(Integer workoutPlanId) {
        return dslContext.selectFrom(WORKOUTPLAN).where(WORKOUTPLAN.ID.eq(workoutPlanId)).fetchSingleInto(WorkoutPlan.class);
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return dslContext.selectFrom(WORKOUTPLAN).fetchInto(WorkoutPlan.class);
    }
}
