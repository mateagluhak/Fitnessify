package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.model.Workout;
import fer.infsus.fitnessify.service.WorkoutService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutController {
    @NonNull
    private WorkoutService workoutService;

    @GetMapping("/workout/{workout_id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable("workout_id") Integer workoutId) {
        return ResponseEntity.ok(workoutService.getWorkout(workoutId));
    }

    @GetMapping("/workouts")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return ResponseEntity.ok(workoutService.getWorkouts());
    }

    @PostMapping("/workout/create")
    public ResponseEntity<Void> createWorkout(Workout newWorkout) {
        return null;
    }

    @PutMapping("/workout/edit/{workout_id}")
    public ResponseEntity<Void> editWorkout(Workout workout, @PathVariable("workout_id") Integer workoutId) {
        return null;
    }

    @DeleteMapping("/workout/{workout_id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workout_id") Integer workoutId) {
        return null;
    }
}
