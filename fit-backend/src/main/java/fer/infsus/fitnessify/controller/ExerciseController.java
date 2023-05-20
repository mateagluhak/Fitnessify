package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.model.Exercise;
import fer.infsus.fitnessify.service.ExerciseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseController {
    @NonNull
    private ExerciseService exerciseService;

    @GetMapping("/exercise/{exercise_id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable("exercise_id") Integer exerciseId) {
        return ResponseEntity.ok(exerciseService.getExercise(exerciseId));
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }

    @PostMapping("/exercise/create")
    public ResponseEntity<Void> createExercise(Exercise newExercise) {
        return null;
    }

    @PutMapping("/exercise/edit/{exercise_id}")
    public ResponseEntity<Void> editExercise(Exercise exercise, @PathVariable("exercise_id") Integer exerciseId) {
        return null;
    }

    @DeleteMapping("/exercise/{exercise_id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable("exercise_id") Integer exerciseId) {
        return null;
    }
}
