package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.dto.WorkoutDto;
import fer.infsus.fitnessify.model.Workout;
import fer.infsus.fitnessify.service.WorkoutService;
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
    public ResponseEntity<Workout> getWorkout(@PathVariable("workout_id") Integer workoutId) {
        return ResponseEntity.ok(workoutService.getWorkout(workoutId));
    }

    @GetMapping("/workouts")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return ResponseEntity.ok(workoutService.getWorkouts());
    }

    @PostMapping("/workout/create")
    public ResponseEntity<Workout> createWorkout(@RequestBody @Valid WorkoutDto newWorkout) {
        Workout workout = workoutService.createWorkout(newWorkout);
        if (workout != null) {
            return ResponseEntity.created(URI.create("/workout/" + workout.getId())).body(workout);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
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
