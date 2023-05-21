package fer.infsus.fitnessify.controller;

import fer.infsus.fitnessify.model.Priority;
import fer.infsus.fitnessify.service.PriorityService;
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
    public ResponseEntity<Priority> getPriority(@PathVariable("priority_id") Integer priorityId) {
        return ResponseEntity.ok(priorityService.getPriority(priorityId));
    }

    @GetMapping("/priorities")
    public ResponseEntity<List<Priority>> getPriorities() {
        return ResponseEntity.ok(priorityService.getPriorities());
    }
}
