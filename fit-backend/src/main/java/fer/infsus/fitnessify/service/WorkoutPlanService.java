package fer.infsus.fitnessify.service;

import fer.infsus.fitnessify.model.WorkoutPlan;
import fer.infsus.fitnessify.repository.WorkoutPlanRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {
    @NonNull
    private WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlan getWorkoutPlan(Integer workoutPlanId) {
        return workoutPlanRepository.getWorkoutPlan(workoutPlanId);
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlanRepository.getWorkoutPlans();
    }

}
