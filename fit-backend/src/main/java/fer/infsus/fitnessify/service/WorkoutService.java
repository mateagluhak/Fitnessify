package fer.infsus.fitnessify.service;

import fer.infsus.fitnessify.dto.WorkoutDto;
import fer.infsus.fitnessify.model.Workout;
import fer.infsus.fitnessify.repository.WorkoutRepository;
import fer.infsus.fitnessify.validation.Validator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    @NonNull
    private WorkoutRepository workoutRepository;
    @NonNull
    private Validator validator;

    public Workout getWorkout(Integer workoutId) {
        return workoutRepository.getWorkoutById(workoutId);
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.getWorkouts();
    }

    public Workout createWorkout(WorkoutDto workoutDto) {
        validator.validateWorkoutExerciseData(workoutDto.workoutExerciseData());
        return workoutRepository.createWorkout(workoutDto);
    }

    public Workout editWorkout(Integer workoutId, WorkoutDto workoutDto) {
        validator.validateWorkoutExerciseData(workoutDto.workoutExerciseData());
        return workoutRepository.editWorkout(workoutId, workoutDto);
    }

    public boolean deleteWorkout(Integer workoutId) {
        return workoutRepository.deleteWorkout(workoutId);
    }
}
