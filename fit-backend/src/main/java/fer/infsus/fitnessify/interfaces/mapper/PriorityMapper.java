package fer.infsus.fitnessify.interfaces.mapper;

import fer.infsus.fitnessify.domain.model.Priority;
import fer.infsus.fitnessify.interfaces.dto.PriorityDto;

public class PriorityMapper {

    public static PriorityDto toDto(Priority priority) {
        return new PriorityDto(priority.getId(), priority.getName());
    }

    public static Priority toPriority(PriorityDto priorityDto) {
        return new Priority(priorityDto.id(), priorityDto.name());
    }
}
