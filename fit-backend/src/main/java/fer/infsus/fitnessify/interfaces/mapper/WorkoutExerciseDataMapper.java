package fer.infsus.fitnessify.interfaces.mapper;

import fer.infsus.fitnessify.domain.model.WorkoutExerciseData;
import fer.infsus.fitnessify.interfaces.dto.WorkoutExerciseDataDto;

public class WorkoutExerciseDataMapper {
    public static WorkoutExerciseDataDto toDto(WorkoutExerciseData workoutExerciseData) {
        return new WorkoutExerciseDataDto(workoutExerciseData.getExerciseId(), workoutExerciseData.getPriorityId(), workoutExerciseData.getRepetitions());
    }

    public static WorkoutExerciseData toModel(WorkoutExerciseDataDto workoutExerciseDataDto) {
        return new WorkoutExerciseData(workoutExerciseDataDto.exerciseId(), workoutExerciseDataDto.priorityId(), workoutExerciseDataDto.repetitions());
    }
}
