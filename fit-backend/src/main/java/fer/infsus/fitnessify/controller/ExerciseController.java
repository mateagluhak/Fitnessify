package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.model.Exercise;
import fer.infsus.fitnessify.model.Workout;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/exercise")
public class ExerciseController {

    @GetMapping("/{exercise_id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable("exercise_id") Integer exerciseId) {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getExercises() {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createExercise(Exercise newExercise) {
        return null;
    }

    @PutMapping("/edit/{exercise_id}")
    public ResponseEntity<Void> editExercise(Exercise exercise, @PathVariable("exercise_id") Integer exerciseId) {
        return null;
    }

    @DeleteMapping("/{exercise_id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable("exercise_id") Integer exerciseId) {
        return null;
    }
}
