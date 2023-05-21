package fer.infsus.fitnessify.interfaces.controller;

import fer.infsus.fitnessify.domain.model.Workout;
import fer.infsus.fitnessify.infrastructure.service.WorkoutService;
import fer.infsus.fitnessify.interfaces.dto.WorkoutDto;
import fer.infsus.fitnessify.interfaces.mapper.WorkoutMapper;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutController {
    @NonNull
    private WorkoutService workoutService;

    @GetMapping("/workout/{workout_id}")
    public ResponseEntity<WorkoutDto> getWorkout(@PathVariable("workout_id") Integer workoutId) {
        Workout workout = workoutService.getWorkout(workoutId);
        return ResponseEntity.ok(WorkoutMapper.toDto(workout));
    }

    @GetMapping("/workouts")
    public ResponseEntity<List<WorkoutDto>> getWorkouts() {
        List<Workout> workouts = workoutService.getWorkouts();
        return ResponseEntity.ok(workouts.stream().map(WorkoutMapper::toDto).toList());
    }

    @PostMapping("/workout/create")
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody @Valid WorkoutDto newWorkout) {
        Workout workout = workoutService.createWorkout(newWorkout);
        if (workout != null) {
            return ResponseEntity.created(URI.create("/workout/" + workout.getId())).body(WorkoutMapper.toDto(workout));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/workout/edit/{workout_id}")
    public ResponseEntity<WorkoutDto> editWorkout(@PathVariable("workout_id") Integer workoutId, @RequestBody @Valid WorkoutDto updatedWorkout) {
        Workout workout = workoutService.editWorkout(workoutId, updatedWorkout);
        if (workout != null) {
            return ResponseEntity.ok(WorkoutMapper.toDto(workout));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/workout/{workout_id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workout_id") Integer workoutId) {
        if (workoutService.deleteWorkout(workoutId)) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
