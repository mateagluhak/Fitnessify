package fer.infsus.fitnessify.interfaces.mapper;

import fer.infsus.fitnessify.domain.model.WorkoutPlan;
import fer.infsus.fitnessify.interfaces.dto.WorkoutPlanDto;

public class WorkoutPlanMapper {

    public static WorkoutPlanDto toDto(WorkoutPlan workoutPlan) {
        return new WorkoutPlanDto(workoutPlan.getId(), workoutPlan.getName(), workoutPlan.getProfileId());
    }

    public static WorkoutPlan toModel(WorkoutPlanDto workoutPlanDto) {
        return new WorkoutPlan(workoutPlanDto.id(), workoutPlanDto.name(), workoutPlanDto.profileId());
    }
}
