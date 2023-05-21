package fer.infsus.fitnessify.interfaces.mapper;

import fer.infsus.fitnessify.domain.model.Workout;
import fer.infsus.fitnessify.domain.model.WorkoutExerciseData;
import fer.infsus.fitnessify.interfaces.dto.WorkoutDto;
import fer.infsus.fitnessify.interfaces.dto.WorkoutExerciseDataDto;

import java.util.List;

public class WorkoutMapper {

    public static WorkoutDto toDto(Workout workout) {
        if (workout.getWorkoutExerciseData() != null) {
            List<WorkoutExerciseDataDto> workoutExerciseDataDtos = workout.getWorkoutExerciseData().stream().map(WorkoutExerciseDataMapper::toDto).toList();
            return new WorkoutDto(workout.getId(), workout.getName(), workout.getWorkoutPlanId(), workoutExerciseDataDtos);
        } else {
            return new WorkoutDto(workout.getId(), workout.getName(), workout.getWorkoutPlanId(), null);
        }
    }

    public static Workout toModel(WorkoutDto workoutDto) {
        if (workoutDto.workoutExerciseData() != null) {
            List<WorkoutExerciseData> workoutExerciseData = workoutDto.workoutExerciseData().stream().map(WorkoutExerciseDataMapper::toModel).toList();
            return new Workout(workoutDto.id(), workoutDto.name(), workoutDto.workoutPlanId(), workoutExerciseData);
        } else {
            return new Workout(workoutDto.id(), workoutDto.name(), workoutDto.workoutPlanId());
        }
    }
}
