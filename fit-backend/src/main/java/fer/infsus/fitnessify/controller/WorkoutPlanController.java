package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.model.WorkoutPlan;
import fer.infsus.fitnessify.service.WorkoutPlanService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutPlanController {
    @NonNull
    private WorkoutPlanService workoutPlanService;

    @GetMapping("/workout_plan/{workout_plan_id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlan(@PathVariable("workout_plan_id") Integer workoutPlanId) {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlan(workoutPlanId));
    }

    @GetMapping("/workout_plans")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlans() {
        return ResponseEntity.ok(workoutPlanService.getWorkoutPlans());
    }
}
