package fer.infsus.fitnessify.interfaces.controller;

import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.infrastructure.service.ExerciseService;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;
import fer.infsus.fitnessify.interfaces.mapper.ExerciseMapper;
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
    public ResponseEntity<ExerciseDto> getExercise(@PathVariable("exercise_id") Integer exerciseId) {
        Exercise exercise = exerciseService.getExercise(exerciseId);
        return ResponseEntity.ok(ExerciseMapper.toDto(exercise));
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDto>> getExercises() {
        List<Exercise> exercises = exerciseService.getExercises();
        return ResponseEntity.ok(exercises.stream().map(ExerciseMapper::toDto).toList());
    }

    @PostMapping("/exercise/create")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody @Valid ExerciseDto newExercise) {
        Exercise exercise = exerciseService.createExercise(newExercise);
        if (exercise != null) {
            return ResponseEntity.created(URI.create("/exercise/" + exercise.getId())).body(ExerciseMapper.toDto(exercise));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/exercise/edit/{exercise_id}")
    public ResponseEntity<ExerciseDto> editExercise(@PathVariable("exercise_id") Integer exerciseId, @RequestBody @Valid ExerciseDto updatedExercise) {
        Exercise exercise = exerciseService.editExercise(exerciseId, updatedExercise);
        if (exercise != null) {
            return ResponseEntity.ok(ExerciseMapper.toDto(exercise));
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
