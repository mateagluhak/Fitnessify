package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.dto.ExerciseDto;
import fer.infsus.fitnessify.model.Exercise;
import fer.infsus.fitnessify.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Exercise> createExercise(@RequestBody @Valid ExerciseDto newExercise) {
        Exercise exercise = exerciseService.createExercise(newExercise);
        if (exercise != null) {
            return ResponseEntity.created(URI.create("/exercise/" + exercise.getId())).body(exercise);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/exercise/edit/{exercise_id}")
    public ResponseEntity<Exercise> editExercise(@PathVariable("exercise_id") Integer exerciseId, @RequestBody @Valid ExerciseDto updatedExercise) {
        Exercise exercise = exerciseService.editExercise(exerciseId, updatedExercise);
        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/exercise/{exercise_id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable("exercise_id") Integer exerciseId) {
        if (exerciseService.deleteExercise(exerciseId)) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
