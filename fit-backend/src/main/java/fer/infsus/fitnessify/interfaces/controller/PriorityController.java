package fer.infsus.fitnessify.interfaces.controller;

import fer.infsus.fitnessify.domain.model.Priority;
import fer.infsus.fitnessify.infrastructure.service.PriorityService;
import fer.infsus.fitnessify.interfaces.dto.PriorityDto;
import fer.infsus.fitnessify.interfaces.mapper.PriorityMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriorityController {
    @NonNull
    private PriorityService priorityService;

    @GetMapping("/priority/{priority_id}")
    public ResponseEntity<PriorityDto> getPriority(@PathVariable("priority_id") Integer priorityId) {
        Priority priority = priorityService.getPriority(priorityId);
        return ResponseEntity.ok(PriorityMapper.toDto(priority));
    }

    @GetMapping("/priorities")
    public ResponseEntity<List<PriorityDto>> getPriorities() {
        List<Priority> priorities = priorityService.getPriorities();
        return ResponseEntity.ok(priorities.stream().map(PriorityMapper::toDto).toList());
    }
}
