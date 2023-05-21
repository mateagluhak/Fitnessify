package fer.infsus.fitnessify.interfaces.mapper;

import fer.infsus.fitnessify.domain.model.Exercise;
import fer.infsus.fitnessify.enums.MuscleGroup;
import fer.infsus.fitnessify.interfaces.dto.ExerciseDto;

public class ExerciseMapper {
    public static ExerciseDto toDto(Exercise exercise) {
        return new ExerciseDto(exercise.getId(), exercise.getName(), exercise.getMuscleGroup().getLiteral(), exercise.getMaxWeight());
    }

    public static Exercise toModel(ExerciseDto exerciseDto) {
        return new Exercise(exerciseDto.id(), exerciseDto.name(), MuscleGroup.valueOf(exerciseDto.muscleGroup()), exerciseDto.maxWeight());
    }
}
