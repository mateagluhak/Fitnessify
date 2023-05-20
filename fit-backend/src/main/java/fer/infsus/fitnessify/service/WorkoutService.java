package fer.infsus.fitnessify.service;

import fer.infsus.fitnessify.model.Workout;
import fer.infsus.fitnessify.repository.WorkoutRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    @NonNull
    private WorkoutRepository workoutRepository;

    public Workout getWorkout(Integer workoutId) {
        return workoutRepository.getWorkoutById(workoutId);
    }

    public List<Workout> getWorkouts() {
        return workoutRepository.getWorkouts();
    }
}
