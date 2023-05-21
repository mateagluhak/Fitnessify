package fer.infsus.fitnessify.interfaces.controller;

import fer.infsus.fitnessify.domain.model.WorkoutPlan;
import fer.infsus.fitnessify.infrastructure.service.WorkoutPlanService;
import fer.infsus.fitnessify.interfaces.dto.WorkoutPlanDto;
import fer.infsus.fitnessify.interfaces.mapper.WorkoutPlanMapper;
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
    public ResponseEntity<WorkoutPlanDto> getWorkoutPlan(@PathVariable("workout_plan_id") Integer workoutPlanId) {
        WorkoutPlan workoutPlan = workoutPlanService.getWorkoutPlan(workoutPlanId);
        return ResponseEntity.ok(WorkoutPlanMapper.toDto(workoutPlan));
    }

    @GetMapping("/workout_plans")
    public ResponseEntity<List<WorkoutPlanDto>> getWorkoutPlans() {
        List<WorkoutPlan> workoutPlans = workoutPlanService.getWorkoutPlans();
        return ResponseEntity.ok(workoutPlans.stream().map(WorkoutPlanMapper::toDto).toList());
    }
}
