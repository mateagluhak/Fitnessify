package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.model.Workout;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/workout")
public class WorkoutController {

    @GetMapping("/{workout_id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable("workout_id") Integer workoutId) {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createWorkout(Workout newWorkout) {
        return null;
    }

    @PutMapping("/edit/{workout_id}")
    public ResponseEntity<Void> editWorkout(Workout workout, @PathVariable("workout_id") Integer workoutId) {
        return null;
    }

    @DeleteMapping("/{workout_id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable("workout_id") Integer workoutId) {
        return null;
    }
}
